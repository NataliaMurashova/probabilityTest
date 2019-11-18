package com.luxoft.probability.tests;

import com.luxoft.probability.app.SeleniumWatcher;
import com.luxoft.probability.pages.JobOpportunitiesPage;
import com.luxoft.probability.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SeleniumWatcher.class)
@Execution(ExecutionMode.CONCURRENT)
public class Career {
    private final WebDriver driver;

    public Career(WebDriver driver) {
        this.driver = driver;
    }

    private JobOpportunitiesPage jobOpportunitiesPage;

    @BeforeEach
    void init() {
        MainPage mainPage = new MainPage(driver);
        jobOpportunitiesPage = mainPage.goToJobOpportunitiesPage();
    }

    @DisplayName("Check that java opportunities list not empty")
    @Test
    public void javaOpportunitiesNotEmptyTest() {
        jobOpportunitiesPage.search("java");

        assertFalse(jobOpportunitiesPage.getOpportunitiesList().isEmpty(),
                "Empty list of java opportunities not expected");
    }

}
