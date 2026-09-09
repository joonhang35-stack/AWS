package com.bcs.zsg.common.helper;

import java.util.HashMap;
import java.util.Map;

import com.bcs.zsg.core.helper.CryptoUtils;

public class AESEncryption  extends CryptoUtils {

	public static Map<String, String> decryptQueryToMap(String encryptedQuery) throws Exception {
		 
		 String[] params = decrypt(encryptedQuery).split("&");  
	     Map<String, String> map = new HashMap<String, String>();  
	     for (String param : params)  
	     {  
	         String name = param.split("=")[0];  
	         String value = param.split("=")[1];  
	         map.put(name, value);  
	     }    
		return map;
	}
	
	public static String decryptQueryToParamVal(String encryptedQuery, String key) throws Exception {
		Map<String, String> map  = decryptQueryToMap(encryptedQuery);
		if (map.get(key) != null)
			return map.get(key);
		else
			return null;
	}
}
