package com.puntos.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class HeaderRequest {
	@Valid
	@NotNull(message = "El campo branch es requerido")
	@NotBlank(message = "El campo branch no debe ir en blanco")
	@Pattern(regexp = "^\\d+$", message = "El campo branch solo acepta caracteres numericos")
	@Size(min = 1, max = 3, message = "El campo branch debe tener un maximo de {max} caracteres")
	private String branch;
	
	@Valid
	@NotNull(message = "El campo supervisor es requerido")
	@NotBlank(message = "El campo supervisor no debe ir en blanco")
	@Pattern(regexp = "^\\d+$", message = "El campo supervisor solo acepta caracteres numericos")
	@Size(min = 1, max = 4, message = "El campo supervisor debe tener un maximo de {max} caracteres")
	private String supervisor;
	
	@Valid
	@NotNull(message = "El campo batch es requerido")
	@NotBlank(message = "El campo batch no debe ir en blanco")
	@Pattern(regexp = "^\\d+$", message = "El campo batch solo acepta caracteres numericos")
	@Size(min = 1, max = 4, message = "El campo batch debe tener un maximo de {max} caracteres")
	private String batch;
	
	@Valid
	@NotNull(message = "El campo date es requerido")
	@NotBlank(message = "El campo date no debe ir en blanco")
	@Pattern(regexp = "^\\d+$", message = "El campo date solo acepta caracteres numericos")
	@Size(min = 1, max = 8, message = "El campo date debe tener un maximo de {max} caracteres")
	private String date;
	
	@Valid
	@NotNull(message = "El campo hour es requerido")
	@NotBlank(message = "El campo hour no debe ir en blanco")
	@Pattern(regexp = "^\\d+$", message = "El campo hour solo acepta caracteres numericos")
	@Size(min = 1, max = 6, message = "El campo hour debe tener un maximo de {max} caracteres")
	private String hour;
	
	@Valid
	@NotNull(message = "El campo system es requerido")
	@NotBlank(message = "El campo system no debe ir en blanco")
	@Size(min = 1, max = 10, message = "El campo system debe tener un maximo de {max} caracteres")
	private String system;
	
	@Valid
	@NotNull(message = "El campo user es requerido")
	@NotBlank(message = "El campo user no debe ir en blanco")
	@Size(min = 1, max = 20, message = "El campo user debe tener un maximo de {max} caracteres")
	private String user;
	
	@Valid
	@NotNull(message = "El campo locationUser es requerido")
	@NotBlank(message = "El campo locationUser no debe ir en blanco")
	@Size(min = 1, max = 16, message = "El campo locationUser debe tener un maximo de {max} caracteres")
	private String locationUser;
	
	@Valid
	@NotNull(message = "El campo channelId es requerido")
	@NotBlank(message = "El campo channelId no debe ir en blanco")
	@Pattern(regexp = "^\\d+$", message = "El campo channelId solo acepta caracteres numericos")
	@Size(min = 1, max = 9, message = "El campo channelId debe tener un maximo de {max} caracteres")
	private String channelId;
	
	@Valid
	@NotNull(message = "El campo instanceId es requerido")
	@NotBlank(message = "El campo instanceId no debe ir en blanco")
	@Pattern(regexp = "^\\d+$", message = "El campo instanceId solo acepta caracteres numericos")
	@Size(min = 1, max = 2, message = "El campo instanceId debe tener un maximo de {max} caracteres")
	private String instanceId;
	
	public HeaderRequest() { /** this constructor was made by used in the basic instances for some methods*/ }

	public String getBranch() {
		return branch;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getSupervisor() {
		return supervisor;
	}
	
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
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
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getLocationUser() {
		return locationUser;
	}
	
	public void setLocationUser(String locationUser) {
		this.locationUser = locationUser;
	}
	
	public String getChannelId() {
		return channelId;
	}
	
	public void setChannelId(String channelID) {
		this.channelId = channelID;
	}
	
	public String getInstanceId() {
		return instanceId;
	}
	
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
}
