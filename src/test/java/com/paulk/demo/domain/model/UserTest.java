package com.paulk.demo.domain.model;

import com.paulk.demo.model.Role;
import com.paulk.demo.model.User;
import com.paulk.demo.utils.ObjectMapperInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User userJohnLennon;
    private User userJohnLennonLookAlike;
    private User userPaulMcCartney;

    /**
     * Setup {@link EntryTest} for each test.
     */
    @BeforeEach
    public void setup() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        Role roleUser = new Role();
        roleAdmin.setName("USER");

        userJohnLennon = new User();
        userJohnLennon.setUsername("John Lennon");
        userJohnLennon.setPassword("123");
        userJohnLennon.setRole(roleAdmin);

        userJohnLennonLookAlike = new User();
        userJohnLennonLookAlike.setUsername("John Lennon");
        userJohnLennonLookAlike.setPassword("456");
        userJohnLennonLookAlike.setRole(roleUser);

        // Default Constructor used.
        userPaulMcCartney = new User();
        userPaulMcCartney.setUsername("Paul McCartney");
        userPaulMcCartney.setPassword("Paul");
        userPaulMcCartney.setRole(roleUser);
    }

    /**
     * Validates the comparison of two {@link User} objects.
     */
    @Test
    public void userEqualsSuccess() {
        // Setup
        Assertions.assertEquals(userJohnLennon, userJohnLennon, "Assert User equals successfully.");
    }

    /**
     * Validates the comparison of two {@link User} objects.
     */
    @Test
    public void userEqualsDifferentObjectsSuccess() {
        Assertions.assertNotEquals(userJohnLennon, userJohnLennonLookAlike, "Assert User equals successfully.");
    }

    /**
     * Validates the comparison of two {@link User} objects.
     */
    @Test
    public void userEqualsInvalid() {
        Assertions.assertNotEquals(userJohnLennon, userPaulMcCartney, "Assert User equals invalid.");
    }

    /**
     * Validates the comparison of two {@link User} objects.
     */
    @Test
    public void userEqualsNullSuccess() {
        Assertions.assertFalse(userJohnLennon.equals(null), "Assert User equals null successfully.");
    }

    /**
     * Validates the comparison of two {@link User} objects.
     */
    @Test
    public void userCompareHashCodeSuccess() {
        Assertions.assertNotEquals(userJohnLennon.hashCode(), userJohnLennonLookAlike.hashCode(), "Assert User equals successfully.");
    }

    /**
     * Validates the comparison of two {@link User} objects.
     */
    @Test
    public void userCompareHashCodeInvalid() {
        Assertions.assertNotEquals(userJohnLennon.hashCode(), userPaulMcCartney.hashCode(), "Assert User equals invalid.");
    }

    /**
     * Validates the comparison of two {@link User} objects.
     */
    @Test
    public void userGetValueSuccess() {
        Assertions.assertEquals("Paul McCartney", userPaulMcCartney.getUsername(), "Assert User getValue equals successfully.");
    }

    /**
     * Validate the toString method constructs JSON serializable version of {@link User}.
     */
    @Test
    public void toStringSuccess() {
        System.out.println(userJohnLennon.toString());
        Assertions.assertTrue(ObjectMapperInstance.INSTANCE.isValidJson(userJohnLennon.toString()),
                "Assert toString method constructs valid json in the correct format.");
    }
}
