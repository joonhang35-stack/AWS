package com.bcs.zsg.common.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommonVOHelper {

	private static CommonVOHelper objCommonVOHelper;
	
	private CommonVOHelper(){
	}
	
	public static CommonVOHelper getObject(){
		if(objCommonVOHelper == null)
			objCommonVOHelper = new CommonVOHelper();
		return objCommonVOHelper;
	}

	public <T,S,U> List<U> getAllSelectedID(Class<T> objClass, List<S> _objSelectedVO, String _method, Class<U> _objCast) throws SecurityException, NoSuchMethodException, 
						IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<U> tmpList = new ArrayList<U>();
		CommonMethodMapper<?> tmpMethodMapper;
		
		tmpMethodMapper = new CommonMethodMapper<T>(objClass, _method);

		for(S tmpVO: _objSelectedVO)
			tmpList.add(tmpMethodMapper.invoke(tmpVO, _objCast));

		return tmpList;
	}

}
