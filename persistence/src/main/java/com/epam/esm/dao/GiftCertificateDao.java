package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;

import java.util.List;
import java.util.Optional;

/**
 * The interface GiftCertificateDao.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface GiftCertificateDao {
    /**
     * Add gift certificate
     *
     * @param giftCertificate the gift certificate
     */
    GiftCertificate add(GiftCertificate giftCertificate);

    /**
     * Find gift certificate by id.
     *
     * @return optional
     */
    Optional<GiftCertificate> findById(long certificateId);

    /**
     * Find all gift certificate.
     *
     * @return the list of found gift certificate
     */
    List<GiftCertificate> findAll();

    /**
     * Find all gift certificate by search parameters.
     *
     * @param giftCertificateQueryParameters the gift certificate query parameter
     * @return the list of found gift certificate
     */
    List<GiftCertificate> findByQueryParameters(GiftCertificateQueryParameters giftCertificateQueryParameters);

    /**
     * Remove gift certificate
     *
     * @param certificateId the certificate id
     */
    void remove(long certificateId);

    /**
     * Remove gift certificate has tag
     *
     * @param certificateId the certificate id
     */
    void removeGiftCertificateHasTag(long certificateId);

    /**
     * Add gift certificate has tag
     *
     * @param giftCertificate the gift certificate
     */
    void addGiftCertificateHasTag(GiftCertificate giftCertificate);

    /**
     * update gift certificate
     *
     * @param giftCertificate the gift certificate
     */
    GiftCertificate update(GiftCertificate giftCertificate);
}
