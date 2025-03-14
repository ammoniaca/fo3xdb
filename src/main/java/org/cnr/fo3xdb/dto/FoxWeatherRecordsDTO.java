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
public class FoxWeatherRecordsDTO {

    @JsonProperty("timestamp")
    private List<OffsetDateTime> timestamp = new ArrayList<>();

    @JsonProperty("rainTotal")
    private List<Double> rainTotal = new ArrayList<>();;

    @JsonProperty("rainIntensityMax")
    private List<Double> rainIntensityMax = new ArrayList<>();;

    @JsonProperty("airTemperatureMin")
    private List<Double> airTemperatureMin = new ArrayList<>();;

    @JsonProperty("airTemperatureMax")
    private List<Double> airTemperatureMax = new ArrayList<>();;

    @JsonProperty("airTemperatureAvg")
    private List<Double> airTemperatureAvg = new ArrayList<>();;

    @JsonProperty("airHumidityAvg")
    private List<Double> airHumidityAvg = new ArrayList<>();;

    @JsonProperty("dewPoint")
    private List<Double> dewPoint = new ArrayList<>();;

    @JsonProperty("airPressureAvg")
    private List<Double> airPressureAvg = new ArrayList<>();;

    @JsonProperty("solarRadiationMin")
    private List<Double> solarRadiationMin = new ArrayList<>();;

    @JsonProperty("solarRadiationAvg")
    private List<Double> solarRadiationAvg = new ArrayList<>();;

    @JsonProperty("solarRadiationMax")
    private List<Double> solarRadiationMax = new ArrayList<>();;

    @JsonProperty("windSpeedAvg")
    private List<Double> windSpeedAvg = new ArrayList<>();;

    @JsonProperty("windDirectionAvg")
    private List<Double> windDirectionAvg = new ArrayList<>();;

    @JsonProperty("windDirectionSTD")
    private List<Double> windDirectionSTD = new ArrayList<>();;

    @JsonProperty("windSpeedMax")
    private List<Double> windSpeedMax = new ArrayList<>();;

    @JsonProperty("windDirectionAtMaximumSpeed")
    private List<Double> windDirectionAtMaximumSpeed = new ArrayList<>();;

    @JsonProperty("evapotranspiration")
    private List<Double> evapotranspiration = new ArrayList<>();;

    @JsonProperty("solarRadiationCalculated")
    private List<Double> solarRadiationCalculated = new ArrayList<>();;

    @JsonProperty("batteryVoltage")
    private List<Double> batteryVoltage = new ArrayList<>();;

    @JsonProperty("dataLoggerTemperature")
    private List<Double> dataLoggerTemperature = new ArrayList<>();;

    @JsonProperty("airTemperatureMeasurementErrors")
    private List<Double> airTemperatureMeasurementErrors = new ArrayList<>();;

    @JsonProperty("airHumidityMeasurementErrors")
    private List<Double> airHumidityMeasurementErrors = new ArrayList<>();;

    @JsonProperty("atmosphericPressureMeasurementErrors")
    private List<Double> atmosphericPressureMeasurementErrors = new ArrayList<>();;

    @JsonProperty("solarRadiationMeasurementErrors")
    private List<Double> solarRadiationMeasurementErrors = new ArrayList<>();;

    @JsonProperty("windMeasurementErrors")
    private List<Double> windMeasurementErrors = new ArrayList<>();;

}
