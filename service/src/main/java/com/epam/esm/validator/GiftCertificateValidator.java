package com.epam.esm.validator;

import com.epam.esm.entity.GiftCertificate;

import java.util.Objects;

/**
 * The type GiftCertificateValidator.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class GiftCertificateValidator {
    private static final String NAME_PATTERN = "^[0-9a-zA-Z-_]{1,45}$";
    private static final String DESCRIPTION_PATTERN = "[^*<>\\\\/{|}]{0,100}";
    private static final double MIN_PRICE = 1;
    private static final double MAX_PRICE = 100000;
    private static final int MIN_DURATION = 1;
    private static final int MAX_DURATION = 100;

    private GiftCertificateValidator(){
    }

    /**
     * Validate gift certificate data.
     *
     * @param certificate the gift certificate
     * @return String
     */
    public static String isGiftCertificateDataCorrect(GiftCertificate certificate) {
        StringBuilder builder = new StringBuilder();
        if (!isNameCorrect(certificate.getName())) {
            builder.append("incorrect certificate name");
            builder.append("\n");
        }
        if (!isDescriptionCorrect(certificate.getDescription())) {
            builder.append("incorrect description");
            builder.append("\n");
        }
        if (!isPriceCorrect(certificate.getPrice())) {
            builder.append("incorrect price");
            builder.append("\n");
        }
        if (!isDurationCorrect(certificate.getDuration())) {
            builder.append("incorrect duration");
            builder.append("\n");
        }
        return builder.toString();
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

    /**
     * Validate description.
     *
     * @param description the description
     * @return boolean
     */
    public static boolean isDescriptionCorrect(String description) {
        return (Objects.nonNull(description) && description.matches(DESCRIPTION_PATTERN));
    }

    /**
     * Validate price.
     *
     * @param price the price
     * @return boolean
     */
    public static boolean isPriceCorrect(double price) {
        return (price >= MIN_PRICE && price <= MAX_PRICE);
    }

    /**
     * Validate duration.
     *
     * @param duration the duration
     * @return boolean
     */
    public static boolean isDurationCorrect(int duration) {
        boolean result = true;

        if (duration < MIN_DURATION || duration > MAX_DURATION) {
            result = false;
        }
        return result;
    }
}
