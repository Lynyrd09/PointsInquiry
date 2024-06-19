package com.puntos.backends;

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

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.models.request.ibs.consulta.IbsConsultaRequest;
import com.puntos.models.request.ork.consulta.OrkConsultaRequest;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;
import com.puntos.service.utils.QueueServices;
import com.puntos.utils.ConstantJson;
import com.puntos.utils.ConstantJsonCMC;
import com.puntos.utils.ConstantJsonIbs;
import com.puntos.utils.ObjectsForBackEnds;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(LogicApps.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@ActiveProfiles({ "test" })
class LogicsAppsTest {
	private final Logger logger = LoggerFactory.getLogger(LogicsAppsTest.class);
	@MockBean
	QueueServices queueServices;
	@InjectMocks
	LogicApps lg;
	
	@Autowired Environment environment;

	private static final String DESTINATION_NAME_IBS = "orquestacion.to.babroker.integracion.req.lq";
	private static final String RESPONSE_NAME_IBS = "babroker.to.orquestacion.integracion.rsp.lq";
	private String logApps = "Endpoint=sb://sb-ba-development.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=5wpoptRm5GcW31HKBzXoPULC9fYLVkZSRuZ7HAO0eCc=";
	String timeOut = "60";
	String ibsServiceIdConsulta = "9000642";

	
	private ObjectMapper mapper = new ObjectMapper();
	private OrkConsultaRequest data = new OrkConsultaRequest();
	private ObjectsForBackEnds objs = new ObjectsForBackEnds();
	private CmcConcuentasResponse cmcCuenRsp = new CmcConcuentasResponse();

	@BeforeEach
	public void setUp() throws Exception {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ReflectionTestUtils.setField(lg, "queueServices", queueServices);
		ReflectionTestUtils.setField(lg, "timeOutReceiverClient", timeOut);
		this.logApps = environment.getProperty("servicebus.conectionstring.logapps");
		
		this.cmcCuenRsp = mapper.readValue(ConstantJsonCMC.CMC_RESPONSE_CONCUENTAS, CmcConcuentasResponse.class);
		logger.info("Request: {} ", (new JSONObject(this.cmcCuenRsp)).toString());
	}

	@Test
	void consumeTest() throws Exception {
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		logger.info(new JSONObject(this.data).toString());

		Mockito.when(queueServices.receiverFormQueue(Mockito.any(), Mockito.anyString()))
				.thenReturn(ConstantJsonIbs.IBS_RESPONSE_CONSULTA);

		ServiceBusSenderClient sender = new ServiceBusClientBuilder().connectionString(logApps).sender()
				.queueName(DESTINATION_NAME_IBS).buildClient();

		ServiceBusSessionReceiverClient receiver = new ServiceBusClientBuilder().connectionString(logApps)
				.sessionReceiver().queueName(RESPONSE_NAME_IBS).buildClient();
		
		Object resp = lg.consume("IBS", data.getMetadata(), objs.ibsConsulta(data, cmcCuenRsp, IbsConsultaRequest.class), ibsServiceIdConsulta, sender, receiver, IbsConsultaRequest.class);

		IbsConsultaRequest ibsTest = mapper.readValue(ConstantJsonIbs.IBS_REQUEST_CONSULTA, IbsConsultaRequest.class);
		logger.info(new JSONObject(ibsTest).toString());

		Assertions.assertEquals(String.class, resp.getClass());
	}
	
	@Test
	void consumeTestNull() throws Exception{
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		logger.info(new JSONObject(this.data).toString());
		
		Mockito.when(queueServices.receiverFormQueue(Mockito.any(), Mockito.anyString()))
		.thenReturn(null);
		
		ServiceBusSenderClient sender = new ServiceBusClientBuilder().connectionString(logApps)
				.sender()
				.queueName(DESTINATION_NAME_IBS)
				.buildClient();

		ServiceBusSessionReceiverClient receiver = new ServiceBusClientBuilder().connectionString(logApps)
				.sessionReceiver()
				.queueName(RESPONSE_NAME_IBS)
				.buildClient();
		
		Object resp = lg.consume("IBS", data.getMetadata(), objs.ibsConsulta(data, null, IbsConsultaRequest.class), ibsServiceIdConsulta, sender, receiver, IbsConsultaRequest.class);
		
		Assertions.assertEquals(ErrorResponse.class, resp.getClass());
	}
	
	@Test
	void consumeTestErrorWithoutMetadata() throws Exception{
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		logger.info(new JSONObject(this.data).toString());
		
		Mockito.when(queueServices.receiverFormQueue(Mockito.any(), Mockito.anyString()))
		.thenReturn(ConstantJsonIbs.IBS_ERROR_RESPONSE_WITHOUT_META);
		
		ServiceBusSenderClient sender = new ServiceBusClientBuilder().connectionString(logApps)
				.sender()
				.queueName(DESTINATION_NAME_IBS)
				.buildClient();

		ServiceBusSessionReceiverClient receiver = new ServiceBusClientBuilder().connectionString(logApps)
				.sessionReceiver()
				.queueName(RESPONSE_NAME_IBS)
				.buildClient();
		
		Object resp = lg.consume("IBS", data.getMetadata(), objs.ibsConsulta(data, null, IbsConsultaRequest.class), ibsServiceIdConsulta, sender, receiver, IbsConsultaRequest.class);
		
		Assertions.assertEquals(ErrorResponse.class, resp.getClass());
	}
	
	@Test
	void consumeTestErrorWithMetadata() throws Exception {
		this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
		logger.info(new JSONObject(this.data).toString());
		
		Mockito.when(queueServices.receiverFormQueue(Mockito.any(), Mockito.anyString()))
		.thenReturn(ConstantJsonIbs.JSON_ERROR);
		
		ServiceBusSenderClient sender = new ServiceBusClientBuilder().connectionString(logApps)
				.sender()
				.queueName(DESTINATION_NAME_IBS)
				.buildClient();

		ServiceBusSessionReceiverClient receiver = new ServiceBusClientBuilder().connectionString(logApps)
				.sessionReceiver()
				.queueName(RESPONSE_NAME_IBS)
				.buildClient();
		
		Object resp = lg.consume("IBS", data.getMetadata(), objs.ibsConsulta(data, null, IbsConsultaRequest.class), ibsServiceIdConsulta, sender, receiver, IbsConsultaRequest.class);
		
		Assertions.assertEquals(ErrorResponse.class, resp.getClass());
	}

}