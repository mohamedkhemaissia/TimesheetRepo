package tn.esprit.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestControlEmployeTest {
    @MockBean
   private IEmployeService iemployeservice;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ajouterEmploye() throws Exception {
     //Employee without id
     Employe submittedEmploye = new Employe("Khemaicia", "Med", "kh@med", true, Role.CHEF_DEPARTEMENT);
     //Employee with id=1
     Employe expectedEmploye = new Employe(0, "Khemaicia", "Med", "kh@med", true, Role.CHEF_DEPARTEMENT);

     //Configure Employee service mock: accept submittedEmployee end return 0 which is the id of the newly added Employee
     Employe employe = Mockito.doReturn(expectedEmploye).when(iemployeservice).ajouterEmploye(Mockito.any());

     //Perform a POST request to "/ajouterEmployer" with JSON payload of submittedEmploye and expect status code 200 and returned
     //Employee id value equals 0
     mockMvc.perform(MockMvcRequestBuilders.post("/ajouterEmployer")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(Utils.toJsonString(submittedEmploye)))
             .andExpect(MockMvcResultMatchers.status().isOk())
             .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(0)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.nom", Matchers.is("Khemaicia")));
    }

    static String asJsonString(Object o){
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}