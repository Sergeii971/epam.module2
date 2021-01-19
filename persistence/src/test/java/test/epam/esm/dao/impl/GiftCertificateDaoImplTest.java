package test.epam.esm.dao.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class GiftCertificateDaoImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private GiftCertificateDaoImpl giftCertificateDao;
    private static final String CREATE_TABLE_QUERY_FILENAME = "createDatabase.sql";

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
    .addScript(CREATE_TABLE_QUERY_FILENAME).setType(EmbeddedDatabaseType.H2)
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        giftCertificateDao = new GiftCertificateDaoImpl(jdbcTemplate);
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void addPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, tags);
        GiftCertificate giftCertificate1 = giftCertificateDao.add(giftCertificate);
        long addedGiftCertificateId = giftCertificate1.getCertificateId();
        Optional<GiftCertificate> actual = giftCertificateDao.findById(addedGiftCertificateId);
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description,
                price, duration, createDate, lastUpdateDate, tags));
        expected.get().setCertificateId(addedGiftCertificateId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, tags);
        GiftCertificate giftCertificate1 = giftCertificateDao.add(giftCertificate);
        long addedGiftCertificateId = giftCertificate1.getCertificateId();
        Optional<GiftCertificate> actual = giftCertificateDao.findById(addedGiftCertificateId);
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description,
                price, duration, createDate, lastUpdateDate, tags));
        
        expected.get().setCertificateId(addedGiftCertificateId);
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void findByIdPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description, price,
                duration, createDate, lastUpdateDate, tags));
        Optional<GiftCertificate> actual = giftCertificateDao.findById(certificateId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByIdNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 0;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description, price,
                duration, createDate, lastUpdateDate, tags));
        Optional<GiftCertificate> actual = giftCertificateDao.findById(certificateId);
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void findAllPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(new GiftCertificate(certificateId, name, description, price, duration, createDate, lastUpdateDate, tags));
        expected.add(new GiftCertificate(2, name, description, price, duration, createDate, lastUpdateDate, tags));
        List<GiftCertificate> actual = giftCertificateDao.findAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(new GiftCertificate(certificateId, name, description, price, duration, createDate, lastUpdateDate, tags));
        List<GiftCertificate> actual = giftCertificateDao.findAll();
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void removePositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(new GiftCertificate(certificateId, name, description, price, duration, createDate, lastUpdateDate, tags));
        giftCertificateDao.remove(2);
        List<GiftCertificate> actual = giftCertificateDao.findAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void removeNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(new GiftCertificate(certificateId, name, description, price, duration, createDate, lastUpdateDate, tags));
        giftCertificateDao.remove(1);
        List<GiftCertificate> actual = giftCertificateDao.findAll();
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void updatePositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, tags);
        GiftCertificate expected = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, tags);
        GiftCertificate actual = giftCertificateDao.update(giftCertificate);
        expected.setCertificateId(actual.getCertificateId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updateNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        double price = 12;
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, tags);
        GiftCertificate expected = new GiftCertificate(2, name, description, price, duration, createDate,
                lastUpdateDate, tags);
        GiftCertificate actual = giftCertificateDao.update(giftCertificate);
        expected.setCertificateId(actual.getCertificateId());
        Assertions.assertEquals(expected, actual);
    }
}