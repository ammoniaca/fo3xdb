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

    // level 1

    @JsonProperty("ozoneLevel1")
    private String ozoneLevel1;

    // level 2

    @JsonProperty("ozoneLevel2")
    private String ozoneLevel2;

}
