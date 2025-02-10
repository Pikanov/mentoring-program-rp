Feature: User Authentication

  Scenario: Login page is loaded successfully
    Then the login page should be displayed

  Scenario Outline: User cannot log in with invalid credentials
    When they enter username "<username>" and password "<password>"
    Then an error message should be displayed

    Examples:
      | username | password  |
      | user1    | wrongPass |
      | user2    | 123456    |
      | admin    | test123   |