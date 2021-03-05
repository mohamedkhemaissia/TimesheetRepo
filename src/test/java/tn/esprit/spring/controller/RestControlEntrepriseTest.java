package tn.esprit.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestControlEntrepriseTest {
    @MockBean
    private IEmployeService iemployeservice;
    @MockBean
    private IEntrepriseService ientrepriseservice;
    @MockBean
    private ITimesheetService itimesheetservice;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAjouterEntreprise() throws Exception {
        Entreprise mocEntreprise =  new Entreprise("SSII Consulting","raisonSocial");
        mocEntreprise.setId(1);
        Mockito.doReturn(1 ).when(ientrepriseservice).ajouterEntreprise(mocEntreprise);
        mockMvc.perform(MockMvcRequestBuilders.post("/ajouterEntreprise").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mocEntreprise))).andExpect(MockMvcResultMatchers.status().isOk());

    }
    static String asJsonString(Object o){
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}