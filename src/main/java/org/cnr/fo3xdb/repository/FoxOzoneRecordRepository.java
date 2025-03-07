package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxOzoneRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoxOzoneRecordRepository extends
        JpaRepository<FoxOzoneRecordEntity, Long> {
}
