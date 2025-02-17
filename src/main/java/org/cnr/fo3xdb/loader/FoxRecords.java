package org.cnr.fo3xdb.loader;

import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;

import java.util.List;

public record FoxRecords(List<FoxHourlyRecordEntity> records) {
}
