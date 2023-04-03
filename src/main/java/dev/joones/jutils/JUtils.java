package dev.joones.jutils;

import dev.joones.jutils.features.FeatureProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class JUtils extends JavaPlugin {
    private FeatureProvider featureProvider;

    @Override
    public void onEnable() {
        this.featureProvider = new FeatureProvider(this);
    }

    /**
     * Checks if a {@link Feature} with the specified id exists under the default parent
     * @param id The id of the {@link Feature} to find
     * @return {@code true} if the {@link Feature} exists, {@code false} otherwise
     */
    public boolean hasFeature(String id){
        return this.featureProvider.getFeature(id) != null;
    }

    /**
     * Checks if a {@link Feature} with the specified id exists under the specified parent
     * @param id The id of the {@link Feature} to find
     * @param parent The parent of the {@link Feature} to find
     * @return {@code true} if a {@link Feature} exists, {@code false} otherwise
     */
    public boolean hasFeature(String id, String parent){
        return this.featureProvider.getFeature(id, parent) != null;
    }

    /**
     * Gets the {@link Feature} under the default parent
     * @param id The id of the {@link Feature} to find
     * @return The {@link Feature} or {@code null} if it does not exist
     * @throws IllegalStateException If the {@link Feature} is disabled
     */
    public Feature getFeature(String id){
        return this.getFeature(id, FeatureProvider.DEFAULT_PARENT);
    }

    /**
     * Gets the {@link Feature} under the specified parent
     * @param id The id of the {@link Feature} to find
     * @param parent The parent of the {@link Feature} to find
     * @return The {@link Feature} or {@code null} if it does not exist
     * @throws IllegalStateException if the {@link Feature} is disabled
     */
    public Feature getFeature(String id, String parent){
        Feature f = this.featureProvider.getFeature(id, parent);
        if(!hasFeature(id, parent))
            return null;
        if(!f.isEnabled())
            throw new IllegalStateException("The feature '"+id+"' has been disabled by "+f.getLastIssuer());
        return f;
    }
}
