package com.capgemini.training.api.service;

import com.capgemini.training.api.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomerDetailsService {

  private CustomerRepository customerRepository;

  public void deleteCustomerDetails(String customerId) {
    if (Strings.isEmpty(customerId)) {
      throw new IllegalArgumentException();
    }
    customerRepository.deleteById(customerId);
  }
}
