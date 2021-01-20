package com.epam.esm.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type GiftCertificate.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class GiftCertificate implements BaseEntity {
    private long certificateId;
    @NotNull(message = " Name cannot be null ")
    @Size(min = 1, max = 45, message = " name must be between 10 and 45 characters ")
    private String name;
    @NotNull(message = " description cannot be null ")
    @Size(min = 1, max = 100, message = " description must be between 10 and 100 characters ")
    private String description;
    @NotNull(message = " incorrect price")
    @Min(value = 1, message = "price should not be less than 1.0")
    @Max(value = 100000, message = "price should not be greater than 100000.0")
    private BigDecimal price;
    @Min(value = 1, message = "duration should not be less than 1")
    @Max(value = 100, message = "duration should not be greater than 100")
    private int duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private List<Tag> tags;

    public GiftCertificate(long certificateId, String name, String description, BigDecimal price, int duration,
                           LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        tags = new ArrayList<>();
    }

    public GiftCertificate(long certificateId, String name, String description, BigDecimal price, int duration,
                           LocalDateTime createDate, LocalDateTime lastUpdateDate, List<Tag> tags) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = tags;
    }

    public GiftCertificate() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getCertificateId() {
        return certificateId;
    }

    /**
     * Sets id.
     *
     * @param certificateId the certificate id
     */
    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets create date.
     *
     * @return the createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets lastUpdateDate.
     *
     * @return the last update date
     */
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets last update date.
     *
     * @param lastUpdateDate the last update date
     */
    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o ==null || this.getClass() != o.getClass()) {
            return false;
        }
        GiftCertificate certificate = (GiftCertificate) o;

        if (name == null) {
            if (certificate.getName() != null) {
                return false;
            }
        } else {
            if (!name.equals(certificate.getName())) {
                return false;
            }
        }
        if (description == null) {
            if (certificate.getDescription() != null) {
                return false;
            }
        } else {
            if (!description.equals(certificate.getDescription())) {
                return false;
            }
        }
        if (createDate == null) {
            if (certificate.getCreateDate() != null) {
                return false;
            }
        } else {
            if (!createDate.equals(certificate.getCreateDate())) {
                return false;
            }
        }
        if (lastUpdateDate == null) {
            if (certificate.getLastUpdateDate() != null) {
                return false;
            }
        } else {
            if (!lastUpdateDate.equals(certificate.getLastUpdateDate())) {
                return false;
            }
        }
        if (tags == null) {
            if (certificate.getTags() != null) {
                return false;
            }
        } else {
            if (!tags.equals(certificate.getTags())) {
                return false;
            }
        }
        if (price == null) {
            if (certificate.getPrice() != null) {
                return false;
            }
        } else {
            if (!price.equals(certificate.getPrice())) {
                return false;
            }
        }
        return ((certificateId == certificate.getCertificateId()) && (duration == certificate.getDuration()));
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + Long.hashCode(certificateId);
        result += result * 31 + (name == null ? 0 : name.hashCode());
        result += result * 31 + (description == null ? 0 : description.hashCode());
        result += result * 31 + (createDate == null ? 0 : createDate.hashCode());
        result += result * 31 + (lastUpdateDate == null ? 0 : lastUpdateDate.hashCode());
        result += result * 31 + (tags == null ? 0 : tags.hashCode());
        result += result * 31 + duration;
        result += result * 31 + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(certificateId)
                .append(" ").append(name)
                .append(" ").append(description)
                .append(" ")
                .append(price)
                .append(" ")
                .append(duration)
                .append(" ")
                .append(createDate)
                .append(" ")
                .append(lastUpdateDate)
                .append(" ")
                .append(tags)
                .toString();
    }
}
