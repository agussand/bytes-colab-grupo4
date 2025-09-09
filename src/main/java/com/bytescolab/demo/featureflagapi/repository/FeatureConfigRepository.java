package com.bytescolab.demo.featureflagapi.repository;

import com.bytescolab.demo.featureflagapi.model.Environment;
import com.bytescolab.demo.featureflagapi.model.FeatureConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureConfigRepository extends JpaRepository<FeatureConfig, Long> {

    Optional<FeatureConfig> findByFeatureIdAndClientIdAndEnvironment(Long featureId, String clientId, Environment environment);
    List<FeatureConfig> findByFeatureIdAndEnvironment(Long featureId, Environment environment);


}
