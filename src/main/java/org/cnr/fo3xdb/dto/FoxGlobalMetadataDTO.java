package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoxGlobalMetadataDTO {

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

//    //"timezone_abbreviation": "GMT+1"
//    @JsonProperty("timezoneAbbreviation")
//    private String timezoneAbbreviation;
//
//    // utc_offset_seconds
//    @JsonProperty("utcOffsetSeconds")
//    private Integer utcOffsetSeconds;

}
