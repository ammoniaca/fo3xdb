package org.cnr.fo3xdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cnr.fo3xdb.dto.FoxHourlyWeatherMetadataDTO;
import org.cnr.fo3xdb.dto.FoxHourlyWeatherResponseDTO;
import org.cnr.fo3xdb.enums.CSVNoDataType;
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

@RestController
@RequestMapping("/api/v1/fo3x")
@Tag(name = "FO3X APIs")
public class FoxHourlyController {

    private final FoxHourlyService service;

    @Autowired
    public FoxHourlyController(FoxHourlyService service) {

        this.service = service;
    }

    @Operation(
            summary = "Find FO3X data measurement units.",
            description = "This endpoint allows users to search for FO3X (Ozone FACE – free air controlled " +
                    "exposure) data measurement units in SI (International System of Units)."
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
            value = "/records/measurement-units",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FoxHourlyWeatherMetadataDTO> getFoxHourlyMetadata(){
        FoxHourlyWeatherMetadataDTO foxHourlyWeatherMetadataDTO = service.getHourly();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foxHourlyWeatherMetadataDTO);
    }

    @Operation(
            summary = "Search for FO3X data given a specific date range.",
            description = "This endpoint allows users to search for FO3X (Ozone FACE – free air controlled " +
                    "exposure) data in a specific date range and produce a JSON (JavaScript Object Notation) format."
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
            value = "/records/json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FoxHourlyWeatherResponseDTO> getJSONRecords(
                @RequestParam(value="start")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                @RequestParam(value="end")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        // get data
        FoxHourlyWeatherResponseDTO response = service
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
                    "exposure) data in a specific date range and produce a CSV (comma-separated values) file format."
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
}
