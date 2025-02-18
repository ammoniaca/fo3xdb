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

    @Column(name="rain_total", length = 15)
    private String rainTotal;

    @Column(name="rain_intensity_max", length = 20)
    private String rainIntensityMax;

    @Column(name="air_temperature_min", length = 20)
    private String airTemperatureMin;

    @Column(name="air_temperature_max", length = 20)
    private String airTemperatureMax;

    @Column(name="air_temperature_avg", length = 20)
    private String airTemperatureAvg;

    @Column(name="air_humidity_avg", length = 18)
    private String airHumidityAvg;

    @Column(name="dew_point", length = 10)
    private String dewPoint;

    @Column(name="air_pressure_avg", length = 18)
    private String airPressureAvg;

    @Column(name="solar_radiation_min", length = 20)
    private String solarRadiationMin;

    @Column(name="solar_radiation_avg", length = 20)
    private String solarRadiationAvg;

    @Column(name="solar_radiation_max", length = 20)
    private String solarRadiationMax;

    @Column(name="wind_speed_avg", length = 16)
    private String windSpeedAvg;

    @Column(name="wind_direction_avg", length = 20)
    private String windDirectionAvg;

    @Column(name="wind_direction_std", length = 20)
    private String windDirectionSTD;

    @Column(name="wind_speed_max", length = 16)
    private String windSpeedMax;

    @Column(name="wind_direction_at_maximum_speed", length = 32)
    private String windDirectionAtMaximumSpeed;

    @Column(name="evapotranspiration", length = 20)
    private String evapotranspiration;

    @Column(name="solar_radiation_calculated", length = 30)
    private String solarRadiationCalculated;

    @Column(name="battery_voltage", length = 18)
    private String batteryVoltage;

    @Column(name="data_logger_temperature", length = 25)
    private String dataLoggerTemperature;

//    @Column(name="air_temperature_measurement_errors", length = 36)
//    private String airTemperatureMeasurementErrors;
//
//    @Column(name="air_humidity_measurement_errors", length = 36)
//    private String airHumidityMeasurementErrors;
//
//    @Column(name="atmospheric_pressure_measurement_errors", length = 40)
//    private String atmosphericPressureMeasurementErrors;
//
//    @Column(name="solar_radiation_measurement_errors", length = 40)
//    private String solarRadiationMeasurementErrors;
//
//    @Column(name="wind_measurement_errors", length = 40)
//    private String windMeasurementErrors;

}
