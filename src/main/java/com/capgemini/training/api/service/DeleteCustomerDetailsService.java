package com.capgemini.training.api.service;

import com.capgemini.training.api.repository.CustomerRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerDetailsService {

  private final CustomerRepository customerRepository;

  public DeleteCustomerDetailsService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public void deleteCustomerDetails(String customerId) {
    if (Strings.isEmpty(customerId)) {
      throw new IllegalArgumentException();
    }
    customerRepository.deleteById(customerId);
  }
}
