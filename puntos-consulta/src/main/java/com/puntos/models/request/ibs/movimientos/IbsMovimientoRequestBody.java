package com.puntos.models.request.ibs.movimientos;

import org.apache.commons.lang3.StringUtils;

import com.puntos.models.request.ork.movimientos.OrkMovimientosRequestBody;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;

public class IbsMovimientoRequestBody extends OrkMovimientosRequestBody{

	public IbsMovimientoRequestBody() {
		super();
	}

	public IbsMovimientoRequestBody(OrkMovimientosRequestBody data, CmcConcuentasResponse data2) {
		if ( data2 != null) {
			this.setCardNumber((data2).getData().getBody().get(0).getAccountNumber());

		} else {
			this.setCardNumber(data.getCardNumber());
		}
		this.setAccountNumber(data.getAccountNumber());
		this.setMegamileType(data.getMegamileType());
		this.setCustomerNumber(StringUtils.leftPad(String.format(data.getCustomerNumber()),9, "0"));
		this.setStartDate(data.getStartDate());
		this.setFinishDate(data.getFinishDate());
	
	}
	
}
