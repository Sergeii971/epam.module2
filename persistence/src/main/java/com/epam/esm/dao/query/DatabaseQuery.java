package com.epam.esm.dao.query;

/**
 * The type DatabaseQuery.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class DatabaseQuery {
    public static final String ADD_GIFT_CERTIFICATE = "INSERT INTO gift_certificate(name, description, price, "
            + "duration, createDate, lastUpdateDate) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND_ALL_GIFT_CERTIFICATE = "SELECT certificateId, name, description," +
            " price, duration, createDate, lastUpdateDate FROM gift_certificate";
    public static final String REMOVE_GIFT_CERTIFICATE = "DELETE FROM gift_certificate WHERE certificateId = ?";
    public static final String FIND_CERTIFICATE_BY_ID = FIND_ALL_GIFT_CERTIFICATE + " WHERE certificateId = ?";
    public static final String ADD_GIFT_CERTIFICATE_HAS_TAG = "INSERT INTO gift_certificate_has_tag "
            + "(gift_certificate_giftId, tag_tagId) VALUES (?, ?)";
    public static final String GIFT_CERTIFICATE_REMOVE_GIFT_CERTIFICATE_HAS_TAG = "DELETE FROM gift_certificate_has_tag "
            + "WHERE gift_certificate_giftId = ?";
    public static final String FIND_BY_QUERY_PARAMETERS = "SELECT certificateId, gift_certificate.name, "
            + "description, price, duration, createDate, lastUpdateDate FROM gift_certificate "
            + "LEFT JOIN gift_certificate_has_tag ON gift_certificate.certificateId = gift_certificate_giftId "
            + "LEFT JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId WHERE tag.name LIKE concat(?, '%') AND " +
            "gift_certificate.name LIKE concat(?, '%') AND description LIKE concat(?, '%') GROUP BY certificateId ";
    public static final String UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificate SET "
            + "name = ?, description = ?, price = ?, duration = ?, createDate = ?, "
            + "lastUpdateDate = ? WHERE certificateId = ?";

    public static final String ADD_TAG = "INSERT INTO tag(name) VALUES (?)";
    public static final String FIND_ALL_TAGS = "SELECT tagId, name FROM tag";
    public static final String FIND_TAG_BY_ID = FIND_ALL_TAGS + " WHERE tagId = ?";
    public static final String FIND_BY_GIFT_CERTIFICATE_ID = FIND_ALL_TAGS +
            " INNER JOIN gift_certificate_has_tag ON tag.tagId = gift_certificate_has_tag.tag_tagId WHERE "
            + "gift_certificate_has_tag.gift_certificate_giftId = ?";
    public static final String REMOVE_TAG = "DELETE FROM tag WHERE tagId = ?";
    public static final String FIND_BY_NAME = "SELECT tagId, name FROM tag WHERE name = ?";
    public static final String TAG_REMOVE_GIFT_CERTIFICATE_HAS_TAG = "DELETE FROM gift_certificate_has_tag "
            + "WHERE tag_tagId = ?";

    private DatabaseQuery() {
    }
}
