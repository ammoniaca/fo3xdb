package org.cnr.fo3xdb.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.cnr.fo3xdb.entity.FoxHourlyWeatherRecordEntity;
import org.cnr.fo3xdb.enums.CSVNoDataType;
import org.cnr.fo3xdb.enums.HeaderFo3xCSVLabels;
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
            List<FoxHourlyWeatherRecordEntity> listRecords,
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
                    HeaderFo3xCSVLabels.ID.getLabel(),
                    HeaderFo3xCSVLabels.TIMESTAMP.getLabel(),
                    HeaderFo3xCSVLabels.RAIN_TOTAL.getLabel(),
                    HeaderFo3xCSVLabels.RAIN_INTENSITY_MAX.getLabel(),
                    HeaderFo3xCSVLabels.AIR_TEMPERATURE_MIN.getLabel(),
                    HeaderFo3xCSVLabels.AIR_TEMPERATURE_MAX.getLabel(),
                    HeaderFo3xCSVLabels.AIR_TEMPERATURE_AVG.getLabel(),
                    HeaderFo3xCSVLabels.AIR_HUMIDITY_AVG.getLabel(),
                    HeaderFo3xCSVLabels.DEW_POINT.getLabel(),
                    HeaderFo3xCSVLabels.AIR_PRESSURE_AVG.getLabel(),
                    HeaderFo3xCSVLabels.SOLAR_RADIATION_MIN.getLabel(),
                    HeaderFo3xCSVLabels.SOLAR_RADIATION_MAX.getLabel(),
                    HeaderFo3xCSVLabels.SOLAR_RADIATION_AVG.getLabel(),
                    HeaderFo3xCSVLabels.SOLAR_RADIATION_CALCULATED.getLabel(),
                    HeaderFo3xCSVLabels.WIND_SPEED_MAX.getLabel(),
                    HeaderFo3xCSVLabels.WIND_SPEED_AVG.getLabel(),
                    HeaderFo3xCSVLabels.WIND_DIRECTION_AVG.getLabel(),
                    HeaderFo3xCSVLabels.WIND_DIRECTION_STD.getLabel(),
                    HeaderFo3xCSVLabels.WIND_DIRECTION_AT_MAXIMUM_SPEED.getLabel(),
                    HeaderFo3xCSVLabels.EVAPOTRANSPIRATION.getLabel(),
                    HeaderFo3xCSVLabels.BATTERY_VOLTAGE.getLabel(),
                    HeaderFo3xCSVLabels.DATA_LOGGER_TEMPERATURE.getLabel(),
                    HeaderFo3xCSVLabels.AIR_TEMPERATURE_MEASUREMENT_ERRORS.getLabel(),
                    HeaderFo3xCSVLabels.ATMOSPHERIC_PRESSURE_MEASUREMENT_ERRORS.getLabel(),
                    HeaderFo3xCSVLabels.SOLAR_RADIATION_MEASUREMENT_ERRORS.getLabel(),
                    HeaderFo3xCSVLabels.WIND_MEASUREMENT_ERRORS.getLabel()
            );
            csvPrinter.printRecord(header);
            // store data records
            for(FoxHourlyWeatherRecordEntity record : listRecords) {
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
