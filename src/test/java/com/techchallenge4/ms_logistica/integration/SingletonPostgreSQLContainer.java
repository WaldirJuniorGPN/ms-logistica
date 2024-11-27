package com.techchallenge4.ms_logistica.integration;

import org.testcontainers.containers.PostgreSQLContainer;

public class SingletonPostgreSQLContainer extends PostgreSQLContainer<SingletonPostgreSQLContainer> {

    private static final String IMAGE_VERSION = "postgres:16-alpine";
    private static SingletonPostgreSQLContainer container;

    private SingletonPostgreSQLContainer() {
        super(IMAGE_VERSION);
    }

    public static SingletonPostgreSQLContainer getInstance() {
        if (container == null) {
            container = new SingletonPostgreSQLContainer();
        }
        return container;
    }

}