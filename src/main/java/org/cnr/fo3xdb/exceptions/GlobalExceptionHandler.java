package org.cnr.fo3xdb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DateRangeNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleDateIntervalNotValidException(
            DateRangeNotValidException ex,
            WebRequest request)
    {
        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(ex.getMessage())
                .message(HttpStatus.BAD_REQUEST.name())
                .instance(getPath(request))
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidDateFormatException(
            DateTimeParseException ex,
            WebRequest request)
    {
        String errorMessage = MessageFormat.format(
                "The date format {0} is not correct. Both dates are in the correct format yyyy-MM-dd.",
                ex.getParsedString());

        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(errorMessage)
                .message(HttpStatus.BAD_REQUEST.name())
                .instance(getPath(request))
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(GlobalMetadataTableException.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalMetadataTableException(
            GlobalMetadataTableException ex,
            WebRequest request)
    {
        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(ex.getMessage())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .instance(getPath(request))
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(HourlyMetadataTableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHourlyMetadataTableException(
            HourlyMetadataTableException ex,
            WebRequest request)
    {
        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(ex.getMessage())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .instance(getPath(request))
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(RecordsNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerRecordsNotFoundException(
            RecordsNotFoundException ex,
            WebRequest request)
    {
        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .details(ex.getMessage())
                .message(HttpStatus.NOT_FOUND.name())
                .instance(getPath(request))
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    private String getPath(WebRequest request){
        return request
                .getDescription(false)
                .replace("uri=", "");
    }



}
