package com.bytescolab.demo.featureflagapi.service;

import com.bytescolab.demo.featureflagapi.dto.FeatureDTO;
import com.bytescolab.demo.featureflagapi.dto.FeaturePOSTDTO;
import com.bytescolab.demo.featureflagapi.model.Feature;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeatureService {
    Feature save(FeaturePOSTDTO newFeature);
    FeatureDTO getById(Long id);
    List<FeatureDTO> getAll();
}
