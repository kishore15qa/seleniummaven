Feature: Psychologist User CRUD Operations
Background: Logged in as Psychologist user
Given logging in as psych admin user
When user is on the psychologist user management page
@Smoke @Regression
Scenario: Create Psychologist User
And user clicks Add psychologist button
And user fills in the psychologist details
And user clicks Add button
Then user should be redirected to the psychologist user list page
@Regression
Scenario: Edit Psychologist User
And user searches for the psychologist by name
And user edits the psychologist details
Then user should see the success toaster in the psychologist list page
@Regression
Scenario: Delete Psychologist User
And user searches for the psychologist by name
And user deletes the psychologist
Then user should see the delete success toaster in the psychologist list page