package com.puntos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.puntos.models.errors.ErrorResponse;
import com.puntos.models.request.ork.movimientos.OrkMovimientosRequest;
import com.puntos.models.response.ork.movimientos.OrkMovimientosResponse;
import com.puntos.service.MovimientosService;
import com.puntos.utils.GeneralResponses;
import com.puntos.utils.ValidateModels;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Inquiry Move Of Points", description = "Inquiry Move Of Points")
public class MovimientoController {

	@Autowired private MovimientosService orq;
	
	@PostMapping("inquiryMoveOfPoints")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OrkMovimientosResponse.class))
			}),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			})
	})
	public ResponseEntity<Object> getData(@Valid @RequestBody OrkMovimientosRequest data,  BindingResult result){
		
		GeneralResponses.jsonString(data, "[inquiryMoveOfPoints] - Request entrada al orquestador");
		
		if (result.hasErrors()) {
			return ValidateModels.startValidation(data, data.getMetadata());
		}

		Object objeto = orq.proceso(data);

		if(objeto instanceof ErrorResponse errorResponse)
			return GeneralResponses.getCustomResponse(errorResponse, errorResponse.getStatusCode());
		
		return GeneralResponses.getGeneralResponse(objeto, HttpStatus.OK);

	}
	

}
