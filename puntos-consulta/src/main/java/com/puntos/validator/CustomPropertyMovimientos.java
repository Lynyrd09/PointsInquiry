package com.puntos.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CustomPropertyMovimientosValidator.class)
@Target( { FIELD, PARAMETER, ANNOTATION_TYPE, TYPE } )
@Retention(RUNTIME)
public @interface CustomPropertyMovimientos {
	public String message() default "Accion requerida para validar campos";
    
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
    
    @Target({ TYPE })
    @Retention(RUNTIME)
    @interface List {
    	CustomPropertyMovimientos[] value();
    }
}
