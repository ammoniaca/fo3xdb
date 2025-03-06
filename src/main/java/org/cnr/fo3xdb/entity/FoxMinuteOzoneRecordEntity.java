package org.cnr.fo3xdb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "fox_ozone_minute")
public class FoxMinuteOzoneRecordEntity {

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

    @Column(name="ozone_level0", length = 50)
    private Double ozoneLevel0;

    @Column(name="temperature_level0", length = 50)
    private Double temperatureLevel0;

    @Column(name="pressure_level0", length = 50)
    private Double pressureLevel0;

    @Column(name="flow_level0", length = 50)
    private Double flowLevel0;

    @Column(name="ozone_run_Level0_avg", length = 50)
    private Double ozoneRunLevel0Avg;

    // level 1

    @Column(name="ozone_level1", length = 50)
    private Double ozoneLevel1;

    @Column(name="temperature_level1", length = 50)
    private Double temperatureLevel1;

    @Column(name="pressure_level1", length = 50)
    private Double pressureLevel1;

    @Column(name="flow_level1", length = 50)
    private Double flowLevel1;

    @Column(name="ozone_run_level1_avg", length = 50)
    private Double ozoneRunLevel1Avg;

    // level 2

    @Column(name="ozone_level2", length = 50)
    private Double ozoneLevel2;

    @Column(name="temperature_level2", length = 50)
    private Double temperatureLevel2;

    @Column(name="pressure_level2", length = 50)
    private Double pressureLevel2;

    @Column(name="flow_level2", length = 50)
    private Double flowLevel2;

    @Column(name="ozone_run_level2_avg", length = 50)
    private Double ozoneRunLevel2Avg;

    public void setTimestamp(OffsetDateTime timestamp) {
        ZonedDateTime romeZonedDateTime = timestamp.atZoneSameInstant(ZoneId.of(ZONE_ID));
        this.timestamp = romeZonedDateTime.toOffsetDateTime();
    }

}
