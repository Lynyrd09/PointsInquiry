package com.puntos.models.request.ork.movimientos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import com.puntos.models.HeaderRequest;

public class OrkMovimientosRequestData {
	@Valid
	@NotNull(message = "El campo header es requerido")
	private HeaderRequest header;
	
	@Valid
	@NotNull(message = "El campo body es requerido")
	private OrkMovimientosRequestBody body;

	public HeaderRequest getHeader() {
		return header;
	}

	public void setHeader(HeaderRequest header) {
		this.header = header;
	}

	public OrkMovimientosRequestBody getBody() {
		return body;
	}

	public void setBody(OrkMovimientosRequestBody body) {
		this.body = body;
	}
}
