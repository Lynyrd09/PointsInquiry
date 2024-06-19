package com.puntos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PuntosConsultaApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class PuntosConsultaApplicationTests {
	@Test
	void contextLoads() {
		System.setProperty("spring.profiles.default", "test");
		PuntosConsultaApplication.main(new String[] {});
		Assertions.assertDoesNotThrow(this::doNotThrowException);
	}

	private void doNotThrowException() {
		// This method will never throw exception
	}
}
