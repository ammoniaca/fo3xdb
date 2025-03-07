package org.cnr.fo3xdb.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "fox_weather_records")
public class FoxWeatherRecordEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Setter(AccessLevel.NONE)
    @Column(name="timestamp", length = 50)
    private OffsetDateTime timestamp;

    @Column(name="rain_total", length = 50)
    private Double rainTotal;

    @Column(name="rain_intensity_max", length = 50)
    private Double rainIntensityMax;

    @Column(name="air_temperature_min", length = 50)
    private Double airTemperatureMin;

    @Column(name="air_temperature_max", length = 50)
    private Double airTemperatureMax;

    @Column(name="air_temperature_avg", length = 50)
    private Double airTemperatureAvg;

    @Column(name="air_humidity_avg", length = 50)
    private Double airHumidityAvg;

    @Column(name="dew_point", length = 50)
    private Double dewPoint;

    @Column(name="air_pressure_avg", length = 50)
    private Double airPressureAvg;

    @Column(name="solar_radiation_min", length = 50)
    private Double solarRadiationMin;

    @Column(name="solar_radiation_avg", length = 50)
    private Double solarRadiationAvg;

    @Column(name="solar_radiation_max", length = 50)
    private Double solarRadiationMax;

    @Column(name="wind_speed_avg", length = 50)
    private Double windSpeedAvg;

    @Column(name="wind_direction_avg", length = 50)
    private Double windDirectionAvg;

    @Column(name="wind_direction_std", length = 50)
    private Double windDirectionSTD;

    @Column(name="wind_speed_max", length = 50)
    private Double windSpeedMax;

    @Column(name="wind_direction_at_maximum_speed", length = 50)
    private Double windDirectionAtMaximumSpeed;

    @Column(name="evapotranspiration", length = 50)
    private Double evapotranspiration;

    @Column(name="solar_radiation_calculated", length = 50)
    private Double solarRadiationCalculated;

    @Column(name="battery_voltage", length = 50)
    private Double batteryVoltage;

    @Column(name="data_logger_temperature", length = 50)
    private Double dataLoggerTemperature;

    @Column(name="air_temperature_measurement_errors", length = 50)
    private Double airTemperatureMeasurementErrors;

    @Column(name="air_humidity_measurement_errors", length = 50)
    private Double airHumidityMeasurementErrors;

    @Column(name="atmospheric_pressure_measurement_errors", length = 50)
    private Double atmosphericPressureMeasurementErrors;

    @Column(name="solar_radiation_measurement_errors", length = 50)
    private Double solarRadiationMeasurementErrors;

    @Column(name="wind_measurement_errors", length = 50)
    private Double windMeasurementErrors;

    public void setTimestamp(OffsetDateTime timestamp) {
        ZonedDateTime romeZonedDateTime = timestamp.atZoneSameInstant(ZoneId.of("Europe/Rome"));
        this.timestamp = romeZonedDateTime.toOffsetDateTime();
    }
}
