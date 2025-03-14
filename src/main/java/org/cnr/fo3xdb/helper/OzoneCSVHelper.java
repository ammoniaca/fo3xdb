package org.cnr.fo3xdb.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.cnr.fo3xdb.dto.FoxOzoneRecordsDTO;
import org.cnr.fo3xdb.entity.FoxOzoneRecordEntity;
import org.cnr.fo3xdb.entity.FoxWeatherRecordEntity;
import org.cnr.fo3xdb.enums.CSVNoDataType;
import org.cnr.fo3xdb.enums.HeaderFoxOzoneCSVLabels;
import org.cnr.fo3xdb.enums.HeaderFoxWeatherCSVLabels;
import org.cnr.fo3xdb.exceptions.CSVProducerException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class OzoneCSVHelper extends CSVHelper {

    public static ByteArrayInputStream recordsToCSV(
            FoxOzoneRecordsDTO records,
            CSVNoDataType noData)
    {
        final CSVFormat format = CSVFormat
                .Builder
                .create()
                .setDelimiter(';')
                .get();
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format))
        {
            // store ozone header
            List<String> header = Arrays.asList(
                    HeaderFoxOzoneCSVLabels.TIMESTAMP.getLabel(),
                    HeaderFoxOzoneCSVLabels.WIND_SPEED.getLabel(),
                    HeaderFoxOzoneCSVLabels.OZONE_LEVEL_0.getLabel(),
                    HeaderFoxOzoneCSVLabels.OZONE_LEVEL_1.getLabel(),
                    HeaderFoxOzoneCSVLabels.OZONE_LEVEL_2.getLabel());
            csvPrinter.printRecord(header);
            // store ozone data records
            for (int i = 0; i < records.getTimestamp().size() ; i++){
                List<String> data = Arrays.asList(
                        records.getTimestamp().get(i).toString(),
                        safetyDoubleToString(records.getWindSpeed().get(i), noData),
                        safetyDoubleToString(records.getOzoneLevel0().get(i), noData),
                        safetyDoubleToString(records.getOzoneLevel1().get(i), noData),
                        safetyDoubleToString(records.getOzoneLevel2().get(i), noData));
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            String errorMessage = MessageFormat.format("CSV: {0}", e.getMessage());
            throw new CSVProducerException(errorMessage);
        }
    }
}
