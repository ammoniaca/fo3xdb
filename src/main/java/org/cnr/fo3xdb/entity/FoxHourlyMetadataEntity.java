package org.cnr.fo3xdb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "fox_hourly_metadata")
public class FoxHourlyMetadataEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(name="time", length = 10)
    private String time;

    @Column(name="timezone", length = 10)
    private String timezone;

//    @Column(name="timezone_abbreviation", length = 10)
//    private String timezoneAbbreviation;
//
//    @Column(name="utc_offset_seconds", length = 10)
//    private Integer utcOffsetSeconds;

    @Column(name="rain_total", length = 10)
    private String rainTotal;

    @Column(name="rain_intensity_max", length = 10)
    private String rainIntensityMax;

    @Column(name="air_temperature_min", length = 10)
    private String airTemperatureMin;

    @Column(name="air_temperature_max", length = 10)
    private String airTemperatureMax;

    @Column(name="air_temperature_avg", length = 10)
    private String airTemperatureAvg;

    @Column(name="air_humidity_avg", length = 10)
    private String airHumidityAvg;

    @Column(name="dew_point", length = 10)
    private String dewPoint;

    @Column(name="air_pressure_avg", length = 10)
    private String airPressureAvg;

    @Column(name="solar_radiation_min", length = 10)
    private String solarRadiationMin;

    @Column(name="solar_radiation_avg", length = 10)
    private String solarRadiationAvg;

    @Column(name="solar_radiation_max", length = 10)
    private String solarRadiationMax;

    @Column(name="wind_speed_avg", length = 10)
    private String windSpeedAvg;

    @Column(name="wind_direction_avg", length = 10)
    private String windDirectionAvg;

    @Column(name="wind_direction_std", length = 10)
    private String windDirectionSTD;

    @Column(name="wind_speed_max", length = 10)
    private String windSpeedMax;

    @Column(name="wind_direction_at_maximum_speed", length = 10)
    private Double windDirectionAtMaximumSpeed;

    @Column(name="evapotranspiration", length = 10)
    private Double evapotranspiration;

    @Column(name="solar_radiation_calculated", length = 10)
    private Double solarRadiationCalculated;

    @Column(name="battery_voltage", length = 10)
    private Double batteryVoltage;

    @Column(name="data_logger_temperature", length = 10)
    private Double dataLoggerTemperature;

    @Column(name="air_temperature_measurement_errors", length = 10)
    private Double airTemperatureMeasurementErrors;

    @Column(name="air_humidity_measurement_errors", length = 10)
    private Double airHumidityMeasurementErrors;

    @Column(name="atmospheric_pressure_measurement_errors", length = 10)
    private Double atmosphericPressureMeasurementErrors;

    @Column(name="solar_radiation_measurement_errors", length = 10)
    private Double solarRadiationMeasurementErrors;

    @Column(name="wind_measurement_errors", length = 10)
    private Double windMeasurementErrors;




}
