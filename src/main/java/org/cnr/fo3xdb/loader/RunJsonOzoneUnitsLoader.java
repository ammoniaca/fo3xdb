package org.cnr.fo3xdb.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cnr.fo3xdb.entity.FoxOzoneUnitsEntity;
import org.cnr.fo3xdb.repository.FoxOzoneUnitsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonOzoneUnitsLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonOzoneUnitsLoader.class);
    private final FoxOzoneUnitsRepository repository;
    private final ObjectMapper objectMapper;

    public RunJsonOzoneUnitsLoader(FoxOzoneUnitsRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/ozone-units.json")) {
                FoxOzoneUnitsEntity ozoneUnit = objectMapper.readValue(inputStream, FoxOzoneUnitsEntity.class);
                log.info("Reading ozone units from JSON data and saving to in-memory collection.");
                //log.info("Reading {} records from JSON ozone units and saving to in-memory collection.", AllRecords.records().size());
                repository.save(ozoneUnit);
            } catch (IOException ex) {
                throw new RuntimeException("Failed to read JSON ozone units", ex);
            }
        } else {
            log.info("Not loading ozone units from JSON data because the collection contains data.");
        }
    }
}
