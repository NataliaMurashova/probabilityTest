package com.luxoft.probability.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LocationPage extends CommonPagePattern {

    LocationPage(WebDriver driver) {
        super(driver);
        waitDriver().until(visibilityOfElementLocated(By.cssSelector("div.row.form-top")));
    }


    @Step("Press FindLocation")
    public void openFindLocationTab() {
        getDriver().findElement(By.cssSelector("li[data-tab='tab-3']")).click();
    }


    @Step("Select country")
    public void selectCountry() {
        openFindLocationTab();

        Select countries = new Select(getDriver().findElement(By.cssSelector("select[data-select2-id='54']")));

        countries.selectByVisibleText("Russia");

        openFindLocationTab();
    }

    @Step("Select city")
    public void selectCity() {
        Select cities = new Select(getDriver().findElement(By.cssSelector("select[data-select2-id='90']")));
        cities.selectByVisibleText("Omsk");
    }

    @Step("Verify Omsk Location")
    public void checkLocation() {


        List<WebElement> elements = getDriver().findElements(By.cssSelector(".office[style=''] .inner"));

        Assert.assertEquals(elements.size(), 3);

        Assert.assertTrue(elements.get(0).findElement(By.cssSelector(".info p")).getText().contains("Karl Marx Av. , 41, bld. 7\n" +
                "\n" +
                "644042 , Omsk\n" +
                "Russia"));

        Assert.assertTrue(elements.get(1).findElement(By.cssSelector(".info p")).getText().contains("Karl Marx Av. , 18, bld. 28\n" +
                "\n" +
                "644010, Omsk\n" +
                "Russia"));

        Assert.assertTrue(elements.get(2).findElement(By.cssSelector(".info p")).getText().contains("Karl Marx Av. , 41, bld. 13\n" +
                "\n" +
                "644042 , Omsk\n" +
                "Russia"));


        System.out.println("Done");
    }

}
