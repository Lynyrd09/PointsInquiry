package com.puntos.config;

public class ServiceBusErrorException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8991037984235655550L;

	public ServiceBusErrorException(String errorMessage,Throwable err ){
        super(errorMessage,err);
    }
}
