package com.bcs.zsg.sales.web.bean;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.helper.AccountHelper;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.AESEncryption;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.EInvoiceUtils;
import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.db.bterp.vo.AmountCalcViewVO;
import com.bcs.zsg.maintenance.bo.IpayConfigBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.bo.InvoiceEmailPaymentBO;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.web.SystemProperty;

public class EmailPaymentBean extends AppBackingBean {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient InvoiceEmailPaymentBO invoiceEmailPaymentBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient IpayConfigBO ipayConfigBO;
	@Autowired
	private transient TourPackageBO tourPackageBO;
	
	private InvoiceEmailPaymentVO invoiceEmailPaymentVO;
	
	// Email Payment Process
	private String prodDesc;
	private String totalPaymentAmt;
	private String userName, userEmail, userContact;
	private String signature;
	private String merchantRemark;
	private boolean isValidURL;
	
	// Email Payment Result Process
	private boolean isSuccess;
	
	@Override
	public void resetForm() {
		isSuccess = false;
		isValidURL = false;
		invoiceEmailPaymentVO = new InvoiceEmailPaymentVO();
		prodDesc = "";
		userName = "";
		userEmail = "";
		userContact = "";
	}
	
	public void init() {
		resetForm();
		validateRequestURL();
	}
	
	public void initPaymentResult() {
		isSuccess = false;
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			
			logger.error("----- Ipay88 Message : " + httpServletRequest.getParameter("ErrDesc"));
			logger.error("----- Ipay88 Status : " + httpServletRequest.getParameter("Status") + " / " + httpServletRequest.getRemoteAddr());
			
			// 0 means unsuccessful, 1 means success
			if (StringUtils.equals(httpServletRequest.getParameter("Status") , "1")) {
				String tempRefNo = httpServletRequest.getParameter("RefNo");
				
				String idEmailPayment = tempRefNo.substring(tempRefNo.indexOf("_") + 1, tempRefNo.length());
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", idEmailPayment);
				logger.error("----- Ipay88 tempRefNo / idEmailPayment : " + tempRefNo + " / " + idEmailPayment);
				invoiceEmailPaymentVO = invoiceEmailPaymentBO.getInvoiceEmailPayment(params);
				
				if (invoiceEmailPaymentVO != null) {
					if (CommonConstant.EMAIL_PMNT_SUCCESS.equals(invoiceEmailPaymentVO.getPaymentStatus())) {
						isSuccess = true;
						
					} else if (CommonConstant.EMAIL_PMNT_PENDING.equals(invoiceEmailPaymentVO.getPaymentStatus())) {
						invoiceEmailPaymentVO.setPaymentStatus(CommonConstant.EMAIL_PMNT_SUCCESS);
						invoiceEmailPaymentVO.setTransId(httpServletRequest.getParameter("TransId"));
						invoiceEmailPaymentVO.setAuthCode(httpServletRequest.getParameter("AuthCode"));
						invoiceEmailPaymentVO.setCcName(httpServletRequest.getParameter("CCName"));
						invoiceEmailPaymentVO.setCcNo(httpServletRequest.getParameter("CCNo"));
						invoiceEmailPaymentVO.setsBankName(httpServletRequest.getParameter("S_bankname"));
						invoiceEmailPaymentVO.setsCountry(httpServletRequest.getParameter("S_country"));
						invoiceEmailPaymentBO.updateInvoiceEmailPaymentBySystem(invoiceEmailPaymentVO);
						isSuccess = true;
						
						/*InvoiceVO invoiceVO = invoiceBO.getInvoiceById(invoiceEmailPaymentVO.getIdInvoice());
						
						if (invoiceVO != null) {
							if (StringUtils.equals(invoiceVO.getStatusCd(), CommonConstant.STATUS_CD_VOID) || 
									StringUtils.equals(invoiceVO.getStatusCd(), CommonConstant.STATUS_CD_CANCELLED)) {
								return;
							}
							
							invoiceBO.getInvoiceDetails(invoiceVO);
							
							InvoicePaymentVO paymentVO = new InvoicePaymentVO();
							paymentVO.setInvId(invoiceEmailPaymentVO.getIdInvoice());
							paymentVO.setPmntDt(new Date());
							if (httpServletRequest.getParameter("PaymentId") != null 
									&& httpServletRequest.getParameter("PaymentId").equals("2")) {
								paymentVO.setPmntTypeCd("crd_card");
							} else {
								paymentVO.setPmntTypeCd("online_banking");
							}
							paymentVO.setRefNo(invoiceEmailPaymentVO.getTransId());
							paymentVO.setPmntFor("ONLINE PAYMENT");
							paymentVO.setAmount(invoiceEmailPaymentVO.getPaymentAmt().doubleValue());
							paymentVO.setPmntIssuedBy("SYSTEM");
							if (invoiceVO.getCustDetailsVO().getCustType() != null) {
								if (invoiceVO.getCustDetailsVO().getCustType().equals("P")) {
									paymentVO.setRecievedFr(invoiceVO.getCustDetailsVO().getContPersonName());
								} else if (invoiceVO.getCustDetailsVO().getCustType().equals("C")) {
									paymentVO.setRecievedFr(invoiceVO.getCustDetailsVO().getCompanyName());
								}
							}
							paymentVO.setCreatedBy("SYSTEM");
							paymentVO.setUpdatedBy("SYSTEM");
							
							IpayConfigVO ipayConfigVO = ipayConfigBO.getIpayConfigVO(invoiceEmailPaymentVO.getIdIpayConfig());
							
							Boolean financialClosed = getFinPeriodClosedStatus(invoiceVO);
							
							// If financial no close then add new invoice item with admin charges account
							if (!financialClosed) {
								if (ipayConfigVO != null) {
									InvoiceItemVO invoiceItemVO = new InvoiceItemVO();
									invoiceItemVO.setAcctId(ipayConfigVO.getIdAcct());
									invoiceItemVO.setDesc(ipayConfigVO.getAcctDesc());
									invoiceItemVO.setInvItemCd("");
									invoiceItemVO.setQty(1);
									invoiceItemVO.setUnitPrice(invoiceEmailPaymentVO.getAdminCharges().doubleValue());
									invoiceItemVO.setAmount(invoiceEmailPaymentVO.getAdminCharges().doubleValue());
									invoiceItemVO.setInvId(invoiceVO.getId());
									invoiceVO.getInvoiceItemList().add(invoiceItemVO);
								}
								
								invoiceVO.getInvoicePaymentList().add(paymentVO);
								
								calTtlAmt(invoiceVO);
								
								invoiceBO.updateInvoiceForEmailPayment(invoiceVO, invoiceVO.getTourDepId());
								
								// Check if financial close then create a new invoice to store admin charges
							} else {
								InvoiceVO tempVO = SerializationUtils.clone(invoiceVO);
								tempVO.setId(null);
								tempVO.setInvoiceDt(new Date());
								tempVO.setBookingId(null);
								tempVO.setCode(null);
								tempVO.setInvoiceDue(addInvoiceDueDate(new Date()));
								tempVO.setInvoicePaymentList(new ArrayList<InvoicePaymentVO>());
								tempVO.setInvoiceItemList(new ArrayList<InvoiceItemVO>());
								tempVO.setInvoiceRefVOList(new ArrayList<InvoiceReferenceVO>());
								tempVO.setEoInvList(new ArrayList<EOInvoiceVO>());
								tempVO.setInvoiceAttachmentVOList(new ArrayList<InvoiceAttachmentVO>());
								tempVO.setRemarks(null);
								tempVO.setInternalRemarks(null);
								tempVO.setTrvWarrantChecked(false);
								
								// Amount will set to admin charges amount
								paymentVO.setAmount(invoiceEmailPaymentVO.getAdminCharges().doubleValue());
								tempVO.getInvoicePaymentList().add(paymentVO);
								
								// Insert Invoice item related to admin charges
								if (ipayConfigVO != null) {
									InvoiceItemVO invoiceItemVO = new InvoiceItemVO();
									invoiceItemVO.setAcctId(ipayConfigVO.getIdAcct());
									invoiceItemVO.setDesc(ipayConfigVO.getAcctDesc());
									invoiceItemVO.setInvItemCd("");
									invoiceItemVO.setQty(1);
									invoiceItemVO.setUnitPrice(invoiceEmailPaymentVO.getAdminCharges().doubleValue());
									invoiceItemVO.setAmount(invoiceEmailPaymentVO.getAdminCharges().doubleValue());
									tempVO.getInvoiceItemList().add(invoiceItemVO);
								}
								
								// Insert Invoice Reference to email payment made
								InvoiceReferenceVO invoiceReferenceVO = new InvoiceReferenceVO();
								invoiceReferenceVO.setInvRefId(invoiceVO.getId());
								invoiceReferenceVO.setInvRefCode(invoiceVO.getCode());
								tempVO.setGenAUDINvoiceRefVOList(new GenAddUpdDelVO<InvoiceReferenceVO>());
								tempVO.getGenAUDINvoiceRefVOList().getAddList().add(invoiceReferenceVO);
								
								calTtlAmt(tempVO);
								
								invoiceBO.insertInvoiceByEmailPayment(tempVO, tempVO.getTourDepId());
								invoiceBO.updInvCd(tempVO);
							}
							isSuccess = true;
						}*/
					}
				}
			}
			logger.error("----- isSuccess : " + httpServletRequest.getRemoteAddr() + " / " + isSuccess);
		} catch (Throwable e) {
			isSuccess = false;
			errorResult(e);
		}
	}
	
	public boolean getFinPeriodClosedStatus(InvoiceVO invoiceVO) {
		boolean rtn = false;
		try {
			rtn = LookupItemUtils.getFinAndGSTPeriodClosedStatus(invoiceVO.getCompanyId(), invoiceVO.getInvoiceDt(), false);
		} catch (Throwable t) {
			errorResult(t);
		}
		
		return rtn;
	}
	
	public Date addInvoiceDueDate(Date dt) {
		Calendar invDt = Calendar.getInstance();
		if (dt == null) {
			invDt.setTime(new Date());
		} else {
			invDt.setTime(dt);
		}
		invDt.add(Calendar.DATE, Integer.parseInt(LookupItemUtils.getGlobalConfigValue(LookupItemConstant.GC_CATEGORY, LookupItemConstant.GC_PAID_EXP)));
		return invDt.getTime();
	}
	
	private void calTtlAmt(InvoiceVO invoiceVO) {
		try {
			//Manual update the item Amount here
			AmountCalcViewVO amtCalcViewVO = AccountHelper.computeAmountTotalBasedItem(new AmountCalcViewVO(), false, invoiceVO.getInvoiceItemList(),
																invoiceVO.getInvoicePaymentList(), false);
			
			invoiceVO.setTaxAmt(amtCalcViewVO.getTotalTax().doubleValue());
			invoiceVO.setTaxIncAmt(amtCalcViewVO.getTotalAmountIncludeTax().doubleValue());
			invoiceVO.setRoundingAmt(amtCalcViewVO.getTotalRounding().doubleValue());
			invoiceVO.setAmount(amtCalcViewVO.getTotalAmountAfterRounding().doubleValue());
			invoiceVO.setAmtPaid(amtCalcViewVO.getTotalAmountPaid().doubleValue());
			invoiceVO.setBalance(amtCalcViewVO.getTotalAmountAfterRounding()
									.subtract(amtCalcViewVO.getTotalAmountPaid()).doubleValue());
			
			BigDecimal taxAmt = new BigDecimal(String.valueOf(invoiceVO.getTaxAmt()));
			BigDecimal taxIncAmt = new BigDecimal(String.valueOf(invoiceVO.getTaxIncAmt()));
			BigDecimal taxExcAmt = taxIncAmt.subtract(taxAmt);
			invoiceVO.setTaxExcAmt(taxExcAmt.doubleValue());
			
			EInvoiceUtils.calcEInvItemList(invoiceVO);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void validateRequestURL() {
		isValidURL = false;
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String queryString = httpServletRequest.getQueryString() != null ? httpServletRequest.getQueryString() : "";
		
		try {
			if (StringUtils.isBlank(queryString))
				return;
				
			Map<String, String> paramURL = AESEncryption.decryptQueryToMap(queryString);
			
			if (paramURL.get("id") != null) {
				String idEmailPayment = paramURL.get("id");
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", idEmailPayment);
				invoiceEmailPaymentVO = invoiceEmailPaymentBO.getInvoiceEmailPayment(params);
				
				if (invoiceEmailPaymentVO != null) {
					if (new Date().after(invoiceEmailPaymentVO.getDtExpiry())) return;
					
					InvoiceVO invoiceVO = invoiceBO.getInvoiceById(invoiceEmailPaymentVO.getIdInvoice());
					
					if (invoiceVO == null) return;
					if (CommonConstant.STATUS_CD_VOID.equals(invoiceVO.getStatusCd())
							|| CommonConstant.STATUS_CD_CANCELLED.equals(invoiceVO.getStatusCd())) return;
					
					prodDesc = invoiceVO.getSubjLine();
					
					merchantRemark = "";
					
					TourDepartureVO departureVO = tourPackageBO.getTourDepById(invoiceVO.getTourDepId());
					if (departureVO != null)
						merchantRemark = "| DEPARTURE CODE : " + StringUtils.defaultIfBlank(departureVO.getCode(), "-");
					
					List<EmployeeViewVO> employeeViewVOList = invoiceBO.getFullEmployeeViewList(invoiceVO.getCompanyId());
					
					for(EmployeeViewVO vo : employeeViewVOList) {
						if(vo.getId().longValue() == invoiceVO.getSalerId().longValue()) {
							merchantRemark = merchantRemark + " DEPARTMENT : " + StringUtils.defaultIfBlank(LookupItemUtils.getLookupItemDesc("dept_type", vo.getDepartment()), "-");
							break;
						}
					}
					
					DecimalFormat myFormatter = new DecimalFormat("###,###.##");
					
					if (SystemProperty.IPAY_PAYMENT_TESTING.equals("true"))
						totalPaymentAmt = "1.00";
					else
						totalPaymentAmt = myFormatter.format(invoiceEmailPaymentVO.getTotalPaymentAmt().doubleValue());
					
					invoiceBO.getInvoiceDetails(invoiceVO);
					
					if (invoiceVO.getCustDetailsVO() != null) {
						if (StringUtils.equals(invoiceVO.getCustDetailsVO().getCustType(), "C")) {
							userName = invoiceVO.getCustDetailsVO().getCompanyName();
							
							for (CorContactVO corContactVO : invoiceVO.getCustDetailsVO().getCorContactList()) {
								if (StringUtils.equals(corContactVO.getContactType(), "office")) {
									userContact = corContactVO.getContactNo();
									break;
								} else {
									userContact = corContactVO.getContactNo();
								}
							}							
						} else {
							userName = invoiceVO.getCustDetailsVO().getContPersonName() + " " + (StringUtils.isNotBlank(invoiceVO.getCustDetailsVO().getNickName()) ? "(" + invoiceVO.getCustDetailsVO().getNickName() + ")" : "");
							
							for (PersonContactVO contactVO : invoiceVO.getCustDetailsVO().getContactList()) {
								if (StringUtils.equals(contactVO.getTypeCd(), "mobile")) {
									userContact = contactVO.getNumber();
									break;
								} else {
									userContact = contactVO.getNumber();
								}
							}	
						}
						
						userEmail = invoiceEmailPaymentVO.getEmail();
					}
					
					// Hash Signature Key
					String amount = totalPaymentAmt.replace(".", "").replace(",", "");
					
//					signature = encryptSignature(invoiceEmailPaymentVO.getMerchantKey() + invoiceEmailPaymentVO.getMerchantCode() + 
//							invoiceEmailPaymentVO.getPrefix() + invoiceEmailPaymentVO.getInvoiceNo() + "_" + invoiceEmailPaymentVO.getId().toString() +
//							amount + "MYR");
					signature = securityHmacSha512(invoiceEmailPaymentVO.getMerchantKey() + invoiceEmailPaymentVO.getMerchantCode() + 
							invoiceEmailPaymentVO.getPrefix() + invoiceEmailPaymentVO.getInvoiceNo() + "_" + invoiceEmailPaymentVO.getId().toString() +
							amount + "MYR", invoiceEmailPaymentVO.getMerchantKey());
					
					isValidURL = true;
				} else {
					invoiceEmailPaymentVO = new InvoiceEmailPaymentVO();
				}
			}
		} catch (Exception e) {
			isValidURL = false;
			errorResult(e);
		}
	}
	
	private String encryptSignature(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		
		byte byteData[] = md.digest();
		
		//convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
	
	private String securityHmacSha512(String toEncrypt, String key) throws Exception {
		// Convert key to byte array
		byte[] keyBytes = key.getBytes("UTF-8");

		// Create an instance of HMACSHA512 with the key
		Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA512");
		hmacSHA512.init(secretKeySpec);

		// Compute the hash
		byte[] hashBytes = hmacSHA512.doFinal(toEncrypt.getBytes("UTF-8"));

		// Convert the byte array to a hexadecimal string
		return byteArrayToHex(hashBytes);
	}
	 
	private String byteArrayToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public boolean getIsValidURL() {
		return isValidURL;
	}

	public void setIsValidURL(boolean isValidURL) {
		this.isValidURL = isValidURL;
	}

	public InvoiceEmailPaymentVO getInvoiceEmailPaymentVO() {
		return invoiceEmailPaymentVO;
	}

	public void setInvoiceEmailPaymentVO(InvoiceEmailPaymentVO invoiceEmailPaymentVO) {
		this.invoiceEmailPaymentVO = invoiceEmailPaymentVO;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTotalPaymentAmt() {
		return totalPaymentAmt;
	}

	public void setTotalPaymentAmt(String totalPaymentAmt) {
		this.totalPaymentAmt = totalPaymentAmt;
	}

	public String getMerchantRemark() {
		return merchantRemark;
	}

	public void setMerchantRemark(String merchantRemark) {
		this.merchantRemark = merchantRemark;
	}
	
	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
