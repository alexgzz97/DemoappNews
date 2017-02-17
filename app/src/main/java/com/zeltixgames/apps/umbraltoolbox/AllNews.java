package com.zeltixgames.apps.umbraltoolbox;

/**
 * Created by jesus.gonzalez on 16/02/2017.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllNews {

    private List<News> news = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
