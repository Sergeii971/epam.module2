package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.GiftCertificateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type GiftCertificateServiceImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao giftCertificateDao;
    private final TagService tagService;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String EMPTY_VALUE = "";

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
    }

    @Override
    public void add(GiftCertificateDto giftCertificateDto) {
        addAndSetTags(giftCertificateDto);
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        String errorMessage = GiftCertificateValidator.isGiftCertificateDataCorrect(giftCertificate);
        if (!errorMessage.isEmpty()) {
            throw new IncorrectParameterValueException(errorMessage);
        }
        LocalDateTime currentTime = LocalDateTime.now();
        giftCertificate.setCreateDate(currentTime);
        giftCertificate.setLastUpdateDate(currentTime);
        giftCertificateDao.add(giftCertificate);
        giftCertificateDao.addGiftCertificateHasTag(giftCertificate);
    }

    @Override
    public void remove(long certificateId) {
        Optional<GiftCertificate> giftCertificate = giftCertificateDao.findById(certificateId);

        if (!giftCertificate.isPresent()) {
            throw new ResourceNotFoundException("Gift certificate with id " + certificateId + " not found.");
        }
        giftCertificateDao.removeGiftCertificateHasTag(certificateId);
        giftCertificateDao.remove(certificateId);
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findAll();
        return giftCertificates.stream().map(giftCertificate1 -> modelMapper.map(giftCertificate1, GiftCertificateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto updateGiftCertificate(GiftCertificateDto modifiedGiftCertificateDto) {
        GiftCertificate modifiedGiftCertificate = modelMapper.map(modifiedGiftCertificateDto, GiftCertificate.class);
        String errorMessage = GiftCertificateValidator.isGiftCertificateDataCorrect(modifiedGiftCertificate);

        if (!errorMessage.isEmpty()) {
            throw new IncorrectParameterValueException(errorMessage);
        }
        Optional<GiftCertificate> foundGiftCertificate = giftCertificateDao.findById(modifiedGiftCertificateDto
                .getCertificateId());
        if (!foundGiftCertificate.isPresent()) {
            throw new ResourceNotFoundException("Gift certificate with id " + modifiedGiftCertificateDto
                    .getCertificateId() + " not found.");
        }
        updateGiftCertificateFields(foundGiftCertificate.get(), modifiedGiftCertificate);
        GiftCertificate updatedGiftCertificate = giftCertificateDao.update(foundGiftCertificate.get());
        return modelMapper.map(updatedGiftCertificate, GiftCertificateDto.class);
    }

    @Override
    public GiftCertificateDto findGiftCertificateById(long certificateId) {
        Optional<GiftCertificate> foundGiftCertificate = giftCertificateDao.findById(certificateId);
        return foundGiftCertificate
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Gift certificate with id " + certificateId + " not found."));
    }

    @Override
    public List<GiftCertificateDto> findGiftCertificatesByParameters(
            GiftCertificateQueryParametersDto giftCertificateQueryParametersDto) {
        GiftCertificateQueryParameters giftCertificateQueryParameters
                = modelMapper.map(giftCertificateQueryParametersDto, GiftCertificateQueryParameters.class);
        List<GiftCertificate> foundGiftCertificates;
        if (isAllQueryParametersNull(giftCertificateQueryParameters)) {
            foundGiftCertificates = giftCertificateDao.findAll();
        } else {
            prepareParametersForRequest(giftCertificateQueryParameters);
            foundGiftCertificates = giftCertificateDao.findByQueryParameters(giftCertificateQueryParameters);
        }
        return foundGiftCertificates.stream().map(this::convertGiftCertificateAndSetTags).collect(Collectors.toList());
    }

    private GiftCertificateDto convertGiftCertificateAndSetTags(GiftCertificate giftCertificate) {
        GiftCertificateDto giftCertificateDto = modelMapper.map(giftCertificate, GiftCertificateDto.class);
        giftCertificateDto.setTags(tagService.findTagsByGiftCertificateId(giftCertificateDto.getCertificateId()));
        return giftCertificateDto;
    }


    private void addAndSetTags(GiftCertificateDto giftCertificateDto) {
        List<TagDto> tags = new ArrayList<>();
        if (Objects.nonNull(giftCertificateDto.getTags())) {
            for (TagDto tagDto : giftCertificateDto.getTags()) {
                TagDto add = tagService.add(tagDto);
                tags.add(add);
            }
        }
        giftCertificateDto.setTags(tags);
    }

    private boolean isAllQueryParametersNull(GiftCertificateQueryParameters parameters) {
        return (Objects.isNull(parameters.getTagName()) && Objects.isNull(parameters.getName())
                && Objects.isNull(parameters.getDescription()) && Objects.isNull(parameters.getSortType()) &&
                Objects.isNull(parameters.getOrderType()));
    }

    private void prepareParametersForRequest(GiftCertificateQueryParameters parameters) {
        if (Objects.isNull(parameters.getTagName())) {
            parameters.setTagName(EMPTY_VALUE);
        }
        if (Objects.isNull(parameters.getName())) {
            parameters.setName(EMPTY_VALUE);
        }
        if (Objects.isNull(parameters.getDescription())) {
            parameters.setDescription(EMPTY_VALUE);
        }
        if (Objects.isNull(parameters.getSortType())) {
            parameters.setSortType(GiftCertificateQueryParameters.SortType.DEFAULT);
            parameters.setOrderType(GiftCertificateQueryParameters.OrderType.DEFAULT);
        } else {
            if (Objects.isNull(parameters.getOrderType())) {
                parameters.setOrderType(GiftCertificateQueryParameters.OrderType.DEFAULT);
            }
        }
    }

    private void updateGiftCertificateFields(GiftCertificate foundGiftCertificate, GiftCertificate modifiedGiftCertificate) {
        if (modifiedGiftCertificate.getDuration() != 0) {
            foundGiftCertificate.setDuration(modifiedGiftCertificate.getDuration());
        }
        if (Objects.nonNull(modifiedGiftCertificate.getName())) {
            foundGiftCertificate.setName(modifiedGiftCertificate.getName());
        }
        if (Objects.nonNull(modifiedGiftCertificate.getDescription())) {
            foundGiftCertificate.setDescription(modifiedGiftCertificate.getDescription());
        }
        if (modifiedGiftCertificate.getPrice() != 0) {
            foundGiftCertificate.setPrice(modifiedGiftCertificate.getPrice());
        }
        foundGiftCertificate.setLastUpdateDate(LocalDateTime.now());
    }
}
