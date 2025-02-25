package org.cnr.fo3xdb.service;

import org.cnr.fo3xdb.dto.FoxGlobalMetadataDTO;
import org.cnr.fo3xdb.dto.FoxHourlyMetadataDTO;
import org.cnr.fo3xdb.dto.FoxHourlyRecordsDTO;
import org.cnr.fo3xdb.dto.FoxHourlyResponseDTO;
import org.cnr.fo3xdb.entity.FoxGlobalMetadataEntity;
import org.cnr.fo3xdb.entity.FoxHourlyMetadataEntity;
import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;
import org.cnr.fo3xdb.exceptions.GlobalMetadataTableException;
import org.cnr.fo3xdb.exceptions.HourlyMetadataTableException;
import org.cnr.fo3xdb.exceptions.RecordsNotFoundException;
import org.cnr.fo3xdb.repository.FoxGlobalMetadataRepository;
import org.cnr.fo3xdb.repository.FoxHourlyMetadataRepository;
import org.cnr.fo3xdb.repository.FoxHourlyRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoxHourlyService {

    private static final String ZONE_EUROPE_ROME = "Europe/Rome";
    private final FoxGlobalMetadataRepository globalMetadataRepository;
    private final FoxHourlyMetadataRepository hourlyMetadataRepository;
    private final FoxHourlyRecordRepository recordRepository;
    private final ModelMapper mapper;

    @Autowired
    public FoxHourlyService(
            FoxGlobalMetadataRepository globalMetadataRepository,
            FoxHourlyMetadataRepository hourlyMetadataRepository,
            FoxHourlyRecordRepository recordRepository,
            ModelMapper mapper
    ) {
        this.globalMetadataRepository = globalMetadataRepository;
        this.hourlyMetadataRepository = hourlyMetadataRepository;
        this.recordRepository = recordRepository;
        this.mapper = mapper;
    }

    public FoxHourlyResponseDTO retrieveRecordsByDateInterval(
            LocalDate startDate,
            LocalDate endDate
    ) {
        // Get global metadata and map entity to DTO class
        Optional<FoxGlobalMetadataEntity> optionalGlobalMetadata = globalMetadataRepository.findById(1L);
        FoxGlobalMetadataDTO foxGlobalMetadataDTO = optionalGlobalMetadata
                .map(t -> mapper.map(t, FoxGlobalMetadataDTO.class))
                .orElseThrow(
                        () -> new GlobalMetadataTableException("Error retrieving global metadata in table.")
                );

        // Get hourly metadata and map entity to DTO class
        Optional<FoxHourlyMetadataEntity> optionalHourlyMetadata = hourlyMetadataRepository.findById(1L);
        FoxHourlyMetadataDTO foxHourlyMetadataDTO = optionalHourlyMetadata
                .map(t -> mapper.map(t, FoxHourlyMetadataDTO.class))
                .orElseThrow(
                        () -> new HourlyMetadataTableException("Error retrieving hourly metadata in table.")
                );

        OffsetDateTime odtStartDate = startDate
                .atStartOfDay(ZoneId.of(ZONE_EUROPE_ROME))
                .toOffsetDateTime();
        OffsetDateTime odtEndDate = endDate
                .atStartOfDay(ZoneId.of(ZONE_EUROPE_ROME))
                .toOffsetDateTime();

        // Get data
        List<FoxHourlyRecordEntity> listRecords = recordRepository
                .findAllByTimestampBetween(odtStartDate, odtEndDate);
        if(listRecords.isEmpty()){
            throw new RecordsNotFoundException("Records not found.");
        }

        FoxHourlyRecordsDTO foxHourlyRecords = convertRecordsToListDTO(listRecords);

        return FoxHourlyResponseDTO
                .builder()
                .latitude(foxGlobalMetadataDTO.getLatitude())
                .longitude(foxGlobalMetadataDTO.getLongitude())
                .elevation(foxGlobalMetadataDTO.getElevation())
                .experiment(foxGlobalMetadataDTO.getExperiment())
                .time(foxGlobalMetadataDTO.getTime())
                .timezone(foxGlobalMetadataDTO.getTimezone())
                .hourlyUnits(foxHourlyMetadataDTO)
                .hourly(foxHourlyRecords)
                .build();
    }

    private FoxHourlyRecordsDTO convertRecordsToListDTO(List<FoxHourlyRecordEntity> records){
        FoxHourlyRecordsDTO responseDTO = new FoxHourlyRecordsDTO();
        for(FoxHourlyRecordEntity record : records){
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
