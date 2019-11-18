package com.luxoft.probability.app.config;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.beans.ConstructorProperties;

public class RemoteDriver {
    private final String hubUrl;
    private final String browserName;
    private final String browserVersion;
    private final ObjectNode browserCapabilities;

    @ConstructorProperties({"hubUrl", "browserName", "browserVersion", "browserCapabilities"})
    public RemoteDriver(String hubUrl, String browserName, String browserVersion, ObjectNode browserCapabilities) {
        this.hubUrl = hubUrl;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.browserCapabilities = browserCapabilities;
    }

    public String getHubUrl() {
        return hubUrl;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public ObjectNode getBrowserCapabilities() {
        return browserCapabilities;
    }
}
