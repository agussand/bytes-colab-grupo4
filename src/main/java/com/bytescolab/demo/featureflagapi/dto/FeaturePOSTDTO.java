package com.bytescolab.demo.featureflagapi.dto;


import com.bytescolab.demo.featureflagapi.validation.UniqueName;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeaturePOSTDTO {
    @UniqueName
    @NotBlank(message = "name no puede estar vacío")
    private String name;
    @NotBlank(message = "description no puede estar vacío")
    @JsonProperty("description")
    private String description;
    @NotNull(message = "enabledByDefault no puede ser nulo")
    private Boolean enabledByDefault;
}
