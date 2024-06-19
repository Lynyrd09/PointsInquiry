package com.puntos.utils;

public class CommonConstants {

	public static final String REQUEST_TYPE = "Request";
	public static final String RESPONSE_TYPE = "Response";

	public static final int HTTP_CODE_UNAUTHORIZER = 401;

	public static final int HTTP_CODE_ERROR_INTERNAL_SERVICE = 500;

	public static final int HTTP_CODE_BAD_REQUEST = 400;

	public static final int HTTP_CODE_SUCCESS = 200;

	public static final int HTTP_CODE_NO_CONTENT = 204;

	public static final int HTTP_CODE_CONFLICT = 409;

	public static final String ORQUESTADOR_SIZE_ERROR_CODE = "0001";
	public static final String ORQUESTADOR_SIZE_TITLE_MESSAGE = "Tamano del campo";

	public static final String ORQUESTADOR_NULL_ERROR_CODE = "0002";
	public static final String ORQUESTADOR_NULL_TITLE_MESSAGE = "Parametro vacio";

	public static final String ORQUESTADOR_BLANK_ERROR_CODE = "0003";
	public static final String ORQUESTADOR_BLANK_TITLE_MESSAGE = "Parametro en blanco";

	public static final String ORQUESTADOR_DATE_RANGE_ERROR_CODE = "0004";
	public static final String ORQUESTADOR_DATE_RANGE_TITTLE_MESSAGE = "Rango de fechas";
	public static final String ORQUESTADOR_DATE_RANGE_MESSAGE = "El Rango entre las fechas no puede exceder de dos a�os";

	public static final String ORQUESTADOR_DATE_DIFERENCES_RANGE_MESSAGE = "La fecha inicial no debe ser mayor que la fecha final";

	public static final String ORQUESTADOR_NUMBER_FORMAT_CODE = "0005";
	public static final String ORQUESTADOR_NUMBER_FORMAT_MESSAGE = "El campo %s solo acepta numeros";

	public static final String ORQUESTADOR_GENERAL_ERROR_CODE = "0006";
	public static final String ORQUESTADOR_GENERAL_TITTLE_MESSAGE = "Error desconocido";

	public static final String ORQUESTADOR_FORMAT_ERROR_CODE = "0007";
	public static final String ORQUESTADOR_FORMAT_TITTLE_MESSAGE = "Formato invalido";

	public static final String ORQUESTADOR_PAGINATION_ERROR_CODE = "0008";
	public static final String ORQUESTADOR_PAGINATION_TITTLE_MESSAGE = "Numero de pagina invalida";
	public static final String ORQUESTADOR_PAGINATION_MESSAGE = "Numero de pagina a consultar, excede el maximo";
	
	public static final String ORQUESTADOR_CONDITIONAL_FIELDS_CODE = "0009";
	public static final String ORQUESTADOR_CONDITIONAL_FIELDS_TITTLE_MESSAGE = "Campos Condicionales / Opcionales";
	
	public static final String ORQUESTADOR_SQLINJECTION_CODE = "0009";
	public static final String ORQUESTADOR_SQLINJECTION_TITTLE_MESSAGE = "SQL Injection";

	public static final String ORQUESTADOR_NO_DATA_FOUND_CODE = "0039";
	public static final String ORQUESTADOR_NO_DATA_FOUND_TITTLE_MESSAGE = "No hay datos para mostrar";
	public static final String ORQUESTADOR_NO_DATA_FOUND_MESSAGE = "No existen registros con la informacion proporcionada";

	public static final String ORQUESTADOR_MIN_VALUE_CODE = "0010";
	public static final String ORQUESTADOR_MIN_VALUE_TITTLE_MESSAGE = "Valor minimo esperado";
	public static final String ORQUESTADOR_MIN_VALUE_MESSAGE ="Se debe proporcionar info en alguno de los siguientes campos: customerNumber, accountNumber, cardNumber";
	public static final String ORQUESTADOR_MIN_VALUE_MESSAGE_2 ="Solo uno de lo siguientes campos puede tener info: customerNumber, accountNumber, cardNumber";
	public static final String ORQUESTADOR_MIN_VALUE_MESSAGE_3 ="Se debe proporcionar info en alguno de los siguientes campos: accountNumber � cardNumber";
	public static final String ORQUESTADOR_MIN_VALUE_MESSAGE_4 ="Solo uno de lo siguientes campos puede tener info: accountNumber � cardNumber";

	public static final String ORQUESTADOR_SAME_ACCOUNTNUMBER_CODE = "0011";
	public static final String ORQUESTADOR_SAME_ACCOUNTNUMBER_TITTLE_MESSAGE = "Mismo accountNumber";
	public static final String ORQUESTADOR_SAME_ACCOUNTNUMBER_MESSAGE = "Cliente que recibe es invalido";
	
	
	public static final String ORQUESTADOR_MIN_VALUE_MESSAGE_ACCOUNTNUMBER= "Se debe proporcionar info en el campo accountNumber";
	public static final String ORQUESTADOR_MIN_VALUE_MESSAGE_CUSTOMERNUMBER= "Se debe proporcionar info en el campo customerNumber";
	public static final String ORQUESTADOR_MIN_VALUE_MESSAGE_CARDNUMBER= "Se debe proporcionar info en el campo cardNumber";

	public static final String ORQUESTADOR_NO_DATA_RESPONSE_CODE = "9001";
	public static final String ORQUESTADOR_NO_DATA_RESPONSE_TITTLE_MESSAGE = "Error al consumir servicio";

	public static final String ORQUESTADOR_SEM_CODE = "0040";
	public static final String ORQUESTADOR_SEM_TITTLE = "Tarjeta Bloqueada";
	public static final String ORQUESTADOR_SEM_MESSAGE = "Transaccion Denegada por el semaforo transaccional";

	public static final String ORQUESTADOR_VIS_CODE = "9003";
	public static final String ORQUESTADOR_VIS_TITTLE = "Transaccion Denegada";
	public static final String ORQUESTADOR_VIS_MESSAGE = "Transaccion Denegada por Vision+";

	public static final String ORQUESTADOR_TIME_OUT_CODE = "9002";
	public static final String ORQUESTADOR_TIME_OUT_TITTLE = "Tiempo de espera concluido (time-out)";
	public static final String ORQUESTADOR_TIME_OUT_MESSAGE = "No se obtuvo respuesta de %s en el tiempo estipulado: %s Segundos";

	/**
	 * Instantiates a new common constants.
	 */
	
	public static final String ORQUESTADOR_CONDITIONAL_NULL_ACCOUNTNUMBER_MESSAGE = "El campo accountNumber es requerido";
	public static final String ORQUESTADOR_CONDITIONAL_SIZE_ACCOUNTNUMBER_MESSAGE = "El campo accountNumber no debe tener mas de 12 caracteres";
	public static final String ORQUESTADOR_CONDITIONAL_FORMAT_ACCOUNTNUMBER_MESSAGE = "El campo accountNumber solo acepta numeros";

	public static final String ORQUESTADOR_CONDITIONAL_NULL_CARDNUMBER_MESSAGE = "El campo cardNumber es requerido";

	public static final String ORQUESTADOR_CONDITIONAL_SIZE_CARDNUMBER_MESSAGE = "El campo cardNumber debe tener entre 16 y 19 caracteres";
	public static final String ORQUESTADOR_CONDITIONAL_FORMAT_CARDNUMBER_MESSAGE = "El campo cardNumber solo acepta numeros";

	public static final String ORQUESTADOR_CONDITIONAL_NULL_CUSTOMERNUMBER_MESSAGE = "El campo customerNumber es requerido";
	public static final String ORQUESTADOR_CONDITIONAL_SIZE_CUSTOMERNUMBER_MESSAGE = "El campo customerNumber no debe tener mas de 19 caracteres";
	public static final String ORQUESTADOR_CONDITIONAL_FORMAT_CUSTOMERNUMBER_MESSAGE = "El campo customerNumber solo acepta numeros";
	
	public static final String ORQUESTADOR_CONDITIONAL_NULL_MEGAMILETYPE_MESSAGE = "El campo megamileType es requerido";
	public static final String ORQUESTADOR_CONDITIONAL_SIZE_MEGAMILETYPE_MESSAGE = "El campo megamileType no debe tener mas de 2 caracteres";
	public static final String ORQUESTADOR_CONDITIONAL_FORMAT_MEGAMILETYPE_MESSAGE = "El campo megamileType solo acepta valores 01, 02, y 03";


	public static final String ORQUESTADOR_CONDITIONAL_NULL_CREDITCARD_MESSAGE = "El campo cardNumber es requerido";
	public static final String ORQUESTADOR_CONDITIONALS_NULL_ACCOUNTNUMBER_MESSAGE = "El campo accountNumber es requerido";
	public static final String ORQUESTADOR_CONDITIONAL_NULL_OPERATIONTYPE_MESSAGE = "El campo operationType es requerido";
	public static final String ORQUESTADOR_CONDITIONAL_SIZE_OPERATIONTYPE_MESSAGE = "El campo operationType no debe tener mas de 1 caracter";
	public static final String ORQUESTADOR_CONDITIONAL_NULL_TRANSACTIONAMOUNT_MESSAGE = "El campo transactionAmount es requerido";

	public static final String ORQUESTADOR_CASHBAC_CODE = "29";
	public static final String ORQUESTADOR_LFM = "30";
	public static final String ORQUESTADOR_TCR = "TCR";
	public static final String ORQUESTADOR_TCR_MOV = "1";
	
	
	public static final String ORQUESTADOR_CONDITIONAL_NULL_LIFEMILESCUSTOMERNUMBER_MESSAGE = "El campo lifemilesCustomerNumber es requerido";
	public static final String ORQUESTADOR_CONDITIONAL_SIZE_LIFEMILESCUSTOMERNUMBER_MESSAGE = "El campo lifemilesCustomerNumber no debe tener mas de 11 caracteres";
	

	public static final String ORQUESTADOR_NOT_REQUIRED_ERROR_CODE = "0015";
	public static final String ORQUESTADOR_NOT_REQUIRED_TITLE_MESSAGE = "Parametro no requerido";
	public static final String ORQUESTADOR_CONDITIONAL_NOT_REQUIRED_MEGAMILETYPE_MESSAGE = "El campo megamileType no es requerido";

	public static final String ORQUESTADOR_CONDITIONALS_NULL_ACCOUNTNUMBER_CARDNUMBER_CUSTOMERNUMBER_MESSAGE = "Es requerido al menos uno de estos campos: accountNumber, cardNumber o customerNumber";
	public static final String ORQUESTADOR_CONDITIONALS_NULL_ACCOUNTNUMBER_CARDNUMBER_MESSAGE = "Es requerido al menos uno de estos campos: accountNumber o cardNumber";

	public static final String ORQUESTADOR_CONDITIONAL_PAYMENTINSTRUMENTTYPE_TDC = "TDC";
	public static final String ORQUESTADOR_CONDITIONAL_PAYMENTINSTRUMENTTYPE_CAC = "CAC";
	


	private CommonConstants() {
	}
}
