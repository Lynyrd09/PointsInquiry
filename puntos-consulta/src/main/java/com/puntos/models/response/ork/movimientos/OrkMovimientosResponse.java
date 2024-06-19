     package com.puntos.models.response.ork.movimientos;

import com.puntos.models.Metadata;

public class OrkMovimientosResponse {

	private Metadata metadata;
	private OrkMovimientosResponseData data;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public OrkMovimientosResponseData getData() {
		return data;
	}

	public void setData(OrkMovimientosResponseData data) {
		this.data = data;
	}
}
