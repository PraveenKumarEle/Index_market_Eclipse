Feature: S&P 500 Index History

  Background: Successful login with valid credentials
    Given User is on Spdji login page
    When User entered valid Username and Password and clicks on login
    Then User Should be logged Successfully

  Scenario: compare S&P 500 Index History for last 10 years
    Given User is on Home page and clicks index finder
    And User clicks on Search box and search for "S&P 500" index
    Then User Should find "S&P 500® | S&P Dow Jones Indices" Index Page
    And User select graph view type
    And User Clicks on time period in series
    Then User get change price value