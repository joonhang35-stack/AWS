package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.SalesCommConfigDetailVO;
import com.bcs.zsg.product.vo.SalesCommConfigVO;

public interface SalesCommConfigDAO extends BaseDAO {

	public List<SalesCommConfigVO> getSalesCommConfigList() throws BusinessException;
	
	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailList(Long id) throws BusinessException;
	
	public SalesCommConfigVO getSalesCommConfig(Long id) throws BusinessException;

	public void terminateDetails(SalesCommConfigVO salesCommConfigVO) throws BusinessException;

	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailListByIdTourPkg(Long idTourPkg) throws BusinessException;

	public void insertSalesCommConfigHistory(Long id, String actionCd, String reason) throws BusinessException;

}
