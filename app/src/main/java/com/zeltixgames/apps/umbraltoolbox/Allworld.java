package com.zeltixgames.apps.umbraltoolbox;

import java.util.HashMap;
import java.util.Map;

public class Allworld {

    private String name;
    private String online;
    private String location;
    private String worldtype;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorldtype() {
        return worldtype;
    }

    public void setWorldtype(String worldtype) {
        this.worldtype = worldtype;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}