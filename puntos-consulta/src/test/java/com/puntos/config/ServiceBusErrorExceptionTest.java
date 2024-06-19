package com.puntos.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceBusErrorExceptionTest {
	
	private ServiceBusErrorException sb;
	
	@BeforeEach
	void setUp() {
		
		sb = new ServiceBusErrorException(null, sb);
	}

	@Test
	void testServiceBusErrorException() {
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	        Integer.parseInt("1a");
	    });

	    String expectedMessage = "For input string";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
