package com.puntos.models.request.ibs.consulta;

import com.puntos.models.HeaderRequest;

public class IbsConsultaRequestData{

	private HeaderRequest header;
	private IbsConsultaRequestBody body;
	
	public IbsConsultaRequestData() {

	}

	public IbsConsultaRequestData(HeaderRequest header, IbsConsultaRequestBody body) {
		this.header = header;
		this.body = body;
	}
	
	public HeaderRequest getHeader() {
		return header;
	}

	public void setHeader(HeaderRequest header) {
		this.header = header;
	}

	public IbsConsultaRequestBody getBody() {
		return body;
	}

	public void setBody(IbsConsultaRequestBody body) {
		this.body = body;
	}
}