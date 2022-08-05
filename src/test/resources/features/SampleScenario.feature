@ui @samplescenario
Feature: This is sample feature

  Scenario Outline: This sample scenario opens google
    Given As a user go to the "<webpage>" page
    Then As a user verify the page title is not empty
    And As a user search "<searchWord>" on google page
    And As a user close the web page

    Examples:
      | webpage                | searchWord                       |
      | https://www.google.com | Dependency Injection in Cucumber |
