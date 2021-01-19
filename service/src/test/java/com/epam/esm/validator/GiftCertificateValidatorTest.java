package com.epam.esm.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GiftCertificateValidatorTest {
    @Test
    public void isNameCorrectPositiveTest() {
        boolean actual = GiftCertificateValidator.isNameCorrect("eee");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void isNameCorrectNegativeTest() {
        boolean actual = GiftCertificateValidator.isNameCorrect("eee");
        boolean expected = false;
        assertNotEquals(actual, expected);
    }

    @Test
    public void isDescriptionCorrectPositiveTst() {
        boolean actual = GiftCertificateValidator.isDescriptionCorrect("eee");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void isDescriptionCorrectNegativeTst() {
        boolean actual = GiftCertificateValidator.isDescriptionCorrect("eee");
        boolean expected = false;
        assertNotEquals(actual, expected);
    }

    @Test
    public void isPriceCorrectPositiveTest() {
        boolean actual = GiftCertificateValidator.isPriceCorrect(222);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void isPriceCorrectNegativeTest() {
        boolean actual = GiftCertificateValidator.isPriceCorrect(222);
        boolean expected = false;
        assertNotEquals(actual, expected);
    }

    @Test
    public void isDurationCorrectPositiveTest() {
        boolean actual = GiftCertificateValidator.isDurationCorrect(22);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void isDurationCorrectNegativeTest() {
        boolean actual = GiftCertificateValidator.isDurationCorrect(22);
        boolean expected = false;
        assertNotEquals(actual, expected);
    }
}