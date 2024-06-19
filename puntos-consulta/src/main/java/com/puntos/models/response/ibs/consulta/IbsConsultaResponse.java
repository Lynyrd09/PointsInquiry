package com.puntos.models.response.ibs.consulta;

import com.puntos.models.Metadata;

public class IbsConsultaResponse {
	
	private Metadata metadata;
	private IbsConsultaResponseData data;
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public IbsConsultaResponseData getData() {
		return data;
	}
	public void setData(IbsConsultaResponseData data) {
		this.data = data;
	}
}

