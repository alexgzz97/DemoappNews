package com.zeltixgames.apps.umbraltoolbox;

/**
 * Created by jesus.gonzalez on 17/02/2017.
 */

import java.util.HashMap;
import java.util.Map;

public class JSONWorld {

    private Worlds worlds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Worlds getWorlds() {
        return worlds;
    }

    public void setWorlds(Worlds worlds) {
        this.worlds = worlds;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}