Feature: login feature

  Scenario: Successful login with valid credentials
    Given User is on Spdji login page
    When User entered valid Username and Password and clicks on login
    Then User Should be logged Successfully