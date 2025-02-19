package org.cnr.fo3xdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.cnr.fo3xdb.dto.FoxHourlyResponseDTO;
import org.cnr.fo3xdb.service.FoxHourlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/fox")
public class FoxHourlyController {


    private final FoxHourlyService service;

    @Autowired
    public FoxHourlyController(FoxHourlyService service) {

        this.service = service;
    }

    @GetMapping(
            value = "/hello",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String hello(){
        return "Hello World";
    }

    @GetMapping(
            value = "/records",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FoxHourlyResponseDTO> getRecords(
                @RequestParam(value="start") LocalDate startDate,
                @RequestParam(value="end") LocalDate endDate
            ) {
            FoxHourlyResponseDTO response = service
                    .retrieveRecordsByDateInterval(
                            startDate,
                            endDate
                    );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
    }

}
