package com.bcs.zsg.maintenance.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.POSUploadSalesTransVO;
import com.bcs.zsg.maintenance.vo.POSUploadSalesVO;

public interface POSUploadSalesService {
	
	public int getPOSUploadSalesListCount(Map<String, Object> params) throws BusinessException;
	public List<POSUploadSalesVO> getPOSUploadSalesList(Map<String, Object> params) throws BusinessException;
	public POSUploadSalesVO getPOSUploadSales(Map<String, Object> params) throws BusinessException;
	
	public int getPOSUploadSalesTransListCount(Map<String, Object> params) throws BusinessException;
	public List<POSUploadSalesTransVO> getPOSUploadSalesTransList(Map<String, Object> params) throws BusinessException;
	public POSUploadSalesTransVO getPOSUploadSalesTransVO(Map<String, Object> params) throws BusinessException;
	
	public void insertPOSUploadSalesForScheduler(POSUploadSalesVO vo) throws BusinessException;
	public void insertPOSUploadSales(POSUploadSalesVO vo) throws BusinessException;
	public void updatePOSUploadSales(POSUploadSalesVO vo) throws BusinessException;
	
	public boolean sendPut(String requestBodyJson) throws IOException;
	public List<POSUploadSalesTransVO> getPostedData(Date dateFrom, Date dateTo) throws IOException;
	public boolean clearTestData() throws IOException, BusinessException;
	
	public boolean checkSelectedDateSubmitted(Date uploadDate) throws BusinessException;
	
	public void processPOSUploadSales(Date uploadDate) throws BusinessException, IOException;
}
