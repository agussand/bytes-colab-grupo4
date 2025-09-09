package com.bytescolab.demo.featureflagapi.dto;

import com.bytescolab.demo.featureflagapi.model.FeatureConfig;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureDTO {
    private Long id;

    private String name;

    private String description;

    private Boolean enabledByDefault;

    private List<FeatureConfigDTO> featureConfigs = new ArrayList<>();
}
