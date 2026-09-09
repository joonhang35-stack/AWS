package com.bcs.zsg.common.helper.deserializer;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class UnixTimestampDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		long timestamp = parser.getLongValue();
		return new Date(timestamp * 1000);
	}
}