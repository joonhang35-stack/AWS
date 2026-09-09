package com.bcs.zsg.common.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bcs.zsg.common.bo.CommonObject;


public class CommonMethodMapper<T> {
	private static final Logger logger = LoggerFactory.getLogger(CommonMethodMapper.class);

	private Class<T> objReflect;
	private Method objCurMethod;
	private CommonObject objCommon;
	
	public CommonMethodMapper() {
	}
	
	/**
	 * @param _objReflect
	 */
	public CommonMethodMapper(Class<T> _objReflect) {
		SetMappingClass(_objReflect);
	}

	public CommonMethodMapper(Class<T> _objReflect, String _strMethodName) throws SecurityException, NoSuchMethodException {
		SetMappingClass(_objReflect);
		setCurrentMethod(_strMethodName);
	}
	
	/***
	 * @param _objReflect
	 */
	public void SetMappingClass(Class<T> _objReflect) {
		this.objReflect = _objReflect;
	}
	/***
	 * @param _objInvoke 		- Object to invoke the method on
	 * @param _strMethodName 	- The method name that is going to call.
	 * @param _strMethodArg		- The method arguments if there is (will refer to _boolHaveParam)
	 * @param _boolHaveParam	- True: Method have arguments, False: Method does not have arguments
	 * @return
	 */
	private <S> CommonObject MapMethodArg(S _objInvoke, String _strMethodName, Class<?> _ParamClass, String _strMethodArg, 
					boolean _boolHaveParam, boolean _returnType) {
		objCommon = new CommonObject("MapMethodArg");
			
		try {
			if(_boolHaveParam) {
				Method tmpMethod = objReflect.getMethod(_strMethodName, _ParamClass);
				objCommon.setListResult(tmpMethod.invoke(_objInvoke, _strMethodArg));
			} else {
				if(_returnType)
					objCommon.setListResult(objReflect.getMethod(_strMethodName).invoke(_objInvoke));
				else
					objReflect.getMethod(_strMethodName).invoke(_objInvoke);
			}
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException | SecurityException | NoSuchMethodException e) {
			objCommon.setErrorMessage("Error Mapping");
			logger.info("[MapMethodArg:(" + _strMethodName + ", " + _strMethodArg + ", " + _boolHaveParam + ")]={}",  e.getCause());
		}
		
		return objCommon;
	}

	public <S> CommonObject MapMethodArg(S _objInvoke, String _strMethodName, Class<?> _paramClass, String _strMethodArg)  {
		return MapMethodArg(_objInvoke, _strMethodName, _paramClass, _strMethodArg, true, false);
	}
	
	//TODO: implement mapping with multiple arguments
	public <S> CommonObject MapMethodArg(S _objInvoke, String _strMethodName, List<Class<?>> _listParamClass, String _strMethodArg)  {
		return MapMethodArg(_objInvoke, _strMethodName, null, null, true, false);
	}
	
	public <S> CommonObject MapMethodArg(S _objInvoke, String _strMethodName) {
		return MapMethodArg(_objInvoke, _strMethodName, null, null, false, false);
	}

	public <S> CommonObject MapMethodArg(S _objInvoke, String _strMethodName, boolean _returnType) {
		return MapMethodArg(_objInvoke, _strMethodName, null, null, false, true);
	}
	
	public void setCurrentMethod(String _strMethodName) throws SecurityException, NoSuchMethodException{
		objCurMethod = objReflect.getMethod(_strMethodName);
	}
	
	public <U> U invoke(Object _obj, Class<U> _objCast) throws IllegalArgumentException, IllegalAccessException, 
						InvocationTargetException{
		return _objCast.cast(objCurMethod.invoke(_obj));
	}
}
