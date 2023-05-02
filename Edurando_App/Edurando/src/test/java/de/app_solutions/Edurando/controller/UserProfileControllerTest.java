package de.app_solutions.Edurando.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserProfileControllerTest {
    @Autowired
    private UserProfileController userProfileController;



    @Test
    public void controllerNotNull() throws Exception {
        assertThat(userProfileController).isNotNull();
    }
}
