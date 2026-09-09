package com.bcs.zsg.common.bo;

import java.util.List;

import org.primefaces.model.SortOrder;

import com.bcs.zsg.core.exception.BusinessException;

public interface BaseCommonLMBO extends BaseCommonBO {

	public <S> List<?> getLazyRecordList(S _commonObj, int _first, int _pageSize, String _sortField, SortOrder _sortOrder) 
				throws BusinessException;
	
	public <S> int getRowCount(S _commonObj) throws BusinessException;

}
