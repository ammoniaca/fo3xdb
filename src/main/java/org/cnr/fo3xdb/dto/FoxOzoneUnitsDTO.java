package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxOzoneUnitsDTO {

    @JsonProperty("windSpeed")
    private String windSpeed;

    // level 0

    @JsonProperty("ozoneLevel0")
    private String ozoneLevel0;

    @JsonProperty("temperatureLevel0")
    private String temperatureLevel0;

    @JsonProperty("pressureLevel0")
    private String pressureLevel0;

    @JsonProperty("flowLevel0")
    private String flowLevel0;

    // level 1

    @JsonProperty("ozoneLevel1")
    private String ozoneLevel1;

    @JsonProperty("temperatureLevel1")
    private String temperatureLevel1;

    @JsonProperty("pressureLevel1")
    private String pressureLevel1;

    @JsonProperty("flowLevel1")
    private String flowLevel1;

    // level 2

    @JsonProperty("ozoneLevel2")
    private String ozoneLevel2;

    @JsonProperty("temperatureLevel2")
    private String temperatureLevel2;

    @JsonProperty("pressureLevel2")
    private String pressureLevel2;

    @JsonProperty("flowLevel2")
    private String flowLevel2;

}
