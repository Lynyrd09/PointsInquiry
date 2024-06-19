package com.puntos.models.request.ibs.movimientos;

import com.puntos.models.Metadata;

public class IbsMovimientoRequest {

	private Metadata metadata;
	private IbsMovimientoRequestData data;

	public Metadata getMetadata() {
		return metadata;
	}

	public IbsMovimientoRequestData getData() {
		return data;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public void setData(IbsMovimientoRequestData data) {
		this.data = data;
	}

}
