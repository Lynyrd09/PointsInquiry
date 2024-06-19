package com.puntos.models.response.ibs.consulta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puntos.models.HeaderResponse;


class IbsConsultaResponseDataTest {
	private IbsConsultaResponseData data;
	private IbsConsultaResponseBody body;
	private HeaderResponse header;

	@BeforeEach
	void setUp() {
		 body = new IbsConsultaResponseBody();
		 header = new HeaderResponse();
		 data = new IbsConsultaResponseData();
	}
	@Test
	void testGetHeader() {
		data.setHeader(header);
		assertNotNull(data.getHeader());
		
	}

	@Test
	void testSetHeader() {
		data.setHeader(header);
		assertEquals(header,data.getHeader());
	}

	@Test
	void testGetBody() {
		data.setBody(body);
		assertNotNull(data.getBody());
	}

	@Test
	void testSetBody() {
		data.setBody(body);
		assertEquals(body,data.getBody());
	}

}
