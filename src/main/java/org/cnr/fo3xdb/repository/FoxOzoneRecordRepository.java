package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxOzoneRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface FoxOzoneRecordRepository extends
        JpaRepository<FoxOzoneRecordEntity, Long> {

    List<FoxOzoneRecordEntity> findAllByTimestampBetween(
            OffsetDateTime start,
            OffsetDateTime end
    );
}
