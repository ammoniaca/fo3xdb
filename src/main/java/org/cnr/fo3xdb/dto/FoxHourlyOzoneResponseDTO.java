package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxHourlyOzoneResponseDTO extends FoxResponseDTO {

    @JsonProperty("hourlyUnits")
    private FoxOzoneUnitsDTO units;

}
