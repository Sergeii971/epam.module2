package com.epam.esm.service.impl;

import com.epam.esm.configuration.ServiceConfiguration;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = { ServiceConfiguration.class, GiftCertificateServiceImpl.class, TagServiceImpl.class,
        GiftCertificateDaoImpl.class})
class GiftCertificateServiceImplTest {

    @Autowired
    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    @Autowired
    @Mock
    private GiftCertificateDaoImpl dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        giftCertificates.add(new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, tags));
        List<GiftCertificateDto> expected = new ArrayList<>();
        expected.add(new GiftCertificateDto(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, new ArrayList<>()));
        Mockito.when(dao.findAll()).thenReturn(giftCertificates);
        assertEquals(giftCertificateService.findAll(), expected);
    }

    @Test
    public void findAllNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        giftCertificates.add(new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, tags));
        List<GiftCertificateDto> expected = new ArrayList<>();
        expected.add(new GiftCertificateDto(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, null));
        Mockito.when(dao.findAll()).thenReturn(giftCertificates);
        assertNotEquals(giftCertificateService.findAll(), expected);
    }

    @Test
    public void findGiftCertificateByIdPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration,
                createDate, lastUpdateDate, tags);
        Mockito.doReturn(Optional.of(giftCertificate)).when(dao).findById(1);
        GiftCertificateDto expected = new GiftCertificateDto(certificateId, name, description, price, duration,
                createDate, lastUpdateDate, new ArrayList<>());
        assertEquals(giftCertificateService.findGiftCertificateById(1), expected);
    }

    @Test
    public void findGiftCertificateByIdNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration,
                createDate, lastUpdateDate, tags);
        Mockito.doReturn(Optional.of(giftCertificate)).when(dao).findById(1);
        GiftCertificateDto expected = new GiftCertificateDto(certificateId, name, description, price, duration,
                createDate, lastUpdateDate, null);
        assertNotEquals(giftCertificateService.findGiftCertificateById(1), expected);
    }

    @Test
    public void findGiftCertificateByIdExceptionTest() {
        Mockito.doReturn(Optional.empty()).when(dao).findById(1);
        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.findGiftCertificateById(1));
    }
}