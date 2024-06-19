package com.puntos.models.response.ibs.movimientos;

public class IbsMovimientoResponseBody {

	private String expirationDate;
	private String transactionType;
	private String transactionDescription;
	private String movementType;
	private String movementDescription;
	private String sign;
	private String movementPoints;
	private String moventsDes;
	private String startPoints;
	private String accountNumber;
	private String office;
	private String productType;
	private String cardNumberT;
	private String creditCardAccount;
	private String customFillerInd1;
	private String customFiller1;
	private String customFillerInd2;
	private String customFiller2;
	
	public String getExpirationDate() {
		return expirationDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public String getMovementType() {
		return movementType;
	}

	public String getMovementDescription() {
		return movementDescription;
	}

	public String getSign() {
		return sign;
	}

	public String getMovementPoints() {
		return movementPoints;
	}

	public String getMoventsDes() {
		return moventsDes;
	}

	public String getStartPoints() {
		return startPoints;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getOffice() {
		return office;
	}

	public String getProductType() {
		return productType;
	}

	public String getCardNumberT() {
		return cardNumberT;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public void setMovementDescription(String movementDescription) {
		this.movementDescription = movementDescription;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setMovementPoints(String movementPoints) {
		this.movementPoints = movementPoints;
	}

	public void setMoventsDes(String moventsDes) {
		this.moventsDes = moventsDes;
	}

	public void setStartPoints(String startPoints) {
		this.startPoints = startPoints;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setCardNumberT(String cardNumberT) {
		this.cardNumberT = cardNumberT;
	}
	
	public String getCreditCardAccount() {
		return creditCardAccount == null ? "" : creditCardAccount;
	}

	public String getCustomFillerInd1() {
		return customFillerInd1  == null ? "" : customFillerInd1;
	}

	public String getCustomFiller1() {
		return customFiller1  == null ? "" : customFiller1;
	}

	public String getCustomFillerInd2() {
		return customFillerInd2  == null ? "" : customFillerInd2;
	}

	public String getCustomFiller2() {
		return customFiller2  == null ? "" : customFiller2;
	}

	public void setCreditCardAccount(String creditCardAccount) {
		this.creditCardAccount = creditCardAccount;
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
