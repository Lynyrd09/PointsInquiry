package com.puntos.models.request.ork.consulta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import com.puntos.models.HeaderRequest;

public class OrkConsultaRequestData {
	@Valid
	@NotNull(message = "El campo header es requerido")
	private HeaderRequest header;
	
	@Valid
	@NotNull(message = "El campo body es requerido")
	private OrkConsultaRequestBody body;

	public HeaderRequest getHeader() {
		return header;
	}

	public void setHeader(HeaderRequest header) {
		this.header = header;
	}

	public OrkConsultaRequestBody getBody() {
		return body;
	}

	public void setBody(OrkConsultaRequestBody body) {
		this.body = body;
	}
}
