package com.bcs.zsg.common.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;

public abstract class BaseCommonDAOImpl extends BaseHibernateDAO implements BaseCommonDAO {

	public abstract <S> void deleteRecordByID(List<S> _listVO) throws BusinessException, SecurityException, 
			IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException ;
	
	public abstract <S> S getRecordByID(long _ID, Class<S> _VOClass) throws BusinessException ;
	
	public abstract <S> List<S> getRecordListByID(long _ID, Class<S> _VOClass) throws BusinessException ;
	
	public abstract <S> List<?> getRecordList(S _commonObj) throws BusinessException;

}
