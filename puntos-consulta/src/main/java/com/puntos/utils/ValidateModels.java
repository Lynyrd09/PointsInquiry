package com.puntos.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.puntos.models.Metadata;
import com.puntos.models.errors.Error;
import com.puntos.models.errors.ErrorResponse;


public final class ValidateModels {

	private ValidateModels() {
		throw new IllegalStateException("ValidateModel class");
	}

	public static <T> List<Error> validateModel(T object) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<T>> violations = validator.validate(object);
		ArrayList<Error> errors = new ArrayList<>();
		for (ConstraintViolation<T> violation : violations) {
			String errorCode = "";
			String titleMessage = "";
			String bindingError = violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
			if (bindingError.equals("Size")) {
				errorCode = CommonConstants.ORQUESTADOR_SIZE_ERROR_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_SIZE_TITLE_MESSAGE;
			} else if (bindingError.equals("NotNull")) {
				errorCode = CommonConstants.ORQUESTADOR_NULL_ERROR_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_NULL_TITLE_MESSAGE;
			} else if (bindingError.equals("NotBlank")) {
				errorCode = CommonConstants.ORQUESTADOR_BLANK_ERROR_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_BLANK_TITLE_MESSAGE;
			} else if (bindingError.equals("Pattern")) {
				errorCode = CommonConstants.ORQUESTADOR_FORMAT_ERROR_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_FORMAT_TITTLE_MESSAGE;
			} else if (bindingError.equals("Min")) {
				errorCode = CommonConstants.ORQUESTADOR_MIN_VALUE_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_MIN_VALUE_TITTLE_MESSAGE;
			} else if (bindingError.equals("CustomPropertyMovimientos")
					|| bindingError.equals("CustomPropertyConsulta")) {
				errorCode = CommonConstants.ORQUESTADOR_CONDITIONAL_FIELDS_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_CONDITIONAL_FIELDS_TITTLE_MESSAGE;
			} else if(bindingError.equals("CustomPropertySqlInjection")){
				errorCode = CommonConstants.ORQUESTADOR_SQLINJECTION_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_SQLINJECTION_TITTLE_MESSAGE;
			} else {
				errorCode = CommonConstants.ORQUESTADOR_GENERAL_ERROR_CODE;
				titleMessage = CommonConstants.ORQUESTADOR_GENERAL_TITTLE_MESSAGE;
			}
			errors.add(new Error(errorCode, titleMessage, violation.getMessage()));
		}
		return errors;
	}

	public static <T> ResponseEntity<Object> startValidation(T object, Metadata data) {
		List<Error> recovery = null;
			//we validate any hibernate validation message error
			//in case one of all match then we show the api like a string of error
			recovery = validateModel(object);
		
		Metadata meta = data;
		if(Objects.isNull(meta)) meta = new Metadata();
		
		meta.setDatetime(LocalDateTime.now().withNano(0).toString());
		meta.setMessageType("Response");
		
		return GeneralResponses.getGeneralResponse(new ErrorResponse(meta, recovery), HttpStatus.BAD_REQUEST);
	}
}