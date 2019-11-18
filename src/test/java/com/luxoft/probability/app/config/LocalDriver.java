package com.luxoft.probability.app.config;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.beans.ConstructorProperties;

public class LocalDriver {
    private final String className;
    private final ObjectNode systemProperties;

    @ConstructorProperties({"className", "systemProperties"})
    public LocalDriver(String className, ObjectNode systemProperties) {
        this.className = className;
        this.systemProperties = systemProperties;
    }

    public String getClassName() {
        return className;
    }

    public ObjectNode getSystemProperties() {
        return systemProperties;
    }
}
