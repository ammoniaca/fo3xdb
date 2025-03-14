package org.cnr.fo3xdb.service;

import org.cnr.fo3xdb.dto.FoxGlobalMetadataDTO;
import org.cnr.fo3xdb.dto.FoxWeatherUnitsDTO;
import org.cnr.fo3xdb.dto.FoxWeatherRecordsDTO;
import org.cnr.fo3xdb.dto.FoxWeatherResponseDTO;
import org.cnr.fo3xdb.entity.FoxWeatherUnitsEntity;
import org.cnr.fo3xdb.entity.FoxWeatherRecordEntity;
import org.cnr.fo3xdb.enums.CSVNoDataType;
import org.cnr.fo3xdb.exceptions.UnitsTableException;
import org.cnr.fo3xdb.exceptions.RecordsNotFoundException;
import org.cnr.fo3xdb.repository.FoxGlobalMetadataRepository;
import org.cnr.fo3xdb.repository.FoxWeatherUnitsRepository;
import org.cnr.fo3xdb.repository.FoxWeatherRecordRepository;
import org.cnr.fo3xdb.service.datevalidator.HourlyDateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.cnr.fo3xdb.helper.CSVHelper;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoxWeatherService extends FoxService{

    private static final String ZONE_EUROPE_ROME = "Europe/Rome";
    private final FoxWeatherUnitsRepository unitsRepository;
    private final FoxWeatherRecordRepository recordRepository;

    @Autowired
    public FoxWeatherService(
            FoxGlobalMetadataRepository globalMetadataRepository,
            FoxWeatherUnitsRepository unitsRepository,
            FoxWeatherRecordRepository recordRepository,
            ModelMapper mapper
    ) {
        super(globalMetadataRepository, mapper);
        this.unitsRepository = unitsRepository;
        this.recordRepository = recordRepository;
    }

    /**
     * This is a Javadoc
     */
    public FoxWeatherUnitsDTO getWeatherUnits(){
        Optional<FoxWeatherUnitsEntity> optionalWeatherUnits = unitsRepository.findById(1L);
        return optionalWeatherUnits
                .map(t -> getMapper().map(t, FoxWeatherUnitsDTO.class))
                .orElseThrow(
                        () -> new UnitsTableException("Error in retrieving weather unit values in table.")
                );
    }

    /**
     * This is a Javadoc
     */
    public FoxWeatherResponseDTO retrieveWeatherRecordsByDateRange(
            LocalDate startDate,
            LocalDate endDate)
    {
        // Check if date values are correct otherwise return an Exception
        new HourlyDateValidator(startDate, endDate).checkValidity();
        // Get global metadata
        FoxGlobalMetadataDTO foxGlobalMetadataDTO = globalMetadataEntity();
        // Get weather units
        FoxWeatherUnitsDTO unitsDTO = getWeatherUnits();
        // Convert date in OffsetDateTime with "Europe/Rome" zone
        OffsetDateTime odtStartDate = convertDateToOffsetDateTime(startDate, ZONE_EUROPE_ROME);
        OffsetDateTime odtEndDate = convertDateToOffsetDateTime(endDate, ZONE_EUROPE_ROME);

        // Get weather data records
        List<FoxWeatherRecordEntity> listRecords = recordRepository
                .findAllByTimestampBetween(odtStartDate, odtEndDate);
        if(listRecords.isEmpty()){
            String errorMessage = MessageFormat.format(
                    "Weather records not found from {0} to {1}.", startDate, endDate);
            throw new RecordsNotFoundException(errorMessage);
        }

        FoxWeatherRecordsDTO foxHourlyRecords = convertRecordsToListDTO(listRecords);

        return FoxWeatherResponseDTO
                .builder()
                .latitude(foxGlobalMetadataDTO.getLatitude())
                .longitude(foxGlobalMetadataDTO.getLongitude())
                .elevation(foxGlobalMetadataDTO.getElevation())
                .experiment(foxGlobalMetadataDTO.getExperiment())
                .time(foxGlobalMetadataDTO.getTime())
                .timezone(foxGlobalMetadataDTO.getTimezone())
                .systemOfUnits(foxGlobalMetadataDTO.getSystemOfUnits())
                .hourlyUnits(unitsDTO)
                .records(foxHourlyRecords)
                .build();
    }

    /**
     * This is a Javadoc
     */
    public ByteArrayInputStream downloadCSV(
            LocalDate startDate,
            LocalDate endDate,
            CSVNoDataType noData)
    {
        // Check if date values are correct otherwise return an Exception
        new HourlyDateValidator(startDate, endDate).checkValidity();

        // Convert date in OffsetDateTime with "Europe/Rome" zone
        OffsetDateTime odtStartDate = convertDateToOffsetDateTime(startDate, ZONE_EUROPE_ROME);
        OffsetDateTime odtEndDate = convertDateToOffsetDateTime(endDate,ZONE_EUROPE_ROME);

        // Get data
        List<FoxWeatherRecordEntity> listRecords = recordRepository
                .findAllByTimestampBetween(odtStartDate, odtEndDate);
        if(listRecords.isEmpty()){
            String errorMessage = MessageFormat.format(
                    "Weather records not found from {0} to {1}.", startDate, endDate);
            throw new RecordsNotFoundException(errorMessage);
        }
        return CSVHelper.recordsToCSV(
                listRecords,
                noData
        );
    }

    /**
     * This is a Javadoc
     */
    private FoxWeatherRecordsDTO convertRecordsToListDTO(
            List<FoxWeatherRecordEntity> records)
    {
        FoxWeatherRecordsDTO response = new FoxWeatherRecordsDTO();
        for(FoxWeatherRecordEntity record : records){
            // timestamp in UTC "Europe/Rome"
            OffsetDateTime timestamp = record.getTimestamp();
            ZonedDateTime romeZonedDateTime = timestamp.atZoneSameInstant(ZoneId.of(ZONE_EUROPE_ROME));
            response.getTimestamp().add(romeZonedDateTime.toOffsetDateTime());
            // Rain (total, max)
            response.getRainTotal().add(record.getRainTotal());
            response.getRainIntensityMax().add(record.getRainIntensityMax());
            // Air_temperature (Min, Max, Avg)
            response.getAirTemperatureMin().add(record.getAirTemperatureMin());
            response.getAirTemperatureMax().add(record.getAirTemperatureMax());
            response.getAirTemperatureAvg().add(record.getAirTemperatureAvg());
            // air_humidity (Avg)
            response.getAirHumidityAvg().add(record.getAirHumidityAvg());
            // dew_point
            response.getDewPoint().add(record.getDewPoint());
            // air_pressure (Avg)
            response.getAirPressureAvg().add(record.getAirPressureAvg());
            // Solar_radiation (Min, Max, Avg, calculated)
            response.getSolarRadiationMin().add(record.getSolarRadiationMin());
            response.getSolarRadiationMax().add(record.getSolarRadiationMax());
            response.getSolarRadiationAvg().add(record.getSolarRadiationAvg());
            response.getSolarRadiationCalculated().add(record.getSolarRadiationCalculated());
            // Wind (avg, direction_avg, direction_std, speed_max, direction_at_max_speed)
            response.getWindSpeedAvg().add(record.getWindSpeedAvg());
            response.getWindDirectionAvg().add(record.getWindDirectionAvg());
            response.getWindDirectionSTD().add(record.getWindDirectionSTD());
            response.getWindSpeedMax().add(record.getWindSpeedMax());
            response.getWindDirectionAtMaximumSpeed().add(record.getWindDirectionAtMaximumSpeed());
            // Evapotranspiration
            response.getEvapotranspiration().add(record.getEvapotranspiration());
            // Instrument (battery, data_logger)
            response.getBatteryVoltage().add(record.getBatteryVoltage());
            response.getDataLoggerTemperature().add(record.getDataLoggerTemperature());
            // Errors measurement (air_temperature, air_humidity, atmospheric_pressure, solar_Radiation, wind_measurement)
            response.getAirTemperatureMeasurementErrors().add(record.getAirTemperatureMeasurementErrors());
            response.getAirHumidityMeasurementErrors().add(record.getAirHumidityMeasurementErrors());
            response.getAtmosphericPressureMeasurementErrors().add(record.getAtmosphericPressureMeasurementErrors());
            response.getSolarRadiationMeasurementErrors().add(record.getSolarRadiationMeasurementErrors());
            response.getWindMeasurementErrors().add(record.getWindMeasurementErrors());
        }
        return response;
    }
}
