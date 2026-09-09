package com.bcs.zsg.maintenance.bo;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.POSUploadSalesService;
import com.bcs.zsg.maintenance.vo.POSUploadSalesTransVO;
import com.bcs.zsg.maintenance.vo.POSUploadSalesVO;

public class POSUploadSalesBOImpl implements POSUploadSalesBO {
	
	@Autowired
	private POSUploadSalesService posUploadSalesService;
	
	public int getPOSUploadSalesListCount(Map<String, Object> params) throws BusinessException {
		return posUploadSalesService.getPOSUploadSalesListCount(params);
	}

	@Override
	public List<POSUploadSalesVO> getPOSUploadSalesList(Map<String, Object> params) throws BusinessException {
		return posUploadSalesService.getPOSUploadSalesList(params);
	}
	
	@Override
	public POSUploadSalesVO getPOSUploadSales(Map<String, Object> params) throws BusinessException {
		return posUploadSalesService.getPOSUploadSales(params);
	}
	
	@Override
	public int getPOSUploadSalesTransListCount(Map<String, Object> params) throws BusinessException {
		return posUploadSalesService.getPOSUploadSalesTransListCount(params);
	}

	@Override
	public List<POSUploadSalesTransVO> getPOSUploadSalesTransList(Map<String, Object> params) throws BusinessException {
		return posUploadSalesService.getPOSUploadSalesTransList(params);
	}
	
	@Override
	public POSUploadSalesTransVO getPOSUploadSalesTransVO(Map<String, Object> params) throws BusinessException {
		return posUploadSalesService.getPOSUploadSalesTransVO(params);
	}

	@Override
	public void insertPOSUploadSales(POSUploadSalesVO vo) throws BusinessException {
		posUploadSalesService.insertPOSUploadSales(vo);
	}
	
	@Override
	public void updatePOSUploadSales(POSUploadSalesVO vo) throws BusinessException {
		posUploadSalesService.updatePOSUploadSales(vo);
	}
	
	@Override
	public List<POSUploadSalesTransVO> getPostedData(Date dateFrom, Date dateTo) throws IOException {
		return posUploadSalesService.getPostedData(dateFrom, dateTo);
	}
	
	@Override
	public boolean sendPut(String requestBodyJson) throws IOException, BusinessException {
		return posUploadSalesService.sendPut(requestBodyJson);
	}
	
	@Override
	public boolean clearTestData() throws IOException, BusinessException {
		return posUploadSalesService.clearTestData();
	}
	
	@Override
	public boolean checkSelectedDateSubmitted(Date uploadDate) throws BusinessException {
		return posUploadSalesService.checkSelectedDateSubmitted(uploadDate);
	}
	
	@Override
	public void processPOSUploadSales(Date uploadDate) throws BusinessException, IOException {
		posUploadSalesService.processPOSUploadSales(uploadDate);
	}
}
