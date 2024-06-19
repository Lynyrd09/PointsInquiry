package com.puntos.service;


import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puntos.backends.Cmc;
import com.puntos.backends.LogicApps;
import com.puntos.config.ServiceBusConfig;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.models.request.cmc.concuentas.CmcConcuentasRequest;
import com.puntos.models.request.ibs.movimientos.IbsMovimientoRequest;
import com.puntos.models.request.ork.movimientos.OrkMovimientosRequest;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;
import com.puntos.models.response.ibs.movimientos.IbsMovimientoResponse;
import com.puntos.models.response.ork.consulta.OrkConsultaResponse;
import com.puntos.models.response.ork.movimientos.OrkMovimientosResponse;
import com.puntos.utils.ConstantJson;
import com.puntos.utils.ConstantJsonCMC;
import com.puntos.utils.ConstantJsonIbs;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MovimientosService.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@ActiveProfiles({ "test" })
class MovimientosServiceTest {
	
private static final Logger logger = LoggerFactory.getLogger(MovimientosServiceTest.class);
	
	@MockBean private ServiceBusConfig serviceBusConfig;
	@MockBean private Cmc cmc;
	@MockBean private LogicApps app;
	@InjectMocks private MovimientosService ork;
	
	private ObjectMapper mapper = new ObjectMapper();
	private OrkMovimientosRequest data = new OrkMovimientosRequest();
	private ErrorResponse error = new ErrorResponse();
	

	private IbsMovimientoRequest ibsReq = new IbsMovimientoRequest();
	private IbsMovimientoResponse ibsResp = new IbsMovimientoResponse();
	private OrkMovimientosResponse resp = new OrkMovimientosResponse();
	private CmcConcuentasRequest cmcMonReq = new CmcConcuentasRequest();
	private CmcConcuentasResponse icmcMonResp = new CmcConcuentasResponse();
	
	private String endpointCuentas = "http://10.54.8.46:8081/api/v1/con-listadocuentas";	
	private String serviceIdIbs = "9000643";
	private String timeout = "5";


	
	@Autowired Environment environment;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		this.serviceIdIbs = environment.getProperty("ibs.serviceId.movimiento");
		this.endpointCuentas = environment.getProperty("cmc.conCuentas");
		this.timeout = environment.getProperty("timeOutReceiverClient");

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ReflectionTestUtils.setField(ork, "ibsServiceId", serviceIdIbs);
		
		this.error = mapper.readValue(ConstantJson.JSON_ERROR, ErrorResponse.class);
		logger.info("Request: {} ", (new JSONObject(this.error)).toString());
		
		this.resp = mapper.readValue(ConstantJson.ORK_MOVIMIENTO_RESPONSE, OrkMovimientosResponse.class);
		logger.info("Request: {} ", (new JSONObject(this.resp)).toString());
		
	}

	@Test
	void proceso() throws Exception {
		this.data = mapper.readValue(ConstantJson.ORK_MOVIMIENTO_REQUEST, OrkMovimientosRequest.class);
		
		Mockito.when(app.consume(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.eq(serviceIdIbs), Mockito.any(), Mockito.any(), Mockito.any()))
		.thenReturn(ConstantJsonIbs.IBS_RESPONSE_MOVIMIENTO);
		
		Mockito.when(cmc.consume(Mockito.eq(endpointCuentas), Mockito.any(), Mockito.anyString(), Mockito.any()))
		.thenReturn(ConstantJsonCMC.CMC_RESPONSE_CONCUENTAS);
		
		Object rsp = ork.proceso(this.data);                                    
		
		Assertions.assertEquals(OrkMovimientosResponse.class, rsp.getClass());
	}
	

}
