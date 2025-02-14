package org.cnr.fo3xdb.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cnr.fo3xdb.dto.FoxHourlyRecordDTO;
import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;
import org.cnr.fo3xdb.repository.FoxHourlyRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import com.fasterxml.jackson.core.type.TypeReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final FoxHourlyRecordRepository repository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(FoxHourlyRecordRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.objectMapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/records.json")){
            RecordsFox AllRecords = objectMapper.readValue(inputStream, RecordsFox.class);
            log.info("Reading {} runs from JSON data and saving to in-memory collection.", AllRecords.records().size());
            repository.saveAll(AllRecords.records());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Runs from JSON data because the collection contains data.");
        }
    }
}
