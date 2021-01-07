package com.rates.utils.serialize;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer {

	  @Override
	  public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
	    return Instant.ofEpochSecond(p.getIntValue()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	  }
	}
