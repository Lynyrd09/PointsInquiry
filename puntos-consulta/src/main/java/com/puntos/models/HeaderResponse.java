package com.puntos.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HeaderResponse {
	@NotNull(message = "El campo branch es requerido")
	@NotBlank(message = "El campo branch no puede ir en blanco")
	@Valid
	private String branch;
	
	@NotNull(message = "El campo batch es requerido")
	@NotBlank(message = "El campo batch no puede ir en blanco")
	@Valid
	private String batch;
	
	@NotNull(message = "El campo date es requerido")
	@NotBlank(message = "El campo date no puede ir en blanco")
	@Valid
	private String date;
	
	@NotNull(message = "El campo hour es requerido")
	@NotBlank(message = "El campo hour no puede ir en blanco")
	@Valid
	private String hour;
	
	@NotNull(message = "El campo system es requerido")
	@NotBlank(message = "El campo system no puede ir en blanco")
	@Valid
	private String system;
	
	@NotNull(message = "El campo channelId es requerido")
	@NotBlank(message = "El campo channelId no puede ir en blanco")
	@Valid
	private String channelId;
	
	@NotNull(message = "El campo instanceId es requerido")
	@NotBlank(message = "El campo instanceId no puede ir en blanco")
	@Valid
	private String instanceId;
	
	public HeaderResponse() {}
	
	public HeaderResponse(HeaderRequest header) {
		this.branch = header.getBranch();
		this.batch = header.getBatch();
		this.date = header.getDate();
		this.hour = header.getHour();
		this.system = header.getSystem();
		this.channelId = header.getChannelId();
		this.instanceId = header.getInstanceId();
	}
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
}
