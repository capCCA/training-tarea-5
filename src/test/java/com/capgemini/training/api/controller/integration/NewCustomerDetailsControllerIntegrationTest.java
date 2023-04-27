package com.capgemini.training.api.controller.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.controller.NewCustomerDetailsController;
import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.service.NewCustomerDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@WebMvcTest(NewCustomerDetailsController.class)
public class NewCustomerDetailsControllerIntegrationTest {

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private NewCustomerDetailsService newCustomerDetailsService;

  @Autowired
  private MockMvc mockMvc;


  @Test
  public void shouldReturn201WhenCustomerDetailsIsValid() throws Exception {
    //Creamos un objeto CustomerDetails para usarlo como petición:
    CustomerDetails customerDetails = CustomerDetailsMother.init().getCustomerDetails();

    doReturn(customerDetails).when(this.newCustomerDetailsService)
        .createNewCustomer(any(CustomerDetails.class));

    String jsonRequest = objectMapper.writeValueAsString(customerDetails);

    // Realizamos la petición POST a la API:
    MvcResult result = mockMvc.perform(post("/capgemini/training/customer")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();

    // Comprobamos que el resultado es el esperado:
    String jsonResponse = result.getResponse().getContentAsString();
    CustomerDetails response = objectMapper.readValue(jsonResponse, CustomerDetails.class);
    assertEquals(customerDetails.getCustomerId(), response.getCustomerId());
    assertEquals(customerDetails.getName(), response.getName());
  }

  @Test
  public void shouldReturnBadRequestWhenCustomerDetailsIsInvalid() throws Exception {

    String jsonRequest = objectMapper.writeValueAsString(CustomerDetails.builder().build());

    // Realizamos la petición POST a la API:
    mockMvc.perform(post("/capgemini/training/customer")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andReturn();

  }
}