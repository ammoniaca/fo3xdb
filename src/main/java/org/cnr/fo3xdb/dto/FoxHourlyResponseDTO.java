package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxHourlyResponseDTO {

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("elevation")
    private Double elevation;

    @JsonProperty("experiment")
    private String experiment;

    @JsonProperty("time")
    private String time;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("systemOfUnits")
    private String systemOfUnits;

    @JsonProperty("hourlyUnits")
    private FoxHourlyMetadataDTO hourlyUnits;

    @JsonProperty("hourly")
    public FoxHourlyRecordsDTO hourly;



}
