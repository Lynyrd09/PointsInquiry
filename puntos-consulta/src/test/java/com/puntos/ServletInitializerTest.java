package com.puntos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ServletInitializer.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class ServletInitializerTest {
	@Mock private SpringApplicationBuilder springApplicationBuilder;
	
	@Test
	void testIt() {
		ServletInitializer servletInitializer = new ServletInitializer();
		Mockito.when(springApplicationBuilder.sources(PuntosConsultaApplication.class)).thenReturn(springApplicationBuilder);
		
		SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);
		
		Mockito.verify(springApplicationBuilder).sources(PuntosConsultaApplication.class);
		Assertions.assertEquals(springApplicationBuilder,result);
	}
}
