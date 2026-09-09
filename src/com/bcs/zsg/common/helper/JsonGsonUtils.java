package com.bcs.zsg.common.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * JSON utils from Gson library
 */
public class JsonGsonUtils {
	public static String first(JsonObject obj, String key) {
        if (!obj.has(key) || obj.get(key).isJsonNull()) {
            return null;
        }

        JsonArray arr = obj.getAsJsonArray(key);
        if (arr == null || arr.size() == 0 || arr.get(0).isJsonNull()) {
            return null;
        }

        return arr.get(0).getAsString();
    }
}
