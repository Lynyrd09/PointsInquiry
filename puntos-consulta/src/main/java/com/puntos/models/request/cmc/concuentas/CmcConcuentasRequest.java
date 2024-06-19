package com.puntos.models.request.cmc.concuentas;

import com.puntos.models.Metadata;

public class CmcConcuentasRequest {

	private Metadata metadata;
	private CmcConcuentasRequestData data;
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public CmcConcuentasRequestData getData() {
		return data;
	}
	public void setData(CmcConcuentasRequestData data) {
		this.data = data;
	}
	
}
