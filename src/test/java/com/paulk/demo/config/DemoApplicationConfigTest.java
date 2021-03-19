package com.paulk.demo.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A test to verify the configuration properties accessible from the {@link DemoApplicationConfig}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DemoApplicationConfig.class)
@SpringBootTest()
public class DemoApplicationConfigTest {

    @Autowired
    private DemoApplicationConfig demoApplicationConfig;

    /**
     * Validate the {@link {@link DemoApplicationConfig#getHostname()}}.
     */
    @Test
    public void testDefaultSpringRedisHostName() {
        Assertions.assertEquals("localhost", demoApplicationConfig.getHostname());
    }

    /**
     * Validate the {@link {@link DemoApplicationConfig#getPort()} ()}}.
     */
    @Test
    public void testDefaultSpringRedisPort() {
        Assertions.assertEquals(6379, demoApplicationConfig.getPort());
    }

    /**
     * Validate the {@link {@link DemoApplicationConfig#getPassword()} ()} ()}}.
     */
    @Test
    public void testDefaultSpringRedisPassword() {
        Assertions.assertEquals("", demoApplicationConfig.getPassword());
    }
}
