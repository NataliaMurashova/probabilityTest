package com.luxoft.probability.app;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class SeleniumScreenShotForAllure implements SeleniumScreenShot {
    @Override
    public void takeScreenShot(ExtensionContext context, WebDriver driver) {
        byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("ScreenShot", "", new ByteArrayInputStream(screenShot), ".jpg");
    }
}
