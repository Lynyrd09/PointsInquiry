package com.puntos.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.SerializedName;

import jakarta.validation.ConstraintValidatorContext;

public class SqlObjectValidator {

	private static final String NOT_SPECIAL_CHARS = "[<>'\\\"/|;`%+*()]";
	private static final String SQL_MSG = "Se detecto codigo SQL o Script en el campo %s";
	private static final Logger logger = LoggerFactory.getLogger(SqlObjectValidator.class);
	
	private SqlObjectValidator() {
		throw new IllegalStateException("PropertyValidator class");
	}
	
	public static boolean startValidation(Object obj, ConstraintValidatorContext context) {
		final String regex = "(\\s*([\\\'\\\"\\n\\r\\t\\%\\_\\\\]*\\s*(((select\\s*.+\\s*from\\s*.+)|(insert\\s*.+\\s*into\\s*.+)|(update\\s*.+\\s*set\\s*.+)|(delete\\s*.+\\s*from\\s*.+)|(drop\\s*.+)|(truncate\\s*.+)|(alter\\s*.+)|(exec\\s*.+)|(\\s*(all|any|not|and|between|in|like|or|some|contains|containsall|containskey)\\s*.+[\\=\\>\\<=\\!\\~]+.+)|(let\\s+.+[\\=]\\s*.*)|(begin\\s*.*\\s*end)|(\\s*[\\/\\*]+\\s*.*\\s*[\\*\\/]+)|(\\s*(\\-\\-)\\s*.*\\s+)|(\\s*(contains|containsall|containskey)\\s+.*)))(\\s*[\\;]\\s*)*)+)";
		Class<?> clazz = obj.getClass();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (validateString(regex, method, obj, context)) {
				return true;
			}

			if (validateList(regex, method, obj, context)) {
				return true;
			}
		}

		return false;
	}
	
	private static boolean validateString(String regex, Method method, Object obj, ConstraintValidatorContext context) {
		if (method.getName().startsWith("get") && method.getReturnType().isAssignableFrom(String.class)) {
			try {
				Field field = method.getDeclaringClass().getDeclaredField(String.format("%s%s", method.getName().substring(3, 4).toLowerCase(), method.getName().substring(4)));
				String nameField = getNameFromAnnotation(field);
				String value = (String) method.invoke(obj);
				if (validateRegExp(regex, value)) {
					customMessageForValidation(context, String.format(SQL_MSG, nameField));
					return true;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException e) {
				customMessageForValidation(context, "Error al realizar validación de posible inyección");
			}
		}
		return false;
	}

	private static boolean validateList(String regex, Method method, Object obj, ConstraintValidatorContext context) {
		if (method.getName().startsWith("get") && method.getReturnType().isAssignableFrom(List.class)) {
			try {
				Field field = method.getDeclaringClass().getDeclaredField(String.format("%s%s",	method.getName().substring(3, 4).toLowerCase(), method.getName().substring(4)));
				Type type = field.getGenericType();
				Class<?> lClazz = null;
				if (type instanceof ParameterizedType pt) {
					Type[] tp = pt.getActualTypeArguments();
					lClazz = Class.forName(tp[0].getTypeName());
				}
				if (lClazz != String.class) {
					return false;
				}

				@SuppressWarnings("unchecked")
				List<String> val = (List<String>) method.invoke(obj);

				for (String data : val) {
					if (validateRegExp(regex, data)) {
						customMessageForValidation(context, String.format(SQL_MSG, getNameFromAnnotation(field)));
						return true;
					}
				}
			} catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
				logger.error(e.getLocalizedMessage());
				customMessageForValidation(context, "Error al realizar validación de posible inyección");
			}
		}
		return false;
	}

	/**
	 * Obtiene el nombre del campo especificado en la anotación SerializedName, sino
	 * existe dicha anotación retorna cadena vacia
	 * 
	 * @param field
	 * @return
	 */
	private static String getNameFromAnnotation(final Field field) {
		String name = "";
		Annotation[] annotations = field.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			Annotation annotation = annotations[i];
			if (annotation instanceof SerializedName ann) {
				name = ann.value();
				break;
			}
		}
		name = name.equals("") ? field.getName() : name;
		return name;
	}

	private static boolean validateRegExp(String regex, String value) {
		if (Objects.isNull(value))
			return false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		boolean match = (matcher.find() || matcher.matches());
		if (!match) {
			pattern = Pattern.compile(NOT_SPECIAL_CHARS);
			matcher = pattern.matcher(value);
			match = matcher.find() || matcher.matches();
		}
		return match;
	}

	private static void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
		// build new violation message and add it
		constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}
	
}
