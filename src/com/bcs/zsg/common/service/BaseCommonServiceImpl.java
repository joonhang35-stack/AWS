package com.bcs.zsg.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.bcs.zsg.common.bo.CommonObject;
import com.bcs.zsg.common.dao.BaseCommonDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public abstract class BaseCommonServiceImpl {

	protected abstract BaseCommonDAO getDAO();
	
	public <S> void deleteRecordByID(List<S> _listVO) throws BusinessException, SecurityException, 
			IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		getDAO().deleteRecordByID(_listVO);		
	}

	public <S> void addRecord(S _VO) throws BusinessException {
		getDAO().insert((BaseVO)_VO);
	}

	public <S> void updateRecord(S _VO) throws BusinessException {
		getDAO().update((BaseVO)_VO);
	}

	public <S> S getRecordByID(long _ID, Class<S> _VOClass) throws BusinessException {
		return (S) getDAO().getRecordByID(_ID, _VOClass);
	}

	public <S> List<S> getRecordListByID(long _ID, Class<S> _VOClass) throws BusinessException {
		return getDAO().getRecordListByID(_ID, _VOClass);
	}
	
	public <S> List<?> getRecordList(S _commonObj) throws BusinessException {
		return getDAO().getRecordList(_commonObj);
	}
	
	public <S> S getRecordByID(long _ID, Class<S> _VOClass, CommonObject _commonObj) throws BusinessException {
		return null;
	}
	
}
