package com.luxoft.probability.tests;

import com.luxoft.probability.app.SeleniumWatcher;
import com.luxoft.probability.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumWatcher.class)
@Execution(ExecutionMode.CONCURRENT)
public class MainTest {
    private final WebDriver driver;

    public MainTest(WebDriver driver) {
        this.driver = driver;
    }

    @DisplayName("Open main page")
    @Test
    public void openMainPageTest() {

        MainPage mainPage = new MainPage(driver);
    }

}
