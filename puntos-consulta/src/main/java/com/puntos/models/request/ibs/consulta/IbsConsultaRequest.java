package com.puntos.models.request.ibs.consulta;

import com.puntos.models.Metadata;

public class IbsConsultaRequest{
	
	private Metadata metadata;
	private IbsConsultaRequestData data;
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public IbsConsultaRequestData getData() {
		return data;
	}
	public void setData(IbsConsultaRequestData data) {
		this.data = data;
	}
	
}