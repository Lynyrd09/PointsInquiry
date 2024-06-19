package com.puntos.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.puntos.models.errors.ErrorResponse;
import com.puntos.models.request.ork.movimientos.OrkMovimientosRequest;
import com.puntos.service.MovimientosService;
import com.puntos.utils.ConstantJson;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MovimientoController.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class MovimientoControllerTest {
	
	private final Logger logger = LoggerFactory.getLogger(MovimientoControllerTest.class);
    @Autowired private MockMvc mockMvc;
	@MockBean private MovimientosService orq; 

	private ObjectMapper mapper = new ObjectMapper();
	private OrkMovimientosRequest data = new OrkMovimientosRequest();
	private Gson gson = new Gson();
	
	@BeforeEach
	public void setUp() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	@Test
    void getData() throws Exception {
        this.data = mapper.readValue(ConstantJson.ORK_MOVIMIENTO_REQUEST , OrkMovimientosRequest.class);
        logger.info("Request: {} ", (new JSONObject(data)).toString());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/inquiryMoveOfPoints")
                .content(ConstantJson.ORK_MOVIMIENTO_REQUEST )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
    void getDataCustomErrorResponse() throws Exception {
        this.data = mapper.readValue(ConstantJson.ORK_MOVIMIENTO_REQUEST , OrkMovimientosRequest.class);
        logger.info("Request: {} ", (new JSONObject(data)).toString());
        
        ErrorResponse err = new ErrorResponse();
        err.setStatusCode(440);
        
        Mockito.when(orq.proceso(Mockito.any())).thenReturn(err);
        
        mockMvc.perform(MockMvcRequestBuilders
                .post("/inquiryMoveOfPoints")
                .content(ConstantJson.ORK_MOVIMIENTO_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(440));
    }
	
	@Test
    void getDataBad() throws Exception {
        this.data = mapper.readValue(ConstantJson.ORK_MOVIMIENTO_REQUEST_BAD , OrkMovimientosRequest.class);
        logger.info("Request: {} ", (new JSONObject(data)).toString());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/inquiryMoveOfPoints")
                .content(ConstantJson.ORK_MOVIMIENTO_REQUEST_BAD)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	@ParameterizedTest
    @ValueSource(strings = { "metadata", "header", "body", "metadatanull", "datanull", "headernull", "bodynull" })
    void sqlInjection(String type) throws Exception {
    	this.data = mapper.readValue(ConstantJson.ORK_MOVIMIENTO_REQUEST, OrkMovimientosRequest.class);
      
        switch (type) {
            case "metadata":
            	data.getMetadata().setApplicationId("' or ''='");
                break;
            case "header":
                data.getData().getHeader().setLocationUser("+(select*from(select(sleep(20)))a)+");
                break;
            case "body":
                data.getData().getBody().setCustomerNumber("+(select*from(select(sleep(20)))a)+");
                break;
            case "metadatanull":
				data.setMetadata(null);
				break;
            case "datanull":
				data.setData(null);
				break;
			case "headernull":
				data.getData().setHeader(null);
				break;
			case "bodynull":
				data.getData().setBody(null);
				break;
      
        }

        mockMvc.perform(MockMvcRequestBuilders
                .post("/inquiryMoveOfPoints")
                .content(this.gson.toJson(this.data))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        
    }
	

}
