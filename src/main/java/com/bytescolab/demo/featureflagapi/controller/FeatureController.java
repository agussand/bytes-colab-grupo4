package com.bytescolab.demo.featureflagapi.controller;

import com.bytescolab.demo.featureflagapi.dto.FeatureDTO;
import com.bytescolab.demo.featureflagapi.dto.FeaturePOSTDTO;
import com.bytescolab.demo.featureflagapi.dto.request.EnableFeatureRequestDto;
import com.bytescolab.demo.featureflagapi.model.Feature;
import com.bytescolab.demo.featureflagapi.service.FeatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @PostMapping
    public ResponseEntity<Feature> save(
            @Valid @RequestBody FeaturePOSTDTO newFeature){
        return ResponseEntity.ok(featureService.save(newFeature));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(featureService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<FeatureDTO>> getAll(){
        return ResponseEntity.ok(featureService.getAll());
    }

    @PostMapping(path = "/{featureId}/enable")
    public ResponseEntity<Void> enableFeature(
            @PathVariable Long featureId,
            @RequestBody EnableFeatureRequestDto enableFeatureRequestDto){
        return new ResponseEntity<Void>(featureService.enableFeature(featureId, enableFeatureRequestDto), HttpStatus.OK);
    }

    @PostMapping(path = "/{featureId}/disable")
    public ResponseEntity<Void> disableFeature(
            @PathVariable Long featureId,
            @RequestBody EnableFeatureRequestDto enableFeatureRequestDto){
        return new ResponseEntity<Void>(featureService.disableFeature(featureId, enableFeatureRequestDto), HttpStatus.OK);
    }
}
