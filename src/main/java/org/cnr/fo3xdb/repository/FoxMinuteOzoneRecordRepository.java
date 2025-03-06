package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxMinuteOzoneRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoxMinuteOzoneRecordRepository extends
        JpaRepository<FoxMinuteOzoneRecordEntity, Long> {
}
