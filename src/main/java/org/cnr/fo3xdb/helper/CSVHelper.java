package org.cnr.fo3xdb.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.cnr.fo3xdb.entity.FoxWeatherRecordEntity;
import org.cnr.fo3xdb.enums.CSVNoDataType;
import org.cnr.fo3xdb.enums.HeaderFoxWeatherCSVLabels;
import org.cnr.fo3xdb.exceptions.CSVProducerException;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream recordsToCSV(
            List<FoxWeatherRecordEntity> listRecords,
            CSVNoDataType noData
    ) {
        final CSVFormat format = CSVFormat
                .Builder
                .create()
                .setDelimiter(';')
                .get();
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);
            )
        {
            // store header
            List<String> header = Arrays.asList(
                    HeaderFoxWeatherCSVLabels.ID.getLabel(),
                    HeaderFoxWeatherCSVLabels.TIMESTAMP.getLabel(),
                    HeaderFoxWeatherCSVLabels.RAIN_TOTAL.getLabel(),
                    HeaderFoxWeatherCSVLabels.RAIN_INTENSITY_MAX.getLabel(),
                    HeaderFoxWeatherCSVLabels.AIR_TEMPERATURE_MIN.getLabel(),
                    HeaderFoxWeatherCSVLabels.AIR_TEMPERATURE_MAX.getLabel(),
                    HeaderFoxWeatherCSVLabels.AIR_TEMPERATURE_AVG.getLabel(),
                    HeaderFoxWeatherCSVLabels.AIR_HUMIDITY_AVG.getLabel(),
                    HeaderFoxWeatherCSVLabels.DEW_POINT.getLabel(),
                    HeaderFoxWeatherCSVLabels.AIR_PRESSURE_AVG.getLabel(),
                    HeaderFoxWeatherCSVLabels.SOLAR_RADIATION_MIN.getLabel(),
                    HeaderFoxWeatherCSVLabels.SOLAR_RADIATION_MAX.getLabel(),
                    HeaderFoxWeatherCSVLabels.SOLAR_RADIATION_AVG.getLabel(),
                    HeaderFoxWeatherCSVLabels.SOLAR_RADIATION_CALCULATED.getLabel(),
                    HeaderFoxWeatherCSVLabels.WIND_SPEED_MAX.getLabel(),
                    HeaderFoxWeatherCSVLabels.WIND_SPEED_AVG.getLabel(),
                    HeaderFoxWeatherCSVLabels.WIND_DIRECTION_AVG.getLabel(),
                    HeaderFoxWeatherCSVLabels.WIND_DIRECTION_STD.getLabel(),
                    HeaderFoxWeatherCSVLabels.WIND_DIRECTION_AT_MAXIMUM_SPEED.getLabel(),
                    HeaderFoxWeatherCSVLabels.EVAPOTRANSPIRATION.getLabel(),
                    HeaderFoxWeatherCSVLabels.BATTERY_VOLTAGE.getLabel(),
                    HeaderFoxWeatherCSVLabels.DATA_LOGGER_TEMPERATURE.getLabel(),
                    HeaderFoxWeatherCSVLabels.AIR_TEMPERATURE_MEASUREMENT_ERRORS.getLabel(),
                    HeaderFoxWeatherCSVLabels.ATMOSPHERIC_PRESSURE_MEASUREMENT_ERRORS.getLabel(),
                    HeaderFoxWeatherCSVLabels.SOLAR_RADIATION_MEASUREMENT_ERRORS.getLabel(),
                    HeaderFoxWeatherCSVLabels.WIND_MEASUREMENT_ERRORS.getLabel()
            );
            csvPrinter.printRecord(header);
            // store data records
            for(FoxWeatherRecordEntity record : listRecords) {
                List<String> data = Arrays.asList(
                        String.valueOf(record.getId()),
                        record.getTimestamp().toString(),
                        safetyDoubleToString(record.getRainTotal(), noData),
                        safetyDoubleToString(record.getRainIntensityMax(), noData),
                        safetyDoubleToString(record.getRainIntensityMax(), noData),
                        safetyDoubleToString(record.getAirTemperatureMin(), noData),
                        safetyDoubleToString(record.getAirTemperatureMax(), noData),
                        safetyDoubleToString(record.getAirTemperatureAvg(), noData),
                        safetyDoubleToString(record.getAirHumidityAvg(), noData),
                        safetyDoubleToString(record.getDewPoint(), noData),
                        safetyDoubleToString(record.getAirPressureAvg(), noData),
                        safetyDoubleToString(record.getSolarRadiationMin(), noData),
                        safetyDoubleToString(record.getSolarRadiationMax(), noData),
                        safetyDoubleToString(record.getSolarRadiationAvg(), noData),
                        safetyDoubleToString(record.getSolarRadiationCalculated(), noData),
                        safetyDoubleToString(record.getWindSpeedMax(), noData),
                        safetyDoubleToString(record.getWindSpeedAvg(),noData),
                        safetyDoubleToString(record.getWindDirectionAvg(), noData),
                        safetyDoubleToString(record.getWindDirectionSTD(), noData),
                        safetyDoubleToString(record.getWindDirectionAtMaximumSpeed(), noData),
                        safetyDoubleToString(record.getEvapotranspiration(), noData),
                        safetyDoubleToString(record.getBatteryVoltage(), noData),
                        safetyDoubleToString(record.getDataLoggerTemperature(), noData),
                        safetyDoubleToString(record.getAirTemperatureMeasurementErrors(), noData),
                        safetyDoubleToString(record.getAtmosphericPressureMeasurementErrors(), noData),
                        safetyDoubleToString(record.getSolarRadiationMeasurementErrors(), noData),
                        safetyDoubleToString(record.getWindMeasurementErrors(), noData)
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            String errorMessage = MessageFormat.format("CSV: {0}", e.getMessage());
            throw new CSVProducerException(errorMessage);
        }
    }

    private static String safetyDoubleToString(Double value, CSVNoDataType noData) {
        return value == null ? noData.toString() : value.toString();
    }

}
