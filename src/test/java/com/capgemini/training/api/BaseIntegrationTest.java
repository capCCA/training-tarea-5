package com.capgemini.training.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;


public abstract class BaseIntegrationTest {

  static PostgreSQLContainer postgres;

  @BeforeAll
  static void setUp() {
    postgres = new PostgreSQLContainer("postgres:latest")
        .withDatabaseName("test-db")
        .withUsername("test-user")
        .withPassword("test-password");

    postgres.start();
  }

  @AfterAll
  static void tearDown() {
    postgres.stop();
  }
}
