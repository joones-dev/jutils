package dev.joones.jutils.features;

import dev.joones.jutils.Feature;
import dev.joones.jutils.JUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureProvider {
    public static final String DEFAULT_PARENT = "JUtils";
    private static FeatureProvider instance = null;
    private final JUtils plugin;
    private final List<Feature> features;

    public FeatureProvider(JUtils plugin) {
        if(instance != null)
            throw new IllegalStateException("There may only be one feature provider!");
        instance = this;
        this.plugin = plugin;
        this.features = new ArrayList<>();
    }

    public JUtils getPlugin() {
        return plugin;
    }

    public void register(Feature feature){
        if(this.features.stream()
                .anyMatch(f-> f.getId().equals(feature.getId()) && f.getParent().equals(feature.getParent())))
            throw new IllegalArgumentException("Feature already registered");
        this.features.add(feature);
    }

    public void enable(String issuer, String feature){
        this.getFeature(feature).enable(issuer);
    }
    public void enable(String issuer, String feature, String parent){
        this.getFeature(parent, feature).enable(issuer);
    }
    public void disable(String issuer, String feature) {
        this.getFeature(feature).disable(issuer);
    }
    public void disable(String issuer, String feature, String parent){
        this.getFeature(feature, parent).disable(issuer);
    }

    public Feature getFeature(String id){
        return getFeature(id, DEFAULT_PARENT);
    }

    public Feature getFeature(String id, String parent){
        return this.features.stream()
                .filter(f->f.getParent().equals(parent))
                .filter(f->f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Feature> getAllFeatures(String parent){
        return Collections.unmodifiableList(this.features.stream().filter(f->f.getParent().equals(parent)).collect(Collectors.toList()));
    }
}
