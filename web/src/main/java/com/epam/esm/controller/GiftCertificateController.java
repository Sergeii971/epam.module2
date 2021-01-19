package com.epam.esm.controller;

import com.epam.esm.converter.ToOrderTypeConverter;
import com.epam.esm.converter.ToSortTypeConverter;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type GiftCertificateController.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/giftCertificates")
public class GiftCertificateController {
    private final GiftCertificateService service;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.service = giftCertificateService;
    }

    /**
     * Add gift certificate
     *
     * @param giftCertificateDto the gift certificate dto
     */
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {
        service.add(giftCertificateDto);
    }

    /**
     * Remove gift certificate
     *
     * @param id the certificate id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGiftCertificate(@PathVariable(value = "id") long id) {
        service.remove(id);
    }

    /**
     * Find gift certificate by id.
     *
     * @return GiftCertificateDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GiftCertificateDto> findGiftCertificateById(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(service.findGiftCertificateById(id), HttpStatus.OK);
    }

    /**
     * update gift certificate
     *
     * @param giftCertificateDto the gift certificate dto
     */
    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto updateGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {
        return service.updateGiftCertificate(giftCertificateDto);
    }

    /**
     * Find all gift certificate by search parameters.
     *
     * @param tagName the tag name
     * @param description the description
     * @param name the gift certificate name
     * @param sortType the sort type
     * @param orderType the order type
     * @return the list of found gift certificate
     */
    @RequestMapping(method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificateDto> findGiftCertificatesByParameters(@RequestParam(value = "tagName",
            required = false) String tagName, @RequestParam(value = "name", required = false) String name,
                                                                     @RequestParam(value = "description",
                                                                             required = false) String description,
                                                                     @RequestParam(value = "sortType", required = false)
                                                                                 String sortType,
                                                                     @RequestParam(value = "orderType", required = false)
                                                                                 String orderType) {
        GiftCertificateQueryParametersDto.OrderType orderType1 = ToOrderTypeConverter.convertToOrderType(orderType);
        GiftCertificateQueryParametersDto.SortType sortType1 = ToSortTypeConverter.convertToSortType(sortType);
        GiftCertificateQueryParametersDto giftCertificateQueryParametersDto = new GiftCertificateQueryParametersDto(
                tagName, name, description, sortType1, orderType1);
        return service.findGiftCertificatesByParameters(giftCertificateQueryParametersDto);
    }
}
