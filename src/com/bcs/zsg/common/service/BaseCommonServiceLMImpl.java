package com.bcs.zsg.common.service;

import java.util.List;

import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.dao.BaseCommonDAOLM;
import com.bcs.zsg.core.exception.BusinessException;

public abstract class BaseCommonServiceLMImpl extends BaseCommonServiceImpl {

	protected abstract BaseCommonDAOLM getLMDAO();
	
	public <S> List<?> getLazyRecordList(S _commonObj, int _first, int _pageSize, 
			String _sortField, SortOrder _sortOrder) throws BusinessException {
		return getLMDAO().getLazyRecordList(_commonObj, _first, _pageSize, _sortField, _sortOrder);
	}

	public <S> int getRowCount(S _commonObj) throws BusinessException {
		return getLMDAO().getRowCount(_commonObj);
	}
}
