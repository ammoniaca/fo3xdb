package org.cnr.fo3xdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "fox_ozone_records")
public class FoxOzoneRecordEntity {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private static final String ZONE_ID = "Europe/Rome";

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Setter(AccessLevel.NONE)
    @Column(name="timestamp", length = 50)
    private OffsetDateTime timestamp;

    @Column(name="wind_speed", length = 50)
    private Double windSpeed;

    // level 0

    @Column(name="ozone_level0", length = 50)
    private Double ozoneLevel0;

    // level 1

    @Column(name="ozone_level1", length = 50)
    private Double ozoneLevel1;

    // level 2

    @Column(name="ozone_level2", length = 50)
    private Double ozoneLevel2;

    public void setTimestamp(OffsetDateTime timestamp) {
        ZonedDateTime romeZonedDateTime = timestamp.atZoneSameInstant(ZoneId.of(ZONE_ID));
        this.timestamp = romeZonedDateTime.toOffsetDateTime();
    }

}
