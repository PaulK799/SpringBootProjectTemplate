package com.paulk.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DemoApplicationStartTest {

    @Test
    public void demoApplicationStartSuccess() {
        DemoApplication.main(new String[]{});
    }
}
