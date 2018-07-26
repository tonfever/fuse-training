package com.test.lab05.model;

import java.util.HashMap;

public class EmailServiceRequest {

    private String templateID;
    private String language;
    private String from;
    private String to;

    private HashMap<String,String> templateVars;

    public EmailServiceRequest() {
    }

    public EmailServiceRequest(String templateID, String language, String from, String to, HashMap<String, String> templateVars) {
        this.templateID = templateID;
        this.language = language;
        this.from = from;
        this.to = to;
        this.templateVars = templateVars;
    }

    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public HashMap<String, String> getTemplateVars() {
        return templateVars;
    }

    public void setTemplateVars(HashMap<String, String> templateVars) {
        this.templateVars = templateVars;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "EmailServiceRequest{" +
                "templateID='" + templateID + '\'' +
                ", language='" + language + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", templateVars=" + templateVars +
                '}';
    }
}


