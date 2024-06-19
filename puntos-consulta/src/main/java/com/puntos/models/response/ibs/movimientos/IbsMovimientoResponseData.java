package com.puntos.models.response.ibs.movimientos;

import java.util.List;

public class IbsMovimientoResponseData {
	
	private List<IbsMovimientoResponseBody> body;

	public List<IbsMovimientoResponseBody> getBody() {
		return body;
	}

	public void setBody(List<IbsMovimientoResponseBody> body) {
		this.body = body;
	}

}
