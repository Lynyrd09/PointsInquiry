package com.puntos.models.response.ibs.consulta;

import com.puntos.models.HeaderResponse;

public class IbsConsultaResponseData {

	private IbsConsultaResponseBody body;
	private HeaderResponse header;

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
