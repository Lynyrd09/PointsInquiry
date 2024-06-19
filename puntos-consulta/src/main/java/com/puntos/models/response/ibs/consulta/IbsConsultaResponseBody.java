package com.puntos.models.response.ibs.consulta;

import java.math.BigDecimal;
import java.util.List;

public class IbsConsultaResponseBody{

	private String queryId;
	private String pageNumber;
	private String recordsPerPage;
	private String totalRecords;
	private String totalPages;
	private BigDecimal customerPoints;
	private BigDecimal customerPointsExpiringNextPeriod;
	private BigDecimal customerCash;
	private String responseCode;
	private String responseDescription;
	private String cardHolder;
	private List<IbsConsultaResponsePoints> points;


	public String getCardHolder() {
		return (cardHolder == null ? "" : cardHolder);
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public BigDecimal getCustomerPoints() {
		return (customerPoints == null ? new BigDecimal("0") : customerPoints);
	}

	public void setCustomerPoints(BigDecimal customerPoints) {
		this.customerPoints = customerPoints;
	}

	public BigDecimal getCustomerPointsExpiringNextPeriod() {
		return (customerPointsExpiringNextPeriod == null ? new BigDecimal("0") : customerPointsExpiringNextPeriod);
	}

	public void setCustomerPointsExpiringNextPeriod(BigDecimal customerPointsExpiringNextPeriod) {
		this.customerPointsExpiringNextPeriod = customerPointsExpiringNextPeriod;
	}

	public BigDecimal getCustomerCash() {
		return (customerCash == null ? new BigDecimal("0.00") : customerCash.setScale(2));
	}

	public void setCustomerCash(BigDecimal customerCash) {
		this.customerCash = customerCash;
	}
	
	public String getTotalPages() {
		return (totalPages == null ? "" : totalPages);
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getResponseCode() {
		return (responseCode == null ? "" : responseCode);
	}

	public String getResponseDescription() {
		return (responseDescription == null ? "" : responseDescription);
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public List<IbsConsultaResponsePoints> getPoints() {
		return points;
	}

	public void setPoints(List<IbsConsultaResponsePoints> points) {
		this.points = points;
	}

	public String getPageNumber() {
		return (pageNumber  == null ? "" : pageNumber);
	}

	public String getRecordsPerPage() {
		return (recordsPerPage  == null ? "" : recordsPerPage);
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public String getTotalRecords() {
		return (totalRecords  == null ? "" : totalRecords);
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

}
