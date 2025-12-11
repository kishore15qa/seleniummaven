Feature: Psychologist User Management - Creation, Editing and Deletion

  As an admin user
  I want to create, edit, and delete psychologist users
  So that I can manage psychologist accounts effectively
  Background: 
    Given user launch the browser and logs into the application
    And user navigates to the Psychologist Management screen

    @CreatePsychologistUser
    Scenario: Create new Psychologist user
    When user navigates to the Psychologist User Creation Screen
    And user fills in the psychologist user details
      | User Name | John       |
      | Gender    | Male      |
      | DOB       | 1990-01-01 |
      | Email      | john.doe+psych@example.com |
      | Mobile    | 9123456789 |
      | Pronouns  | He, Him    |
      | Education | PhD in Psychology |
      | Residence  | New Delhi   |
      | DOJ       | 2023-01-15 |
      | Experience | 5 years    |
      | Languages | English, Hindi |
      | No of clients | 50   |
      | Expertise | Health psychologist|
      | Relationships comfortable with | Parents|
      | Domains | Anxiety, Emotion|
      | Counselling Focus | T1, T2|
    And user selects toggle on manage access
    And user submits the psychologist user creation form
    Then a new psychologist user should be created successfully  













    