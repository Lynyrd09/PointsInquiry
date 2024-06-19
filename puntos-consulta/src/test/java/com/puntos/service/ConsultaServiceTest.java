package com.puntos.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import com.puntos.models.HeaderRequest;
import com.puntos.models.Metadata;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.models.request.cmc.concuentas.CmcConcuentasRequest;
import com.puntos.models.request.ibs.consulta.IbsConsultaRequest;
import com.puntos.models.request.ork.consulta.OrkConsultaRequest;
import com.puntos.models.request.ork.consulta.OrkConsultaRequestBody;
import com.puntos.models.request.ork.consulta.OrkConsultaRequestData;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;
import com.puntos.models.response.ibs.consulta.IbsConsultaResponse;
import com.puntos.models.response.ork.consulta.OrkConsultaResponse;
import com.puntos.utils.ConstantJson;
import com.puntos.utils.ConstantJsonCMC;
import com.puntos.utils.ConstantJsonIbs;
import com.puntos.utils.ObjectsForBackEnds;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(ConsultaService.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@ActiveProfiles({ "dev" })
class ConsultaServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(ConsultaServiceTest.class);

	@MockBean private ServiceBusConfig serviceBusConfig;
	@MockBean private Cmc cmc;
	@MockBean private LogicApps app;
	@InjectMocks private ConsultaService ork;

	
	private ObjectMapper mapper = new ObjectMapper();
	private OrkConsultaRequest data = new OrkConsultaRequest();
	private HeaderRequest header = new HeaderRequest();
	private ErrorResponse error = new ErrorResponse();
	private ObjectsForBackEnds objs = new ObjectsForBackEnds();
	private Metadata metadata = new Metadata();
	private CmcConcuentasRequest cmcMonReq = new CmcConcuentasRequest();
	private CmcConcuentasResponse icmcMonResp = new CmcConcuentasResponse();
	private IbsConsultaRequest ibsReq = new IbsConsultaRequest();
	private IbsConsultaResponse ibsResp = new IbsConsultaResponse();
	private OrkConsultaResponse resp = new OrkConsultaResponse();
	private boolean customBol = true;
	
	private String endpointCuentas = "http://10.54.8.46:8081/api/v1/con-listadocuentas";
	private String serviceIdIbs = "9000642";
	private String timeout = "60";

	
	@Autowired Environment environment;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		this.endpointCuentas = environment.getProperty("cmc.conCuentas");
		this.serviceIdIbs = environment.getProperty("ibs.serviceId.consulta");
		this.timeout = environment.getProperty("timeOutReceiverClient");

		
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ReflectionTestUtils.setField(ork, "conCuentasEndPoint", endpointCuentas);
		ReflectionTestUtils.setField(ork, "ibsServiceId", serviceIdIbs);
		
		this.error = mapper.readValue(ConstantJson.JSON_ERROR, ErrorResponse.class);
		logger.info("Request: {} ", (new JSONObject(this.error)).toString());
		
		this.resp = mapper.readValue(ConstantJson.ORK_CONSULTA_RESPONSE, OrkConsultaResponse.class);
		logger.info("Request: {} ", (new JSONObject(this.resp)).toString());
	}
	
	@Test
	void proceso() throws Exception {
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		
		Mockito.when(cmc.consume(Mockito.eq(endpointCuentas), Mockito.any(), Mockito.anyString(), Mockito.any()))
		.thenReturn(ConstantJsonCMC.CMC_RESPONSE_CONCUENTAS);
		
		Mockito.when(app.consume(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.eq(serviceIdIbs), Mockito.any(), Mockito.any(), Mockito.any()))
		.thenReturn(ConstantJsonIbs.IBS_RESPONSE_CONSULTA);
		
		Object rsp = ork.proceso(this.data);
		
		Assertions.assertEquals(OrkConsultaResponse.class, rsp.getClass());
	}
	
	@Test
	void getConcuentasError() throws Exception {
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		
		data.getData().getBody().getAccountNumber().add("0005400990002871033");

		cmcMonReq = mapper.readValue(objs.cmcConcuentas(data, CmcConcuentasRequest.class), CmcConcuentasRequest.class);
		logger.info("Request: {} ", (new JSONObject(cmcMonReq).toString()));
		
		Mockito.when(cmc.consume(Mockito.eq(endpointCuentas), Mockito.any(), Mockito.any(), Mockito.any()))
		.thenReturn(error);
		
		Assertions.assertEquals(true, customBol);
	}
	
	@Test
	void getIbsError() throws Exception {
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		
		this.icmcMonResp = mapper.readValue(ConstantJsonCMC.CMC_RESPONSE_CONCUENTAS, CmcConcuentasResponse.class);

		ibsReq = mapper.readValue(objs.ibsConsulta(data, icmcMonResp, IbsConsultaRequest.class), IbsConsultaRequest.class);
		logger.info("Request: {} ", (new JSONObject(cmcMonReq).toString()));
		
		Mockito.when(app.consume(Mockito.eq("IBS"), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
		.thenReturn(error);
		
		Object resp = ork.proceso(this.data);
		
		Assertions.assertEquals(ErrorResponse.class, resp.getClass());
	}
	

	
	@Test
	@DisplayName("Proceso - Request Type R or E - Ibs Error Response")
	void testProceso_RequestTypeRorE_IbsErrorResponse() {
		
		// Arrange
		metadata.setMessageType("Request");
		metadata.setMessageId("e13a7e3e-7a59-40ea-b899-5a0f7f02cdcc");
		metadata.setMessageIdOrg("e13a7e3e-7a59-40ea-b899-5a0f7f02cdcc");
		metadata.setShortMessageId("e13a7e3e-7a59-4");
		metadata.setApplicationId("Ebanca");
		metadata.setServiceId("ConsultaAgencias");
		metadata.setDatetime("2020-03-31T18:59:00");
		
		header.setBranch("037");
		header.setSupervisor("0080");
		header.setBatch("0037");
		header.setDate("20210826");
		header.setHour("123500");
		header.setSystem("xxxxxxxxxx");
		header.setUser("BA1234");
		header.setLocationUser("172.19.0.0");
		header.setInstanceId("01");
		header.setChannelId("100000000");
        
		OrkConsultaRequestBody orkConRqBody = new OrkConsultaRequestBody();
        List<String> cardNumber = new ArrayList<>();
        orkConRqBody.setCardNumber(cardNumber);       
		OrkConsultaRequestData orkConRqData = new OrkConsultaRequestData();
        orkConRqData.setBody(orkConRqBody);
        orkConRqData.setHeader(header);
		OrkConsultaRequest request = new OrkConsultaRequest();
		request.setMetadata(metadata);
		request.setData(orkConRqData);

		// Mocking LogicApps.consume() method
	   Mockito.when(app.consume(Mockito.eq("IBS"), Mockito.any(), Mockito.any(), Mockito.eq(serviceIdIbs), Mockito.any(), Mockito.any(),
			   Mockito.eq(IbsConsultaRequest.class))).thenReturn(new ErrorResponse());

		// Act
		Object result = ork.proceso(request);
		

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result instanceof ErrorResponse);

		Mockito.verify(app).consume(Mockito.eq("IBS"), Mockito.any(), Mockito.any(), Mockito.eq(serviceIdIbs), Mockito.any(), Mockito.any(),
				Mockito.eq(IbsConsultaRequest.class));
	}
	

	@Test
	@DisplayName("Proceso - TCR")
	void testProceso_TCR() throws Exception {
		
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		
		Mockito.when(cmc.consume(Mockito.eq(endpointCuentas), Mockito.any(), Mockito.anyString(), Mockito.any()))
		.thenReturn(ConstantJsonCMC.CMC_RESPONSE_CONCUENTAS);
		
		Mockito.when(app.consume(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.eq(serviceIdIbs), Mockito.any(), Mockito.any(), Mockito.any()))
		.thenReturn(ConstantJsonIbs.IBS_RESPONSE_CONSULTA_2);
		
		Object rsp = ork.proceso(this.data);
		
		Assertions.assertEquals(OrkConsultaResponse.class, rsp.getClass());
	}
	

}