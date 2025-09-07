package com.bytescolab.demo.featureflagapi.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeaturePOSTDTO {
    //agregar que sea único
    private String name;
    @NotBlank
    private String description;
    private Boolean enabledByDefault;
}
