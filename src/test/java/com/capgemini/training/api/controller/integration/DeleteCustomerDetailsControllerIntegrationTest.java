package com.capgemini.training.api.controller.integration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.training.api.controller.DeleteCustomerDetailsController;
import com.capgemini.training.api.service.DeleteCustomerDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = DeleteCustomerDetailsController.class)
class DeleteCustomerDetailsControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;


  @MockBean
  private DeleteCustomerDetailsService deleteCustomerDetailsService;

  @Test
  public void shouldDeleteCustomerWhenIdIsValid() throws Exception {
    // Realizamos la llamada DELETE a la API:
    MvcResult result = mockMvc.perform(delete("/capgemini/training/customer/123"))
        .andExpect(status().isNoContent())
        .andReturn();

    // Comprobamos que el servicio mock es invocado correctamente:
    verify(deleteCustomerDetailsService, times(1)).deleteCustomerDetails("123");
  }

}