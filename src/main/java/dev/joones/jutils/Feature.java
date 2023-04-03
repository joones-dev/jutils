package dev.joones.jutils;

import dev.joones.jutils.features.FeatureProvider;

/**
 * A JUtils feature that can be enabled and disabled
 */
public interface Feature {
    String getParent();
    void setProvider(FeatureProvider provider);
    FeatureProvider getProvider();
    void enable(String issuer);
    boolean isEnabled();
    void disable(String issuer);
    String getId();
    String getLastIssuer();
}
