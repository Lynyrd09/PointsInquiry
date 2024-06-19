package com.puntos.models.errors;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puntos.models.Metadata;


public class ErrorResponse {
	@JsonIgnore
	private int statusCode = 200;
	private Metadata metadata;
	private List<Error> errors;
	
	public ErrorResponse() { }

	public ErrorResponse(Metadata metadata, List<Error> errors) {
		this.metadata = metadata;
		this.errors = errors;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
	public List<Error> getErrors() {
		return errors;
	}
	
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
