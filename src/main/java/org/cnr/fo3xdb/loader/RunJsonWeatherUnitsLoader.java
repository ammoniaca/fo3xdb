package org.cnr.fo3xdb.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cnr.fo3xdb.entity.FoxWeatherUnitsEntity;
import org.cnr.fo3xdb.repository.FoxWeatherUnitsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonWeatherUnitsLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonWeatherUnitsLoader.class);
    private final FoxWeatherUnitsRepository repository;
    private final ObjectMapper objectMapper;

    public RunJsonWeatherUnitsLoader(FoxWeatherUnitsRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/weather-units.json")) {
                FoxWeatherUnitsEntity weatherUnits = objectMapper.readValue(inputStream, FoxWeatherUnitsEntity.class);
                log.info("Reading weather units from JSON data and saving to in-memory collection.");
                repository.save(weatherUnits);
            } catch (IOException ex) {
            throw new RuntimeException("Failed to read JSON units", ex);
        }
        } else {
            log.info("Not loading weather units from JSON data because the collection contains data.");
        }
    }
}
