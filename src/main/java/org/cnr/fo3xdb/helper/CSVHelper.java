package org.cnr.fo3xdb.helper;

import org.cnr.fo3xdb.enums.CSVNoDataType;

public class CSVHelper {

    public static String safetyDoubleToString(Double value, CSVNoDataType noData) {
        return value == null ? noData.toString() : value.toString();
    }

}
