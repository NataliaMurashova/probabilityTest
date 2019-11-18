package com.luxoft.probability.app.config;

import java.beans.ConstructorProperties;

import static com.luxoft.probability.service.FileUtils.loadFile;
import static com.luxoft.probability.service.JsonUtils.readJsonObject;

public class Config {
    private final String applicationUrl;
    private final UsedDriver usedDriver;
    private final LocalDriver localDriver;
    private final RemoteDriver remoteDriver;

    @ConstructorProperties({"applicationUrl", "usedDriver", "localDriver", "remoteDriver"})
    public Config(
            String applicationUrl,
            UsedDriver usedDriver,
            LocalDriver localDriver,
            RemoteDriver remoteDriver) {
        this.applicationUrl = applicationUrl;
        this.usedDriver = usedDriver;
        this.localDriver = localDriver;
        this.remoteDriver = remoteDriver;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public UsedDriver getUsedDriver() {
        return usedDriver;
    }

    public LocalDriver getLocalDriver() {
        return localDriver;
    }

    public RemoteDriver getRemoteDriver() {
        return remoteDriver;
    }

    public static Config readConfig(String path) {
        return readJsonObject(loadFile(path), Config.class);
    }

}
