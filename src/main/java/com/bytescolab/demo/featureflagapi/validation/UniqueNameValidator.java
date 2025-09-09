package com.bytescolab.demo.featureflagapi.validation;

import com.bytescolab.demo.featureflagapi.repository.FeatureRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
    private final FeatureRepository featureRepository;

    public UniqueNameValidator(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // ya se valida con @NotBlank
        }
        return !featureRepository.existsByName(value);
    }
}
