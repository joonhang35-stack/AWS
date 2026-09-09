package com.bcs.zsg.common.helper.deserializer;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;

import java.lang.reflect.Type;
import java.util.Date;

public class GsonUnixTimestampDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        long timestampSeconds = json.getAsLong();
        return new Date(timestampSeconds * 1000); // convert to milliseconds
    }
}