package com.puntos.validator;

import org.apache.commons.lang3.StringUtils;

import com.puntos.models.request.ork.movimientos.OrkMovimientosRequestBody;
import com.puntos.utils.CommonConstants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomPropertyMovimientosValidator
		implements ConstraintValidator<CustomPropertyMovimientos, OrkMovimientosRequestBody> {

	private static final String MSG_FNUM = "^(\\d)*$";
	private static final String MSG_FM = "01|02|03";

	@Override
	public boolean isValid(OrkMovimientosRequestBody data, ConstraintValidatorContext context) {
		// disable existing violation message
		context.disableDefaultConstraintViolation();

		if (!(data instanceof OrkMovimientosRequestBody)) {
			customMessageForValidation(context, "La clase a validar no es instancia del servicio Puntos");
			return false;
		}

		if (StringUtils.isBlank(data.getAccountNumber()) && StringUtils.isBlank(data.getCardNumber())
				&& StringUtils.isBlank(data.getCustomerNumber())) {
			customMessageForValidation(context,
					CommonConstants.ORQUESTADOR_CONDITIONALS_NULL_ACCOUNTNUMBER_CARDNUMBER_CUSTOMERNUMBER_MESSAGE);
			return false;
		}

		// AccountNumber
		if (!(data.getAccountNumber() == null || data.getAccountNumber().isBlank()))

		{

			if (!data.getAccountNumber().matches(MSG_FNUM)) {
				customMessageForValidation(context,
						CommonConstants.ORQUESTADOR_CONDITIONAL_FORMAT_ACCOUNTNUMBER_MESSAGE);
				return false;
			} else if (data.getAccountNumber().length() > 12) {
				customMessageForValidation(context, CommonConstants.ORQUESTADOR_CONDITIONAL_SIZE_ACCOUNTNUMBER_MESSAGE);
				return false;

			}

		}

		// CardNumber
		if (!(data.getCardNumber() == null || data.getCardNumber().isBlank()))

		{

			if (data.getCardNumber().length() < 16 || data.getCardNumber().length() > 19) {
				customMessageForValidation(context, CommonConstants.ORQUESTADOR_CONDITIONAL_SIZE_CARDNUMBER_MESSAGE);
				return false;
			} else if (!data.getCardNumber().matches(MSG_FNUM)) {
				customMessageForValidation(context, CommonConstants.ORQUESTADOR_CONDITIONAL_FORMAT_CARDNUMBER_MESSAGE);
				return false;
			}

		}

		// MegamileType
		if (!(data.getCardNumber() == null || data.getCardNumber().isBlank())
				|| !(data.getAccountNumber() == null || data.getAccountNumber().isBlank()))

		{

			if (data.getMegamileType() == null || "".equals(data.getMegamileType())) {
				customMessageForValidation(context, CommonConstants.ORQUESTADOR_CONDITIONAL_NULL_MEGAMILETYPE_MESSAGE);
				return false;
			} else if (data.getMegamileType().length() > 2) {
				customMessageForValidation(context, CommonConstants.ORQUESTADOR_CONDITIONAL_SIZE_MEGAMILETYPE_MESSAGE);
				return false;
			}else if (!data.getMegamileType().matches(MSG_FM)) {
				customMessageForValidation(context, CommonConstants.ORQUESTADOR_CONDITIONAL_FORMAT_MEGAMILETYPE_MESSAGE);
				return false;
			}

			
		}

		// CustomerNumber
		if (!(data.getCustomerNumber() == null || data.getCustomerNumber().isBlank()))

		{

			if (data.getCustomerNumber().length() > 19) {
				customMessageForValidation(context,
						CommonConstants.ORQUESTADOR_CONDITIONAL_SIZE_CUSTOMERNUMBER_MESSAGE);
				return false;
			} else if (!data.getCustomerNumber().matches(MSG_FNUM)) {
				customMessageForValidation(context,
						CommonConstants.ORQUESTADOR_CONDITIONAL_FORMAT_CUSTOMERNUMBER_MESSAGE);
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