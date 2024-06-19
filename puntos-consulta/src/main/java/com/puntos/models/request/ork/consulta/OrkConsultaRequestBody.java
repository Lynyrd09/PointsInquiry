package com.puntos.models.request.ork.consulta;

import java.util.List;

import com.puntos.validator.CustomPropertyConsulta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@CustomPropertyConsulta
public class OrkConsultaRequestBody {
	
	@Valid
	@NotNull(message = "El campo inquiryType es requerido")
	@NotBlank(message = "El campo inquiryType no debe ir en blanco")
	@Pattern(regexp = "(A|C|CN|AC)", message = "El campo inquiryType solo acepta los siguientes caracteres: A, C, CN, AC")
	@Size(min = 1, max = 2, message = "El campo inquiryType debe tener entre {min} a {max} caracteres")
	private String inquiryType;
	
	@Valid
	@NotNull(message = "El campo queryId es requerido")
	@NotBlank(message = "El campo queryId no debe ir en blanco")
	@Pattern(regexp = "^[0-9]+$", message = "El campo queryId solo acepta caracteres numericos")
	@Size(min = 1, max = 16, message = "El campo queryId debe tener entre {min} a {max} caracteres")
	private String queryId;
	
	@Valid
	@NotNull(message = "El campo pageNumber es requerido")
	@NotBlank(message = "El campo pageNumber no debe ir en blanco")
	@Pattern(regexp = "^[0-9]+$", message = "El campo pageNumber solo acepta caracteres numericos")
	@Size(min = 1, max = 5, message = "El campo pageNumber debe tener entre {min} a {max} caracteres")
	private String pageNumber;
	
	@Valid
	@NotNull(message = "El campo recordsPerPage es requerido")
	@NotBlank(message = "El campo recordsPerPage no debe ir en blanco")
	@Pattern(regexp = "^[0-9]+$", message = "El campo recordsPerPage solo acepta caracteres numericos")
	@Size(min = 1, max = 5, message = "El campo recordsPerPage debe tener entre {min} a {max} caracteres")
	private String recordsPerPage;

	@Size(max = 10, message = "El arreglo de campos customerNumber permite un maximo de {max} elementos")
	private List<
	@Valid
	@Pattern(regexp = "^[0-9]+$", message = "El campo customerNumber solo acepta caracteres numericos")
	@Size(min = 1, max = 19, message = "El campo customerNumber debe tener entre {min} a {max} caracteres")
	String> customerNumber;
	
	@Size(max = 10, message = "El arreglo de campos accountNumber permite un maximo de {max} elementos")
	private List<
	@Valid
	@Pattern(regexp = "^[0-9]+$", message = "El campo accountNumber solo acepta caracteres numericos")
	@Size(min = 1, max = 12, message = "El campo accountNumber debe tener entre {min} a {max} caracteres")
	String>accountNumber;
	
	@Size(max = 10, message = "El arreglo de campos cardNumber permite un maximo de {max} elementos")
	private List<
	@Valid
	@Pattern(regexp = "^[0-9]+$", message = "El campo cardNumber solo acepta caracteres numericos")
	@Size(min = 1, max = 19, message = "El campo cardNumber debe tener entre {min} a {max} caracteres")
	String>cardNumber;
	
	
	private String customFillerInd1;
	private String customFiller1;
	private String customFillerInd2;
	private String customFiller2;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public List<String> getCustomerNumber() {
		return customerNumber;
	}
	
	public void setCustomerNumber(List<String> customerNumber) {
		this.customerNumber = customerNumber;
	}

	public List<String> getAccountNumber() {
		return accountNumber;
	}

	public List<String> getCardNumber() {
		return cardNumber;
	}

	public void setAccountNumber(List<String> accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setCardNumber(List<String> cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getPageNumber() {
		return pageNumber;
	}

	public String getInquiryType() {
		return inquiryType;
	}

	public String getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setInquiryType(String inquiryType) {
		this.inquiryType = inquiryType;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public String getCustomFillerInd1() {
		return customFillerInd1;
	}

	public void setCustomFillerInd1(String customFillerInd1) {
		this.customFillerInd1 = customFillerInd1;
	}

	public String getCustomFiller1() {
		return customFiller1;
	}

	public void setCustomFiller1(String customFiller1) {
		this.customFiller1 = customFiller1;
	}

	public String getCustomFillerInd2() {
		return customFillerInd2;
	}

	public void setCustomFillerInd2(String customFillerInd2) {
		this.customFillerInd2 = customFillerInd2;
	}

	public String getCustomFiller2() {
		return customFiller2;
	}

	public void setCustomFiller2(String customFiller2) {
		this.customFiller2 = customFiller2;
	}
	
}
