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
@Table(name = "fox_ozone_units")
public class FoxOzoneUnitsEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(name="wind_speed", length = 10)
    private String windSpeed;

    // level 0

    @Column(name="ozone_level0", length = 10)
    private String ozoneLevel0;

    // level 1

    @Column(name="ozone_level1", length = 10)
    private String ozoneLevel1;

    // level 2

    @Column(name="ozone_level2", length = 10)
    private String ozoneLevel2;


}
