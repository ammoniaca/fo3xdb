package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxWeatherUnitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoxWeatherUnitsRepository extends
        JpaRepository<FoxWeatherUnitsEntity, Long> {
}
