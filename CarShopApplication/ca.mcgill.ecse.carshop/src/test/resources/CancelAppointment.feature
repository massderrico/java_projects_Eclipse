Feature: Cancel appointment
  As a customer, I wish to be able to cancel an appointment so that my appointment time slot becomes available for other customers

  Background: 
    Given a Carshop system exists
    Given the system's time and date is "2021-02-01+09:00"
    Given an owner account exists in the system
    Given a business exists in the system
    Given the following customers exist in the system:
      | username  | password |
      | customer1 | 12345678 |
      | customer2 | 12345678 |
    Given the following technicians exist in the system:
      | username                | password | type         |
      | Tire-Technician         | pass1    | Tire         |
      | Engine-Technician       | pass2    | Engine       |
      | Transmission-Technician | pass3    | Transmission |
      | Electronics-Technician  | pass4    | Electronics  |
      | Fluids-Technician       | pass5    | Fluids       |
    Given each technician has their own garage
    Given the following services exist in the system:
      | name               | duration | garage       |
      | tire-change        |      120 | Tire         |
      | transmission-check |       75 | Transmission |
      | engine-check       |       60 | Engine       |
      | electronics-repair |       50 | Electronics  |
    Given the following service combos exist in the system:
      | name                     | mainService        | services                                    | mandatory       |
      | engine-check-basic       | engine-check       | engine-check,transmission-check             | true,false      |
      | electronics-repair-basic | electronics-repair | electronics-repair,engine-check,tire-change | true,true,false |
    Given the business has the following opening hours
      | day       | startTime | endTime |
      | Monday    | 9:00      | 17:00   |
      | Tuesday   | 9:00      | 17:00   |
      | Wednesday | 9:00      | 17:00   |
      | Thursday  | 9:00      | 17:00   |
      | Friday    | 9:00      | 15:00   |
    Given the following appointments exist in the system:
      | customer  | serviceName        | optServices        | date       | timeSlots              |
      | customer1 | engine-check-basic | transmission-check | 2021-02-22 | 9:00-10:00,10:10-11:25 |

  Scenario: A customer attempts to cancel their appointment before the appointment date
    Given "customer1" is logged in to their account
    When "customer1" attempts to cancel their "engine-check-basic" appointment on "2021-02-22" at "9:00"
    Then "customer1"'s "engine-check-basic" appointment on "2021-02-22" at "9:00" shall be removed from the system
    Then there shall be 1 less appointment in the system

  Scenario: A customer attempts to cancel their appointment on the appointment date
    Given the system's time and date is "2021-02-22+06:00"
    Given "customer1" is logged in to their account
    When "customer1" attempts to cancel their "engine-check-basic" appointment on "2021-02-22" at "9:00"
    Then the system shall report "Cannot cancel an appointment on the appointment date"
    Then "customer1" shall have a "engine-check-basic" appointment on "2021-02-22" at "9:00" with the following properties
      | serviceName        | optServices        | date       | timeSlots              |
      | engine-check-basic | transmission-check | 2021-02-22 | 9:00-10:00,10:10-11:25 |
    Then there shall be 0 more appointment in the system

  Scenario Outline: A user attempts to cancel another user's appointment
    Given "<user>" is logged in to their account
    When "<user>" attempts to cancel "customer1"'s "engine-check-basic" appointment on "2021-02-22" at "9:00"
    Then the system shall report "<error>"
    Then "customer1" shall have a "engine-check-basic" appointment on "2021-02-22" at "9:00" with the following properties
      | serviceName        | optServices        | date       | timeSlots              |
      | engine-check-basic | transmission-check | 2021-02-22 | 9:00-10:00,10:10-11:25 |
    Then there shall be 0 more appointment in the system

    Examples: 
      | user            | error                                             |
      | owner           | An owner cannot cancel an appointment             |
      | Tire-Technician | A technician cannot cancel an appointment         |
      | customer2       | A customer can only cancel their own appointments |
