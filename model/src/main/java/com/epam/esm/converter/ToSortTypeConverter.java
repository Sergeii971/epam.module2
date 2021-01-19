package com.epam.esm.converter;

import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.exception.IncorrectParameterValueException;

import java.util.Objects;

/**
 * The type ToSortTypeConverter.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class ToSortTypeConverter {
    private ToSortTypeConverter() {
    }

    /**
     * convert To Sort Type.
     *
     * @return the SortType
     */
    public static GiftCertificateQueryParametersDto.SortType convertToSortType(String sortTypeStringFormat) {
        GiftCertificateQueryParametersDto.SortType sortType = null;
        if (Objects.nonNull(sortTypeStringFormat)) {
            try {
                sortType = GiftCertificateQueryParametersDto.SortType.valueOf(sortTypeStringFormat.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IncorrectParameterValueException("incorrect sort type: " + sortTypeStringFormat);
            }
        }
        return sortType;
    }
}
