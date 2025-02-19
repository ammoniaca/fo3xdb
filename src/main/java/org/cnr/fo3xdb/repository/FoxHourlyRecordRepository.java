package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface FoxHourlyRecordRepository extends
        JpaRepository<FoxHourlyRecordEntity, Long> {

    List<FoxHourlyRecordEntity> findAllByTimestampBetween(
            OffsetDateTime start,
            OffsetDateTime end
    );

}
