package com.paulk.demo.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * A test to verify the configuration properties accessible from the {@link DemoApplicationConfig}.
 */
public class DemoApplicationConfigTest {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 6379;
    private static final String DUMMY_PASSWORD = "password123";

    @Mock
    private DemoApplicationConfig demoApplicationConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Validate the {@link {@link DemoApplicationConfig#getHostname()}}.
     */
    @Test
    public void testDefaultSpringRedisHostName() {
        Mockito.when(demoApplicationConfig.getHostname()).thenReturn(HOSTNAME);
        Assertions.assertEquals(HOSTNAME, demoApplicationConfig.getHostname());
    }

    /**
     * Validate the {@link {@link DemoApplicationConfig#getPort()} ()}}.
     */
    @Test
    public void testDefaultSpringRedisPort() {
        Mockito.when(demoApplicationConfig.getPort()).thenReturn(PORT);
        Assertions.assertEquals(PORT, demoApplicationConfig.getPort());
    }

    /**
     * Validate the {@link {@link DemoApplicationConfig#getPassword()} ()} ()}}.
     */
    @Test
    public void testDefaultSpringRedisPassword() {
        Mockito.when(demoApplicationConfig.getPassword()).thenReturn(DUMMY_PASSWORD);
        Assertions.assertEquals(DUMMY_PASSWORD, demoApplicationConfig.getPassword());
    }
}
