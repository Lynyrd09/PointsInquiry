package com.puntos.backends;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.models.request.ork.consulta.OrkConsultaRequest;
import com.puntos.utils.ConstantJson;
import com.puntos.utils.ConstantJsonCMC;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(Cmc.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class CmcTest {
	private final Logger logger = LoggerFactory.getLogger(LogicsAppsTest.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	private OrkConsultaRequest data = new OrkConsultaRequest();
	ErrorResponse errorData = new ErrorResponse();

	private String listadoCuentas = "http://10.54.8.46:8081/api/v1/con-listadocuentas";

	@InjectMocks Cmc cmc;
	
	@BeforeEach
	public void setUp() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
		
	@Test
	void procesaErrors() throws Exception{
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		logger.info(new JSONObject(this.data).toString());
		
		Object info = cmc.consume(listadoCuentas, data.getMetadata(), "", null);
		
		Assertions.assertEquals(ErrorResponse.class, info.getClass());
	}

	@Test
	void procesaHasErrors() throws Exception{
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		logger.info(new JSONObject(this.data).toString());
		
		Object info = cmc.consume(listadoCuentas, data.getMetadata(), ConstantJsonCMC.CMC_REQUEST_CONCUENTAS_BAD, null);
		
		Assertions.assertEquals(ErrorResponse.class, info.getClass());
	}
		
}
