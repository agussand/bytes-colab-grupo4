package com.bytescolab.demo.featureflagapi.controller;

import com.bytescolab.demo.featureflagapi.dto.FeaturePOSTDTO;
import com.bytescolab.demo.featureflagapi.model.Feature;
import com.bytescolab.demo.featureflagapi.service.FeatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/features")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @PostMapping
    public ResponseEntity<Feature> save(
            @Valid FeaturePOSTDTO newFeature
            ){
        return ResponseEntity.ok(featureService.save(newFeature));
    }
}
