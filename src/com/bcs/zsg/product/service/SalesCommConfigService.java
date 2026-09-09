package com.bcs.zsg.product.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.SalesCommConfigDetailVO;
import com.bcs.zsg.product.vo.SalesCommConfigVO;

public interface SalesCommConfigService {
	
	public List<SalesCommConfigVO> getSalesCommConfigList() throws BusinessException;

	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailList(Long id) throws BusinessException;
	
	public SalesCommConfigVO getSalesCommConfigDetails(Long id) throws BusinessException;

	public void addSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException;

	public void updSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException;
	
	public void delSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException;

	public void insertSalesCommConfigHistory(Long id, String actionCd, String reason) throws BusinessException;

}
