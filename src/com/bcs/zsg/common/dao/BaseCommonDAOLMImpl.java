package com.bcs.zsg.common.dao;

import java.util.List;

import org.primefaces.model.SortOrder;

import com.bcs.zsg.core.exception.BusinessException;

public abstract class BaseCommonDAOLMImpl extends BaseCommonDAOImpl implements BaseCommonDAOLM {

	public abstract <S> int getRowCount(S _commonObj) throws BusinessException;

	public abstract <S> List<?> getLazyRecordList(S _commonObj, int _first, int _pageSize, 
			String _sortField, SortOrder _sortOrder) throws BusinessException;

}
