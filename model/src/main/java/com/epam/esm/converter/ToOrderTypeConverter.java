package com.epam.esm.converter;

import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.exception.IncorrectParameterValueException;

import java.util.Objects;

/**
 * The type ToOrderTypeConverter.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class ToOrderTypeConverter {
    private ToOrderTypeConverter() {
    }

    /**
     * convert To Order Type.
     *
     * @return the OrderType
     */
    public static GiftCertificateQueryParametersDto.OrderType convertToOrderType(String orderTypeStringFormat) {
        GiftCertificateQueryParametersDto.OrderType orderType = null;
        if (Objects.nonNull(orderTypeStringFormat)) {
            try {
                orderType = GiftCertificateQueryParametersDto.OrderType.valueOf(orderTypeStringFormat.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IncorrectParameterValueException("incorrect order type: " + orderTypeStringFormat);
            }
        }
        return orderType;
    }
}
