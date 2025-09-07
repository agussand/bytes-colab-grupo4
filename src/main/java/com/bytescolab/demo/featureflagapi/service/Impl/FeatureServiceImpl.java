package com.bytescolab.demo.featureflagapi.service.Impl;

import com.bytescolab.demo.featureflagapi.dto.FeaturePOSTDTO;
import com.bytescolab.demo.featureflagapi.model.Feature;
import com.bytescolab.demo.featureflagapi.model.FeatureConfig;
import com.bytescolab.demo.featureflagapi.repository.FeatureRepository;
import com.bytescolab.demo.featureflagapi.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Override
    public Feature save(FeaturePOSTDTO newFeature) {
        return new Feature(1L, "name", "description", true, new ArrayList<FeatureConfig>());
    }
}
