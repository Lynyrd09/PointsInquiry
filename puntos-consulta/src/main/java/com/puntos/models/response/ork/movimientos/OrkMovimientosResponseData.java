package com.puntos.models.response.ork.movimientos;

import java.util.List;

import com.puntos.models.HeaderResponse;
import com.puntos.models.response.ibs.movimientos.IbsMovimientoResponseBody;

public class OrkMovimientosResponseData {

	private HeaderResponse header;
	private List<IbsMovimientoResponseBody> body;
	
	public OrkMovimientosResponseData() {

	}

	public OrkMovimientosResponseData(HeaderResponse header, List<IbsMovimientoResponseBody> body) {

		this.header = header;
		this.body = body;

	}

	public HeaderResponse getHeader() {
		return header;
	}

	public void setHeader(HeaderResponse header) {
		this.header = header;
	}

	public List<IbsMovimientoResponseBody> getBody() {
		return body;
	}

	public void setBody(List<IbsMovimientoResponseBody> body) {
		this.body = body;
	}


}
