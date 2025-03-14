package org.cnr.fo3xdb.enums;

public enum HeaderFoxOzoneCSVLabels {

    ID("id"),
    TIMESTAMP("timestamp"),
    WIND_SPEED("wind_speed"),
    OZONE_LEVEL_0("ozone_level_0"),
    OZONE_LEVEL_1("ozone_level_1"),
    OZONE_LEVEL_2("ozone_level_2");

    private final String label;

    HeaderFoxOzoneCSVLabels(String label) {

        this.label = label;
    }

    public String getLabel() {

        return label;
    }

}
