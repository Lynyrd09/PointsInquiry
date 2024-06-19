package com.puntos.models.request.ibs.movimientos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puntos.models.request.ork.movimientos.OrkMovimientosRequestBody;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;



class IbsMovimientoRequestBodyTest {
	
	private IbsMovimientoRequestBody ibMRq;
	private OrkMovimientosRequestBody orkMovRq;
	private CmcConcuentasResponse cmc;
	
	@BeforeEach
	void setUp() {
		
		orkMovRq = new OrkMovimientosRequestBody();
		orkMovRq.setAccountNumber("003000787435");
		orkMovRq.setCardNumber("00000132452405010");
		orkMovRq.setMegamileType("02");
		orkMovRq.setCustomerNumber("001465733");
		orkMovRq.setStartDate("20210101");
		orkMovRq.setFinishDate("20210101");

		orkMovRq.setCustomFillerInd1("");
		orkMovRq.setCustomFiller1("");
		orkMovRq.setCustomFillerInd2("");
		orkMovRq.setCustomFiller2("");
		ibMRq = new IbsMovimientoRequestBody(orkMovRq, cmc);

	}

	@Test
	void testSetAccountNumber() {
		ibMRq.setAccountNumber(orkMovRq.getAccountNumber());
		assertNotNull(ibMRq.toString());
	}
	
	@Test
	void testSetCardNumber() {
		ibMRq.setCardNumber(orkMovRq.getCardNumber());
		assertNotNull(ibMRq.toString());
	}

	@Test
	void testSetMegamileType() {
		ibMRq.setMegamileType(orkMovRq.getMegamileType());
		assertNotNull(ibMRq.toString());
	}

	@Test
	void testSetCustomerNumber() {
		ibMRq.setCustomerNumber(orkMovRq.getCustomerNumber());
		assertNotNull(ibMRq.toString());
	}

	@Test
	void testSetStartDate() {
		ibMRq.setStartDate(orkMovRq.getStartDate());
		assertNotNull(ibMRq.toString());
	}

	@Test
	void testSetFinishDate() {
		ibMRq.setFinishDate(orkMovRq.getFinishDate());
		assertNotNull(ibMRq.toString());
	}

}
