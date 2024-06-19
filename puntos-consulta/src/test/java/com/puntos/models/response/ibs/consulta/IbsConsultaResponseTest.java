package com.puntos.models.response.ibs.consulta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puntos.models.Metadata;

class IbsConsultaResponseTest {
	
	private IbsConsultaResponse ibsConRes;
	private Metadata metadata;
	private IbsConsultaResponseData data;

	@BeforeEach
	void setUp() {
		ibsConRes = new IbsConsultaResponse();
		metadata = new Metadata();
		data = new IbsConsultaResponseData();
	}

	@Test
	void testGetMetadata() {
		ibsConRes.setMetadata(ibsConRes.getMetadata());
		assertNotNull(ibsConRes.toString());
	}

	@Test
	void testSetMetadata() {
		ibsConRes.setMetadata(metadata);
		assertNotNull(ibsConRes.toString());
	}

	@Test
	void testGetData() {
		ibsConRes.setData(ibsConRes.getData());
		assertNotNull(ibsConRes.toString());
	}

	@Test
	void testSetData() {
		ibsConRes.setData(data);
		assertNotNull(ibsConRes.toString());
	}

}
