package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxWeatherRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface FoxWeatherRecordRepository extends
        JpaRepository<FoxWeatherRecordEntity, Long> {

    List<FoxWeatherRecordEntity> findAllByTimestampBetween(
            OffsetDateTime start,
            OffsetDateTime end
    );

}
