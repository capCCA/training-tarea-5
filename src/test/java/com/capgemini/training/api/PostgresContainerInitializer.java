package com.capgemini.training.api;


import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class PostgresContainerInitializer {

  private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
      new PostgreSQLContainer<>("postgres:latest")
          .withDatabaseName("test")
          .withUsername("test")
          .withPassword("test");
  @Container
  public static final GenericContainer<?> CONTAINER = POSTGRESQL_CONTAINER;

  static {
    POSTGRESQL_CONTAINER.start();
  }
}