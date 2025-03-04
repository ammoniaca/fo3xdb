package org.cnr.fo3xdb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "fox_global_metadata")
public class FoxGlobalMetadataEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(name="time", length = 30)
    private String time;

    @Column(name="timezone", length = 30)
    private String timezone;

//    @Column(name="timezone_abbreviation", length = 10)
//    private String timezoneAbbreviation;
//
//    @Column(name="utc_offset_seconds", length = 10)
//    private Integer utcOffsetSeconds;

    @Column(name="latitude", length = 30)
    private Double latitude;

    @Column(name="longitude", length = 30)
    private Double longitude;

    @Column(name="elevation", length = 30)
    private Double elevation;

    @Column(name="experiment", length = 30)
    private String experiment;

    @Column(name="system_of_units", length = 30)
    private String systemOfUnits;

}
