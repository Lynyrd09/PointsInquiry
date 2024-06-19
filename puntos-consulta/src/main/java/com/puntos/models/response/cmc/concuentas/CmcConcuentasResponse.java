package com.puntos.models.response.cmc.concuentas;

import com.puntos.models.Metadata;

public class CmcConcuentasResponse {

	private Metadata metadata;
	private CmcConcuentasResponseData data;
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public CmcConcuentasResponseData getData() {
		return data;
	}
	public void setData(CmcConcuentasResponseData data) {
		this.data = data;
	}
}
