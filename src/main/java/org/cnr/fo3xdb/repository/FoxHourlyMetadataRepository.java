package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxHourlyWeatherMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoxHourlyMetadataRepository extends
        JpaRepository<FoxHourlyWeatherMetadataEntity, Long> {
}
