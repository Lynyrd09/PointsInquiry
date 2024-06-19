package com.puntos.service;

import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;

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
import com.puntos.models.request.ibs.consulta.IbsConsultaRequest;
import com.puntos.models.request.ork.consulta.OrkConsultaRequest;
import com.puntos.models.response.cmc.concuentas.CmcConcuentasResponse;
import com.puntos.models.response.ibs.consulta.IbsConsultaResponse;
import com.puntos.models.response.ibs.consulta.IbsConsultaResponsePoints;
import com.puntos.models.response.ork.consulta.OrkConsultaResponse;
import com.puntos.models.response.ork.consulta.OrkConsultaResponseData;
import com.puntos.utils.CommonConstants;
import com.puntos.utils.ObjectsForBackEnds;

import com.puntos.models.HeaderResponse;
import com.puntos.models.errors.ErrorResponse;

@Service
public class ConsultaService {

	@Autowired
	private Cmc bckEnd;
	
	@Autowired
	private LogicApps lgApps;
	
	@Autowired
	private ServiceBusConfig serviceBusConfig;

	@Value("${cmc.conCuentas}")
	private String conCuentasEndPoint;

	@Value("${ibs.serviceId.consulta}")
	private String ibsServiceId;

	private static final Logger logger = LoggerFactory.getLogger(ConsultaService.class);
	
	private ObjectsForBackEnds objs = new ObjectsForBackEnds();
	private Gson gson = new Gson();
	

	public Object proceso(OrkConsultaRequest data) {

		Object responseOrquestador = null;
		CmcConcuentasResponse conCuentas = null;
		
		if (!(data.getData().getBody().getCardNumber().isEmpty())) {

			logger.warn("[ConsultaService][proceso] - [{}] - Ejecutando consumo hacia CMC ConCuentas",
					data.getMetadata().getMessageId());
			Object cmcData = bckEnd.consume(conCuentasEndPoint, data.getMetadata(), objs.cmcConcuentas(data, CmcConcuentasRequest.class), null);
			
			if (cmcData instanceof ErrorResponse)
				return cmcData;

			conCuentas = gson.fromJson((String) cmcData, CmcConcuentasResponse.class);

		}

		logger.warn("[ConsultaService][proceso] - [{}] - Ejecutando consumo hacia IBS",
				data.getMetadata().getMessageId());
		Object ibsData = lgApps.consume("IBS", data.getMetadata(), objs.ibsConsulta(data, conCuentas, IbsConsultaRequest.class), ibsServiceId, serviceBusConfig.senderIbs(), serviceBusConfig.receiverIbs(), IbsConsultaRequest.class);
    	
		if (ibsData instanceof ErrorResponse)
			return ibsData;

		IbsConsultaResponse ibsResponse = gson.fromJson((String) ibsData, IbsConsultaResponse.class);

		ibsResponse.getData().getBody().getPoints().forEach(n -> {

			if (Objects.equals(n.getAccountType(), CommonConstants.ORQUESTADOR_TCR)) {

				String cardNumber = null;
				data.getData().getBody().getCardNumber().clear();
				data.getData().getBody().getAccountNumber().clear();

				logger.warn("[ConsultaService][proceso] - [{}] - Ejecutando consumo hacia CMC ConCuentas - Busqueda TCR",
						data.getMetadata().getMessageId());
				data.getData().getBody().getAccountNumber().add(n.getCreditCardAccount());
				Object cmcDataR = bckEnd.consume(conCuentasEndPoint, data.getMetadata(), objs.cmcConcuentas(data, CmcConcuentasRequest.class), null);
				
				if (cmcDataR instanceof ErrorResponse) {
					cardNumber = "";
				} else {
					CmcConcuentasResponse conCuentasV2 = gson.fromJson((String) cmcDataR, CmcConcuentasResponse.class);

					cardNumber =  (conCuentasV2).getData().getBody().get(0).getCardNumber();
				}
				n.setCardNumber(cardNumber);
			}
		});

		List<IbsConsultaResponsePoints> list;

		list = (ibsResponse).getData().getBody().getPoints();
		
		int eliminatedCount = 0;
		
		for (int i = 0; i < list.size(); i++)
        {
            if ((Objects.equals(list.get(i).getAccountType(), CommonConstants.ORQUESTADOR_TCR)) && ("".equals(list.get(i).getCardNumber())))
            {
                list.remove(i);
                i--;
                eliminatedCount++;
                
            }
            
        }

		list.sort((IbsConsultaResponsePoints a, IbsConsultaResponsePoints b) -> a.getAccountType()
				.compareTo(b.getAccountType()));

		(ibsResponse).getData().getBody().setTotalRecords(String.valueOf(Integer.valueOf((ibsResponse).getData().getBody().getTotalRecords()) - eliminatedCount));
		(ibsResponse).getData().getBody().setPoints(list);
		
		

		responseOrquestador = responseFinal(data, ibsResponse);

		return responseOrquestador;

	}
	
	private Object responseFinal(OrkConsultaRequest data, IbsConsultaResponse ibsResponse) {

		/** FINAL RESPONSE */
		OrkConsultaResponse orq = new OrkConsultaResponse();

		orq.setMetadata(data.getMetadata());
		orq.getMetadata().setMessageType("Response");
		orq.getMetadata().setDatetime(LocalDateTime.now().withNano(0).toString());

		orq.setData(new OrkConsultaResponseData(new HeaderResponse(data.getData().getHeader()), ibsResponse.getData().getBody()));

		return orq;

	}

}
