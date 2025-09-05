package com.bytescolab.demo.featureflagapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "feature_config",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"feature_id", "environment", "client_id"})
        })
public class FeatureConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;

    private String clientId;

    @Column(nullable = false)
    private Boolean enabled = false;

    private String additionalParams;
}
