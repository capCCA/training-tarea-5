package com.capgemini.training.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.repository.CustomerRepository;
import com.capgemini.training.api.repository.model.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NewCustomerDetailsServiceTest {

  @Mock
  private CustomerRepository repository;

  @InjectMocks
  private NewCustomerDetailsService service;

  @Test
  public void shouldCreateNewCustomerWhenRequestIsValid() {

    doReturn(CustomerDetailsMother.init().getCustomerEntity()).when(repository).save(
        ArgumentMatchers.any(CustomerEntity.class));
    CustomerDetails newCustomer = this.service.createNewCustomer(
        CustomerDetailsMother.init().getCustomerDetails());
    verify(this.repository).save(ArgumentMatchers.any(CustomerEntity.class));
    assertThat(newCustomer).isNotNull();
    assertThat(newCustomer.getName()).isEqualTo("Marck");
    assertThat(newCustomer.getLastName()).isEqualTo("fernandez");
  }
}