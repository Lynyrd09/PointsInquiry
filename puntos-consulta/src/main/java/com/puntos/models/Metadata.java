package com.puntos.models;

import java.io.Serializable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Metadata implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1650034331648154480L;
	@Valid
	@NotNull(message = "EL campo _messageType es requerido")
	@NotBlank(message = "El campo _messageType no debe ir en blanco")
	@JsonProperty(value = "_messageType")
	private String messageType;
	
	@Valid
	@NotNull(message = "EL campo _messageId es requerido")
	@NotBlank(message = "El campo _messageId no debe ir en blanco")
	@JsonProperty(value = "_messageId")
	private String messageId;

	@JsonProperty(value = "_messageIdOrg")
	private String messageIdOrg;
	
	@Valid
	@NotNull(message = "EL campo _shortMessageId es requerido")
	@NotBlank(message = "El campo _shortMessageId no debe ir en blanco")
	@JsonProperty(value = "_shortMessageId")
	private String shortMessageId;
	
	@Valid
	@NotNull(message = "EL campo _applicationId es requerido")
	@NotBlank(message = "El campo _applicationId no debe ir en blanco")
	@JsonProperty(value = "_applicationId")
	private String applicationId;
	
	@Valid
	@NotNull(message = "EL campo _serviceId es requerido")
	@NotBlank(message = "El campo _serviceId no debe ir en blanco")
	@JsonProperty(value = "_serviceId")
	private String serviceId;
	
	@Valid
	@NotNull(message = "EL campo _datetime es requerido")
	@NotBlank(message = "El campo _datetime no debe ir en blanco")
	@JsonProperty(value = "_datetime")
	private String datetime;
	
	public Metadata() { 
		/*  */ 
		}

	public String getMessageType() {
		return messageType;
	}

	public String getMessageId() {
		return messageId;
	}

	public String getMessageIdOrg() {
		return messageIdOrg;
	}

	public String getShortMessageId() {
		return shortMessageId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setMessageIdOrg(String messageIdOrg) {
		this.messageIdOrg = messageIdOrg;
	}

	public void setShortMessageId(String shortMessageId) {
		this.shortMessageId = shortMessageId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	

}
