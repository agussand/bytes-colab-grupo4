package com.bytescolab.demo.featureflagapi.dto.request;

import com.bytescolab.demo.featureflagapi.model.Environment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnableFeatureRequestDto {
    private Environment environment;
    private String clientId;
}
