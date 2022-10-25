Feature: Acceptance test for ISTQB Project
   Description: The purpose of these tests is to validate end to end workflows of the application.

  Scenario: User is able to get a list of students
    When I query the API for a list of students
    Then Then a list of students is returned

  Scenario: User is able to add a new student
    When I add a new student to the list of students
    Then The student is added

  Scenario: User is able to delete a student
    Given The student exists in the list of students
    When I delete the student from the list of students
    Then The student is deleted