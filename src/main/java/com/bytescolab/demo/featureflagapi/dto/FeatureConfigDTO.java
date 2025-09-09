package com.bytescolab.demo.featureflagapi.dto;

import com.bytescolab.demo.featureflagapi.model.Environment;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureConfigDTO {
    private Long id;
    private Environment environment;
    private String clientId;
    private Boolean enabled;
}
