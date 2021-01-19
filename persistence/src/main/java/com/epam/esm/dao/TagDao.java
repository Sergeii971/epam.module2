package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * The interface TagDao.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface TagDao {
    /**
     * Add tag
     *
     * @param tag the tag
     */
    Tag add(Tag tag);

    /**
     * Find all tags.
     *
     * @return the list of found tags
     */
    List<Tag> findAll();

    /**
     * Find tag by id.
     *
     * @return optional
     */
    Optional<Tag> findById(long tagId);

    /**
     * Remove tag
     *
     * @param tagId the tag id
     */
    void remove(long tagId);

    /**
     * Remove gift certificate has tag
     *
     * @param id the tag id
     */
    void removeGiftCertificateHasTag(long id);

    /**
     * Find all tags by gift certificate id.
     *
     * @return the list of found tags
     */
    List<Tag> findByGiftCertificateId(long certificateId);

    /**
     * Find tag by name.
     *
     * @return optional
     */
    Optional<Tag> findByName(String name);
}
