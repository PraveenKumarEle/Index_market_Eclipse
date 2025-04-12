Feature: Positive Index Listing

  Background: Successful login with valid credentials
    Given User is on Spdji login page
    When User entered valid Username and Password and clicks on login
    Then User Should be logged Successfully

  Scenario: List out the indexes which are in Positive
    Given User is on Home page
		When User finds listed index with Percentage value
    And User print the positive listed indexes finally