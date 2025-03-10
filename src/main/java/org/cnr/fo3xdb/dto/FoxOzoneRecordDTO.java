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

    // level 1

    @JsonProperty("ozoneLevel1")
    private Double ozoneLevel1;

    // level 2

    @JsonProperty("ozoneLevel2")
    private Double ozoneLevel2;

}
