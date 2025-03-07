package org.cnr.fo3xdb.loader;

import org.cnr.fo3xdb.entity.FoxWeatherRecordEntity;

import java.util.List;

public record FoxWeatherRecords(List<FoxWeatherRecordEntity> records) {
}
