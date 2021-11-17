Feature: Login as customer or owner
  As an owner, I want to log in so that I can access the space to manage my business. 
  As a technician, I want to log in so that I can view my appointments.
  As a customer, I want to log in so that I can manage my appointments.
  The owner account is created automatically if it does not exist.
  The technician account is created automatically if it does not exist.

  Background: 
    Given a Carshop system exists
    Given the following customers exist in the system:
      | username | password |
      | User1    | apple    |
      | User2    | grape    |

  Scenario: Log in successfully
    When the user tries to log in with username "User1" and password "apple"
    Then the user should be successfully logged in

  Scenario: Log in with a username that does not exist
    When the user tries to log in with username "User3" and password "apple"
    Then the user should not be logged in
    Then an error message "Username/password not found" shall be raised

  Scenario: Log in as the owner for the first time
    When the user tries to log in with username "owner" and password "owner"
    Then a new account shall be created
    Then the account shall have username "owner" and password "owner"
    Then the user shall be successfully logged in

  Scenario Outline: Log in as a technician for the first time
    When the user tries to log in with username "<username>" and password "<password>"
    Then a new account shall be created
    Then the account shall have username "<username>", password "<password>" and technician type "<type>"
    Then the corresponding garage for the technician shall be created
    Then the garage should have the same opening hours as the business
    Then the user shall be successfully logged in

    Examples: 
      | username                | password                | type         |
      | Tire-Technician         | Tire-Technician         | Tire         |
      | Engine-Technician       | Engine-Technician       | Engine       |
      | Transmission-Technician | Transmission-Technician | Transmission |
      | Electronics-Technician  | Electronics-Technician  | Electronics  |
      | Fluids-Technician       | Fluids-Technician       | Fluids       |

  Scenario: Log in with incorrect password
    When the user tries to log in with username "User1" and password "grape"
    Then the user should not be logged in
    Then an error message "Username/password not found" shall be raised
