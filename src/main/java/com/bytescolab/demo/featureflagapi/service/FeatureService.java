package com.bytescolab.demo.featureflagapi.service;

import com.bytescolab.demo.featureflagapi.dto.FeaturePOSTDTO;
import com.bytescolab.demo.featureflagapi.model.Feature;
import org.springframework.stereotype.Service;

@Service
public interface FeatureService {
    Feature save(FeaturePOSTDTO newFeature);
}
