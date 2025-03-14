package org.cnr.fo3xdb.service;

import org.apache.commons.math3.util.Precision;
import org.cnr.fo3xdb.dto.*;
import org.cnr.fo3xdb.entity.FoxOzoneRecordEntity;
import org.cnr.fo3xdb.entity.FoxOzoneUnitsEntity;
import org.cnr.fo3xdb.enums.TemporalUnit;
import org.cnr.fo3xdb.exceptions.RecordsNotFoundException;
import org.cnr.fo3xdb.exceptions.UnitsTableException;
import org.cnr.fo3xdb.repository.FoxGlobalMetadataRepository;
import org.cnr.fo3xdb.repository.FoxOzoneRecordRepository;
import org.cnr.fo3xdb.repository.FoxOzoneUnitsRepository;
import org.cnr.fo3xdb.service.datevalidator.HourlyDateValidator;
import org.cnr.fo3xdb.service.datevalidator.MinuteDateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.*;

@Service
public class FoxOzoneService extends FoxService{

    private static final String ZONE_EUROPE_ROME = "Europe/Rome";
    private static final int PRECISION_SCALE = 3;
    private final FoxOzoneRecordRepository recordRepository;
    private final FoxOzoneUnitsRepository unitsRepository;

    @Autowired
    public FoxOzoneService(
            FoxOzoneRecordRepository recordRepository,
            FoxGlobalMetadataRepository globalMetadataRepository,
            FoxOzoneUnitsRepository unitsRepository,
            ModelMapper mapper)
    {
        super(globalMetadataRepository, mapper);
        this.recordRepository = recordRepository;
        this.unitsRepository = unitsRepository;
    }

    /**
     * This is a Javadoc
     */
    public FoxOzoneUnitsDTO getOzoneUnits(){
        Optional<FoxOzoneUnitsEntity> optionalOzoneUnits = unitsRepository.findById(1L);
        if(optionalOzoneUnits.isEmpty()){
            throw new UnitsTableException("Error in retrieving ozone unit values in table.");
        }
        FoxOzoneUnitsEntity ozoneUnitsEntity = optionalOzoneUnits.get();
        return getMapper().map(ozoneUnitsEntity, FoxOzoneUnitsDTO.class);
    }

    /**
     * This is a Javadoc
     */
    public FoxOzoneResponseDTO fetchOzoneByDateRange(
            LocalDate startDate,
            LocalDate endDate,
            TemporalUnit temporal)
    {
        // Check if date values are correct otherwise return an Exception
        switch (temporal){
            case HOURLY -> new HourlyDateValidator(startDate, endDate).checkValidity();
            case MINUTE -> new MinuteDateValidator(startDate, endDate).checkValidity();
        }

        // Get metadata
        FoxGlobalMetadataDTO metadata = globalMetadataEntity();
        // Get unit
        FoxOzoneUnitsDTO units = getOzoneUnits();
        // Create Ozone Response
        FoxOzoneResponseDTO response = FoxOzoneResponseDTO
                .builder()
                .latitude(metadata.getLatitude())
                .longitude(metadata.getLongitude())
                .elevation(metadata.getElevation())
                .experiment(metadata.getExperiment())
                .time(metadata.getTime())
                .timezone(metadata.getTimezone())
                .systemOfUnits(metadata.getSystemOfUnits())
                .temporal(temporal)
                .units(units)
                .build();

        // Convert date in OffsetDateTime with "Europe/Rome" zone
        OffsetDateTime odtStartDate = convertDateToOffsetDateTime(startDate, ZONE_EUROPE_ROME);
        OffsetDateTime odtEndDate = convertDateToOffsetDateTime(endDate,ZONE_EUROPE_ROME);
        // Get Ozone (minute) data
        List<FoxOzoneRecordEntity> listRecords = recordRepository
                .findAllByTimestampBetween(odtStartDate, odtEndDate);
        if(listRecords.isEmpty()){
            String errorMessage = MessageFormat.format(
                    "Ozone records not found from {0} to {1}.", startDate, endDate);
            throw new RecordsNotFoundException(errorMessage);
        }

        if(TemporalUnit.MINUTE.equals(temporal)){
            response.setRecords(convertToRecordsList(listRecords));
        } else {
            List<OffsetDateTime> dateList = generateHourlyDates(odtStartDate, odtEndDate);
            FoxOzoneRecordsDTO recordsAvg = new FoxOzoneRecordsDTO();
            for (int i = 0; i < dateList.size() - 1; i++) {
                OffsetDateTime current = dateList.get(i);
                OffsetDateTime next = dateList.get(i + 1);
                List<FoxOzoneRecordEntity> records = recordRepository.findAllByTimestampBetween(current, next);
                if(!listRecords.isEmpty()){
                    FoxOzoneRecordsDTO  recordInsideHour = convertToRecordsList(records);
                    // add next time
                    recordsAvg.getTimestamp().add(next);
                    // add wind Speed hourly avg
                    recordsAvg.getWindSpeed().add(
                            calculateAverage(recordInsideHour.getWindSpeed(), PRECISION_SCALE));
                    // add Ozone Level 0 hourly avg
                    recordsAvg.getOzoneLevel0().add(
                            calculateAverage(recordInsideHour.getOzoneLevel0(), PRECISION_SCALE));
                    // add Ozone Level 1 hourly avg
                    recordsAvg.getOzoneLevel1().add(
                            calculateAverage(recordInsideHour.getOzoneLevel1(), PRECISION_SCALE));
                    // add Ozone Level 2 hourly avg
                    recordsAvg.getOzoneLevel2().add(
                            calculateAverage(recordInsideHour.getOzoneLevel2(), PRECISION_SCALE));
                }
            }
            response.setRecords(recordsAvg);
        }
        return response;
    }

    private FoxOzoneRecordsDTO convertToRecordsList(
            List<FoxOzoneRecordEntity> records) {
        FoxOzoneRecordsDTO response = new FoxOzoneRecordsDTO();
        for (FoxOzoneRecordEntity record : records) {
            // timestamp in UTC "Europe/Rome"
            OffsetDateTime timestamp = record.getTimestamp();
            ZonedDateTime romeZonedDateTime = timestamp.atZoneSameInstant(ZoneId.of(ZONE_EUROPE_ROME));
            response.getTimestamp().add(romeZonedDateTime.toOffsetDateTime());
            // Wind Speed
            response.getWindSpeed().add(record.getWindSpeed());
            // Ozone Level 0
            response.getOzoneLevel0().add(record.getOzoneLevel0());
            // Ozone Level 1
            response.getOzoneLevel1().add(record.getOzoneLevel1());
            // Ozone Level 2
            response.getOzoneLevel2().add(record.getOzoneLevel2());
        }
        return response;
    }

    private List<OffsetDateTime> generateHourlyDates(
            OffsetDateTime start, OffsetDateTime end)
    {
        List<OffsetDateTime> dates = new ArrayList<>();
        OffsetDateTime current = start;
        while (!current.isAfter(end)) {
            dates.add(current);
            current = current.plusHours(1); // add 1 hour
        }
        return dates;
    }

    private Double calculateAverage(List<Double> numbers, int precisionScale) {
        if (numbers == null || numbers.isEmpty()) {
            return null;
        }
        OptionalDouble average = numbers.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average();
        return average.isPresent() ? Precision.round(average.getAsDouble(), precisionScale) : null;
    }

}
