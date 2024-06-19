package com.puntos.models.request.ibs.movimientos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.puntos.models.HeaderRequest;
import com.puntos.models.Metadata;

class IbsMovimientoRequestTest {
	
	private Metadata metadata;
	private IbsMovimientoRequestData data;
	private HeaderRequest header;
	private IbsMovimientoRequestBody body;
	private IbsMovimientoRequest iMovRq;
	
	@BeforeEach
	void setUp() {
		metadata = new Metadata();
		header = new HeaderRequest();
		data = new IbsMovimientoRequestData(header,body);
		iMovRq = new IbsMovimientoRequest();
	}

	@Test
	void testGetMetadata() {
		iMovRq.setMetadata(metadata);
		assertNotNull(iMovRq.getMetadata());
	}

	@Test
	void testGetData() {
		iMovRq.setData(data);
		assertNotNull(iMovRq.getData());
	}

	@Test
	void testSetMetadata() {
		iMovRq.setMetadata(metadata);
		assertNotNull(iMovRq.getMetadata().toString());
	}

	@Test
	void testSetHeader() {
		data.setHeader(header);
		assertNotNull(data.getHeader().toString());
	}

	@Test
	void testSetBody() {
		data.setBody(body);
		assertNull(data.getBody());
	}
}
