package org.cnr.fo3xdb.service.datevalidator;

import org.cnr.fo3xdb.exceptions.DateRangeNotValidException;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MinuteDateValidator implements DateValidator {

    private static final int UPPER_MINUTE_DAYS_BOUND = 3;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public MinuteDateValidator(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void checkValidity() {
        long flagDays = ChronoUnit.DAYS.between(startDate, endDate);
        if(!(LOWER_DAYS_BOUND < flagDays && flagDays < UPPER_MINUTE_DAYS_BOUND)){
            String errorMessage = MessageFormat.format(
                    "The date range between start date {0} and end date {1} cannot more of {2} days.",
                    startDate, endDate, UPPER_MINUTE_DAYS_BOUND);
            if(flagDays <= LOWER_DAYS_BOUND) {
                errorMessage = MessageFormat.format(
                        "The start date {0} cannot be equal to or less than the end date {1}.",
                        startDate, endDate);
            }
            throw new DateRangeNotValidException(errorMessage);
        }
    }
}
