package com.puntos.models.response.ibs.movimientos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puntos.models.Metadata;

class IbsMovimientoResponseTest {
	
	private Metadata metadata;
	private IbsMovimientoResponseData data;
	private IbsMovimientoResponseBody body;
	private IbsMovimientoResponse ibMovRes;

	@BeforeEach
	void setUp() {
		
		metadata = new Metadata();
		data = new IbsMovimientoResponseData();
		body = new IbsMovimientoResponseBody();
		ibMovRes = new  IbsMovimientoResponse();
		
		metadata.setMessageType("Request");
		metadata.setMessageId("e13a7e3e-7a59-40ea-b899-5a0f7f02cdcc");
		metadata.setMessageIdOrg("e13a7e3e-7a59-40ea-b899-5a0f7f02cdcc");
		metadata.setShortMessageId("e13a7e3e-7a59-4");
		metadata.setApplicationId("Ebanca");
		metadata.setServiceId("MovimientoAgencias");
		metadata.setDatetime("2020-03-31T18:59:00");
		
		body.setAccountNumber(null);
		body.setCardNumberT(null);
		body.setExpirationDate(null);
	
		body.setTransactionType(null);
		body.setTransactionDescription(null);
		body.setMovementType(null);
		body.setMovementDescription(null);
		body.setSign(null);
		body.setMovementPoints(null);
		body.setMoventsDes(null);
		body.setStartPoints(null);
		body.setAccountNumber(null);
		body.setOffice(null);
		body.setProductType(null);
		body.setCardNumberT(null);
		body.setCreditCardAccount(null);
		data.setBody(null);
		
	
	}

	@Test
	void testGetMetadata() {
		ibMovRes.setMetadata(ibMovRes.getMetadata());
		assertNotNull(ibMovRes.toString());

	}

	@Test
	void testGetData() {
		
		body.setAccountNumber(body.getAccountNumber());
		assertNotNull(body.toString());
		
		body.setCardNumberT(body.getCardNumberT());
		assertNotNull(body.toString());
		
		body.setExpirationDate(body.getExpirationDate());
		assertNotNull(body.toString());
		
		body.setTransactionType(body.getTransactionType());
		assertNotNull(body.toString());
		
		body.setTransactionDescription(body.getTransactionDescription());
		assertNotNull(body.toString());
		
		body.setMovementType(body.getMovementType());
		assertNotNull(body.toString());
		
		body.setMovementDescription(body.getMovementDescription());
		assertNotNull(body.toString());
		
		body.setSign(body.getSign());
		assertNotNull(body.toString());
		
		body.setMovementPoints(body.getMovementPoints());
		assertNotNull(body.toString());
		
		body.setMoventsDes(body.getMoventsDes());
		assertNotNull(body.toString());
		
		body.setStartPoints(body.getStartPoints());
		assertNotNull(body.toString());
		
		body.setAccountNumber(body.getAccountNumber());
		assertNotNull(body.toString());
		
		body.setOffice(body.getOffice());
		assertNotNull(body.toString());
		
		body.setProductType(body.getProductType());
		assertNotNull(body.toString());
		
		
		body.setCreditCardAccount(body.getCreditCardAccount());
		assertNotNull(body.toString());

	}

	@Test
	void testSetMetadata() {
		List<IbsMovimientoResponseBody> bodyList = new ArrayList<>();
		data.setBody(bodyList);
		assertNotNull(data.toString());
		
	}
	
	@Test
	void testSetMetadata_2() {
		ibMovRes.setMetadata(metadata);
		assertNotNull(ibMovRes.toString());

		
	}

	@Test
	void testSetData() {
		ibMovRes.setData(ibMovRes.getData());
		assertNotNull(ibMovRes.toString());
	}

}
