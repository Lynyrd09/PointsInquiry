package com.puntos.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puntos.models.Metadata;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.models.errors.Error;

public class GeneralResponses {

	private static final Logger logger = LoggerFactory.getLogger(GeneralResponses.class);

	GeneralResponses() {
		throw new IllegalStateException("GeneralResponses class");
	}

	public static ResponseEntity<Object> getGeneralResponse(Object data, HttpStatus status) {
		return new ResponseEntity<>(data, status);
	}

	public static ErrorResponse getCustomError(Metadata data, String code, String tittle, String msg) {
		ErrorResponse errorData = new ErrorResponse();
		Metadata localMetadata = data;
		localMetadata.setDatetime(LocalDateTime.now().withNano(0).toString());
		localMetadata.setMessageType(CommonConstants.RESPONSE_TYPE);

		List<Error> lstErr = new ArrayList<>();
		lstErr.add(new Error(code, tittle, msg));

		errorData.setMetadata(localMetadata);
		errorData.setErrors(lstErr);
		return errorData;
	}

	public static ResponseEntity<Object> getCustomResponse(Object data, int status) {
		return ResponseEntity.status(status).body(data);
	}

	public static Metadata getGeneratedMetadata(Metadata data, String kind) {
		Metadata metadata = new Metadata();
		if (CommonConstants.REQUEST_TYPE.equalsIgnoreCase(kind))
			metadata.setMessageType(CommonConstants.REQUEST_TYPE);
		else
			metadata.setMessageType(CommonConstants.RESPONSE_TYPE);
		metadata.setMessageId(UUID.randomUUID().toString());
		if (data.getMessageIdOrg() == null || data.getMessageIdOrg().equals("")) {
			metadata.setMessageIdOrg(data.getMessageId());
		} else {
			metadata.setMessageIdOrg(data.getMessageIdOrg());
		}
		metadata.setShortMessageId(data.getShortMessageId());
		metadata.setApplicationId(data.getApplicationId());
		metadata.setServiceId(data.getServiceId());
		metadata.setDatetime(LocalDateTime.now().withNano(0).toString());

		return metadata;
	}

	public static String jsonString(Object obj, String proceso) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
		  json = mapper.writeValueAsString(obj);
		  logger.info("[{}] - ResultingJSONstring = {}", proceso, json);
		} catch (JsonProcessingException e) {
			logger.error("context", e);
		}
		
		return json;
	}

}
