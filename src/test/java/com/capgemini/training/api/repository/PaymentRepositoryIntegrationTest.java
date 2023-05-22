package com.capgemini.training.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.capgemini.training.api.BaseIntegrationTest;
import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.repository.model.BeneficiaryEntity;
import com.capgemini.training.api.repository.model.CustomerEntity;
import com.capgemini.training.api.repository.model.PaymentEntity;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class PaymentRepositoryIntegrationTest extends BaseIntegrationTest {


  @Autowired
  private PaymentRepository repository;

  @Test
  public void shouldSaveCustomerDetails() {
    repository.save(PaymentEntity.builder().paymentId(1L)
            .customer(CustomerDetailsMother.init().withCustomerId("1234").getCustomerEntity())
            .beneficiary(new BeneficiaryEntity("1234"))
            .paymentType("BANK")
            .amount(new BigDecimal(100))
        .build());
    List<PaymentEntity> all = repository.findAll();

    assertThat(all.size()).isEqualTo(1);
  }

}