package com.puntos.models.response.ibs.consulta;

import java.math.BigDecimal;

public class IbsConsultaResponsePoints {

	private String creditCardAccount;
	private BigDecimal accountCash;
	private BigDecimal accountPoints;
	private BigDecimal accountBonus;
	private BigDecimal accountTotalPoints;
	private BigDecimal accountCashExpiringNextPeriod;
	private BigDecimal accountPointsExpiringNextPeriod;
	private BigDecimal accountBonusExpiringNextPeriod;
	private String accountType;
	private String cardNumber;
	private String accountNumber;
	private String customerNumber;
	private String customFillerInd1;
	private String customFiller1;
	private String customFillerInd2;
	private String customFiller2;


	public String getCreditCardAccount() {
		return (creditCardAccount == null ? "" : creditCardAccount);
	}

	public void setCreditCardAccount(String creditCardAccount) {
		this.creditCardAccount = creditCardAccount;
	}

	public BigDecimal getAccountCash() {
		return (accountCash == null ? new BigDecimal("0.00") : accountCash.setScale(2));
	}

	public void setAccountCash(BigDecimal accountCash) {
		this.accountCash = accountCash;
	}

	public BigDecimal getAccountPoints() {
		return (accountPoints == null ? new BigDecimal("0") : accountPoints);
	}

	public void setAccountPoints(BigDecimal accountPoints) {
		this.accountPoints = accountPoints;
	}

	public BigDecimal getAccountBonus() {
		return (accountBonus == null ? new BigDecimal("0") : accountBonus);
	}

	public void setAccountBonus(BigDecimal accountBonus) {
		this.accountBonus = accountBonus;
	}

	public BigDecimal getAccountCashExpiringNextPeriod() {
		return (accountCashExpiringNextPeriod == null ? new BigDecimal("0.00")
				: accountCashExpiringNextPeriod.setScale(2));
	}

	public void setAccountCashExpiringNextPeriod(BigDecimal accountCashExpiringNextPeriod) {
		this.accountCashExpiringNextPeriod = accountCashExpiringNextPeriod;
	}

	public BigDecimal getAccountPointsExpiringNextPeriod() {
		return (accountPointsExpiringNextPeriod == null ? new BigDecimal("0") : accountPointsExpiringNextPeriod);
	}

	public void setAccountPointsExpiringNextPeriod(BigDecimal accountPointsExpiringNextPeriod) {
		this.accountPointsExpiringNextPeriod = accountPointsExpiringNextPeriod;
	}

	public BigDecimal getAccountBonusExpiringNextPeriod() {
		return (accountBonusExpiringNextPeriod == null ? new BigDecimal("0") : accountBonusExpiringNextPeriod);
	}

	public void setAccountBonusExpiringNextPeriod(BigDecimal accountBonusExpiringNextPeriod) {
		this.accountBonusExpiringNextPeriod = accountBonusExpiringNextPeriod;
	}

	public String getAccountType() {
		return (accountType == null ? "" : accountType);
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAccountTotalPoints() {
		return (accountTotalPoints == null ? new BigDecimal("0") : accountTotalPoints);
	}

	public void setAccountTotalPoints(BigDecimal accountTotalPoints) {
		this.accountTotalPoints = accountTotalPoints;
	}

	public String getCardNumber() {
		return (cardNumber == null ? "" : cardNumber);
	}

	public String getAccountNumber() {
		return (accountNumber == null ? "" : accountNumber);
	}

	public String getCustomerNumber() {
		return (customerNumber == null ? "" : customerNumber);
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomFillerInd1() {
		return customFillerInd1 == null ? "" : customFillerInd1;
	}

	public String getCustomFiller1() {
		return customFiller1 == null ? "" : customFiller1;
	}

	public String getCustomFillerInd2() {
		return customFillerInd2 == null ? "" : customFillerInd2;
	}

	public String getCustomFiller2() {
		return customFiller2 == null ? "" : customFiller2;
	}

	public void setCustomFillerInd1(String customFillerInd1) {
		this.customFillerInd1 = customFillerInd1;
	}

	public void setCustomFiller1(String customFiller1) {
		this.customFiller1 = customFiller1;
	}

	public void setCustomFillerInd2(String customFillerInd2) {
		this.customFillerInd2 = customFillerInd2;
	}

	public void setCustomFiller2(String customFiller2) {
		this.customFiller2 = customFiller2;
	}

}
