package com.puntos.service.utils;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.core.util.BinaryData;
import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import com.puntos.config.ServiceBusErrorException;

@Service
public class QueueServices{
	
	@Value("${timeOutReceiverClient}")
	private String timeOutReceiverClient;
	
    private static final Logger logger = LoggerFactory.getLogger(QueueServices.class);
    
    public void sendToQueue(ServiceBusSenderClient sender, String randomSessionId, String serviceId, Object request) {
    	try {
    		BinaryData binaryData = BinaryData.fromObject(request);
    		
    	   	logger.info ("binary Data : {}" , binaryData);

	    	ServiceBusMessage message = new ServiceBusMessage(BinaryData.fromObject(request));
	    	message.setContentType("application/json");
	    	message.setSessionId(randomSessionId);
	    	message.getApplicationProperties().put("serviceid", serviceId);
	    	
	    	sender.sendMessage(message);
    	}
        catch (ServiceBusErrorException exception){
            throw new ServiceBusErrorException("Error enviando mensaje a service bus:"+/**message*/ "test",exception);
        }
        catch (Exception ex){
            throw ex;
        }
    }
    
    public String receiverFormQueue(ServiceBusSessionReceiverClient sessionReceiver, String randomSessionId) {
    	logger.info ("Session id a buscar : {}" , randomSessionId);
    	String finalResponse = null;
    	
    	/** A receiver is returned when a lock on the session is acquired, otherwise, it throws an exception. */
        ServiceBusReceiverClient receiver = sessionReceiver.acceptSession(randomSessionId);
        try {
        	logger.warn("{} - COMENZANDO BUSQUEDA: {}", LocalDateTime.now(), randomSessionId);
	        IterableStream<ServiceBusReceivedMessage> messages = receiver.receiveMessages(1, Duration.ofSeconds(Long.parseLong(timeOutReceiverClient)));
	        
	        for (ServiceBusReceivedMessage message : messages) {
	            /** Process message. */
	        	logger.warn("{} - ITERANDO MENSAJE", LocalDateTime.now());
	        	finalResponse = processMessage(message);
	
	            /** Messages from the sync receiver MUST be settled explicitly. In this case, we complete the message if
	             *  it was successfully
	             */
	            if (finalResponse != null) {
	            	logger.warn("{} - COMPLETE MENSAJE", LocalDateTime.now());
	                receiver.complete(message);
	            } else {
	            	logger.warn("{} - ABANDON MENSAJE", LocalDateTime.now());
	                receiver.abandon(message, null);
	            }
	        }
        } catch (Exception e) {
        	logger.info(e.getMessage());
		} finally {
			receiver.close();
		}
        logger.warn("{} - DEVOLVIENDO RESPUESTA", LocalDateTime.now());
        return finalResponse;
    }
    
    private String processMessage(ServiceBusReceivedMessage message) {
        logger.info("Session: {}. Sequence #: {}. Contents: {}", message.getSessionId(), message.getSequenceNumber(), message.getBody());
        return message.getBody().toString();
    }
}
