package com.puntos.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puntos.service.utils.QueueServices;
import com.puntos.utils.ConstantJsonIbs;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(QueueServices.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@ActiveProfiles({ "test" })
class QueueServicesTest {
	private ObjectMapper mapper = new ObjectMapper();
	
	private static final String DESTINATION_NAME_IBS  = "orquestacion.to.babroker.integracion.req.lq";
	private static final String RESPONSE_NAME_IBS = "babroker.to.orquestacion.integracion.rsp.lq";
	private String logApps = "Endpoint=sb://sb-ba-development.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=5wpoptRm5GcW31HKBzXoPULC9fYLVkZSRuZ7HAO0eCc=";
	String timeOut = "5";
	String ibsServiceId = "9000642";
	
	@Autowired Environment environment;
	
	@InjectMocks private QueueServices queue;
	
	@BeforeEach
	public void setUp() {
		this.logApps = environment.getProperty("servicebus.conectionstring.logapps");
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ReflectionTestUtils.setField(queue, "timeOutReceiverClient", timeOut);
	}
	
	@Test
	void sendToQueueTest() {
		String randomSessionId = RandomStringUtils.random(32, true, true).toUpperCase();
		
		ServiceBusSenderClient sender = new ServiceBusClientBuilder().connectionString(logApps)
										.sender()
										.queueName(DESTINATION_NAME_IBS)
										.buildClient();
		
		queue.sendToQueue(sender, randomSessionId, ibsServiceId, ConstantJsonIbs.IBS_REQUEST_CONSULTA);
		
		ServiceBusSessionReceiverClient receiver = new ServiceBusClientBuilder().connectionString(logApps)
				.sessionReceiver()
				.queueName(RESPONSE_NAME_IBS)
				.buildClient();
		
		ServiceBusReceiverClient rec = receiver.acceptSession(randomSessionId);
		
		String rsp = queue.receiverFormQueue(receiver, randomSessionId);
		Assertions.assertEquals(null, rsp);
	}
	
	
}
