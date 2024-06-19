package com.puntos.validator;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CustomPropertySqlInjectionValidator.class)
@Target( { FIELD, PARAMETER, ANNOTATION_TYPE, TYPE } )
@Retention(RUNTIME)
public @interface CustomPropertySqlInjection {
	public String message() default "Accion requerida para validar campos";
	public String field() default "";
    public String fieldMatch() default "";
    
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
    
    @Target({ TYPE })
    @Retention(RUNTIME)
    @interface List {
    	CustomPropertySqlInjection[] value();
    }
}
