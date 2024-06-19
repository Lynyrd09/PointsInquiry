package com.puntos.models.response.ork.consulta;

import com.puntos.models.HeaderResponse;
import com.puntos.models.response.ibs.consulta.IbsConsultaResponseBody;

public class OrkConsultaResponseData {

	private HeaderResponse header;
	private IbsConsultaResponseBody body;

	public OrkConsultaResponseData() {

	}

	public OrkConsultaResponseData (HeaderResponse header, IbsConsultaResponseBody body) {
		
		this.header = header;
		this.body = body;
		
	}

	public HeaderResponse getHeader() {
		return header;
	}

	public void setHeader(HeaderResponse header) {
		this.header = header;
	}

	public IbsConsultaResponseBody getBody() {
		return body;
	}

	public void setBody(IbsConsultaResponseBody body) {
		this.body = body;
	}

}
