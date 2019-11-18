package com.luxoft.probability.app;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public interface SeleniumScreenShot {
    void takeScreenShot(ExtensionContext context, WebDriver driver);
}
