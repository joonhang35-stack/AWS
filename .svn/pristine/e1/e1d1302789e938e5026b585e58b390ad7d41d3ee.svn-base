package com.bcs.zsg.common.bo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;

public interface BaseCommonBO {

	public <S> S getRecordByID(long _ID, Class<S> _VOClass) throws BusinessException;

	public <S> List<S> getRecordListByID(long _ID, Class<S> _VOClass) throws BusinessException;

	public <S> List<?> getRecordList(S _commonObj) throws BusinessException;
	
	public <S> void deleteRecord(List<S> _listVO) throws BusinessException, SecurityException, 
					IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
