package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.cnr.fo3xdb.enums.TemporalUnit;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxOzoneResponseDTO {

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

    @JsonProperty("temporalRange")
    private TemporalUnit temporal;

    @JsonProperty("units")
    private FoxOzoneUnitsDTO units;

    @JsonProperty("data")
    private FoxOzoneRecordsDTO records;

}
