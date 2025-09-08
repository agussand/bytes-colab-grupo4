package com.bytescolab.demo.featureflagapi.service.Impl;

import com.bytescolab.demo.featureflagapi.dto.FeatureConfigDTO;
import com.bytescolab.demo.featureflagapi.dto.FeatureDTO;
import com.bytescolab.demo.featureflagapi.dto.FeaturePOSTDTO;
import com.bytescolab.demo.featureflagapi.model.Feature;
import com.bytescolab.demo.featureflagapi.model.FeatureConfig;
import com.bytescolab.demo.featureflagapi.repository.FeatureRepository;
import com.bytescolab.demo.featureflagapi.service.FeatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Override
    public Feature save(FeaturePOSTDTO newFeature) {
        Feature feature = new Feature();
        feature.setName(newFeature.getName());
        feature.setDescription(newFeature.getDescription());
        feature.setEnabledByDefault(newFeature.getEnabledByDefault());
        return featureRepository.save(feature);
    }

    @Override
    public FeatureDTO getById(Long id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feature no encontrada con el id "+id));

        List<FeatureConfigDTO> featureConfigDTOS = new ArrayList<>();
        if (!feature.getFeatureConfigs().isEmpty()){
            featureConfigDTOS = feature.getFeatureConfigs().stream()
                    .map(f -> new FeatureConfigDTO(
                            f.getId(),
                            f.getEnvironment(),
                            f.getClientId(),
                            f.getEnabled()
                    )).toList();
        }
        return new FeatureDTO().builder()
                .id(feature.getId())
                .name(feature.getName())
                .description(feature.getDescription())
                .enabledByDefault(feature.getEnabledByDefault())
                .featureConfigs(featureConfigDTOS)
                .build();
    }

    @Override
    public List<FeatureDTO> getAll() {
        List<Feature> features = featureRepository.findAll();
        List<FeatureDTO> featureDTOS = new ArrayList<>();
        for (Feature f : features){
            FeatureDTO featureDTO = new FeatureDTO();
            featureDTO.setId(f.getId());
            featureDTO.setName(f.getName());
            featureDTO.setDescription(f.getDescription());
            featureDTO.setEnabledByDefault(f.getEnabledByDefault());
            if(!f.getFeatureConfigs().isEmpty()){
                featureDTO.setFeatureConfigs(f.getFeatureConfigs()
                        .stream()
                        .map(cfg ->
                                new FeatureConfigDTO(
                                        cfg.getId(),
                                        cfg.getEnvironment(),
                                        cfg.getClientId(),
                                        cfg.getEnabled())
                        ).toList());
            }
            featureDTOS.add(featureDTO);
        }
        return featureDTOS;
    }




}
