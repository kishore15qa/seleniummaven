Feature: Login Feature

Scenario: Successful login with valid credentials
    Given User is on Login Page
    When User enters username "karthi.s@spritle.com" and password "Password$5000"
    And Clicks on Login button
    Then User should be redirected to Home Page

Scenario: Login with invalid credentials
  Given User is on Login Page
  When User enters username "invaliduser@spritle.com" and password "wrongpass"
  And Clicks on Login button
  Then User should see an error message "Invalid email address or password"
