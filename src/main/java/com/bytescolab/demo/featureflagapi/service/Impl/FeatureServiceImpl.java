package com.bytescolab.demo.featureflagapi.service.Impl;

import com.bytescolab.demo.featureflagapi.dto.FeatureConfigDTO;
import com.bytescolab.demo.featureflagapi.dto.FeatureDTO;
import com.bytescolab.demo.featureflagapi.dto.FeaturePOSTDTO;
import com.bytescolab.demo.featureflagapi.dto.request.EnableFeatureRequestDto;
import com.bytescolab.demo.featureflagapi.model.Feature;
import com.bytescolab.demo.featureflagapi.model.FeatureConfig;
import com.bytescolab.demo.featureflagapi.repository.FeatureConfigRepository;
import com.bytescolab.demo.featureflagapi.repository.FeatureRepository;
import com.bytescolab.demo.featureflagapi.service.FeatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;
    private final FeatureConfigRepository featureConfigRepository;

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
                .orElseThrow(() -> new EntityNotFoundException("Feature no encontrada con el id " + id));

        List<FeatureConfigDTO> featureConfigDTOS = new ArrayList<>();
        if (!feature.getFeatureConfigs().isEmpty()) {
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
        for (Feature f : features) {
            FeatureDTO featureDTO = new FeatureDTO();
            featureDTO.setId(f.getId());
            featureDTO.setName(f.getName());
            featureDTO.setDescription(f.getDescription());
            featureDTO.setEnabledByDefault(f.getEnabledByDefault());
            if (!f.getFeatureConfigs().isEmpty()) {
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

    @Override
    public Void enableFeature(Long featureId, EnableFeatureRequestDto enableFeatureRequestDto) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature no encontrada."));

        if (enableFeatureRequestDto.getEnvironment() != null && enableFeatureRequestDto.getClientId() == null) {
            List<FeatureConfig> existingConfigs = featureConfigRepository
                    .findByFeatureIdAndEnvironment(featureId, enableFeatureRequestDto.getEnvironment());

            if(!existingConfigs.isEmpty()){
                existingConfigs
                        .forEach(config -> {
                            config.setEnabled(true);
                            featureConfigRepository.save(config);
                        });
            }else{
                throw new RuntimeException("No hay registros de enviroment y ese feature.");
            }

        }
        else if (enableFeatureRequestDto.getEnvironment() != null && enableFeatureRequestDto.getClientId() != null) {

            FeatureConfig existingConfig = featureConfigRepository
                    .findByFeatureIdAndClientIdAndEnvironment(featureId, enableFeatureRequestDto.getClientId(), enableFeatureRequestDto.getEnvironment())
                    .orElseThrow(() -> new RuntimeException("No se encuentra un feature para ese usuario y ambiente"));

                existingConfig.setEnabled(true);
                featureConfigRepository.save(existingConfig);

        }

        return null;
    }

    @Override
    public Void disableFeature(Long featureId, EnableFeatureRequestDto enableFeatureRequestDto) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature no encontrada."));

        if (enableFeatureRequestDto.getEnvironment() != null && enableFeatureRequestDto.getClientId() == null) {
            List<FeatureConfig> existingConfigs = featureConfigRepository
                    .findByFeatureIdAndEnvironment(featureId, enableFeatureRequestDto.getEnvironment());

            if (!existingConfigs.isEmpty()) {
                existingConfigs.forEach(config -> {
                    config.setEnabled(false);
                    featureConfigRepository.save(config);
                });
            } else {
                throw new RuntimeException("No hay registros de environment y ese feature.");
            }
        }

        else if (enableFeatureRequestDto.getEnvironment() != null && enableFeatureRequestDto.getClientId() != null) {
            FeatureConfig existingConfig = featureConfigRepository
                    .findByFeatureIdAndClientIdAndEnvironment(featureId, enableFeatureRequestDto.getClientId(), enableFeatureRequestDto.getEnvironment())
                    .orElseThrow(() -> new RuntimeException("No se encuentra un feature para ese usuario y ambiente"));

            existingConfig.setEnabled(false);
            featureConfigRepository.save(existingConfig);
        }

        return null;
    }

}


