package com.puntos.models.request.ork.consulta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import com.puntos.models.Metadata;
import com.puntos.validator.CustomPropertySqlInjection;

@CustomPropertySqlInjection
public class OrkConsultaRequest {
	@Valid
	@NotNull(message = "El campo metadata es requerido")
	private Metadata metadata;
	
	@Valid
	@NotNull(message = "El campo data es requerido")
	private OrkConsultaRequestData data;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public OrkConsultaRequestData getData() {
		return data;
	}

	public void setData(OrkConsultaRequestData data) {
		this.data = data;
	}
}
