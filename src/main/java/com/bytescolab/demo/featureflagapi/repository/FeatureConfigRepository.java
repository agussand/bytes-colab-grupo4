package com.bytescolab.demo.featureflagapi.repository;

import com.bytescolab.demo.featureflagapi.model.FeatureConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureConfigRepository extends JpaRepository<FeatureConfig, Long> {
}
