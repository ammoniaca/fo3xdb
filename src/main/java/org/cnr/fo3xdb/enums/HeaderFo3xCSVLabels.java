package org.cnr.fo3xdb.enums;

public enum HeaderFo3xCSVLabels {

    ID("id"),
    TIMESTAMP("timestamp"),
    RAIN_TOTAL("rain_total"),
    RAIN_INTENSITY_MAX("rain_intensity_max"),
    AIR_TEMPERATURE_MIN("air_temperature_min"),
    AIR_TEMPERATURE_MAX("air_temperature_max"),
    AIR_TEMPERATURE_AVG("air_temperature_avg"),
    AIR_HUMIDITY_AVG("air_humidity_avg"),
    AIR_PRESSURE_AVG("air_pressure_avg"),
    DEW_POINT("dew_point"),
    SOLAR_RADIATION_MIN("solar_radiation_min"),
    SOLAR_RADIATION_AVG("solar_radiation_avg"),
    SOLAR_RADIATION_MAX("solar_radiation_max"),
    WIND_SPEED_MAX("wind_speed_max"),
    WIND_SPEED_AVG("wind_speed_avg"),
    WIND_DIRECTION_AVG("wind_direction_avg"),
    WIND_DIRECTION_STD("wind_direction_std"),
    WIND_DIRECTION_AT_MAXIMUM_SPEED("wind_direction_at_maximum_speed"),
    EVAPOTRANSPIRATION("evapotranspiration"),
    SOLAR_RADIATION_CALCULATED("solar_radiation_calculated"),
    BATTERY_VOLTAGE("battery_voltage"),
    DATA_LOGGER_TEMPERATURE("data_logger_temperature"),
    AIR_TEMPERATURE_MEASUREMENT_ERRORS("air_temperature_measurement_errors"),
    AIR_HUMIDITY_MEASUREMENT_ERRORS("air_humidity_measurement_errors"),
    ATMOSPHERIC_PRESSURE_MEASUREMENT_ERRORS("atmospheric_pressure_measurement_errors"),
    SOLAR_RADIATION_MEASUREMENT_ERRORS("solar_radiation_measurement_errors"),
    WIND_MEASUREMENT_ERRORS("wind_measurement_errors");

    private final String label;

    HeaderFo3xCSVLabels(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
