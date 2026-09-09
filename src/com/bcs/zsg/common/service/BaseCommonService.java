package com.bcs.zsg.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.bcs.zsg.common.bo.CommonObject;
import com.bcs.zsg.core.exception.BusinessException;

public interface BaseCommonService {

	public <S> S getRecordByID(long _ID, Class<S> _VOClass) throws BusinessException;
	public <S> S getRecordByID(long _ID, Class<S> _VOClass, CommonObject _commonObj) throws BusinessException;
	
	public <S> List<S> getRecordListByID(long _ID, Class<S> _VOClass) throws BusinessException;

	public <S> List<?> getRecordList(S _commonObj) throws BusinessException;
	
	public <S> void addRecord(S _VO) throws BusinessException;
	public <S> void updateRecord(S _VO) throws BusinessException;

	public <S> void deleteRecordByID(List<S> _listVO) 
			throws BusinessException, SecurityException, IllegalArgumentException, NoSuchMethodException, 
					IllegalAccessException, InvocationTargetException;
	
}
