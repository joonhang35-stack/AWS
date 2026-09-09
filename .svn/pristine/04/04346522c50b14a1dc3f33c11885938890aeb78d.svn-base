package com.bcs.zsg.product.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.product.dao.SalesCommConfigDAO;
import com.bcs.zsg.product.vo.SalesCommConfigDetailVO;
import com.bcs.zsg.product.vo.SalesCommConfigVO;

public class SalesCommConfigServiceImpl implements SalesCommConfigService {

	@Autowired
	private SalesCommConfigDAO salesCommConfigDAO;
	
	@Override
	public List<SalesCommConfigVO> getSalesCommConfigList() throws BusinessException {
		return salesCommConfigDAO.getSalesCommConfigList();
	}

	@Override
	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailList(Long id) throws BusinessException {
		return salesCommConfigDAO.getSalesCommConfigDetailList(id);
	}
	
	@Override
	public SalesCommConfigVO getSalesCommConfigDetails(Long id) throws BusinessException {
		SalesCommConfigVO salesCommConfigVO = new SalesCommConfigVO();
		
		salesCommConfigVO = salesCommConfigDAO.getSalesCommConfig(id);
		
		if (salesCommConfigVO != null) {
			salesCommConfigVO.setSalesCommConfigDetailList(salesCommConfigDAO.getSalesCommConfigDetailList(salesCommConfigVO.getId()));
		}
		
		return salesCommConfigVO;
	}

	@Override
	public void addSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException {
		salesCommConfigVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		Long idSalesCommConf = (Long) salesCommConfigDAO.insert(salesCommConfigVO);
		
		if (CollectionUtils.isNotEmpty(salesCommConfigVO.getSalesCommConfigDetailList())) {
			for (SalesCommConfigDetailVO detailVO : salesCommConfigVO.getSalesCommConfigDetailList()) {
				detailVO.setIdSalesCommConf(idSalesCommConf);
				detailVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				salesCommConfigDAO.insert(detailVO);
			}
		}
	}
	
	@Override
	public void updSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException {
		salesCommConfigDAO.update(salesCommConfigVO);
		
		salesCommConfigDAO.terminateDetails(salesCommConfigVO);
		
		if (CollectionUtils.isNotEmpty(salesCommConfigVO.getSalesCommConfigDetailList())) {
			for (SalesCommConfigDetailVO detailVO : salesCommConfigVO.getSalesCommConfigDetailList()) {
				if (detailVO.getId() == null) {
					detailVO.setIdSalesCommConf(salesCommConfigVO.getId());
					detailVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					salesCommConfigDAO.insert(detailVO);
				} else {
					salesCommConfigDAO.update(detailVO);
				}
				
			}
		}
	}
	
	@Override
	public void delSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException {
		salesCommConfigVO.setStatusCode(BaseConstant.STATUS_DELETED);
		salesCommConfigDAO.update(salesCommConfigVO);
	}

	@Override
	public void insertSalesCommConfigHistory(Long id, String actionCd, String reason) throws BusinessException {
		salesCommConfigDAO.insertSalesCommConfigHistory(id, actionCd, reason);
	}

}
