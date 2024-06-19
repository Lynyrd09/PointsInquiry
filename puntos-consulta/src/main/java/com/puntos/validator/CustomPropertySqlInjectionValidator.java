package com.puntos.validator;

import java.util.Objects;

import com.puntos.models.request.ork.consulta.OrkConsultaRequest;
import com.puntos.models.request.ork.movimientos.OrkMovimientosRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomPropertySqlInjectionValidator implements ConstraintValidator<CustomPropertySqlInjection, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		context.disableDefaultConstraintViolation();

		if (value instanceof OrkConsultaRequest con) {
			return validaCon(con, context);
		} else if (value instanceof OrkMovimientosRequest mov) {
			return validaMov(mov, context);
		}

		return true;
	}

	private boolean validaMov(OrkMovimientosRequest mov, ConstraintValidatorContext context) {
		
		if (Objects.isNull(mov.getMetadata()))
			return true;
		if (Objects.isNull(mov.getData()))
			return true;
		if (Objects.isNull(mov.getData().getHeader()))
			return true;
		if (Objects.isNull(mov.getData().getBody()))
			return true;

		if (SqlObjectValidator.startValidation(mov.getMetadata(), context)) {
			return false;
		}

		if (SqlObjectValidator.startValidation(mov.getData().getHeader(), context)) {
			return false;
		}

		if (SqlObjectValidator.startValidation(mov.getData().getBody(), context)) {
			return false;
		}

		return true;
	}

	private boolean validaCon(OrkConsultaRequest con, ConstraintValidatorContext context) {

		if (Objects.isNull(con.getMetadata()))
			return true;
		if (Objects.isNull(con.getData()))
			return true;
		if (Objects.isNull(con.getData().getHeader()))
			return true;
		if (Objects.isNull(con.getData().getBody()))
			return true;

		if (SqlObjectValidator.startValidation(con.getMetadata(), context)) {
			return false;
		}

		if (SqlObjectValidator.startValidation(con.getData().getHeader(), context)) {
			return false;
		}

		if (SqlObjectValidator.startValidation(con.getData().getBody(), context)) {
			return false;
		}

		return true;
	}

}
