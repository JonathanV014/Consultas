package com.example.demo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.xml.namespace.QName;

@TestConfiguration(proxyBeanMethods = false)
public class ContainerConfiguracion {
    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> containerPostgreSQL() {
        return new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("0914");
    }
}
