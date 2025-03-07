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
        dateChecker(startDate, endDate);
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
            throw new RecordsNotFoundException("Weather records not found.");
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
                .hourly(foxHourlyRecords)
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
        dateChecker(startDate, endDate);

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
        FoxWeatherRecordsDTO responseDTO = new FoxWeatherRecordsDTO();
        for(FoxWeatherRecordEntity record : records){
            // timestamp in UTC "Europe/Rome"
            OffsetDateTime timestamp = record.getTimestamp();
            ZonedDateTime romeZonedDateTime = timestamp.atZoneSameInstant(ZoneId.of(ZONE_EUROPE_ROME));
            responseDTO.appendTimestamp(romeZonedDateTime.toOffsetDateTime());
            // Rain (total, max)
            responseDTO.appendRainTotal(record.getRainTotal());
            responseDTO.appendRainIntensityMax(record.getRainIntensityMax());
            // Air_temperature (Min, Max, Avg)
            responseDTO.appendAirTemperatureMin(record.getAirTemperatureMin());
            responseDTO.appendAirTemperatureMax(record.getAirTemperatureMax());
            responseDTO.appendAirTemperatureAvg(record.getAirTemperatureAvg());
            // air_humidity (Avg)
            responseDTO.appendAirHumidityAvg(record.getAirHumidityAvg());
            // dew_point
            responseDTO.appendAirDewPoint(record.getDewPoint());
            // air_pressure (Avg)
            responseDTO.appendAirPressureAvg(record.getAirPressureAvg());
            // Solar_radiation (Max, Min, Avg)
            responseDTO.appendSolarRadiationMax(record.getSolarRadiationMax());
            responseDTO.appendSolarRadiationMin(record.getSolarRadiationMin());
            responseDTO.appendSolarRadiationAvg(record.getSolarRadiationAvg());
            // Wind (avg, direction_avg, direction_std, speed_max, direction_at_max_speed)
            responseDTO.appendWindSpeedAvg(record.getWindSpeedAvg());
            responseDTO.appendWindDirectionAvg(record.getWindDirectionAvg());
            responseDTO.appendWindDirectionSTD(record.getWindDirectionSTD());
            responseDTO.appendWindSpeedMax(record.getWindSpeedMax());
            responseDTO.appendWindDirectionAtMaximumSpeed(record.getWindDirectionAtMaximumSpeed());
            // Evapotranspiration
            responseDTO.appendEvapotranspiration(record.getEvapotranspiration());
            // Solar_Radiation_Calculated
            responseDTO.appendSolarRadiationCalculated(record.getSolarRadiationCalculated());
            // Instrument (battery, data_logger)
            responseDTO.appendBatteryVoltage(record.getBatteryVoltage());
            responseDTO.appendDataLoggerTemperature(record.getDataLoggerTemperature());
            // Errors measurement (air_temperature, air_humidity, atmospheric_pressure, solar_Radiation, wind_measurement)
            responseDTO.appendAirTemperatureMeasurementErrors(record.getAirTemperatureMeasurementErrors());
            responseDTO.appendAirHumidityMeasurementErrors(record.getAirHumidityMeasurementErrors());
            responseDTO.appendAtmosphericPressureMeasurementErrors(record.getAtmosphericPressureMeasurementErrors());
            responseDTO.appendSolarRadiationMeasurementErrors(record.getSolarRadiationMeasurementErrors());
            responseDTO.appendWindMeasurementErrors(record.getWindMeasurementErrors());
        }
        return responseDTO;
    }
}
