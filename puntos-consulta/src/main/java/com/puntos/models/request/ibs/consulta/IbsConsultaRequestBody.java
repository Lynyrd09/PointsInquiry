package com.puntos.models.request.ibs.consulta;

import java.util.ArrayList;
import java.util.List;

import com.puntos.models.request.ork.consulta.OrkConsultaRequestBody;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;

public class IbsConsultaRequestBody {

	private String queryId;
	private String inquiryType;
	private List<String> customerNumber;
	private List<String> accountNumber;
	private List<String> cardNumber;
	private String pageNumber;
	private String recordsPerPage;
	
	public IbsConsultaRequestBody() {

	}

	public IbsConsultaRequestBody(OrkConsultaRequestBody data, Object data2) {

		this.queryId = data.getQueryId();
		this.inquiryType = data.getInquiryType();
		this.customerNumber = data.getCustomerNumber();
		this.accountNumber = data.getAccountNumber();
		if ( data2 != null) {
			List<String> listaBody = new ArrayList<>();
			for (int i = 0; i < ((CmcConcuentasResponse)data2).getData().getBody().size(); i++) {
				listaBody.add(((CmcConcuentasResponse)data2).getData().getBody().get(i).getAccountNumber());
			}
			this.cardNumber = listaBody;

		} else {
			this.cardNumber = data.getCardNumber();
		}
		this.customerNumber = data.getCustomerNumber();
		this.pageNumber = data.getPageNumber();
		this.recordsPerPage = data.getRecordsPerPage();

	}
	
	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public String getRecordsPerPage() {
		return recordsPerPage;
	}

	public List<String> getCardNumber() {
		return cardNumber;
	}

	public List<String> getCustomerNumber() {
		return customerNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public void setCardNumber(List<String> cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setCustomerNumber(List<String> customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getInquiryType() {
		return inquiryType;
	}

	public List<String> getAccountNumber() {
		return accountNumber;
	}

	public void setInquiryType(String inquiryType) {
		this.inquiryType = inquiryType;
	}

	public void setAccountNumber(List<String> accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
