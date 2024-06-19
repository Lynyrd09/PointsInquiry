package com.puntos.backends;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.puntos.models.Metadata;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.utils.CommonConstants;
import com.puntos.utils.GeneralResponses;

@Service
public class Cmc {

	private Gson gson = new Gson();

	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();

	private static final Logger logger = LoggerFactory.getLogger(Cmc.class);

	public Object consume(String endpoint, Metadata metadata, String jsonRequest, String token) {
		String serviceName = endpoint.split("/")[(endpoint.split("/")).length - 1];
		logger.warn("[Cmc][consume] - [{}] - Enviando request a {} : {}", metadata.getMessageId(), serviceName, jsonRequest);
		ErrorResponse errorData = new ErrorResponse();
		this.inicializa(token);

		String jsonData = jsonRequest;
		HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);

		/** Consumo hacia CMC */
		try {
			JSONObject jsonObjeto = new JSONObject(restTemplate.postForObject(endpoint, entity, String.class));

			logger.warn("[Cmc][consume] - [{}] - respuesta : {}", metadata.getMessageId(), jsonObjeto);

			if (jsonObjeto.has("errors") && !(jsonObjeto.isNull("errors"))) {
				errorData = gson.fromJson(jsonObjeto.toString(), ErrorResponse.class);
				if (!Objects.isNull(errorData.getMetadata())) {
					Metadata meta = metadata;
					metadata.setDatetime(LocalDateTime.now().withNano(0).toString());
					metadata.setMessageType(CommonConstants.RESPONSE_TYPE);

					errorData.setMetadata(meta);
				}
				errorData.setStatusCode(430);
				return errorData;
			}
			logger.warn("Respuesta del servicio: {}", jsonObjeto);
			return jsonObjeto.toString();
		} catch (HttpClientErrorException ex) {

			logger.warn("[Cmc][consume] - [{}] - Error en respuesta: {}", metadata.getMessageId(),
					ex.getLocalizedMessage());
			String responseBody = ex.getResponseBodyAsString();

			Gson gJSON = new Gson();
			errorData = gJSON.fromJson(responseBody, ErrorResponse.class);

			if (Objects.isNull(errorData.getErrors())) {
				JSONObject json = new JSONObject(responseBody);
				String body = "";
				try {
					body = String.valueOf(json.get("message"));
				} catch (Exception e) {
					body = ex.getMessage();
				}
				errorData = GeneralResponses.getCustomError(metadata, String.valueOf(json.get("status")),
						"No hay Datos", body);
			}
			errorData.setStatusCode(430);
			return errorData;
		} catch (Exception e) {

			logger.warn("[Cmc][consume] - [{}] - Excepcion en respuesta: {}", metadata.getMessageId(),
					e.getLocalizedMessage());
			errorData = GeneralResponses.getCustomError(metadata, CommonConstants.ORQUESTADOR_NO_DATA_RESPONSE_CODE,
					CommonConstants.ORQUESTADOR_NO_DATA_RESPONSE_TITTLE_MESSAGE,
					"Error al consumir servicio : " + e.getMessage());
			errorData.setStatusCode(430);
			return errorData;
		}
	}

	public Object consume(String endpoint, Metadata metadata, String jsonRequest, String token,
			LinkedMultiValueMap<String, String> map) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint).queryParams(map);
		URI urlWithParameters = builder.build().toUri();
		return consume(urlWithParameters.toString(), metadata, jsonRequest, token);
	}

	private void inicializa(String token) {
		this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();

		if (!("".equalsIgnoreCase(token) || token == null)) {
			this.headers.setBearerAuth(token);
		}

		this.headers.setContentType(MediaType.APPLICATION_JSON);
		this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	}
}
