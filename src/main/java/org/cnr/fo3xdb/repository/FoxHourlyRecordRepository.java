package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxHourlyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoxHourlyRecordRepository extends
        JpaRepository<FoxHourlyRecordEntity, Long> {

}
