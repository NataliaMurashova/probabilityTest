package com.luxoft.probability.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MainPage extends CommonPagePattern {

    public MainPage(WebDriver driver) {
        super(driver);
        waitDriver().until(titleIs("Luxoft | Digital Strategy, Consulting and Engineering at Scale"));
        waitDriver().until(visibilityOfElementLocated(By.cssSelector("div.logo")));
    }

    public JobOpportunitiesPage goToJobOpportunitiesPage() {

        getDriver().findElement(By.cssSelector("header div#menu-switch.switch")).click();

        waitDriver().until(elementToBeClickable(By.xpath("//div[contains(@class, 'menu')]//li/a[text()='Careers']"))).click();

        return new JobOpportunitiesPage(getDriver());
    }

    public LocationPage goToLocationPage() {

        getDriver().findElement(By.id("eucookies-dontshow")).click();

        getDriver().findElement(By.cssSelector("header div#menu-switch.switch")).click();

        waitDriver().until(elementToBeClickable(By.xpath("//div[contains(@class, 'menu')]//li/a[text()='Contact Us']"))).click();

        return new LocationPage(getDriver());
    }
}