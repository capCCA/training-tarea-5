package com.capgemini.training.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.capgemini.training.api.BaseIntegrationTest;
import com.capgemini.training.api.controller.CustomerDetailsMother;
import com.capgemini.training.api.repository.model.BeneficiaryEntity;
import com.capgemini.training.api.repository.model.CustomerEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class BeneficiaryRepositoryIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private BeneficiaryRepository repository;

  @Test
  public void shouldSaveBeneficiaryEntity() {
    repository.save(new BeneficiaryEntity("test"));
    repository.save(new BeneficiaryEntity("test2"));
    repository.save(new BeneficiaryEntity("test3"));

    List<BeneficiaryEntity> beneficiaryEntities = repository.findAll();

    assertThat(beneficiaryEntities.size()).isEqualTo(3);
  }

}