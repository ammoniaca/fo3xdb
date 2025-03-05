package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoxHourlyWeatherMetadataDTO {

    @JsonProperty("rainTotal")
    private String rainTotal;

    @JsonProperty("rainIntensityMax")
    private String rainIntensityMax;

    @JsonProperty("airTemperatureMin")
    private String airTemperatureMin;

    @JsonProperty("airTemperatureMax")
    private String airTemperatureMax;

    @JsonProperty("airTemperatureAvg")
    private String airTemperatureAvg;

    @JsonProperty("airHumidityAvg")
    private String airHumidityAvg;

    @JsonProperty("dewPoint")
    private String dewPoint;

    @JsonProperty("airPressureAvg")
    private String airPressureAvg;

    @JsonProperty("solarRadiationMin")
    private String solarRadiationMin;

    @JsonProperty("solarRadiationAvg")
    private String solarRadiationAvg;

    @JsonProperty("solarRadiationMax")
    private String solarRadiationMax;

    @JsonProperty("windSpeedAvg")
    private String windSpeedAvg;

    @JsonProperty("windDirectionAvg")
    private String windDirectionAvg;

    @JsonProperty("windDirectionSTD")
    private String windDirectionSTD;

    @JsonProperty("windSpeedMax")
    private String windSpeedMax;

    @JsonProperty("windDirectionAtMaximumSpeed")
    private String windDirectionAtMaximumSpeed;

    @JsonProperty("evapotranspiration")
    private String evapotranspiration;

    @JsonProperty("solarRadiationCalculated")
    private String solarRadiationCalculated;

    @JsonProperty("batteryVoltage")
    private String batteryVoltage;

    @JsonProperty("dataLoggerTemperature")
    private String dataLoggerTemperature;


}
