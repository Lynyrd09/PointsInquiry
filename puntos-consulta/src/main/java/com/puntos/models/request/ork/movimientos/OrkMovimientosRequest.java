package com.puntos.models.request.ork.movimientos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.puntos.models.Metadata;
import com.puntos.validator.CustomPropertySqlInjection;

@CustomPropertySqlInjection
public class OrkMovimientosRequest {
	@Valid
	@NotNull(message = "El campo metadata es requerido")
	private Metadata metadata;
	
	@Valid
	@NotNull(message = "El campo data es requerido")
	private OrkMovimientosRequestData data;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public OrkMovimientosRequestData getData() {
		return data;
	}

	public void setData(OrkMovimientosRequestData data) {
		this.data = data;
	}
}
