package com.puntos.models.request.ibs.movimientos;

import com.puntos.models.HeaderRequest;

public class IbsMovimientoRequestData {

	private HeaderRequest header;
	private IbsMovimientoRequestBody body;

	public IbsMovimientoRequestData(HeaderRequest header, IbsMovimientoRequestBody body) {
		this.header = header;
		this.body = body;
	}

	public HeaderRequest getHeader() {
		return header;
	}

	public IbsMovimientoRequestBody getBody() {
		return body;
	}

	public void setHeader(HeaderRequest header) {
		this.header = header;
	}

	public void setBody(IbsMovimientoRequestBody body) {
		this.body = body;
	}

}
