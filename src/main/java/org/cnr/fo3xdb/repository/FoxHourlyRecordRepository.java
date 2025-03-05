package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxHourlyWeatherRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface FoxHourlyRecordRepository extends
        JpaRepository<FoxHourlyWeatherRecordEntity, Long> {

    List<FoxHourlyWeatherRecordEntity> findAllByTimestampBetween(
            OffsetDateTime start,
            OffsetDateTime end
    );

}
