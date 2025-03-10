package org.cnr.fo3xdb.service;

import org.cnr.fo3xdb.dto.FoxGlobalMetadataDTO;
import org.cnr.fo3xdb.dto.FoxHourlyOzoneResponseDTO;
import org.cnr.fo3xdb.dto.FoxOzoneRecordDTO;
import org.cnr.fo3xdb.dto.FoxOzoneUnitsDTO;
import org.cnr.fo3xdb.entity.FoxOzoneUnitsEntity;
import org.cnr.fo3xdb.enums.TemporalUnit;
import org.cnr.fo3xdb.exceptions.UnitsTableException;
import org.cnr.fo3xdb.repository.FoxGlobalMetadataRepository;
import org.cnr.fo3xdb.repository.FoxOzoneRecordRepository;
import org.cnr.fo3xdb.repository.FoxOzoneUnitsRepository;
import org.cnr.fo3xdb.service.datevalidator.HourlyDateValidator;
import org.cnr.fo3xdb.service.datevalidator.MinuteDateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FoxOzoneService extends FoxService{

    private static final int LOWER_MINUTE_DAYS_BOUND = 0;
    private static final int UPPER_MINUTE_DAYS_BOUND = 3;
    private static final String ZONE_EUROPE_ROME = "Europe/Rome";
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
    public FoxOzoneRecordDTO retrieveOzoneRecordsByDateRange(
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

        FoxHourlyOzoneResponseDTO response = FoxHourlyOzoneResponseDTO
                .builder()
                .units(units)
                .build();

        response.setLatitude(metadata.getLatitude());
        response.setLongitude(metadata.getLongitude());
        response.setElevation(metadata.getElevation());
        response.setExperiment(metadata.getExperiment());
        response.setTime(metadata.getTime());
        response.setTimezone(metadata.getTimezone());
        metadata.setSystemOfUnits(metadata.getSystemOfUnits());








        return null;
    }


}
