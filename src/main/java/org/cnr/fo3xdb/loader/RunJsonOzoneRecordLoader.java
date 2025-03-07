package org.cnr.fo3xdb.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cnr.fo3xdb.repository.FoxOzoneRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonOzoneRecordLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonOzoneRecordLoader.class);
    private final FoxOzoneRecordRepository repository;
    private final ObjectMapper objectMapper;

    public RunJsonOzoneRecordLoader(FoxOzoneRecordRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/ozone-records.json")){
                FoxOzoneRecords AllRecords = objectMapper.readValue(inputStream, FoxOzoneRecords.class);
                log.info("Reading {} records from JSON ozone data and saving to in-memory collection.",
                        AllRecords.records().size());
                repository.saveAll(AllRecords.records());
            } catch (IOException ex) {
                throw new RuntimeException("Failed to read JSON ozone data", ex);
            }
        } else {
            log.info("Not loading records from JSON ozone data because the collection contains data.");
        }
    }
}
