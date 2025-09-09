package com.bytescolab.demo.featureflagapi.repository;

import com.bytescolab.demo.featureflagapi.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    boolean existsByName(String name);
}
