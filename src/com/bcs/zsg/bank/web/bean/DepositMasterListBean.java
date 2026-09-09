package com.bcs.zsg.bank.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.bank.bo.BankAcctBO;
import com.bcs.zsg.bank.bo.DepositBO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.AppConfigConstant;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.FileUploadUtils;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.InvPmntAuthVO;
import com.bcs.zsg.sales.vo.InvoicePaymentAttachmentVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;

import net.sf.jasperreports.engine.JasperPrint;

public class DepositMasterListBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient DepositBO depositBO;
	@Autowired
	private transient BankAcctBO bankAcctBO;
	@Autowired
	private transient SecurityBO securityBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	
	private List<InvoicePaymentVO> invPaymentList;
	private List<BankAcctViewVO> bankAcctViewList;
	private List<EmployeeViewVO> employeeList;
	
	private InvPmntAuthVO invPmntAuthVO;
	private InvoicePaymentVO invoicePaymentVO;
	private InvoicePaymentAttachmentVO invoicePaymentAttachmentVO;
	private FileUploadUtils fileUploadUtils;
	
	private final String destinationPaymentAttachmentPath = LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, MaintConstant.GLOBAL_CD_PATH_INV_PMNT_ATTACH);
	
	private LazyInvoicePaymentDataModel lazyInvPmntDataModel;

	@Override
	public void resetForm() {
		invoicePaymentVO = new InvoicePaymentVO();
		resetFileUploadForm();
	}
	
	public void resetFileUploadForm() {
		fileUploadUtils = new FileUploadUtils(AppConfigConstant.uploadPath);
	}
	
	public void init() {
		try {
			resetForm();
			initSearchParam();
			searchParamVO.setObj2(0);
			
			bankAcctViewList = bankAcctBO.getBankAcctList(getSessionInfoBean().getCompanyVO().getId());
			employeeList = invoiceBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());
			
			List<UserRoleViewVO> userRoleList = securityBO.getUserRoleListByUser(this.getSessionInfoBean().getUserVO().getUuid());
			invPmntAuthVO = new InvPmntAuthVO();
			if (CollectionUtils.isNotEmpty(userRoleList)) {
				for (UserRoleViewVO userRoleVO : userRoleList) {
					if (userRoleVO.getRoleCode().equals(SalesConstant.BANK_DEPOSIT_DEL)) {
						invPmntAuthVO.setPmntDel(true);
					} else if (userRoleVO.getRoleCode().equals(SalesConstant.INVOICE_PMNT_ROLE_UPD)) {
						invPmntAuthVO.setPmntUpd(true);
					} else if (userRoleVO.getRoleCode().equalsIgnoreCase(SalesConstant.ACCT_MANAGER_ROLE)) {
						invPmntAuthVO.setAccountManager(true);
					}
				}
			}
			lazyInvPmntDataModel = new LazyInvoicePaymentDataModel();
			initPrefixVal();
			
		} catch (Throwable t) {
			errorResult(t);
		}
		loadInvoicePaymentList();
	}
	
	public void loadInvoicePaymentList() {
		try {
			invPaymentList = depositBO.getInvoicePaymentListNoCashbook(getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
			lazyInvPmntDataModel.setInvPmntList(invPaymentList);
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void hideUnhideInvoicePayment(int hideStatus) {
		try {
			if (CollectionUtils.isNotEmpty(invPaymentList)) {
				StringBuilder paymentIDs = new StringBuilder();
				
				for (InvoicePaymentVO vo : invPaymentList) {
					if (vo.isChecked()) paymentIDs .append(vo.getId()).append(",");
				}
				
				if (paymentIDs.length() == 0) throw new BusinessException("ERR_NO_RECORD_SELECTED");
				depositBO.updateInvoicePaymentHideStatus(paymentIDs.substring(0, paymentIDs.length() - 1), hideStatus);
				loadInvoicePaymentList();
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void postToDeposit() {
		try {
			if (CollectionUtils.isNotEmpty(invPaymentList)) {
				List<InvoicePaymentVO> paymentList = new ArrayList<InvoicePaymentVO>();
				
				for (InvoicePaymentVO vo : invPaymentList) {
					if (vo.isChecked()) paymentList.add(vo);
				}
				
				if (paymentList.isEmpty()) throw new BusinessException("ERR_NO_RECORD_SELECTED");
				depositBO.postToDeposit(paymentList, bankAcctViewList, getSessionInfoBean().getCompanyVO(), invPmntAuthVO.isAccountManager());
				loadInvoicePaymentList();
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On invoice payment selected
	 * @param event
	 */
	public void onInvoicePaymentSelected(InvoicePaymentVO vo) {
		try {
			invoicePaymentVO = invoiceBO.getInvPaymentById(vo.getId());
			invoicePaymentVO.setPmntIssuedBy(getUserName(vo.getIssuerId()));
			
			invoicePaymentVO.setInvoicePaymentAttachmentList(invoiceBO.getInvPmntAttachmentList(vo.getId()));

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void saveInvoicePayment() {
		try {
			invoiceBO.saveInvoicePaymentVO(invoicePaymentVO);
			loadInvoicePaymentList();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void selectAll() {
		try {
			selectAllCheckbox();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void clearAll() {
		try {
			if (CollectionUtils.isNotEmpty(invPaymentList)) {
				for (InvoicePaymentVO vo : invPaymentList) {
					vo.setChecked(false);
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Get staff name
	 */
	private String getUserName(Long id) {
		try {
			if (id != null) {
				for (EmployeeViewVO vo : employeeList) {
					if (vo.getId().longValue() == id.longValue()) {
						return vo.getUserVO().getName();
					}
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
		return "";
	}
	
	private void selectAllCheckbox() throws BusinessException {
		if (CollectionUtils.isNotEmpty(invPaymentList)) {
			for (InvoicePaymentVO vo : invPaymentList) {
				vo.setChecked(true);
			}
		}
	}
	
	public void handlePaymentFileUpload(FileUploadEvent event) {
		try {
			// rename the valid file name
			String fileName = event.getFile().getFileName();
			fileName = fileName.replace(" ", "_");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			fileName = sdf.format(new Date()) + "_" + fileName;
			
			InvoicePaymentAttachmentVO attachVO = new InvoicePaymentAttachmentVO();
			
			attachVO.setTypeCd("attachment");
			attachVO.setScannedPath(fileName);
			attachVO.setFilePath(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(fileName));
			attachVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			
			if (invoicePaymentVO.getId() != null && event.getFile() != null) {
				fileUploadUtils.uploadSingleFile(event.getFile(), destinationPaymentAttachmentPath, attachVO.getFilePath(), null, null, false);

				attachVO.setIdInvPmnt(invoicePaymentVO.getId());
				invoiceBO.addDeleteInvoicePaymentAttachment(attachVO, false);
				invoicePaymentVO.getInvoicePaymentAttachmentList().add(attachVO);
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ", event.getFile().getFileName() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! ", event.getFile().getFileName() + " is not upload.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void deletePmntAttachment() {
		try {
			fileUploadUtils.deleteFile(destinationPaymentAttachmentPath, invoicePaymentAttachmentVO.getFilePath());
			invoiceBO.addDeleteInvoicePaymentAttachment(invoicePaymentAttachmentVO, true);
			invoicePaymentVO.getInvoicePaymentAttachmentList().remove(invoicePaymentAttachmentVO);
			successResult();
			
		} catch (Exception e) {
			errorResult(e);
		}
	}
	
	public void printReceiptReport(String xlsOrPDf) {
		try {
			List<InvoicePaymentVO> reportList = new ArrayList<InvoicePaymentVO>();
			if (CollectionUtils.isEmpty(filteredObjList)) {
				cloneInvPaymentReceiptListForReport(reportList, invPaymentList);
			} else {
				cloneInvPaymentReceiptListForReport(reportList, (List<InvoicePaymentVO>)(List<?>)filteredObjList);
			}
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getFromDate());
			map.put("toDate", (Date) searchParamVO.getToDate());
			map.put("psPrefixVal", psPrefixVal);
			map.put("invPrefixVal", invPrefixVal);
			map.put("invPaymentDepositList", reportList);
			
			String jasperFileName = CommonConstant.JAS_RPT_RECEIPT;
			String reportName = CommonConstant.PDF_RPT_RECEIPT;
			
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
		
	}
	
	/**
	 * Prepare Report Title
	 */
	public HashMap<String, Object> reportTitle() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			CompanyVO companyVO = new CompanyVO();
			companyVO = corporateProfileBO.getCompanyDetails(getSessionInfoBean().getCompanyVO().getId());
			
			StringBuilder address = new StringBuilder();
			map.put("companyName", companyVO.getName());
			map.put("slogan", companyVO.getSlogan());
			
			if (!companyVO.getCompanyAddressVO().getAddress1().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress1()).append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress2().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress2()).append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress3().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress3()).append(" ");
			if (!companyVO.getCompanyAddressVO().getCity().equals("")) address.append(companyVO.getCompanyAddressVO().getCity()).append(" ");
			if (!companyVO.getCompanyAddressVO().getState().equals("")) address.append(companyVO.getCompanyAddressVO().getState()).append(" ");
			if (!companyVO.getCompanyAddressVO().getPostcode().equals("")) address.append(companyVO.getCompanyAddressVO().getPostcode()).append(" ");
			String companyName = regionBO.getCountryById(companyVO.getCompanyAddressVO().getCountryid()).getCountry();
			if (!companyName.equals("")) address.append(companyName);
			map.put("address", address);
	
			String contact = "";
			for (CompanyContactVO vo : companyVO.getCompanyContactList()) {
				contact += LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE, vo.getTypecodecontact()) + ": " + vo.getNumber() + "  ";
			}
			if (!contact.equals("")) contact = "Tel: " + contact;
			map.put("contact", contact);
			
		} catch (Throwable t) {
			errorResult(t);
		}
		return map;
	}
	
	private void cloneInvPaymentReceiptListForReport(List<InvoicePaymentVO> reportList, List<InvoicePaymentVO> listInvPaymentVO) {
		for(Object tmpInvPaymentVO: listInvPaymentVO) {
			InvoicePaymentVO invPaymentVOCloned = (InvoicePaymentVO) ((InvoicePaymentVO) tmpInvPaymentVO).clone();
			reportList.add(invPaymentVOCloned);
		}
	}

	/**
	 * @return the invPaymentList
	 */
	public List<InvoicePaymentVO> getInvPaymentList() {
		return invPaymentList;
	}

	/**
	 * @return the bankAcctViewList
	 */
	public List<BankAcctViewVO> getBankAcctViewList() {
		return bankAcctViewList;
	}

	/**
	 * @return the invPmntAuthVO
	 */
	public InvPmntAuthVO getInvPmntAuthVO() {
		return invPmntAuthVO;
	}

	/**
	 * @return the invoicePaymentVO
	 */
	public InvoicePaymentVO getInvoicePaymentVO() {
		return invoicePaymentVO;
	}

	/**
	 * @param invoicePaymentVO the invoicePaymentVO to set
	 */
	public void setInvoicePaymentVO(InvoicePaymentVO invoicePaymentVO) {
		this.invoicePaymentVO = invoicePaymentVO;
	}
	
	/**
	 * @return the lazyInvPmntDataModel
	 */
	public LazyInvoicePaymentDataModel getLazyInvPmntDataModel() {
		return lazyInvPmntDataModel;
	}
	
	public InvoicePaymentAttachmentVO getInvoicePaymentAttachmentVO() {
		return invoicePaymentAttachmentVO;
	}

	public void setInvoicePaymentAttachmentVO(InvoicePaymentAttachmentVO invoicePaymentAttachmentVO) {
		this.invoicePaymentAttachmentVO = invoicePaymentAttachmentVO;
	}
	
	public String getDestinationPaymentAttachmentPath() {
		return destinationPaymentAttachmentPath;
	}

	class LazyInvoicePaymentDataModel extends LazyDataModel<InvoicePaymentVO> implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private List<InvoicePaymentVO> invPmntList;
		
		@Override
		public List<InvoicePaymentVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			List<InvoicePaymentVO> list = new ArrayList<InvoicePaymentVO>();
			int rowCount = 0;

			if (CollectionUtils.isNotEmpty(invPmntList)) {
				// list size 700
				// page 1 = 0 ~ 499
				// page 2 = 500 ~ 699
				// index start 
				
				String filterInvId = filters.get("invId");
				String filterInvNo = filters.get("invoiceNo");
				String filterRecievedFr = filters.get("recievedFr");
				String filterTourCode = filters.get("tourCode");
				String filterPmntTypeName = filters.get("pmntTypeName");
				boolean isInvId = true, isInvNo = true, isRecievedFr = true, isTourCode = true, isPmntTypeName = true;
				
				for (int i = 0; i < invPmntList.size(); i++) {
					try {
						InvoicePaymentVO vo = invPmntList.get(i);
						
						if (!filters.isEmpty()) {
							isInvId = true;
							isInvNo = true;
							isRecievedFr = true;
							isTourCode = true;
							isPmntTypeName = true;
							
							if (filterInvId != null && !vo.getInvId().toString().contains(filterInvId)) isInvId = false;
//							if (filterInvNo != null && !vo.getInvoiceNo().toString().contains(filterInvNo) && !vo.getPsNo().toString().contains(filterInvNo)) isInvNo = false; // why is start with?
							if (filterRecievedFr != null && !vo.getRecievedFr().contains(filterRecievedFr)) isRecievedFr = false;
							if (filterTourCode != null && !vo.getTourCode().contains(filterTourCode)) isTourCode = false;
							if (filterPmntTypeName != null && !vo.getPmntTypeName().contains(filterPmntTypeName)) isPmntTypeName = false;
							
							if (filterInvNo != null) {
								if (vo.getInvoiceDocTypeCd().equals(SalesConstant.DOC_TYPE_CD_PAX_STMT)) {
									String docNo = psPrefixVal + vo.getPsNo();
									docNo = docNo.toLowerCase();
									isInvNo = docNo.contains(filterInvNo.replaceAll("\\s", "").toLowerCase());
								} else {
//									isInvNo = vo.getInvoiceNo().toString().contains(filterInvNo);
									String docNo = invPrefixVal + vo.getInvoiceNo();
									docNo = docNo.toLowerCase();
									isInvNo = docNo.contains(filterInvNo.replaceAll("\\s", "").toLowerCase());
								}
							}
							
							if (isInvId && isInvNo && isRecievedFr && isTourCode && isPmntTypeName) {
								if (rowCount >= first && list.size() < pageSize) list.add(vo);
								++rowCount;
							}
						} else {
							if (i >= first && list.size() < pageSize) list.add(vo);
							++rowCount;
						}
					} catch (Exception e) {
						//e.printStackTrace();
						break;
					}
				}
			}
			setRowCount(rowCount);
			return list;
		}

		public List<InvoicePaymentVO> getInvPmntList() {
			return invPmntList;
		}

		public void setInvPmntList(List<InvoicePaymentVO> invPmntList) {
			this.invPmntList = invPmntList;
		}
		
	}
}
