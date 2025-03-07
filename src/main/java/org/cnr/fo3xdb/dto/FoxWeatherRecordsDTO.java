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

    //

    public void appendTimestamp(OffsetDateTime timestamp) {
        this.timestamp.add(timestamp);
    }
    //

    public void appendRainTotal(Double rainTotal) {
        this.rainTotal.add(rainTotal);
    }

    //

    public void appendRainIntensityMax(Double rainIntensityMax) {
        this.rainIntensityMax.add(rainIntensityMax);
    }

    //

    public void appendAirTemperatureMin(Double airTemperatureMin) {
        this.airTemperatureMin.add(airTemperatureMin);
    }

    public void appendAirTemperatureMax(Double airTemperatureMax) {
        this.airTemperatureMax.add(airTemperatureMax);
    }

    public void appendAirTemperatureAvg(Double airTemperatureAvg) {
        this.airTemperatureAvg.add(airTemperatureAvg);
    }

    //

    public void appendAirHumidityAvg(Double airHumidityAvg) {
        this.airHumidityAvg.add(airHumidityAvg);
    }

    //

    public void appendAirDewPoint(Double airDewPoint) {
        this.dewPoint.add(airDewPoint);
    }

    //

    public void appendAirPressureAvg(Double airPressureAvg) {
        this.airPressureAvg.add(airPressureAvg);
    }

    // Solar Radiation (Min, Avg, Max)

    public void appendSolarRadiationMin(Double solarRadiationMin) {
        this.solarRadiationMin.add(solarRadiationMin);
    }

    public void appendSolarRadiationAvg(Double solarRadiationAvg) {
        this.solarRadiationAvg.add(solarRadiationAvg);
    }

    public void appendSolarRadiationMax(Double solarRadiationMax) {
        this.solarRadiationMax.add(solarRadiationMax);
    }

    // Wind (avg, direction avg, direction std, speed max, direction at max speed)

    public void appendWindSpeedAvg(Double windSpeedAvg) {
        this.windSpeedAvg.add(windSpeedAvg);
    }

    public void appendWindDirectionAvg(Double windDirectionAvg) {
        this.windDirectionAvg.add(windDirectionAvg);
    }

    public void appendWindDirectionSTD(Double windDirectionSTD) {
        this.windDirectionSTD.add(windDirectionSTD);
    }

    public void appendWindSpeedMax(Double windSpeedMax) {
        this.windSpeedMax.add(windSpeedMax);
    }

    public void appendWindDirectionAtMaximumSpeed(Double windDirectionAtMaximumSpeed) {
        this.windDirectionAtMaximumSpeed.add(windDirectionAtMaximumSpeed);
    }

    // Evapotranspiration

    public void appendEvapotranspiration(Double evapotranspiration) {
        this.evapotranspiration.add(evapotranspiration);
    }

    public void appendSolarRadiationCalculated(Double solarRadiationCalculated) {
        this.solarRadiationCalculated.add(solarRadiationCalculated);
    }

    // Instrument (battery_voltage, data_logger_temperature)

    public void appendBatteryVoltage(Double batteryVoltage) {
        this.batteryVoltage.add(batteryVoltage);
    }

    public void appendDataLoggerTemperature(Double dataLoggerTemperature) {
        this.dataLoggerTemperature.add(dataLoggerTemperature);
    }

    // errors measurement (air_temperature, air_humidity, atmospheric_pressure, solar_Radiation, wind_measurement)

    public void appendAirTemperatureMeasurementErrors(Double airTemperatureMeasurementErrors) {
        this.airTemperatureMeasurementErrors.add(airTemperatureMeasurementErrors);
    }

    public void appendAirHumidityMeasurementErrors(Double airHumidityMeasurementErrors) {
        this.airHumidityMeasurementErrors.add(airHumidityMeasurementErrors);
    }

    public void appendAtmosphericPressureMeasurementErrors(Double atmosphericPressureMeasurementErrors) {
        this.atmosphericPressureMeasurementErrors.add(atmosphericPressureMeasurementErrors);
    }

    public void appendSolarRadiationMeasurementErrors(Double solarRadiationMeasurementErrors) {
        this.solarRadiationMeasurementErrors.add(solarRadiationMeasurementErrors);
    }

    public void appendWindMeasurementErrors(Double windMeasurementErrors) {
        this.windMeasurementErrors.add(windMeasurementErrors);
    }

}
