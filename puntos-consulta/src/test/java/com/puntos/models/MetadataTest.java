package com.puntos.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MetadataTest {
	private Metadata metadata;

	@BeforeEach
	void setUp() {
		metadata = new Metadata();
	}

	@Test
	void testSetAndGetMessageType() {
		String messageType = "type1";
		metadata.setMessageType(messageType);
		assertEquals(messageType, metadata.getMessageType());
	}

	@Test
	void testSetAndGetMessageId() {
		String messageId = "id1";
		metadata.setMessageId(messageId);
		assertEquals(messageId, metadata.getMessageId());
	}

	@Test
	void testSetAndGetMessageIdOrg() {
		String messageIdOrg = "idOrg1";
		metadata.setMessageIdOrg(messageIdOrg);
		assertEquals(messageIdOrg, metadata.getMessageIdOrg());
	}

	@Test
	void testSetAndGetShortMessageId() {
		String shortMessageId = "shortId1";
		metadata.setShortMessageId(shortMessageId);
		assertEquals(shortMessageId, metadata.getShortMessageId());
	}

	@Test
	void testSetAndGetApplicationId() {
		String applicationId = "appId1";
		metadata.setApplicationId(applicationId);
		assertEquals(applicationId, metadata.getApplicationId());
	}

	@Test
	void testSetAndGetServiceId() {
		String serviceId = "serviceId1";
		metadata.setServiceId(serviceId);
		assertEquals(serviceId, metadata.getServiceId());
	}

	@Test
	void testSetAndGetDatetime() {
		String datetime = "2023-05-22T15:30:00";
		metadata.setDatetime(datetime);
		assertEquals(datetime, metadata.getDatetime());
	}
}
