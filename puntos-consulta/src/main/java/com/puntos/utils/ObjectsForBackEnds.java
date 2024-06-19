package com.puntos.utils;

import com.azure.core.util.BinaryData;
import com.puntos.models.request.cmc.concuentas.*;
import com.puntos.models.request.ibs.consulta.*;
import com.puntos.models.request.ibs.movimientos.*;
import com.puntos.models.request.ork.consulta.OrkConsultaRequest;
import com.puntos.models.request.ork.movimientos.OrkMovimientosRequest;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;

public class ObjectsForBackEnds {

	public String cmcConcuentas(Object data , Class<?> cusClass) {
		
		CmcConcuentasRequest requestCuentas = new CmcConcuentasRequest();
		
		if (data instanceof OrkConsultaRequest con) {
			requestCuentas.setMetadata(GeneralResponses.getGeneratedMetadata(con.getMetadata(), CommonConstants.REQUEST_TYPE));
			requestCuentas.setData(new CmcConcuentasRequestData(new CmcConcuentasRequestBody(con.getData().getBody())));
			
		}
		
		if (data instanceof OrkMovimientosRequest mov) {
			requestCuentas.setMetadata(GeneralResponses.getGeneratedMetadata(mov.getMetadata(), CommonConstants.REQUEST_TYPE));
			requestCuentas.setData(new CmcConcuentasRequestData(new CmcConcuentasRequestBody(mov.getData().getBody())));
			
		}

		return BinaryData.fromObject(requestCuentas).toString();
	}

	public String ibsConsulta(OrkConsultaRequest data, CmcConcuentasResponse conCuentas, Class<?> cusClass) {
		
		IbsConsultaRequest requestConsulta = new IbsConsultaRequest();
		requestConsulta.setMetadata(GeneralResponses.getGeneratedMetadata(data.getMetadata(), CommonConstants.REQUEST_TYPE));
		requestConsulta.setData(new IbsConsultaRequestData(data.getData().getHeader(),
				new IbsConsultaRequestBody(data.getData().getBody(), conCuentas)));

		return BinaryData.fromObject(requestConsulta).toString();
	}

	public String ibsMovimiento(OrkMovimientosRequest data, CmcConcuentasResponse conCuentas, Class<?> cusClass) {
		
		IbsMovimientoRequest requestMovimiento = new IbsMovimientoRequest();
		requestMovimiento.setMetadata(GeneralResponses.getGeneratedMetadata(data.getMetadata(), CommonConstants.REQUEST_TYPE));
		requestMovimiento.setData(new IbsMovimientoRequestData((data).getData().getHeader(),
				new IbsMovimientoRequestBody((data).getData().getBody(), conCuentas)));

		return BinaryData.fromObject(requestMovimiento).toString();
	}

}
