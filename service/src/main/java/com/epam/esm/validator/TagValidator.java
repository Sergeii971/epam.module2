package com.epam.esm.validator;

import java.util.Objects;

/**
 * The type TagValidator.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class TagValidator {
    private static final String NAME_PATTERN = "^[0-9a-zA-Z-_]{1,45}$";

    private TagValidator() {
    }

    /**
     * Validate name.
     *
     * @param name the name
     * @return boolean
     */
    public static boolean isNameCorrect(String name) {
        return (Objects.nonNull(name) && name.matches(NAME_PATTERN));
    }
}
