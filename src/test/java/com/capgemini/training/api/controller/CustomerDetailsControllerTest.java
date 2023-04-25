package com.capgemini.training.api.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.service.CustomerDetailsService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CustomerDetailsControllerTest{

  @Mock
  private CustomerDetailsService customerDetailsService;

  @InjectMocks private CustomerDetailsController customerDetailsController;


  @Test
  public void shouldReturnCustomerDetailsWhenCustomerIdIsValid() {
    String customerId = UUID.randomUUID().toString();
    CustomerDetails response =CustomerDetailsMother.init()
        .withCustomerId(customerId)
        .get(); ;
    when(customerDetailsService.getCustomerDetails(anyString())).thenReturn(response);

    ResponseEntity<CustomerDetails> responseEntity =
        customerDetailsController.getCustomerDetails(customerId);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertSame(response, responseEntity.getBody());


  }
}
