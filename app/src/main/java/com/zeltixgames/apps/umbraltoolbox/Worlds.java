package com.zeltixgames.apps.umbraltoolbox;

/**
 * Created by jesus.gonzalez on 17/02/2017.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worlds {

    private Integer online;
    private List<Allworld> allworlds = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public List<Allworld> getAllworlds() {
        return allworlds;
    }

    public void setAllworlds(List<Allworld> allworlds) {
        this.allworlds = allworlds;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}