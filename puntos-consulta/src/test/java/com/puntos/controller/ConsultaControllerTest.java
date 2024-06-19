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
import com.puntos.models.request.ork.consulta.OrkConsultaRequest;
import com.puntos.service.ConsultaService;
import com.puntos.utils.ConstantJson;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ConsultaController.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class ConsultaControllerTest {
	
	private final Logger logger = LoggerFactory.getLogger(ConsultaControllerTest.class);
    @Autowired private MockMvc mockMvc;
	@MockBean private ConsultaService orq;

	private ObjectMapper mapper = new ObjectMapper();
	private OrkConsultaRequest data = new OrkConsultaRequest();
	private Gson gson = new Gson();
	
	@BeforeEach
	public void setUp() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	@Test
    void getData() throws Exception {
        this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
        logger.info("Request: {} ", (new JSONObject(data)).toString());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customerPointsInquiry")
                .content(ConstantJson.ORK_CONSULTA_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
    void getDataCustomErrorResponse() throws Exception {
        this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
        logger.info("Request: {} ", (new JSONObject(data)).toString());
        
        ErrorResponse err = new ErrorResponse();
        err.setStatusCode(460);
        
        Mockito.when(orq.proceso(Mockito.any())).thenReturn(err);
        
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customerPointsInquiry")
                .content(ConstantJson.ORK_CONSULTA_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(460));
    }
	
	@Test
    void getDataBad() throws Exception {
        this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST_BAD, OrkConsultaRequest.class);
        logger.info("Request: {} ", (new JSONObject(data)).toString());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customerPointsInquiry")
                .content(ConstantJson.ORK_CONSULTA_REQUEST_BAD)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	@ParameterizedTest
    @ValueSource(strings = { "metadata", "header", "body", "metadatanull", "datanull", "headernull", "bodynull" })
    void sqlInjection(String type) throws Exception {
    	this.data = mapper.readValue(ConstantJson.ORK_CONSULTA_REQUEST, OrkConsultaRequest.class);
      
        switch (type) {
            case "metadata":
            	data.getMetadata().setApplicationId("' or ''='");
                break;
            case "header":
                data.getData().getHeader().setLocationUser("+(select*from(select(sleep(20)))a)+");
                break;
            case "body":
                data.getData().getBody().setPageNumber("+(select*from(select(sleep(20)))a)+");
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
                .post("/customerPointsInquiry")
                .content(this.gson.toJson(this.data))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        
    }

}
