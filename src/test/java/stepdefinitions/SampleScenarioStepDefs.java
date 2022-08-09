package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.GooglePage;
import utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class SampleScenarioStepDefs {
/*
@ui @samplescenario
Feature: This is sample feature

  Scenario Outline: This sample scenario opens google
    Given As a user go to the "<webpage>" page
    Then As a user verify the page title is not empty
    And As a user close the web page

    Examples:
      | webpage                |
      | https://www.google.com |
 */

    private static Logger logger =LogManager.getLogger(SampleScenarioStepDefs.class.getName());
    private GooglePage googlePage;

    public SampleScenarioStepDefs(GooglePage googlePage) {
        this.googlePage = googlePage;
    }

    @Given("As a user go to the {string} page")
    public void as_a_user_go_to_the_page(String webpage) {

        Driver.getDriver().get(webpage);
        logger.info(webpage+" page was opened");
    }
    @Then("As a user verify the page title is not empty")
    public void as_a_user_verify_the_page_title_is_not_empty() {
        Assert.assertFalse(Driver.getDriver().getTitle().isEmpty());
        logger.info("Page title is not empty");
    }
    @Then("As a user search {string} on google page")
    public void as_a_user_search_on_google_page(String searchWord) {

        googlePage.search(searchWord);
        logger.info(searchWord+" was searched");
    }
    @Then("As a user close the web page")
    public void as_a_user_close_the_web_page() {

        Driver.closeDriver();
        logger.info("driver was closed");
    }
}
