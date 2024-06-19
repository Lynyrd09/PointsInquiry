package com.puntos.models.response.ibs.movimientos;

import com.puntos.models.Metadata;

public class IbsMovimientoResponse {

	private Metadata metadata;
	private IbsMovimientoResponseData data;

	public Metadata getMetadata() {
		return metadata;
	}

	public IbsMovimientoResponseData getData() {
		return data;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public void setData(IbsMovimientoResponseData data) {
		this.data = data;
	}

}
