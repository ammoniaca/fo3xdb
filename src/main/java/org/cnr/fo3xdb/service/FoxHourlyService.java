package org.cnr.fo3xdb.service;

import org.cnr.fo3xdb.dto.FoxGlobalMetadataDTO;
import org.cnr.fo3xdb.dto.FoxHourlyMetadataDTO;
import org.cnr.fo3xdb.dto.FoxHourlyRecordsDTO;
import org.cnr.fo3xdb.dto.FoxHourlyResponseDTO;
import org.cnr.fo3xdb.entity.FoxGlobalMetadataEntity;
import org.cnr.fo3xdb.entity.FoxHourlyMetadataEntity;
import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;
import org.cnr.fo3xdb.repository.FoxGlobalMetadataRepository;
import org.cnr.fo3xdb.repository.FoxHourlyMetadataRepository;
import org.cnr.fo3xdb.repository.FoxHourlyRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FoxHourlyService {

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

    public FoxHourlyResponseDTO retrieveRecordByIntervalData(){
        // get data
        Optional<FoxGlobalMetadataEntity> optionalGlobalMetadata = globalMetadataRepository.findById(1L);
        Optional<FoxHourlyMetadataEntity> optionalHourlyMetadata = hourlyMetadataRepository.findById(1L);
        // TODO
        List<FoxHourlyRecordEntity> listRecords = recordRepository.findAll();

        // map entity to DTO class
        FoxGlobalMetadataDTO foxGlobalMetadataDTO = optionalGlobalMetadata
                .map(t -> mapper.map(t, FoxGlobalMetadataDTO.class))
                .orElse(null);
        // map entity to DTO class
        FoxHourlyMetadataDTO foxHourlyMetadataDTO = optionalHourlyMetadata
                .map(t -> mapper.map(t, FoxHourlyMetadataDTO.class))
                .orElse(null);
        // populate class object
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
            // timestamp
            responseDTO.appendTimestamp(record.getTimestamp());
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
