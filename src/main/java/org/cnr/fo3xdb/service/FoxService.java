package org.cnr.fo3xdb.service;

import lombok.Getter;
import org.cnr.fo3xdb.dto.FoxGlobalMetadataDTO;
import org.cnr.fo3xdb.entity.FoxGlobalMetadataEntity;
import org.cnr.fo3xdb.exceptions.DateRangeNotValidException;
import org.cnr.fo3xdb.exceptions.GlobalMetadataTableException;
import org.cnr.fo3xdb.repository.FoxGlobalMetadataRepository;
import org.modelmapper.ModelMapper;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Getter
public abstract class FoxService {

    private static final int LOWER_HOURLY_DAYS_BOUND = 0;
    private static final int UPPER_HOURLY_DAYS_BOUND = 180;
    private final FoxGlobalMetadataRepository globalMetadataRepository;
    private final ModelMapper mapper;

    public FoxService(FoxGlobalMetadataRepository globalMetadataRepository, ModelMapper mapper) {
        this.globalMetadataRepository = globalMetadataRepository;
        this.mapper = mapper;
    }


    public FoxGlobalMetadataDTO globalMetadataEntity(){
        Optional<FoxGlobalMetadataEntity> optionalGlobalMetadata = globalMetadataRepository.findById(1L);
        return optionalGlobalMetadata
                .map(t -> mapper.map(t, FoxGlobalMetadataDTO.class))
                .orElseThrow(
                        () -> new GlobalMetadataTableException("Error retrieving global metadata in table.")
                );
    }


    public OffsetDateTime convertDateToOffsetDateTime(
            LocalDate date,
            String zone)
    {
        return date
                .atStartOfDay(ZoneId.of(zone))
                .toOffsetDateTime();
    }

    public void dateChecker(LocalDate startDate, LocalDate endDate) {
        long flagDays = ChronoUnit.DAYS.between(startDate, endDate);
        if(!(LOWER_HOURLY_DAYS_BOUND < flagDays && flagDays < UPPER_HOURLY_DAYS_BOUND)){
            String errorMessage = MessageFormat.format(
                    "The date range between start date {0} and end date {1} cannot more of {2} days.",
                    startDate, endDate, UPPER_HOURLY_DAYS_BOUND);
            if(flagDays <= LOWER_HOURLY_DAYS_BOUND) {
                errorMessage = MessageFormat.format(
                        "The start date {0} cannot be equal to or less than the end date {1}.",
                        startDate, endDate);
            }
            throw new DateRangeNotValidException(errorMessage);
        }
    }

}
