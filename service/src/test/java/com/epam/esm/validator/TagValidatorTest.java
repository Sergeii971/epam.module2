package com.epam.esm.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class TagValidatorTest {

    @Test
    public void isNameCorrectPositiveTest() {
        boolean actual = TagValidator.isNameCorrect("eee");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void isNameCorrectNegativeTest() {
        boolean actual = TagValidator.isNameCorrect("eee");
        boolean expected = false;
        assertNotEquals(actual, expected);
    }
}