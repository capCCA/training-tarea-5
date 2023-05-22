package com.capgemini.training.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.capgemini.training.api.BaseIntegrationTest;
import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.repository.model.CustomerEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class CustomerRepositoryIntegrationTest extends BaseIntegrationTest {


  @Autowired
  private CustomerRepository repository;

  @Test
  public void shouldSaveCustomerDetails() {
    repository.save(CustomerDetailsMother.init().withCustomerId("1234").getCustomerEntity());
    repository.save(CustomerDetailsMother.init().withCustomerId("1233").getCustomerEntity());
    repository.save(CustomerDetailsMother.init().withCustomerId("5131").getCustomerEntity());

    List<CustomerEntity> customerEntities = repository.findAll();

    assertThat(customerEntities.size()).isEqualTo(3);
  }

}