Feature: User CRUD feature
Background: Logged in as Psych admin user
Given logging in as psych admin user
When user is on the user creation page

@Smoke
Scenario: Create User
And user clicks Add student button
And user fills in the student details
And user clicks Create button
Then user should redirected to the student list page

@Regression
Scenario: Edit User
And user searches for the student by name 
And user edits the student details
Then user should see the updated student details in the list

@Regression
Scenario: Delete User
And user searches for the student by name 
And user deletes the student
Then user should not see the student in the list anymore