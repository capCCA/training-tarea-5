package com.capgemini.training.api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.capgemini.training.api.service.DeleteCustomerDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerDetailsControllerTest {

  @Mock private DeleteCustomerDetailsService deleteCustomerDetailsService;

  @InjectMocks private DeleteCustomerDetailsController controller;

  // TODO
  // https://stackoverflow.com/questions/36220889/whats-the-purpose-of-the-junit-5-nested-annotation
  // Echale un ojo, es algo interesante ;) Para este caso no lo aplicaría pero así lo veis :P
  @Nested
  @DisplayName("Given a DELETE request to /capgemini/training/customer/{customerId}")
  public class DeleteCustomerDetails {

    @Test
    @DisplayName("When the customer exists, then return 204 NO_CONTENT")
    public void testDeleteCustomerDetailsSuccess() {

      String customerId = "12345";
      doNothing().when(deleteCustomerDetailsService).deleteCustomerDetails(customerId);

      ResponseEntity<Void> response = controller.deleteCustomerDetails(customerId);

      Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
      verify(deleteCustomerDetailsService).deleteCustomerDetails(customerId);
    }
  }
}
