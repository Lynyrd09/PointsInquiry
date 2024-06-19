package com.puntos.models.response.ork.consulta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import com.puntos.models.Metadata;

public class OrkConsultaResponse {
	@Valid
	@NotNull(message = "El campo metadata es requerido")
	private Metadata metadata;
	
	@Valid
	@NotNull(message = "El campo data es requerido")
	private OrkConsultaResponseData data;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public OrkConsultaResponseData getData() {
		return data;
	}

	public void setData(OrkConsultaResponseData data) {
		this.data = data;
	}
}
