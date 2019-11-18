package com.luxoft.probability.app;

import com.luxoft.probability.app.config.Config;
import com.luxoft.probability.app.config.LocalDriver;
import com.luxoft.probability.app.config.RemoteDriver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luxoft.probability.app.config.UsedDriver;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

import static java.lang.System.getProperty;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.fail;

public class SeleniumWatcher implements ParameterResolver, AfterEachCallback, TestExecutionExceptionHandler {
    private static final Config CONFIG = Config.readConfig("config.json");
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    @Override
    public boolean supportsParameter(
            ParameterContext parameterContext, ExtensionContext extensionContext
    ) throws ParameterResolutionException {
        Class<?> parameterType = parameterContext.getParameter().getType();
        return (parameterType == WebDriver.class);
    }

    @Override
    public Object resolveParameter(
            ParameterContext parameterContext, ExtensionContext extensionContext
    ) throws ParameterResolutionException {
        try {
            Class<?> parameterType = parameterContext.getParameter().getType();

            if (parameterType == WebDriver.class) {
                WebDriver driver = CONFIG.getUsedDriver() == UsedDriver.local ? createLocalDriver() : createRemoteDriver();
                String applicationUrl = CONFIG.getApplicationUrl();
                driver.manage().window().maximize();
                driver.get(applicationUrl);

                DRIVER.set(driver);
                return driver;
            }

            return null;

        } catch (Exception e) {
            throw new ParameterResolutionException("Failed to parameter initialization: " + parameterContext, e);
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        WebDriver driver = DRIVER.get();

        if (driver != null) {
            driver.close();
            driver.quit();
            DRIVER.remove();
        }
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        takeScreenShot(context);

        if (throwable instanceof WebDriverException) {
            fail(throwable.getMessage(), throwable);
        } else {
            throw throwable;
        }
    }

    private void takeScreenShot(ExtensionContext context) {
        try {
            String seleniumScreenShot = getProperty("seleniumScreenShot", SeleniumScreenShotForAllure.class.getName());
            Class<? extends SeleniumScreenShot> clazz = Class.forName(seleniumScreenShot)
                    .asSubclass(SeleniumScreenShot.class);

            clazz.newInstance().takeScreenShot(context, DRIVER.get());

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            // noop
        }
    }

    private static WebDriver createLocalDriver() throws Exception {
        LocalDriver localDriver = CONFIG.getLocalDriver();

        asMap(localDriver.getSystemProperties()).forEach((key, value) -> {
            System.setProperty(key, value == null ? "" : value.toString());
        });

        String className = localDriver.getClassName();
        Class<? extends WebDriver> clazz = Class.forName(className).asSubclass(WebDriver.class);

        return clazz.newInstance();
    }

    private static WebDriver createRemoteDriver() throws Exception {
        RemoteDriver remoteDriver = CONFIG.getRemoteDriver();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(remoteDriver.getBrowserName());
        capabilities.setVersion(remoteDriver.getBrowserVersion());

        Map<String, Object> extraCapabilities = asMap(remoteDriver.getBrowserCapabilities());
        capabilities.merge(new DesiredCapabilities(extraCapabilities));

        RemoteWebDriver result = new RemoteWebDriver(new URL(remoteDriver.getHubUrl()), capabilities);
        result.setFileDetector(new LocalFileDetector());

        return result;
    }

    private static Map<String, Object> asMap(ObjectNode obj) {
        if (obj == null) {
            return emptyMap();
        }

        return new ObjectMapper().convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }
}
