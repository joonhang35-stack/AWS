package com.bcs.zsg.history.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HistoryUtils {

	/**
	 * Set method value
	 * @param historyVO
	 * @param originalVO
	 * @return
	 */
	public static void setHistoryMethodValue(Object historyVO, Object originalVO) {
		try {
			// Get value
			Map<String, Object> getterMap = new HashMap<String, Object>();
			Method[] originalMethod = originalVO.getClass().getDeclaredMethods();
			for (int i = 0 ; i < originalMethod.length ; i++) {
				String methodName = originalMethod[i].getName();
				if (methodName.startsWith("get")) {
					Method m = originalVO.getClass().getMethod(methodName);
					getterMap.put(methodName.replace("get", ""), m.invoke(originalVO));
				}
			}
			
			// Set value
			Method[] historyMethod = historyVO.getClass().getSuperclass().getDeclaredMethods();
			for (int i = 0 ; i < historyMethod.length ; i++) {
				String methodName = historyMethod[i].getName();
				if (methodName.startsWith("set")) {
					Method m = historyVO.getClass().getSuperclass().getMethod(methodName, historyMethod[i].getParameterTypes());
					if (getterMap.containsKey(methodName.replace("set", ""))) m.invoke(historyVO, getterMap.get(methodName.replace("set", "")));
					//historyMethod[i].invoke(historyVO, getterMap.get(methodName.replace("set", "")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
