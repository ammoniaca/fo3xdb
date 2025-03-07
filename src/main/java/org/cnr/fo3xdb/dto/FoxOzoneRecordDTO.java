package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxOzoneRecordDTO {

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp;

    @JsonProperty("windSpeed")
    private Double windSpeed;

    // level 0

    @JsonProperty("ozoneLevel0")
    private Double ozoneLevel0;

    @JsonProperty("temperatureLevel0")
    private Double temperatureLevel0;

    @JsonProperty("pressureLevel0")
    private Double pressureLevel0;

    @JsonProperty("flowLevel0")
    private Double flowLevel0;

    // level 1

    @JsonProperty("ozoneLevel1")
    private Double ozoneLevel1;

    @JsonProperty("temperatureLevel1")
    private Double temperatureLevel1;

    @JsonProperty("pressureLevel1")
    private Double pressureLevel1;

    @JsonProperty("flowLevel1")
    private Double flowLevel1;

    // level 2

    @JsonProperty("ozoneLevel2")
    private Double ozoneLevel2;

    @JsonProperty("temperatureLevel2")
    private Double temperatureLevel2;

    @JsonProperty("pressureLevel2")
    private Double pressureLevel2;

    @JsonProperty("flowLevel2")
    private Double flowLevel2;

}
