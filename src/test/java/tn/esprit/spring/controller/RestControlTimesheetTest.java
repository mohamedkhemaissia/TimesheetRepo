package tn.esprit.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.ITimesheetService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestControlTimesheetTest {

    @MockBean
    ITimesheetService iTimesheetService;
    @Autowired
    MockMvc mockMvc;
    @Test
    void ajouterMission() {
        Mission mockMission = new Mission("Alpha mission", "Saving the world");
        mockMission.setId(1);
        Mockito.doReturn(1).when(iTimesheetService).ajouterMission(mockMission);
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/ajouterMission")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(mockMission)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    static String asJsonString(Object o){
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}