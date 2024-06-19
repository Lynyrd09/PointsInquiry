package com.puntos.models.response.ibs.consulta;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class IbsConsultaResponseBodyTest {
	
	private IbsConsultaResponseBody body;
	List<IbsConsultaResponsePoints> points = new ArrayList<>();
	BigDecimal CustomerPoint = new BigDecimal(1.00);
	BigDecimal CustomerPointsExpiringNextPeriod = new BigDecimal(50.00);
	BigDecimal CustomerCash = new BigDecimal(50.00);
	
	@BeforeEach
	void setUp() {
		 body = new IbsConsultaResponseBody();
		 body.setQueryId("");
		 body.setPageNumber("");
		 body.setRecordsPerPage("");
		 body.setTotalRecords("");
		 body.setTotalPages("");
		 body.setCustomerPoints(CustomerPoint);
		 body.setCustomerPointsExpiringNextPeriod(CustomerPointsExpiringNextPeriod);
		 body.setCustomerCash(CustomerCash);
		 body.setResponseCode("");
		 body.setResponseDescription("");
		 body.setCardHolder("12345");
		 body.setPoints(points);
		 
		  
	}
	
	@Test
	void testGetCardHolder() {
		assertNotNull(body.getCardHolder());
	}

	@Test
	void testSetCardHolder() {
		String cardHolder = "12345";
		assertSame(cardHolder, body.getCardHolder().toString());
	}
	
	@Test
	void testGetCustomerPoints() {
		assertNotNull(body.getCustomerPoints());
	}

	@Test
	void testSetCustomerPoints() {
		body.setCustomerPoints(CustomerPoint);
		assertSame(CustomerPoint, body.getCustomerPoints());
	}

	@Test
	void testGetCustomerPointsExpiringNextPeriod() {
		assertNotNull(body.getCustomerPointsExpiringNextPeriod());
	}

	@Test
	void testSetCustomerPointsExpiringNextPeriod() {
		body.setCustomerPointsExpiringNextPeriod(CustomerPointsExpiringNextPeriod);
		assertSame(CustomerPointsExpiringNextPeriod, body.getCustomerPointsExpiringNextPeriod());
	}

	@Test
	void testGetCustomerCash() {
		assertNotNull(body.getCustomerCash());
	}
	
	@Test
	void testGetTotalPages() {
		assertNotNull(body.getTotalPages());
	}

	@Test
	void testSetTotalPages() {
		String totalPages = "";
		assertSame(totalPages, body.getTotalPages());
	}

	@Test
	void testGetResponseCode() {
		assertNotNull(body.getResponseCode());
	}

	@Test
	void testGetResponseDescription() {
		assertNotNull(body.getResponseDescription());
	}

	@Test
	void testSetResponseCode() {
		String responseCode = "";
		assertSame(responseCode, body.getResponseCode());
	}

	@Test
	void testSetResponseDescription() {
		String responseDes = "";
		assertSame(responseDes, body.getResponseDescription());
	}

	@Test
	void testGetPoints() {
		assertNotNull(body.getPoints());
	}

	@Test
	void testSetPoints() {
		assertSame(points, body.getPoints());;
	}

	@Test
	void testGetPageNumber() {
		assertNotNull(body.getPageNumber());
	}

	@Test
	void testGetRecordsPerPage() {
		assertNotNull(body.getRecordsPerPage());
	}

	@Test
	void testSetPageNumber() {
		String PageNumber = "";
		assertSame(PageNumber, body.getPageNumber());
	}

	@Test
	void testSetRecordsPerPage() {
		String RecordsPerPage = "";
		assertSame(RecordsPerPage, body.getRecordsPerPage());
	}

	@Test
	void testGetTotalRecords() {
		assertNotNull(body.getTotalRecords());
	}

	@Test
	void testSetTotalRecords() {
		String TotalRecords = "";
		assertSame(TotalRecords, body.getTotalRecords());
	}

	@Test
	void testGetQueryId() {
		assertNotNull(body.getQueryId());
	}

	@Test
	void testSetQueryId() {
		String QueryId = "";
		assertSame(QueryId, body.getQueryId());
	}

}
