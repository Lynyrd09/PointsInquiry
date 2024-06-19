package com.puntos.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.puntos.backends.Cmc;
import com.puntos.backends.LogicApps;
import com.puntos.config.ServiceBusConfig;
import com.puntos.models.request.cmc.concuentas.CmcConcuentasRequest;
import com.puntos.models.request.ibs.movimientos.IbsMovimientoRequest;
import com.puntos.models.request.ork.movimientos.OrkMovimientosRequest;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;
import com.puntos.models.response.ibs.movimientos.IbsMovimientoResponse;
import com.puntos.models.response.ork.movimientos.OrkMovimientosResponse;
import com.puntos.models.response.ork.movimientos.OrkMovimientosResponseData;
import com.puntos.utils.CommonConstants;
import com.puntos.utils.ObjectsForBackEnds;
import com.puntos.models.HeaderResponse;
import com.puntos.models.errors.ErrorResponse;

@Service
public class MovimientosService {

	@Autowired
	private Cmc bckEnd;
	
	@Autowired
	private LogicApps lgApps;
	
	@Autowired
	private ServiceBusConfig serviceBusConfig;
	
	@Value("${cmc.conCuentas}")
	private String conCuentasEndPoint;

	@Value("${ibs.serviceId.movimiento}")
	private String ibsServiceId;

	private static final Logger logger = LoggerFactory.getLogger(MovimientosService.class);
	private ObjectsForBackEnds objs = new ObjectsForBackEnds();

	public Object proceso(OrkMovimientosRequest data) {

		Gson gson = new Gson();

		Object responseOrquestador = null;
		CmcConcuentasResponse conCuentas = null;
		

		String cardNumber = data.getData().getBody().getCardNumber();
		String customerNumber = data.getData().getBody().getCustomerNumber();

		data.getData().getBody().setCardNumber(cardNumber.replaceFirst("^0+(?!$)", ""));

		if (customerNumber.length() >= 9) {
			data.getData().getBody().setCustomerNumber(customerNumber.substring(customerNumber.length() - 9));
		} else {
			data.getData().getBody().setCustomerNumber(customerNumber);
		}
		
		if (!(data.getData().getBody().getCardNumber().isEmpty())) {

			logger.warn("[ConsultaService][proceso] - [{}] - Ejecutando consumo hacia CMC ConCuentas",
					data.getMetadata().getMessageId());
			Object cmcData = bckEnd.consume(conCuentasEndPoint, data.getMetadata(), objs.cmcConcuentas(data, CmcConcuentasRequest.class), null);
			
			if (cmcData instanceof ErrorResponse)
				return cmcData;

			conCuentas = gson.fromJson((String) cmcData, CmcConcuentasResponse.class);

		}

		logger.warn("[MovimientosService][proceso] - [{}] - Ejecutando consumo hacia IBS",
				data.getMetadata().getMessageId());
		
		Object ibsData = lgApps.consume("IBS", data.getMetadata(), objs.ibsMovimiento(data, conCuentas, IbsMovimientoRequest.class), ibsServiceId, serviceBusConfig.senderIbs(), serviceBusConfig.receiverIbs(), IbsMovimientoRequest.class);
    	
		if (ibsData instanceof ErrorResponse) {
			return ibsData;}

		IbsMovimientoResponse ibsResponse = gson.fromJson((String) ibsData, IbsMovimientoResponse.class);
		

		ibsResponse.getData().getBody().forEach(n -> {

			if (Objects.equals(n.getProductType(), CommonConstants.ORQUESTADOR_TCR_MOV)) {

				logger.warn("[ConsultaService][proceso] - [{}] - Ejecutando consumo hacia CMC ConCuentas - Busqueda TCR",
						data.getMetadata().getMessageId());
				
				data.getData().getBody().setCardNumber("");
				data.getData().getBody().setAccountNumber(n.getCreditCardAccount());
				Object cmcDataR = bckEnd.consume(conCuentasEndPoint, data.getMetadata(), objs.cmcConcuentas(data, CmcConcuentasRequest.class), null);
				
				String cardNumberV2;
				
				if (cmcDataR instanceof ErrorResponse) {
					cardNumberV2 = "";
				} else {
					CmcConcuentasResponse conCuentasV2 = gson.fromJson((String) cmcDataR, CmcConcuentasResponse.class);

					cardNumberV2 =  (conCuentasV2).getData().getBody().get(0).getCardNumber();
				}
				n.setCardNumberT(cardNumberV2);
			}
		});

		responseOrquestador = responseFinal(data, ibsResponse);

		return responseOrquestador;

	}

	private Object responseFinal(OrkMovimientosRequest data, IbsMovimientoResponse ibsResponse) {

		/** FINAL RESPONSE */
		OrkMovimientosResponse orq = new OrkMovimientosResponse();

		orq.setMetadata(data.getMetadata());
		orq.getMetadata().setMessageType("Response");
		orq.getMetadata().setDatetime(LocalDateTime.now().withNano(0).toString());

		orq.setData(new OrkMovimientosResponseData(new HeaderResponse(data.getData().getHeader()),
				ibsResponse.getData().getBody()));

		return orq;

	}

}
