package com.techchallenge4.ms_logistica.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractContainerIT {

    static {
        SingletonPostgreSQLContainer.getInstance().start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", SingletonPostgreSQLContainer.getInstance()::getJdbcUrl);
        registry.add("spring.datasource.username", SingletonPostgreSQLContainer.getInstance()::getUsername);
        registry.add("spring.datasource.password", SingletonPostgreSQLContainer.getInstance()::getPassword);
    }

}
