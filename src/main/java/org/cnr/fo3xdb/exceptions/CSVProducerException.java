package org.cnr.fo3xdb.exceptions;

import java.io.Serial;

public class CSVProducerException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public CSVProducerException(String message){

        super(message);
    }

}
