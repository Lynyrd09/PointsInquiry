package com.puntos.backends;

import java.security.SecureRandom;
import java.util.Objects;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import com.google.gson.Gson;
import com.puntos.models.Metadata;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.service.utils.QueueServices;
import com.puntos.utils.CommonConstants;
import com.puntos.utils.GeneralResponses;

@Service
public class LogicApps {
	
	private static final Logger logger = LoggerFactory.getLogger(LogicApps.class);
	private Gson gson = new Gson();
	
	@Autowired private QueueServices queueServices;
	
	@Value("${timeOutReceiverClient}") private String timeOutReceiverClient;
	
	public Object consume(String servicio, Metadata metadata, String jsonRequest, String serviceId, ServiceBusSenderClient sender, ServiceBusSessionReceiverClient receiver, Class<?> cusClass) {
		
		logger.info("[LogicApps][consume] - [{}] - Enviando Mensaje a {}: {}", metadata.getMessageId(), servicio, jsonRequest);
		
		JSONObject jsonResponse = null;
		ErrorResponse errorData = null;
		
		Object valRequest = gson.fromJson(jsonRequest, cusClass);
		
		/** Formato: (Tama�o, permiteLetras, permiteNumeros)*/
		/** String randomSessionId = RandomStringUtils.random(32, true, true).toUpperCase(); */
		
		String chrs = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomSessionId = "";
		try {
		    SecureRandom secureRandom = SecureRandom.getInstanceStrong();
		    // 9 is the length of the string you want
		    randomSessionId = secureRandom.ints(24, 0, chrs.length()).mapToObj(chrs::charAt).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		    logger.info("[LogicApps][consume] - [{}] - ramdomSessionId generado: {}", metadata.getMessageId(), randomSessionId);
			
		}catch (Exception e) {
			
			logger.error("[LogicApps][consume] - [{}] - error: {}", metadata.getMessageId(), e.getLocalizedMessage());
			
		}
		
		queueServices.sendToQueue(sender, randomSessionId, serviceId, valRequest);
		
		String finalMessage = "";
		
		if(receiver == null) return null;
		
		finalMessage = queueServices.receiverFormQueue(receiver, randomSessionId);
		if(finalMessage == null) {
			
			logger.warn("[LogicApps][consume] - [{}] - Respuesta {}, : {}", metadata.getMessageId(), servicio, "Time OUT al esperar respuesta");
			
			errorData = GeneralResponses.getCustomError(metadata,
													CommonConstants.ORQUESTADOR_TIME_OUT_CODE,
													CommonConstants.ORQUESTADOR_TIME_OUT_TITTLE,
													String.format(CommonConstants.ORQUESTADOR_TIME_OUT_MESSAGE, servicio, timeOutReceiverClient));
			errorData.setStatusCode(504);
			return errorData;
		}else {
			jsonResponse = new JSONObject(finalMessage);
		}
		
		Metadata orkMetadata = GeneralResponses.getGeneratedMetadata(metadata, "RES");
		
		logger.warn("[LogicApps][consume] - [{}] - Respuesta del servicio {}, : {}", metadata.getMessageId(), servicio, finalMessage);
		
		if (jsonResponse.has("errors")) {
			errorData = gson.fromJson(jsonResponse.toString(), ErrorResponse.class);
			if (!Objects.isNull(errorData.getMetadata())) {
				errorData.setMetadata(orkMetadata);
			}
			errorData.setStatusCode(statusCode(servicio));
			return errorData;
		}
		
		return jsonResponse.toString();
	}
	
	private int statusCode(String servicio) {
		if("IBS".equalsIgnoreCase(servicio))
			return 440; 
		return 460;
	}
}
