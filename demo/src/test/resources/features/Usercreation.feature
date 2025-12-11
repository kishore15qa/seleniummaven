Feature: User Management - User Creation, Editing and Deletion

  As an admin user
  I want to create, edit, and delete users
  So that I can manage user accounts effectively
Background: 
    Given user launch the browser and logs into the application
    And user navigates to the User Management screen

    @CreateUserB2C
    Scenario: Create new B2C user
    When user navigates to the User Creation Popup
    And user enters first name as "Johnie"
    And user enters last name as "Tester"
    And user selects origin as "B2C students"
    And user enters email as "john3doe@example.com"
    And user clicks on Create button
    Then new student should be created successfully with toaster message "You have successfully added a new user"
    @CreateExistingUser
    Scenario: Create a new user with an already existing email
    When user navigates to the User Creation Popup
    And user enters first name as "Jane"
    And user enters last name as "Doe"
    And user selects origin as "B2C students"
    And user enters email as "john3doe@example.com"
    And user clicks on Create button
    Then error message for email already exist should be displayed as "Email has already been taken"
    @CreateUserB2B
    Scenario: Create new B2B user
    When user navigates to the User Creation Popup
    And user enters first name as "Kishore"
    And user enters last name as "R"
    And user selects origin as "B2B students"
    And user enters roll number as "B2B1234"
    And user selects college option and search college name as "A1" and user selects the checkbox for college named as "A1 Global Institute Of Engineering & Technology, Prakasam, Andhra Pradesh"
    And user enters email as "john4doe@example.com"
    And user clicks on Create button
    Then new student should be created successfully with toaster message "You have successfully added a new user"

   @EditUser
    Scenario: Edit an existing user's details
    When user searches for the new created user
    And user updates first name to "Johnny"
    And user updates last name to "Doe"
    And user clicks on Update button
    Then student should be updated successfully with toaster message "Updated successfully"

    @DeleteUser
    Scenario: Delete an existing user
    When user searches for the new created user 
    And user clicks on Delete button
    Then student should be deleted successfully with toaster message "User has been successfully deleted"