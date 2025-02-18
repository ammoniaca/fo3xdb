package org.cnr.fo3xdb.repository;

import org.cnr.fo3xdb.entity.FoxGlobalMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoxGlobalMetadataRepository extends
        JpaRepository<FoxGlobalMetadataEntity, Long> {
}
