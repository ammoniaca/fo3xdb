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

}
