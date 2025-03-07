package org.cnr.fo3xdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cnr.fo3xdb.dto.FoxOzoneUnitsDTO;
import org.cnr.fo3xdb.dto.FoxWeatherUnitsDTO;
import org.cnr.fo3xdb.dto.FoxWeatherResponseDTO;
import org.cnr.fo3xdb.enums.CSVNoDataType;
import org.cnr.fo3xdb.enums.OzoneTimeUnit;
import org.cnr.fo3xdb.exceptions.ErrorResponseDTO;
import org.cnr.fo3xdb.service.FoxOzoneService;
import org.cnr.fo3xdb.service.FoxWeatherService;
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

@RestController
@RequestMapping("/api/v1/fo3x")
@Tag(name = "FO3X APIs")
public class FoxController {

    private final FoxWeatherService weatherService;
    private final FoxOzoneService ozoneService;

    @Autowired
    public FoxController(
            FoxWeatherService weatherService,
            FoxOzoneService ozoneService)
    {
        this.weatherService = weatherService;
        this.ozoneService = ozoneService;
    }

    // WEATHER

    @Operation(
            summary = "Find FO3X weather data measurement units.",
            description = "This endpoint allows users to search for FO3X (Ozone FACE – free air controlled " +
                    "exposure) weather data measurement units in SI (International System of Units)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully returns."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )}
    )
    @GetMapping(
            value = "/weather/measurement-units",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FoxWeatherUnitsDTO> getFoxWeatherUnits(){
        FoxWeatherUnitsDTO foxWeatherUnitsDTO = weatherService.getWeatherUnits();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foxWeatherUnitsDTO);
    }

    @Operation(
            summary = "Search for FO3X weather data given a specific date range.",
            description = "This endpoint allows users to search for FO3X (Ozone FACE – free air controlled " +
                    "exposure) weather data in a specific date range and produce a JSON (JavaScript Object Notation) format."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully returns a JSON (JavaScript Object Notation) file format."
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
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )}
    )
    @GetMapping(
            value = "/weather/json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FoxWeatherResponseDTO> getJSONRecords(
                @RequestParam(value="start")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                @RequestParam(value="end")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        // get data
        FoxWeatherResponseDTO response = weatherService
                    .retrieveWeatherRecordsByDateRange(
                            startDate,
                            endDate
                    );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(
            summary = "Search for FO3X weather data given a specific date range.",
            description = "This endpoint allows users to search for FO3X (Ozone FACE – free air controlled " +
                    "exposure) weather data in a specific date range and produce a CSV (comma-separated values) file format."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully returns a CSV (comma-separated values) file format."
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
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )}
    )
    @GetMapping(
            value = "/weather/csv",
            produces = {"application/csv"}
    )
    public ResponseEntity<Resource> getCSVRecords(
            @RequestParam(value="start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value="end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "nodata") CSVNoDataType noData)
    {
        String outMessage = MessageFormat.format(
                "attachment; filename=FO3X_{0}_{1}.csv",
                startDate.toString(), endDate.toString()
        );

        InputStreamResource file = new InputStreamResource(
                weatherService.downloadCSV(startDate, endDate, noData)
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, outMessage)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    // OZONE
    @GetMapping(
            value = "/ozone/measurement-units",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FoxOzoneUnitsDTO> getFoxOzoneUnits(
            @RequestParam(value="time") OzoneTimeUnit timeUnit
            ){
        FoxOzoneUnitsDTO foxOzoneUnitsDTO = ozoneService.getOzoneUnits(timeUnit);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foxOzoneUnitsDTO);
    }

}
