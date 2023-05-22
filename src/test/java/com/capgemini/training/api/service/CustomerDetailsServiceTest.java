package com.capgemini.training.api.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.exceptions.CustomerDetailsException;
import com.capgemini.training.api.exceptions.CustomerNotFoundException;
import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.repository.CustomerRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerDetailsServiceTest {

  @Mock
  private CustomerRepository repository;

  @InjectMocks
  private CustomerDetailsService service;

  @Test
  public void shouldReturnCustomerWhitCustomerIdValid() {

    doReturn(Optional.of(CustomerDetailsMother.init().getCustomerEntity())).when(this.repository)
        .findById(anyString());
    CustomerDetails customerDetails = service.getCustomerDetails("1234");

    assertThat(customerDetails.getName()).isEqualTo("Marck");
    assertThat(customerDetails.getLastName()).isEqualTo("fernandez");
    verify(this.repository).findById(anyString());
  }

  @Test
  public void shouldReturnExceptionWhenCustomerIdIsNull() {
    Assertions.assertThrows(CustomerDetailsException.class,
        () -> this.service.getCustomerDetails(null));
  }

  @Test
  public void shouldReturnExceptionWhenCustomerIdNotFound() {
    doReturn(Optional.ofNullable(null)).when(this.repository)
        .findById(anyString());
    Assertions.assertThrows(CustomerNotFoundException.class,
        () -> this.service.getCustomerDetails("1234"));
  }


}