package dev.joones.jutils.features.gui;

import dev.joones.jutils.Feature;
import dev.joones.jutils.features.FeatureProvider;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.LinkedHashMap;
import java.util.Map;

public class GUIManager implements Feature, Listener {
    private boolean enabled;
    private FeatureProvider provider;
    private String lastIssuer;
    private Map<String, GUI> guis;

    public GUIManager(){
        this.lastIssuer = "JUtils";
        this.enabled = false;
        this.guis = new LinkedHashMap<>();
    }

    public void setProvider(FeatureProvider provider) {
        this.provider = provider;
    }

    public FeatureProvider getProvider() {
        return this.provider;
    }

    public void enable(String issuer) {
        if(enabled)
            return;
        Bukkit.getPluginManager().registerEvents(this, provider.getPlugin());
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void disable(String issuer) {
        HandlerList.unregisterAll(this);
    }

    public String getId() {
        return "GUI";
    }

    public String getLastIssuer() {
        return this.lastIssuer;
    }

    public String getParent() {
        return FeatureProvider.DEFAULT_PARENT;
    }

    public void registerGUI(String id, GUI gui){
        if(this.guis.containsKey(id))
            throw new IllegalStateException("A GUI with the id '"+id+"' already exists!");
        this.guis.put(id, gui);
    }

    public GUI getGUI(String id){
        return this.guis.get(id);
    }
}
