package com.luxoft.probability.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class JobOpportunitiesPage extends CommonPagePattern {
    public JobOpportunitiesPage(WebDriver driver) {
        super(driver);

        waitDriver().until(visibilityOfElementLocated(By.cssSelector("div.lux-search-stripe")));
    }

    @Step("Search by {0}")
    public void search(String value) {
        getDriver().findElement(By.name("arrFilter_ff[NAME]")).sendKeys(value + Keys.RETURN);

        List<WebElement> spin = getDriver().findElements(By.cssSelector("i.fa-circle-o-notch.fa-spin"));

        if (!spin.isEmpty())
            waitDriver().until(stalenessOf(spin.get(0)));
    }

    @Step("Get list of opportunities")
    public List<WebElement> getOpportunitiesList() {
        return getDriver().findElements(By.cssSelector("table.table-jobs tbody tr"));
    }

}
