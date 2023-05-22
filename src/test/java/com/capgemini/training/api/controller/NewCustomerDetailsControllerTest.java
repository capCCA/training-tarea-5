package com.capgemini.training.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.service.NewCustomerDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("NewCustomerDetailsController Tests")
@ExtendWith(MockitoExtension.class)
public class NewCustomerDetailsControllerTest {

  @Mock
  private NewCustomerDetailsService newCustomerDetailsService;
  private NewCustomerDetailsController newCustomerDetailsController;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    newCustomerDetailsController = new NewCustomerDetailsController(newCustomerDetailsService);
  }

  // TODO
  // https://stackoverflow.com/questions/36220889/whats-the-purpose-of-the-junit-5-nested-annotation
  // Echale un ojo, es algo interesante ;) Para este caso no lo aplicaría pero así lo veis :P
  @Nested
  @DisplayName("Create New Customer Details Tests")
  public class CreateNewCustomerDetailsTests {

    @Test
    @DisplayName(
        "When createNewCustomerDetails is called with valid customer details, then it should return 200 OK status")
    public void testCreateNewCustomerDetailsWithValidCustomerDetails() {

      CustomerDetails customerDetails = CustomerDetails.builder().customerId("12345").build();
      when(newCustomerDetailsService.createNewCustomer(customerDetails))
          .thenReturn(customerDetails);

      ResponseEntity<CustomerDetails> responseEntity =
          newCustomerDetailsController.createNewCustomerDetails(customerDetails);

      assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
      assertEquals(customerDetails, responseEntity.getBody());
    }
  }
}
