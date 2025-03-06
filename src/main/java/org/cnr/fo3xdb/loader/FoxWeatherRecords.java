package org.cnr.fo3xdb.loader;

import org.cnr.fo3xdb.entity.FoxHourlyWeatherRecordEntity;

import java.util.List;

public record FoxWeatherRecords(List<FoxHourlyWeatherRecordEntity> records) {
}
