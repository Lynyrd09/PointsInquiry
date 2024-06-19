package com.puntos.validator;

import java.util.List;



import com.puntos.models.request.ork.consulta.OrkConsultaRequestBody;
import com.puntos.utils.CommonConstants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomPropertyConsultaValidator
		implements ConstraintValidator<CustomPropertyConsulta, OrkConsultaRequestBody> {
	
	

	@Override
	public boolean isValid(OrkConsultaRequestBody value, ConstraintValidatorContext context) {
		// disable existing violation message
		context.disableDefaultConstraintViolation();
		
		ConsultaValidatorAssistant cva = new ConsultaValidatorAssistant();

		if (!(value instanceof OrkConsultaRequestBody)) {
			customMessageForValidation(context, "La clase a validar no es instancia del servicio Puntos");
			return false;
		}

		OrkConsultaRequestBody data = value;

		List<String> customerNumber = data.getCustomerNumber();
		List<String> accountNumber = data.getAccountNumber();
		List<String> cardNumber = data.getCardNumber();

		if (cva.secondValidation(customerNumber, accountNumber, cardNumber)) {

			customMessageForValidation(context, CommonConstants.ORQUESTADOR_MIN_VALUE_MESSAGE);
			return false;

		}

		if (cva.threeValidation(data, accountNumber)) {

			customMessageForValidation(context, CommonConstants.ORQUESTADOR_MIN_VALUE_MESSAGE_ACCOUNTNUMBER);
			return false;

		}

		if (cva.fourthValidation(data, customerNumber)) {

			customMessageForValidation(context, CommonConstants.ORQUESTADOR_MIN_VALUE_MESSAGE_CUSTOMERNUMBER);
			return false;

		}

		if ("CN".equals(data.getInquiryType()) && cardNumber.isEmpty()) {

			customMessageForValidation(context, CommonConstants.ORQUESTADOR_MIN_VALUE_MESSAGE_CARDNUMBER);
			return false;

		}

		if ("AC".equals(data.getInquiryType()) && (accountNumber.isEmpty() && cardNumber.isEmpty())) {

			customMessageForValidation(context, CommonConstants.ORQUESTADOR_MIN_VALUE_MESSAGE_3);
			return false;

		}

		if (!"AC".equals(data.getInquiryType())) {

			if ("C".equals(data.getInquiryType())
					&& (!(customerNumber.isEmpty()) && (!(accountNumber.isEmpty()) || !(cardNumber.isEmpty())))) {

				customMessageForValidation(context, CommonConstants.ORQUESTADOR_MIN_VALUE_MESSAGE_2);
				return false;

			}

			if (cva.tenthvalidation(accountNumber, cardNumber)) {

				customMessageForValidation(context, CommonConstants.ORQUESTADOR_MIN_VALUE_MESSAGE_2);
				return false;

			}

		}

		return true;

	}

	private void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
		// build new violation message and add it
		constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}

}
