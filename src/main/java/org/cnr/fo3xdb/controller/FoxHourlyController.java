package org.cnr.fo3xdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cnr.fo3xdb.dto.FoxHourlyResponseDTO;
import org.cnr.fo3xdb.enums.CSVNoDataType;
import org.cnr.fo3xdb.exceptions.DateRangeNotValidException;
import org.cnr.fo3xdb.exceptions.ErrorResponseDTO;
import org.cnr.fo3xdb.service.FoxHourlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/v1/fox")
@Tag(name = "FO3X APIs")
public class FoxHourlyController {

    private static final int LOWER_DAYS_BOUND = 0;
    private static final int UPPER_DAYS_BOUND = 180;
    private final FoxHourlyService service;

    @Autowired
    public FoxHourlyController(FoxHourlyService service) {

        this.service = service;
    }

    @Operation(
            summary = "Search for FO3X data given a specific date range.",
            description = "This endpoint allows users to search for FO3X (Ozone FACE – free air controlled " +
                    "exposure) data in a specific date range."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully returns JSON (JavaScript Object Notation) format."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )}
    )
    @GetMapping(
            value = "/records/json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FoxHourlyResponseDTO> getJSONRecords(
                @RequestParam(value="start")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                @RequestParam(value="end")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        // Check if date values are correct otherwise return an Exception
        dateChecker(startDate, endDate);
        // get data
        FoxHourlyResponseDTO response = service
                    .retrieveRecordsByDateRange(
                            startDate,
                            endDate
                    );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(
            summary = "Search for FO3X data given a specific date range.",
            description = "This endpoint allows users to search for FO3X (Ozone FACE – free air controlled " +
                    "exposure) data in a specific date range."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully returns CSV (comma-separated values) format."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )}
    )
    @GetMapping(
            value = "/records/csv",
            produces = {"application/csv"}
    )
    public ResponseEntity<Resource> getCSVRecords(
            @RequestParam(value="start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value="end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "nodata") CSVNoDataType noData)
    {
        dateChecker(startDate, endDate);
        String outMessage = MessageFormat.format(
                "attachment; filename=FO3X_{0}_{1}.csv",
                startDate.toString(), endDate.toString()
        );

        InputStreamResource file = new InputStreamResource(
                service.downloadCSV(startDate, endDate, noData)
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, outMessage)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    private void dateChecker(LocalDate startDate, LocalDate endDate) {
        long flagDays = ChronoUnit.DAYS.between(startDate, endDate);
        if(!(LOWER_DAYS_BOUND < flagDays && flagDays < UPPER_DAYS_BOUND)){
            String errorMessage = MessageFormat.format(
                    "The date range between start date {0} and end date {1} cannot more of {2} days.",
                    startDate, endDate, UPPER_DAYS_BOUND);
            if(flagDays <= LOWER_DAYS_BOUND) {
                errorMessage = MessageFormat.format(
                        "The start date {0} cannot be equal to or less than the end date {1}.",
                        startDate, endDate);
            }
            throw new DateRangeNotValidException(errorMessage);
        }
    }

}
