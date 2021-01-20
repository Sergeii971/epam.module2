package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type GiftCertificateDaoImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper giftCertificateMapper = new GiftCertificateMapper();
    private static final int NAME_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int PRICE_INDEX = 3;
    private static final int DURATION_INDEX = 4;
    private static final int CREATE_DATE_INDEX = 5;
    private static final int LAST_UPDATE_DATE_INDEX = 6;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate add(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(DatabaseQuery.ADD_GIFT_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(NAME_INDEX, giftCertificate.getName());
            statement.setString(DESCRIPTION_INDEX, giftCertificate.getDescription());
            statement.setBigDecimal(PRICE_INDEX, giftCertificate.getPrice());
            statement.setInt(DURATION_INDEX, giftCertificate.getDuration());
            statement.setTimestamp(CREATE_DATE_INDEX, Timestamp.valueOf(giftCertificate.getCreateDate()));
            statement.setTimestamp(LAST_UPDATE_DATE_INDEX, Timestamp.valueOf(giftCertificate.getLastUpdateDate()));
            return statement;
        }, keyHolder);
        if (Objects.nonNull(keyHolder.getKey())) {
            giftCertificate.setCertificateId(keyHolder.getKey().longValue());
        }
        return giftCertificate;
    }

    @Override
    public Optional<GiftCertificate> findById(long certificateId) {
        return jdbcTemplate.query(DatabaseQuery.FIND_CERTIFICATE_BY_ID, new Object[]{certificateId},
                giftCertificateMapper)
                .stream()
                .findFirst();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(DatabaseQuery.FIND_ALL_GIFT_CERTIFICATE, giftCertificateMapper);
    }

    @Override
    public List<GiftCertificate> findByQueryParameters(GiftCertificateQueryParameters parameters) {
        String query = new StringBuilder()
                .append(DatabaseQuery.FIND_BY_QUERY_PARAMETERS)
                .append(parameters.getSortType().getSortType())
                .append(" ")
                .append(parameters.getOrderType().getOrderType())
                .toString();
        return jdbcTemplate.query(query, new Object[] {
                parameters.getTagName(),
                parameters.getName(),
                parameters.getDescription(),
        }, giftCertificateMapper);
    }

    @Override
    public void remove(long certificateId) {
        jdbcTemplate.update(DatabaseQuery.REMOVE_GIFT_CERTIFICATE, certificateId);
    }

    @Override
    public void removeGiftCertificateHasTag(long certificateId) {
        jdbcTemplate.update(DatabaseQuery.GIFT_CERTIFICATE_REMOVE_GIFT_CERTIFICATE_HAS_TAG, certificateId);
    }

    @Override
    public void addGiftCertificateHasTag(GiftCertificate giftCertificate) {
        List<Tag> tags = giftCertificate.getTags();
        tags.forEach(tag -> jdbcTemplate.update(DatabaseQuery.ADD_GIFT_CERTIFICATE_HAS_TAG, giftCertificate.getCertificateId(),
                tag.getTagId()));
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(DatabaseQuery.UPDATE_GIFT_CERTIFICATE, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(), Timestamp.valueOf(giftCertificate.getCreateDate()),
                Timestamp.valueOf(giftCertificate.getLastUpdateDate()), giftCertificate.getCertificateId());
        return giftCertificate;
    }

}
