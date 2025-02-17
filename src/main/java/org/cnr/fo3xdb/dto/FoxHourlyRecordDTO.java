package org.cnr.fo3xdb.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxHourlyRecordDTO {

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp;

    @JsonProperty("rainTotal")
    private Double rainTotal;

    @JsonProperty("rainIntensityMax")
    private Double rainIntensityMax;

    @JsonProperty("airTemperatureMin")
    private Double airTemperatureMin;

    @JsonProperty("airTemperatureMax")
    private Double airTemperatureMax;

    @JsonProperty("airTemperatureAvg")
    private Double airTemperatureAvg;

    @JsonProperty("airHumidityAvg")
    private Double airHumidityAvg;

    @JsonProperty("dewPoint")
    private Double dewPoint;

    @JsonProperty("airPressureAvg")
    private Double airPressureAvg;

    @JsonProperty("solarRadiationMin")
    private Double solarRadiationMin;

    @JsonProperty("solarRadiationAvg")
    private Double solarRadiationAvg;

    @JsonProperty("solarRadiationMax")
    private Double solarRadiationMax;

    @JsonProperty("windSpeedAvg")
    private Double windSpeedAvg;

    @JsonProperty("windDirectionAvg")
    private Double windDirectionAvg;

    @JsonProperty("windDirectionSTD")
    private Double windDirectionSTD;

    @JsonProperty("windSpeedMax")
    private Double windSpeedMax;

    @JsonProperty("windDirectionAtMaximumSpeed")
    private Double windDirectionAtMaximumSpeed;

    @JsonProperty("evapotranspiration")
    private Double evapotranspiration;

    @JsonProperty("solarRadiationCalculated")
    private Double solarRadiationCalculated;

    @JsonProperty("batteryVoltage")
    private Double batteryVoltage;

    @JsonProperty("dataLoggerTemperature")
    private Double dataLoggerTemperature;

    @JsonProperty("airTemperatureMeasurementErrors")
    private Double airTemperatureMeasurementErrors;

    @JsonProperty("airHumidityMeasurementErrors")
    private Double airHumidityMeasurementErrors;

    @JsonProperty("atmosphericPressureMeasurementErrors")
    private Double atmosphericPressureMeasurementErrors;

    @JsonProperty("solarRadiationMeasurementErrors")
    private Double solarRadiationMeasurementErrors;

    @JsonProperty("windMeasurementErrors")
    private Double windMeasurementErrors;

    public void setTimestamp(OffsetDateTime timestamp) {
        ZonedDateTime romeZonedDateTime = timestamp.atZoneSameInstant(ZoneId.of("Europe/Rome"));
        this.timestamp = romeZonedDateTime.toOffsetDateTime();
    }


    private OffsetDateTime convertToOffsetDateTime(String utcTimeString){
        Instant instant = Instant.parse(utcTimeString);
        return instant.atZone(ZoneId.of("Europe/Rome")).toOffsetDateTime();
    }


}
