package com.puntos.validator;

import java.util.List;

import com.puntos.models.request.ork.consulta.OrkConsultaRequestBody;

public class ConsultaValidatorAssistant {

	public boolean secondValidation(List<String> customerNumber, List<String> accountNumber,
			List<String> cardNumber) {
		return (customerNumber.isEmpty() && accountNumber.isEmpty() && cardNumber.isEmpty());

	}
	
	public boolean threeValidation(OrkConsultaRequestBody data, List<String> accountNumber) {
		
		return("A".equals(data.getInquiryType()) && accountNumber.isEmpty());

	}
	
public boolean fourthValidation(OrkConsultaRequestBody data, List<String> customerNumber) {
		
		return ("C".equals(data.getInquiryType()) && customerNumber.isEmpty());

	}

	public boolean tenthvalidation(List<String> accountNumber, List<String> cardNumber) {
		return (!(accountNumber.isEmpty()) && (!(cardNumber.isEmpty())));
	}

}
