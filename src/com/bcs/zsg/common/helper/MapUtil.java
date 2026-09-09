package com.bcs.zsg.common.helper;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapUtil {
	/***
	 * Returns the key(s) found in the Map object based on the value given.
	 * @param map The Map object that contains the key of the value to search on.
	 * @param value The value to search on.
	 * @return Set of key found based on the value.
	 */
	public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
	    Set<T> keys = new HashSet<T>();
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    return keys;
	}

	/***
	 * Returns the key found in the Map object based on the value given.
	 * @param map The Map object that contains the key of the value to search on.
	 * @param value The value to search on.
	 * @return The key value if there is.
	 */
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

}
