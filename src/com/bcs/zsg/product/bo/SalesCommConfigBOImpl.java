package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.SalesCommConfigService;
import com.bcs.zsg.product.vo.SalesCommConfigDetailVO;
import com.bcs.zsg.product.vo.SalesCommConfigVO;

public class SalesCommConfigBOImpl implements SalesCommConfigBO {

	@Autowired
	private SalesCommConfigService salesCommConfigService;
	
	@Override
	public List<SalesCommConfigVO> getSalesCommConfigList() throws BusinessException {
		return salesCommConfigService.getSalesCommConfigList();
	}

	@Override
	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailList(Long id) throws BusinessException {
		return salesCommConfigService.getSalesCommConfigDetailList(id);
	}
	
	@Override
	public SalesCommConfigVO getSalesCommConfigDetails(Long id) throws BusinessException {
		return salesCommConfigService.getSalesCommConfigDetails(id);
	}

	@Override
	public void addSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException {
		salesCommConfigService.addSalesCommConfig(salesCommConfigVO);
	}

	@Override
	public void updSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException {
		salesCommConfigService.updSalesCommConfig(salesCommConfigVO);
	}

	@Override
	public void delSalesCommConfig(SalesCommConfigVO salesCommConfigVO) throws BusinessException {
		salesCommConfigService.delSalesCommConfig(salesCommConfigVO);
	}
	
	public void insertSalesCommConfigHistory(Long id, String actionCd, String reason) throws BusinessException {
		salesCommConfigService.insertSalesCommConfigHistory(id, actionCd,reason);
	}
}
