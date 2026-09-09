package com.bcs.zsg.maintenance.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.maintenance.dao.AppSettingDAO;
import com.bcs.zsg.maintenance.dao.POSUploadSalesDAO;
import com.bcs.zsg.maintenance.dao.POSUploadSalesTransDAO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.helper.ConstantPOSUpload;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.maintenance.vo.POSUploadSalesTransVO;
import com.bcs.zsg.maintenance.vo.POSUploadSalesVO;
import com.bcs.zsg.sales.dao.InvoiceDAO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.web.SystemProperty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class POSUploadSalesServiceImpl implements POSUploadSalesService, Job {
	
	@Autowired
	private POSUploadSalesDAO posUploadSalesDAO;
	
	@Autowired
	private POSUploadSalesTransDAO posUploadSalesTransDAO;
	
	@Autowired
	private InvoiceDAO invoiceDAO;
	
	@Autowired
	private AppSettingDAO appSettingDAO;
	
	/********************
	 * POS Upload Sales *
	 ********************/
	@Override
	public int getPOSUploadSalesListCount(Map<String, Object> params) throws BusinessException {
		return posUploadSalesDAO.getPOSUploadSalesListCount(params);
	}
	
	@Override
	public List<POSUploadSalesVO> getPOSUploadSalesList(Map<String, Object> params) throws BusinessException {
		return posUploadSalesDAO.getPOSUploadSalesList(params);
	}
	
	@Override
	public POSUploadSalesVO getPOSUploadSales(Map<String, Object> params) throws BusinessException {
		return posUploadSalesDAO.getPOSUploadSales(params);
	}
	
	/***************************
	 * POS Upload Sales Trans *
	 ***************************/
	@Override
	public int getPOSUploadSalesTransListCount(Map<String, Object> params) throws BusinessException {
		return posUploadSalesTransDAO.getPOSUploadSalesTransListCount(params);
	}
	
	@Override
	public List<POSUploadSalesTransVO> getPOSUploadSalesTransList(Map<String, Object> params) throws BusinessException {
		return posUploadSalesTransDAO.getPOSUploadSalesTransList(params);
	}
	
	@Override
	public POSUploadSalesTransVO getPOSUploadSalesTransVO(Map<String, Object> params) throws BusinessException {
		return posUploadSalesTransDAO.getPOSUploadSalesTransVO(params);
	}
	
	/**********************
	 * Insert Posted Data *
	 **********************/
	@Override
	public void insertPOSUploadSalesForScheduler(POSUploadSalesVO vo) throws BusinessException {
		if (posUploadSalesDAO == null)
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		// Insert using query
		posUploadSalesDAO.insertPOSUploadSales(vo);
		
		List<POSUploadSalesTransVO> tempList = vo.getPosUploadSalesTransVOList();
		
		Map<String, Object> params = new HashMap<>();
		params.put("uploadDate", vo.getUploadDate());
		vo = getPOSUploadSales(params);
		vo.setPosUploadSalesTransVOList(tempList);
		
		if (CollectionUtils.isNotEmpty(vo.getPosUploadSalesTransVOList()) && StringUtils.equals(vo.getUploadStatus(), ConstantPOSUpload.UPLOAD_STATUS_SUCCESS)) {
			for (POSUploadSalesTransVO transVO : vo.getPosUploadSalesTransVOList()) {
				transVO.setIdPOSUploadSales(vo.getId());
				posUploadSalesTransDAO.insertPOSUploadSalesTrans(transVO);
			}
		}
	}
	
	@Override
	public void insertPOSUploadSales(POSUploadSalesVO vo) throws BusinessException {
		if (vo != null) {
			vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
			posUploadSalesDAO.insert(vo);
			
			if (CollectionUtils.isNotEmpty(vo.getPosUploadSalesTransVOList()) && StringUtils.equals(vo.getUploadStatus(), ConstantPOSUpload.UPLOAD_STATUS_SUCCESS)) {
				for (POSUploadSalesTransVO transVO : vo.getPosUploadSalesTransVOList()) {
					transVO.setIdPOSUploadSales(vo.getId());
					transVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					posUploadSalesTransDAO.insert(transVO);
				}
			}
		}
	}
	
	@Override
	public void updatePOSUploadSales(POSUploadSalesVO vo) throws BusinessException {
		if (vo != null) {
			posUploadSalesDAO.update(vo);
			
			if (CollectionUtils.isNotEmpty(vo.getPosUploadSalesTransVOList()) && StringUtils.equals(vo.getUploadStatus(), ConstantPOSUpload.UPLOAD_STATUS_SUCCESS)) {
				for (POSUploadSalesTransVO transVO : vo.getPosUploadSalesTransVOList()) {
					transVO.setIdPOSUploadSales(vo.getId());
					transVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					posUploadSalesTransDAO.insert(transVO);
				}
			}
		}
	}
	
	/***************
	 * API Calling *
	 ***************/
	@Override
	public boolean sendPut(String requestBodyJson) throws IOException {
		String url = SystemProperty.POS_UPLOAD_API_SEND_RECEIPTS;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
		TrackingLogUtils.printLogs("CYY requestBodyJson: " + requestBodyJson);

		//add request header
		con.setRequestMethod("PUT");
		con.setRequestProperty("Host", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_HOST);
		con.setRequestProperty("Authorization", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_AUTHORIZATION);
		con.setRequestProperty("Content-Type", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_CONTENT_TYPE);
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
		System.out.println("\nSending 'PUT' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		//System.out.println(response.toString());
		
		return StringUtils.equals(response.toString(), "true");
	}
	
	@Override
	public List<POSUploadSalesTransVO> getPostedData(Date dateFrom, Date dateTo) throws IOException {
		String url = SystemProperty.POS_UPLOAD_API_GET_RECEIPTS;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		url = url.concat(sdf.format(dateFrom)).concat("/").concat(sdf.format(dateTo));
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("Host", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_HOST);
		con.setRequestProperty("Authorization", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_AUTHORIZATION);
		con.setRequestProperty("Content-Type", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_CONTENT_TYPE);
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
		
		List<POSUploadSalesTransVO> voList = new Gson().fromJson(response.toString(), new TypeToken<List<POSUploadSalesTransVO>>(){}.getType());
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (POSUploadSalesTransVO vo : voList) {
				vo.setInvoiceDate(sdf2.parse(vo.getInvoiceDateStr()));
			}
		} catch (ParseException e) {}
		
		return voList;
	}
	
	@Override
	public boolean clearTestData() throws IOException, BusinessException {
		String url = SystemProperty.POS_UPLOAD_API_CLEAR_TEST_DATA;
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("Host", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_HOST);
		con.setRequestProperty("Authorization", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_AUTHORIZATION);
		con.setRequestProperty("Content-Type", SystemProperty.POS_UPLOAD_API_REQUEST_HEADER_CONTENT_TYPE);
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
		
		// Delete Test data in DB
		posUploadSalesDAO.deletePOSUploadSalesTestData();
		posUploadSalesTransDAO.deletePOSUploadSalesTransTestData();
		
		return StringUtils.equals(response.toString(), "true");
	}
	
	@Override
	public  boolean checkSelectedDateSubmitted(Date uploadDate) throws BusinessException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Object> params = new HashMap<>();
		params.put("uploadDate", sdf.format(uploadDate));
		params.put("uploadStatus", ConstantPOSUpload.UPLOAD_STATUS_SUCCESS);
		
		if (getPOSUploadSalesListCount(params) > 0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void processPOSUploadSales(Date uploadDate) throws BusinessException, IOException {
		processPOSUploadSales(uploadDate, false);
	}
	
	private void processPOSUploadSales(Date uploadDate, boolean isFromScheduler) throws BusinessException, IOException {
		if (posUploadSalesDAO == null)
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		if (checkSelectedDateSubmitted(uploadDate))
			throw new BusinessException(CommonErrConstant.ERR_POS_UPLOAD_DATE_ALREADY_POSTED);
		
		POSUploadSalesVO vo = new POSUploadSalesVO();
		vo.setUploadDate(uploadDate);
		
		Map<String, Object> params = new HashMap<>();
		params.put("orderCd", ConstantPOSUpload.DOC_TYPE_CD_IN_HOUSE_1U);
		params.put("date", uploadDate);
		
		if (invoiceDAO == null)
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		List<InvoiceVO> invoiceVOList = invoiceDAO.getInvoiceListForPOSUpload(params);
		
//		if (invoiceDAO != null)	return;
		
		double totalAmount = 0;
		List<POSUploadSalesTransVO> tempList = new ArrayList<>();
		
		if (appSettingDAO == null)
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		String code = ConstantAppSetting.MAINT_POS_CONFIG_UPLOAD_AS_TEST.getValue().split("_", 2)[1];
		AppSettingVO appSettingVO = appSettingDAO.getAppSettingByCode(code);
		
		for (InvoiceVO invoiceVO : invoiceVOList) {
//			System.out.println(invoiceVO.getCode() + invoiceVO.getPsNo() + invoiceVO.getDocTypeCd() + invoiceVO.getId());
//			String prefix = "AV";
			String prefix = LookupItemUtils.getSysNumGenVO(invoiceVO.getCompanyId(), CommonConstant.SYS_NUM_CD_PAX_STMT).getPrefixid();
			if (invoiceVO.getDocTypeCd().equals("C")) prefix = "CN";
			
			POSUploadSalesTransVO transVO = new POSUploadSalesTransVO();
			transVO.setIdInvoice(invoiceVO.getId());
			transVO.setDocTypeCd(invoiceVO.getDocTypeCd());
			transVO.setInvoiceNo(prefix + " " + invoiceVO.getCode());
			transVO.setInvoiceDate(invoiceVO.getInvoiceDt());
			
			if (StringUtils.equals(invoiceVO.getStatusCd(), CommonConstant.STATUS_CD_VOID) || StringUtils.equals(invoiceVO.getStatusCd(), CommonConstant.STATUS_CD_CANCELLED)
					|| StringUtils.equals(invoiceVO.getDocTypeCd(), "C")) {
				transVO.setSubTotal(-invoiceVO.getAmount());
				transVO.setGrandTotal(transVO.getSubTotal());
				transVO.setIsVoid(true);
				
				if (StringUtils.equals(invoiceVO.getStatusCd(), CommonConstant.STATUS_CD_VOID) 
						|| StringUtils.equals(invoiceVO.getStatusCd(), CommonConstant.STATUS_CD_CANCELLED)) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(invoiceVO.getInvoiceDt());
					cal.add(Calendar.SECOND, 1);
					transVO.setInvoiceDate(cal.getTime());
				}
			} else {
				transVO.setSubTotal(invoiceVO.getAmount());
				transVO.setGrandTotal(transVO.getSubTotal());
			}
			
			System.out.println("CYY transVO: " + transVO.getInvoiceNo() + " " + transVO.getGrandTotal());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			transVO.setInvoiceDateStr(sdf.format(transVO.getInvoiceDate())); // For API
			
			transVO.setIsTest(true);
			if (appSettingVO != null) transVO.setIsTest(StringUtils.equals(appSettingVO.getValue(), BaseConstant.YES));
			
			tempList.add(transVO);
			totalAmount += transVO.getSubTotal();
		}
		
		if (CollectionUtils.isNotEmpty(tempList)) {
			// POST Data
			boolean responseSuccess = sendPut(new Gson().toJson(tempList));
			
			vo.setTotalAmount(totalAmount);
			vo.setUploadStatus(responseSuccess ? ConstantPOSUpload.UPLOAD_STATUS_SUCCESS : ConstantPOSUpload.UPLOAD_STATUS_FAILED);
			if (appSettingVO != null) vo.setIsTest(StringUtils.equals(appSettingVO.getValue(), BaseConstant.YES));
			vo.setPosUploadSalesTransVOList(tempList);	
			
			// Insert into DB
			if (isFromScheduler) insertPOSUploadSalesForScheduler(vo);
			else insertPOSUploadSales(vo);
			
		} else {
			throw new BusinessException(CommonErrConstant.ERR_POS_UPLOAD_NOT_INVOICE_FOUND);
		}
	}
	
	// Scheduler Run Task
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Scheduler POS Upload Daily at : " + new Date());
		try {
			processPOSUploadSales(new Date(), true);
		} catch (BusinessException | IOException e) {
			
		}
	}
}
