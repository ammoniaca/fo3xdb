package org.cnr.fo3xdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoxHourlyRecordsDTO {

    @JsonProperty("timestamp")
    private List<OffsetDateTime> timestamp;

    @JsonProperty("rainTotal")
    private List<Double> rainTotal;

    @JsonProperty("rainIntensityMax")
    private List<Double> rainIntensityMax;

    @JsonProperty("airTemperatureMin")
    private List<Double> airTemperatureMin;

    @JsonProperty("airTemperatureMax")
    private List<Double> airTemperatureMax;

    @JsonProperty("airTemperatureAvg")
    private List<Double> airTemperatureAvg;

    @JsonProperty("airHumidityAvg")
    private List<Double> airHumidityAvg;

    @JsonProperty("dewPoint")
    private List<Double> dewPoint;

    @JsonProperty("airPressureAvg")
    private List<Double> airPressureAvg;

    @JsonProperty("solarRadiationMin")
    private List<Double> solarRadiationMin;

    @JsonProperty("solarRadiationAvg")
    private List<Double> solarRadiationAvg;

    @JsonProperty("solarRadiationMax")
    private List<Double> solarRadiationMax;

    @JsonProperty("windSpeedAvg")
    private List<Double> windSpeedAvg;

    @JsonProperty("windDirectionAvg")
    private List<Double> windDirectionAvg;

    @JsonProperty("windDirectionSTD")
    private List<Double> windDirectionSTD;

    @JsonProperty("windSpeedMax")
    private List<Double> windSpeedMax;

    @JsonProperty("windDirectionAtMaximumSpeed")
    private List<Double> windDirectionAtMaximumSpeed;

    @JsonProperty("evapotranspiration")
    private List<Double> evapotranspiration;

    @JsonProperty("solarRadiationCalculated")
    private List<Double> solarRadiationCalculated;

    @JsonProperty("batteryVoltage")
    private List<Double> batteryVoltage;

    @JsonProperty("dataLoggerTemperature")
    private List<Double> dataLoggerTemperature;

    @JsonProperty("airTemperatureMeasurementErrors")
    private List<Double> airTemperatureMeasurementErrors;

    @JsonProperty("airHumidityMeasurementErrors")
    private List<Double> airHumidityMeasurementErrors;

    @JsonProperty("atmosphericPressureMeasurementErrors")
    private List<Double> atmosphericPressureMeasurementErrors;

    @JsonProperty("solarRadiationMeasurementErrors")
    private List<Double> solarRadiationMeasurementErrors;

    @JsonProperty("windMeasurementErrors")
    private List<Double> windMeasurementErrors;

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
