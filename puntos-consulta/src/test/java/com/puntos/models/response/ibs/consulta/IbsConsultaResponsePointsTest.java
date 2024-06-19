package com.puntos.models.response.ibs.consulta;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IbsConsultaResponsePointsTest {
	
	private IbsConsultaResponsePoints points;

	BigDecimal accountCash = new BigDecimal(0.00);
	BigDecimal accountPoints = new BigDecimal(50.00);
	BigDecimal customerCash = new BigDecimal(50.00);
	BigDecimal accountBonus = new BigDecimal(50.00);
	BigDecimal accountTotalPoints = new BigDecimal(50.00);
	BigDecimal accountCashExpiringNextPeriod = new BigDecimal(50.00);
	BigDecimal accountPointsExpiringNextPeriod = new BigDecimal(50.00);
	BigDecimal accountBonusExpiringNextPeriod = new BigDecimal(50.00);

	
	@BeforeEach
	void setUp() {
		points = new IbsConsultaResponsePoints();  
	}

	@Test
	void testGetCreditCardAccount() {
		assertNotNull(points.getCreditCardAccount());
	}

	@Test
	void testSetCreditCardAccount() {
		String creditCardAccount = "";
		points.setCreditCardAccount(creditCardAccount);
		assertSame(creditCardAccount, points.getCreditCardAccount());
	}

	@Test
	void testGetAccountCash() {
		assertNotNull(points.getAccountCash());
	}

	@Test
	void testSetAccountCash() {
	   points.setAccountCash(accountCash);
	   assertNotNull(points.getAccountCash());
	}

	@Test
	void testGetAccountPoints() {
		assertNotNull(points.getAccountPoints());
	}

	@Test
	void testSetAccountPoints() {
		   points.setAccountPoints(accountPoints);
		   assertNotNull(points.getAccountPoints());
	}

	@Test
	void testGetAccountBonus() {
		assertNotNull(points.getAccountBonus());
	}

	@Test
	void testSetAccountBonus() {
		   points.setAccountBonus(accountBonus);
		   assertNotNull(points.getAccountBonus());
    }

	@Test
	void testGetAccountCashExpiringNextPeriod() {
		assertNotNull(points.getAccountCashExpiringNextPeriod());
	}

	@Test
	void testSetAccountCashExpiringNextPeriod() {
		   points.setAccountCashExpiringNextPeriod(accountCashExpiringNextPeriod);
		   assertNotNull(points.getAccountCashExpiringNextPeriod());
	}

	@Test
	void testGetAccountPointsExpiringNextPeriod() {
		assertNotNull(points.getAccountPointsExpiringNextPeriod());
	}

	@Test
	void testSetAccountPointsExpiringNextPeriod() {
		  points.setAccountPointsExpiringNextPeriod(accountPointsExpiringNextPeriod);
		   assertNotNull(points.getAccountCashExpiringNextPeriod());
	}

	@Test
	void testGetAccountBonusExpiringNextPeriod() {
		assertNotNull(points.getAccountBonusExpiringNextPeriod());
	}

	@Test
	void testSetAccountBonusExpiringNextPeriod() {
		  points.setAccountBonusExpiringNextPeriod(accountBonusExpiringNextPeriod);
		   assertNotNull(points.getAccountBonusExpiringNextPeriod());
	}

	@Test
	void testGetAccountType() {
		assertNotNull(points.getAccountType());
	}

	@Test
	void testSetAccountType() {
		String accountType = "";
		points.setAccountType(accountType);
		assertSame(accountType, points.getAccountType());
	}

	@Test
	void testGetAccountTotalPoints() {
		assertNotNull(points.getAccountTotalPoints());
	}

	@Test
	void testSetAccountTotalPoints() {
		  points.setAccountTotalPoints(accountTotalPoints);
		   assertNotNull(points.getAccountTotalPoints());
	}

	@Test
	void testGetCardNumber() {
		assertNotNull(points.getCardNumber());
	}

	@Test
	void testGetAccountNumber() {
		assertNotNull(points.getAccountNumber());
	}

	@Test
	void testGetCustomerNumber() {
		assertNotNull(points.getCustomerNumber());
	}

	@Test
	void testSetCardNumber() {
		String cardNumber = "";
		points.setCardNumber(cardNumber);
		assertSame(cardNumber, points.getCardNumber());

	}

	@Test
	void testSetAccountNumber() {
		String accountNumber = "";
		points.setAccountNumber(accountNumber);
		assertSame(accountNumber, points.getAccountNumber());
	}

	@Test
	void testSetCustomerNumber() {
		String customerNumber = "";
		points.setCustomerNumber(customerNumber);
		assertSame(customerNumber, points.getCustomerNumber());
	}

	@Test
	void testGetCustomFillerInd1() {
		assertNotNull(points.getCustomFillerInd1());
	}

	@Test
	void testGetCustomFiller1() {
		assertNotNull(points.getCustomFiller1());
	}

	@Test
	void testGetCustomFillerInd2() {
		assertNotNull(points.getCustomFillerInd2());
	}

	@Test
	void testGetCustomFiller2() {
		assertNotNull(points.getCustomFiller2());
	}

	@Test
	void testSetCustomFillerInd1() {
		String customFillerInd1 = "";
		points.setCustomFillerInd1(customFillerInd1);
		assertSame(customFillerInd1, points.getCustomFillerInd1());
	}

	@Test
	void testSetCustomFiller1() {
		String customFiller1 = "";
		points.setCustomFiller1(customFiller1);
		assertSame(customFiller1, points.getCustomFiller1());
	}

	@Test
	void testSetCustomFillerInd2() {
		String customFillerInd2 = "";
		points.setCustomFillerInd2(customFillerInd2);
		assertSame(customFillerInd2, points.getCustomFillerInd2());
	}

	@Test
	void testSetCustomFiller2() {
		String customFiller2 = "";
		points.setCustomFiller2(customFiller2);
		assertSame(customFiller2, points.getCustomFiller2());
	}

}
