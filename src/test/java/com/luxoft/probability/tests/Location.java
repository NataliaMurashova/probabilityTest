package com.luxoft.probability.tests;

import com.luxoft.probability.app.SeleniumWatcher;
import com.luxoft.probability.pages.LocationPage;
import com.luxoft.probability.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;


@ExtendWith(SeleniumWatcher.class)
@Execution(ExecutionMode.CONCURRENT)
public class Location {
    private final WebDriver driver;

    public Location(WebDriver driver) {
        this.driver = driver;
    }


    private LocationPage locationPage;

    @BeforeEach
    void init() {
        MainPage mainPage = new MainPage(driver);

        locationPage = mainPage.goToLocationPage();
    }

    @DisplayName("Verify that contacts list for location Russia -> Omsk is equals to expected")
    @Test
    void verifyContactList() {
        locationPage.openFindLocationTab();
        locationPage.selectCountry();
        locationPage.selectCity();
        locationPage.checkLocation();

    }

}


