package com.bcs.zsg.maintenance.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.POSUploadSalesTransVO;

public interface POSUploadSalesTransDAO extends BaseDAO {
	
	public int getPOSUploadSalesTransListCount(Map<String, Object> params) throws BusinessException;
	
	public List<POSUploadSalesTransVO> getPOSUploadSalesTransList(Map<String, Object> params) throws BusinessException;
	
	public POSUploadSalesTransVO getPOSUploadSalesTransVO(Map<String, Object> params) throws BusinessException;
	
	public void insertPOSUploadSalesTrans(POSUploadSalesTransVO vo) throws BusinessException;
	
	public void deletePOSUploadSalesTransTestData() throws BusinessException;
}
