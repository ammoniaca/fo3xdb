package org.cnr.fo3xdb.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;
import org.cnr.fo3xdb.enums.HeaderFo3xCSVLabels;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream recordsToCSV(
            List<FoxHourlyRecordEntity> listRecords
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
            for(FoxHourlyRecordEntity record : listRecords) {
                List<String> data = Arrays.asList(
                        String.valueOf(record.getId()),
                        record.getTimestamp().toString(),
                        record.getRainTotal().toString(),
                        record.getRainIntensityMax().toString(),
                        record.getAirTemperatureMin().toString(),
                        record.getAirTemperatureMax().toString(),
                        record.getAirTemperatureAvg().toString(),
                        record.getAirHumidityAvg().toString(),
                        record.getDewPoint().toString(),
                        record.getAirPressureAvg().toString(),
                        record.getSolarRadiationMin().toString(),
                        record.getSolarRadiationMax().toString(),
                        record.getSolarRadiationAvg().toString(),
                        record.getSolarRadiationCalculated().toString(),
                        record.getWindSpeedMax().toString(),
                        record.getWindSpeedAvg().toString(),
                        record.getWindDirectionAvg().toString(),
                        record.getWindDirectionSTD().toString(),
                        record.getWindDirectionAtMaximumSpeed().toString(),
                        record.getEvapotranspiration().toString(),
                        record.getBatteryVoltage().toString(),
                        record.getDataLoggerTemperature().toString(),
                        record.getAirTemperatureMeasurementErrors().toString(),
                        record.getAtmosphericPressureMeasurementErrors().toString(),
                        record.getSolarRadiationMeasurementErrors().toString(),
                        record.getWindMeasurementErrors().toString()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
