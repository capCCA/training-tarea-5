package com.capgemini.training.api.controller.integration;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.training.api.controller.CustomerDetailsController;
import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.service.CustomerDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = CustomerDetailsController.class)
public class CustomerDetailsControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;


  @MockBean
  private CustomerDetailsService customerDetailsService;

  @Test
  public void shouldReturnValidCustomerWhenIdIsValid() throws Exception {
    //Creamos un objeto CustomerDetails para usarlo como respuesta esperada:
    CustomerDetails customerDetails = CustomerDetailsMother.init().getCustomerDetails();
    // Configuramos nuestro servicio mock:
    when(customerDetailsService.getCustomerDetails(anyString())).thenReturn(
        customerDetails);

    // Realizamos la petición GET a la API:
    mockMvc.perform(get("/capgemini/training/customer/123"))
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.customerId").value(customerDetails.getCustomerId()))
        .andReturn();

  }


}