package com.definityfirst.jesusgonzalez.tibiastats;

/**
 * Created by jesus.gonzalez on 16/02/2017.
 */

import java.util.HashMap;
import java.util.Map;

public class News {
    private String id;
    private String title;
    private String content;
    private String type;
    private String news;
    private String apiurl;
    private String tibiaurl;
    private Date date;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public News(String id, String type, String news, String apiurl, String tibiaurl, Date date, Map<String, Object> additionalProperties){

    }
    public News(String id, String title, String content, Date date, Map<String, Object> additionalProperties){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getApiurl() {
        return apiurl;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }

    public String getTibiaurl() {
        return tibiaurl;
    }

    public void setTibiaurl(String tibiaurl) {
        this.tibiaurl = tibiaurl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}