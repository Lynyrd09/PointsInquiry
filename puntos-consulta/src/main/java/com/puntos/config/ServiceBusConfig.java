package com.puntos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusSessionReceiverClient;

@Configuration
@ConfigurationProperties(prefix = "servicebus.conectionstring")
public class ServiceBusConfig {
	private static final String DESTINATION_NAME_VISION = "orquestacion.to.visionplus.integracion.req.lq";
	private static final String DESTINATION_NAME_IBS  = "orquestacion.to.babroker.integracion.req.lq";
	private static final String RESPONSE_NAME_VISION = "visionplus.to.orquestacion.integracion.rsp.lq";
	private static final String RESPONSE_NAME_IBS = "babroker.to.orquestacion.integracion.rsp.lq";
	
	private String logApps;

	@Bean
	public ServiceBusClientBuilder builder() {
		return new ServiceBusClientBuilder().connectionString(logApps);
	}
	
	@Bean
	public ServiceBusSenderClient senderVision() {

		return builder()
				.sender()
				.queueName(DESTINATION_NAME_VISION)
				.buildClient();
	}
	
	@Bean
	public ServiceBusSessionReceiverClient receiverVision() {
		return builder()
				.sessionReceiver()
				.queueName(RESPONSE_NAME_VISION)
				.buildClient();
	}
	
	@Bean
	public ServiceBusSenderClient senderIbs() {

		return builder()
				.sender()
				.queueName(DESTINATION_NAME_IBS)
				.buildClient();
	}
	
	@Bean
	public ServiceBusSessionReceiverClient receiverIbs() {
		return builder()
				.sessionReceiver()
				.queueName(RESPONSE_NAME_IBS)
				.buildClient();
	}

	public String getLogApps() {
		return logApps;
	}

	public void setLogApps(String logApps) {
		this.logApps = logApps;
	}
}
