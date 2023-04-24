package com.capgemini.training.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.service.CustomerDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CustomerDetailsControllerTest {

  // TODO porque no usamos inyecMocks? Que es mejor usar inyectMocks o inyectar dentro del
  // beforeEach? Os dejo que penseis ;)
  private CustomerDetailsController customerDetailsController;

  @Mock private CustomerDetailsService customerDetailsService;

  @BeforeEach
  void setUp() {
    openMocks(this);
    customerDetailsController = new CustomerDetailsController(customerDetailsService);
  }

  @Test
  @DisplayName("When valid customer ID is provided, should return customer details")
  public void shouldReturnCustomerDetailsWhenValidCustomerIdProvided() {

    String customerId = "123";
    CustomerDetails customerDetails = CustomerDetails.builder().customerId(customerId).build();
    when(customerDetailsService.getCustomerDetails(customerId)).thenReturn(customerDetails);

    ResponseEntity<CustomerDetails> response =
        customerDetailsController.getCustomerDetails(customerId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customerDetails, response.getBody());
  }
}
