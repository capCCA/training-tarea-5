package com.capgemini.training.api.controller.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.controller.UpdateCustomerDetailsController;
import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.model.UpdateCustomerRequest;
import com.capgemini.training.api.service.UpdateCustomerDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(UpdateCustomerDetailsController.class)
public class UpdateCustomerDetailsControllerIntegrationTest {


  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UpdateCustomerDetailsService updateCustomerDetailsService;

  @Test
  public void shouldUpdateCustomerWhenObjectIsValid() throws Exception {
    UpdateCustomerRequest updateCustomer = CustomerDetailsMother.init().getUpdateCustomer();
    CustomerDetails customerDetails = CustomerDetailsMother.init().getCustomerDetails();

    doReturn(customerDetails).when(this.updateCustomerDetailsService)
        .updateCustomerDetails(anyString(), any(UpdateCustomerRequest.class));

    String jsonRequest = objectMapper.writeValueAsString(updateCustomer);

    // Realizamos la petición POST a la API:
    mockMvc.perform(put("/capgemini/training/customer/1234")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.customerId").value(customerDetails.getCustomerId()))
        .andReturn();

  }

}