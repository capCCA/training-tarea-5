package com.capgemini.training.api.controller;

import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.model.UpdateCustomerRequest;
import com.capgemini.training.api.service.UpdateCustomerDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UpdateCustomerDetailsControllerTest {

  @Mock
  private UpdateCustomerDetailsService updateCustomerDetailsService;

  @InjectMocks
  private UpdateCustomerDetailsController updateCustomerDetailsController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
 public void updateCustomerDetailsTest() {

    UpdateCustomerRequest customerRequest = new UpdateCustomerRequest();
    CustomerDetails customerDetails = new CustomerDetails();
    when(updateCustomerDetailsService.updateCustomerDetails(any())).thenReturn(customerDetails);

    ResponseEntity<CustomerDetails> response = updateCustomerDetailsController.updateCustomerDetails(customerRequest);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customerDetails, response.getBody());
  }
}

