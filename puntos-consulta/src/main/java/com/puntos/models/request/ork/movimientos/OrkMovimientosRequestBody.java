package com.puntos.models.request.ork.movimientos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.puntos.validator.CustomPropertyMovimientos;

@CustomPropertyMovimientos
public class OrkMovimientosRequestBody{

	private String accountNumber;
	private String cardNumber;
	private String megamileType;
	private String customerNumber;
	
	@NotNull(message = "El campo startDate es requerido")
	@NotBlank(message = "El campo startDate no puede ir en blanco")
	@Pattern(regexp = "\\d{4}\\d{2}\\d{2}", message = "El campo startDate solo acepta caracteres numericos y con el formato YYYYMMDD")
	@Size(min = 8, max = 8, message = "El campo startDate debe tener {max} caracteres (YYYYMMDD)")
	private String startDate;
	
	@NotNull(message = "El campo finishDate es requerido")
	@NotBlank(message = "El campo finishDate no puede ir en blanco")
	@Pattern(regexp = "\\d{4}\\d{2}\\d{2}", message = "El campo finishDate solo acepta caracteres numericos y con el formato YYYYMMDD")
	@Size(min = 8, max = 8, message = "El campo finishDate debe tener {max} caracteres (YYYYMMDD)")
	private String finishDate;
	
	private String customFillerInd1;
	private String customFiller1;
	private String customFillerInd2;
	private String customFiller2;
	

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getMegamileType() {
		return megamileType;
	}
 
	public String getCustomerNumber() {
		return customerNumber;
	}


	public String getCustomFillerInd1() {
		return customFillerInd1;
	}
 
	public String getCustomFiller1() {
		return customFiller1;
	}

	public String getCustomFillerInd2() {
		return customFillerInd2;
	}

	public String getCustomFiller2() {
		return customFiller2;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
    
	public void setMegamileType(String megamileType) {
		this.megamileType = megamileType;
	}
   
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	

}
