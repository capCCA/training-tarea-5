package com.capgemini.training.api.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.capgemini.training.api.repository.CustomerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerDetailsServiceTest {

  @Mock
  private CustomerRepository repository;

  @InjectMocks
  private DeleteCustomerDetailsService service;

  @ParameterizedTest
  @ValueSource(strings = {"1234", "3456"})
  public void shouldDeleteCustomerWhenCustomerIdIsValid(String value) {

    this.service.deleteCustomerDetails(value);

    verify(this.repository).deleteById(eq(value));
  }
}