package com.puntos.models.request.cmc.concuentas;

public class CmcConcuentasRequestData {

	private CmcConcuentasRequestBody body;
	
	public CmcConcuentasRequestData() { }
	
	public CmcConcuentasRequestData(CmcConcuentasRequestBody pagosColectoresBodyRequestCMC) {
		this.body = pagosColectoresBodyRequestCMC;
	}
	
	public CmcConcuentasRequestBody getBody() {
		return body;
	}
	public void setBody(CmcConcuentasRequestBody body) {
		this.body = body;
	}
}
