package org.cnr.fo3xdb.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream recordsToCSV(List<FoxHourlyRecordEntity> listRecords) {
        final CSVFormat format = CSVFormat
                .Builder
                .create()
                .setDelimiter(';')
                .get();
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);
            )
        {
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
                        record.getWindSpeedAvg().toString(),
                        record.getWindDirectionAvg().toString(),
                        record.getWindDirectionSTD().toString(),
                        record.getWindSpeedMax().toString(),
                        record.getWindDirectionAtMaximumSpeed().toString(),
                        record.getEvapotranspiration().toString(),
                        record.getSolarRadiationCalculated().toString(),
                        record.getBatteryVoltage().toString(),
                        record.getDataLoggerTemperature().toString(),
                        record.getAirTemperatureMeasurementErrors().toString(),
                        record.getAtmosphericPressureMeasurementErrors().toString(),
                        record.getSolarRadiationMeasurementErrors().toString(),
                        record.getWindMeasurementErrors().toString(),
                        record.getAirTemperatureAvg().toString()
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
