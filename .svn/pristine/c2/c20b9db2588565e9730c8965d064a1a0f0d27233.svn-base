package com.bcs.zsg.crm.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.helper.AccountHelper;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.EInvoiceUtils;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.dao.CRMUploadSalesDAO;
import com.bcs.zsg.crm.vo.CRMUploadSalesPassengerVO;
import com.bcs.zsg.crm.vo.CRMUploadSalesVO;
import com.bcs.zsg.db.bterp.vo.AmountCalcViewVO;
import com.bcs.zsg.maintenance.dao.CorporateProfileDAO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.sales.dao.BookingDAO;
import com.bcs.zsg.sales.service.InvoiceService;
import com.bcs.zsg.sales.vo.BookingVO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.web.SystemProperty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CRMUploadSalesServiceImpl implements CRMUploadSalesService {
	
	@Autowired
	private CRMUploadSalesDAO crmUploadSalesDAO;
	
	@Autowired
	private CorporateProfileDAO corporateProfileDAO;
	
	@Autowired
	private BookingDAO bookingDAO;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Override
	public void processCRMUploadSales(Long idBooking) throws BusinessException, IOException {
		InvoiceVO invoiceVO = invoiceService.getInvoice(idBooking);
		invoiceService.getInvoiceDetails(invoiceVO);
		
		calTtlAmt(invoiceVO, false);
		
		processCRMUploadSales(invoiceVO);
	}
	
	@Override
	public void processCRMUploadSales(InvoiceVO invoiceVO) throws BusinessException, IOException {
		if (invoiceVO == null)
			return;
		
		if (CollectionUtils.isEmpty(invoiceVO.getInvoicePaymentList())) {
			return;
		}
		
		// Will post the invoice which contain booking and category type is tour only
		if (invoiceVO.getBookingId() == null || 
				!StringUtils.equals(invoiceVO.getCatCd(), CommonConstant.LOOKUP_ITM_INV_CAT_TOUR)) {
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String invoicePrefix = "AV";
		CompanyVO companyVO = corporateProfileDAO.getCompanyDetails(invoiceVO.getCompanyId());
		
		if (companyVO != null)
			invoicePrefix = companyVO.getCode();
		
		BookingVO bookingVO = bookingDAO.getBooking(invoiceVO.getBookingId());
		
		if (bookingVO == null)
			return;
		
		CRMUploadSalesVO crmUploadSalesVO = new CRMUploadSalesVO();
		crmUploadSalesVO.setInvoiceNo(invoicePrefix + invoiceVO.getCode());
		crmUploadSalesVO.setInvoiceDate(sdf.format(invoiceVO.getInvoiceDt()));
		crmUploadSalesVO.setIsCancellation(
				StringUtils.equals(CommonConstant.STATUS_CD_CANCELLED, invoiceVO.getStatusCd()) || 
				StringUtils.equals(CommonConstant.STATUS_CD_VOID, invoiceVO.getStatusCd()) ||
					StringUtils.equals(CommonConstant.STATUS_CD_CANCELLED, bookingVO.getStatusCode())||
					StringUtils.equals(CommonConstant.STATUS_CD_VOID, bookingVO.getStatusCode())
				? "true" : "false");
		crmUploadSalesVO.setCustomerId(String.valueOf(invoiceVO.getCustomerId()));
		crmUploadSalesVO.setInvoiceAmount(invoiceVO.getAmount());
		crmUploadSalesVO.setInvoicePayment(invoiceVO.getAmtPaid());
		crmUploadSalesVO.setInvoiceNetPayment(invoiceVO.getAmtPaid());
		crmUploadSalesVO.setDepartureDate(sdf.format(invoiceVO.getDepartureDt()));
		crmUploadSalesVO.setBookingType(LookupItemUtils.getLookupItemDesc("ordr_sorc", bookingVO.getOrderTypeCd()));
		crmUploadSalesVO.setPassengerList(new ArrayList<CRMUploadSalesPassengerVO>());
		
		// Currently will get the latest payment date
		crmUploadSalesVO.setPaymentDate(sdf.format(invoiceVO.getInvoicePaymentList().get(invoiceVO.getInvoicePaymentList().size() - 1).getPmntDt()));
		
		crmUploadSalesVO.setInvoiceCategory(invoiceVO.getCatCd());
		crmUploadSalesVO.setProductCode(invoiceVO.getDocTypeCd());
		crmUploadSalesVO.setTourCode(invoiceVO.getTourCd());
		crmUploadSalesVO.setTourName(invoiceVO.getSubjLine());
		
		invoiceVO = crmUploadSalesDAO.getInvoicePaxVO(invoiceVO);
		
		if (CollectionUtils.isNotEmpty(invoiceVO.getInvoicePaxList())) {
			for (InvoicePaxVO paxVO : invoiceVO.getInvoicePaxList()) {
				CRMUploadSalesPassengerVO crmUploadSalesPassengerVO = new CRMUploadSalesPassengerVO();
				crmUploadSalesPassengerVO.setCustomerId(String.valueOf(paxVO.getCustId()));
				crmUploadSalesPassengerVO.setSalutation(paxVO.getCustDetailsVO().getSalutation());
				crmUploadSalesPassengerVO.setSurName(paxVO.getCustDetailsVO().getLastName());
				crmUploadSalesPassengerVO.setGivenName(paxVO.getCustDetailsVO().getFirstName());
				crmUploadSalesPassengerVO.setNickName(paxVO.getCustDetailsVO().getNickName());
				crmUploadSalesPassengerVO.setEmail(StringUtils.isNotBlank(paxVO.getCustDetailsVO().getEmail()) ? paxVO.getCustDetailsVO().getEmail() : "");
				crmUploadSalesVO.getPassengerList().add(crmUploadSalesPassengerVO);
			}
		}
			
		postCRMSales(new Gson().toJson(crmUploadSalesVO), invoiceVO.getCode());
	}
	
	private void postCRMSales(String requestBodyJson, String invCode) {
		// Post Data Through API
		try {
			String url = SystemProperty.CRM_API_RECORD_SALES;
			URL obj = new URL(url);
			
			BufferedReader in = null;
			if (isHttps(url)) {
				HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
				con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				
				byte[] out = requestBodyJson.getBytes(StandardCharsets.UTF_8);
				int length = out.length;
				
				con.setFixedLengthStreamingMode(length);
				
				// Send post request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(out);
				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);
				
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
			} else {
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
				con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
					
				byte[] out = requestBodyJson.getBytes(StandardCharsets.UTF_8);
				int length = out.length;
				
				con.setFixedLengthStreamingMode(length);
				
				// Send post request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(out);
				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);
				
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			Map<String, String> paramsMap = new Gson().fromJson(response.toString(), new TypeToken<Map<String, String>>() {}.getType());
			
			if (StringUtils.equals(paramsMap.get("successful"), "true")) {
				System.out.println("\nCRM Post was successful. Invoice Code : " + invCode);
			} else {
				System.out.println("\nCRM Post Inv was un-successful. Invoice Code : " + invCode);
				System.out.println("Error Code : " + paramsMap.get("errorCode"));
				System.out.println("Error Message : " + paramsMap.get("errorMessage"));
			}
		} catch (IOException e) { }
	}
	
	public void calTtlAmt(InvoiceVO invVO, boolean updateItemAmount) {
		try {
			AmountCalcViewVO amtCalcViewVO = AccountHelper.computeAmountTotalBasedItem(new AmountCalcViewVO(), updateItemAmount, invVO.getInvoiceItemList(),
					invVO.getInvoicePaymentList(), false);
			
			invVO.setTaxAmt(amtCalcViewVO.getTotalTax().doubleValue());
			invVO.setTaxIncAmt(amtCalcViewVO.getTotalAmountIncludeTax().doubleValue());
			invVO.setRoundingAmt(amtCalcViewVO.getTotalRounding().doubleValue());
			invVO.setAmount(amtCalcViewVO.getTotalAmountAfterRounding().doubleValue());
			invVO.setAmtPaid(amtCalcViewVO.getTotalAmountPaid().doubleValue());
			invVO.setBalance(amtCalcViewVO.getTotalAmountAfterRounding()
									.subtract(amtCalcViewVO.getTotalAmountPaid()).doubleValue());
			
			BigDecimal taxAmt = new BigDecimal(String.valueOf(invVO.getTaxAmt()));
			BigDecimal taxIncAmt = new BigDecimal(String.valueOf(invVO.getTaxIncAmt()));
			BigDecimal taxExcAmt = taxIncAmt.subtract(taxAmt);
			invVO.setTaxExcAmt(taxExcAmt.doubleValue());
			
			EInvoiceUtils.calcEInvItemList(invVO);

		} catch (Throwable t) {
			
		}
	}
	
	private boolean isHttps(String url) {
        String patter = "^(https)://.*$";
        if (url.matches(patter)){
            return true;
        }else{
            return false;
        }
    }
}
