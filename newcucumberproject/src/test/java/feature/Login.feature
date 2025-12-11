Feature: Login feature

@Smoke @Regression
Scenario: Valid Login test
When user enter email as "kishore.r+psychadmin2@spritle.com" and password as "Password$5000"
And click on login button
Then user is navigated to the home page "https://app-dev.innerkraft.com/dashboard"
@Regression
Scenario: Invalid Login test
When user enter email as "kishore.r+psychadmin2@spritle.com" and password as "Password$5043"
And click on login button
Then user stayed on same page "https://app-dev.innerkraft.com/"
