package org.cnr.fo3xdb.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cnr.fo3xdb.repository.FoxHourlyRecordRepository;
import org.springframework.boot.CommandLineRunner;
import com.fasterxml.jackson.core.type.TypeReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonRecordLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonRecordLoader.class);
    private final FoxHourlyRecordRepository repository;
    private final ObjectMapper objectMapper;

    public RunJsonRecordLoader(FoxHourlyRecordRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.objectMapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/records.json")){
            FoxRecords AllRecords = objectMapper.readValue(inputStream, FoxRecords.class);
            log.info("Reading {} records from JSON data and saving to in-memory collection.", AllRecords.records().size());
            repository.saveAll(AllRecords.records());
            } catch (IOException ex) {
                throw new RuntimeException("Failed to read JSON data", ex);
            }
        } else {
            log.info("Not loading records from JSON data because the collection contains data.");
        }
    }
}
