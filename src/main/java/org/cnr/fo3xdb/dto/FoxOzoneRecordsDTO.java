package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxOzoneRecordsDTO {

    @JsonProperty("timestamp")
    private List<OffsetDateTime> timestamp = new ArrayList<>();

    @JsonProperty("windSpeed")
    private List<Double> windSpeed = new ArrayList<>();

    @JsonProperty("ozoneLevel0")
    private List<Double> ozoneLevel0 = new ArrayList<>();

    @JsonProperty("ozoneLevel1")
    private List<Double> ozoneLevel1 = new ArrayList<>();

    @JsonProperty("ozoneLevel2")
    private List<Double> ozoneLevel2 = new ArrayList<>();

}
