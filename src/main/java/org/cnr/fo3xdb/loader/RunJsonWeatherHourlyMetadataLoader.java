package org.cnr.fo3xdb.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cnr.fo3xdb.entity.FoxHourlyWeatherMetadataEntity;
import org.cnr.fo3xdb.repository.FoxHourlyWeatherMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonWeatherHourlyMetadataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonWeatherHourlyMetadataLoader.class);
    private final FoxHourlyWeatherMetadataRepository repository;
    private final ObjectMapper objectMapper;

    public RunJsonWeatherHourlyMetadataLoader(FoxHourlyWeatherMetadataRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/weather-metadata-hourly.json")) {
                FoxHourlyWeatherMetadataEntity metadata = objectMapper.readValue(inputStream, FoxHourlyWeatherMetadataEntity.class);
                log.info("Reading hourly metadata from JSON data and saving to in-memory collection.");
                repository.save(metadata);
            } catch (IOException ex) {
            throw new RuntimeException("Failed to read JSON data", ex);
        }
        } else {
            log.info("Not loading hourly metadata from JSON data because the collection contains data.");
        }
    }
}
