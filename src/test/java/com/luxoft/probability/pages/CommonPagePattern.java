package com.luxoft.probability.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CommonPagePattern {
    private final WebDriver driver;

    public CommonPagePattern(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait waitDriver(long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds);
    }

    public WebDriverWait waitDriver() {
        return waitDriver(60);
    }
}
