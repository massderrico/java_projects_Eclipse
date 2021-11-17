Feature: Update business information
  As an owner, I want to update the business information so that customers can see the new changes

  Background: 
    Given a Carshop system exists
    Given an owner account exists in the system with username "owner" and password "ownerPass"
    Given the following customers exist in the system:
      | username | password |
      | User1    | apple    |
      | User2    | grape    |
    Given the following technicians exist in the system:
      | username                | password | type         |
      | Tire-Technician         | pass1    | Tire         |
      | Engine-Technician       | pass2    | Engine       |
      | Transmission-Technician | pass3    | Transmission |
      | Electronics-Technician  | pass4    | Electronics  |
      | Fluids-Technician       | pass5    | Fluids       |
    Given each technician has their own garage
    Given a business exists with the following information:
      | name       | address          | phone number  | email           |
      | Car Center | 507 Henderson Dr | (514)123-4567 | dizzy@dizzy.com |
    Given the system's time and date is "2021-02-01+11:00"
    Given the business has a business hour on "Friday" with start time "08:00" and end time "16:00"
    Given a "vacation" time slot exists with start time "2021-02-28" at "09:00" and end time "2021-03-10" at "16:00"
    Given a "holiday" time slot exists with start time "2021-03-15" at "16:00" and end time "2021-03-17" at "09:00"

  Scenario Outline: Update basic business information
    Given the user is logged in to an account with username "<username>"
    When the user tries to update the business information with new "<name>" and "<address>" and "<phone number>" and "<email>"
    Then the business information shall "<result>" updated with new "<name>" and "<address>" and "<phone number>" and "<email>"
    Then an error message "<error>" shall "<resultError>" raised

    Examples: 
      | username          | name     | address     | phone number  | email          | result | error                                        | resultError |
      | owner             | Car Club | 123 New Str | (514)987-6543 | busy@gmail.com | be     |                                              | not be      |
      | User1             | Car Club | 123 New Str | (514)987-6543 | busy@gmail.com | not be | No permission to update business information | be          |
      | Engine-Technician | Car Club | 123 New Str | (514)987-6543 | busy@gmail.com | not be | No permission to update business information | be          |
      | owner             | Car Club | 123 New Str | (514)987-6543 | busy@gmail     | not be | Invalid email                                | be          |
      | owner             | Car Club | 123 New Str | (514)987-6543 | busy.com       | not be | Invalid email                                | be          |

  Scenario Outline: Add new business hours
    Given the user is logged in to an account with username "<username>"
    When the user tries to add a new business hour on "<Day>" with start time "<newStartTime>" and end time "<newEndTime>"
    Then a new business hour shall "<result>" created
    Then an error message "<error>" shall "<resultError>" raised

    Examples: 
      | username          | Day      | newStartTime | newEndTime | result | error                                        | resultError |
      | owner             | Friday   | 09:00        | 11:00      | not be | The business hours cannot overlap            | be          |
      | owner             | Friday   | 07:00        | 11:00      | not be | The business hours cannot overlap            | be          |
      | owner             | Thursday | 12:30        | 16:00      | be     |                                              | not be      |
      | User1             | Thursday | 12:30        | 16:00      | not be | No permission to update business information | be          |
      | Engine-Technician | Thursday | 12:30        | 16:00      | not be | No permission to update business information | be          |
      | owner             | Thursday | 13:00        | 11:00      | not be | Start time must be before end time           | be          |

  Scenario Outline: Update existing business hours
    Given the business has a business hour on "Thursday" with start time "09:00" and end time "15:00"
    Given the user is logged in to an account with username "<username>"
    When the user tries to change the business hour "Thursday" at "09:00" to be on "<Day>" starting at "<newStartTime>" and ending at "<newEndTime>"
    Then the business hour shall "<result>" be updated
    Then an error message "<error>" shall "<resultError>" raised

    Examples: 
      | username          | Day      | newStartTime | newEndTime | result | error                                        | resultError |
      | owner             | Friday   | 13:00        | 19:00      | not be | The business hours cannot overlap            | be          |
      | owner             | Thursday | 12:30        | 16:00      | be     |                                              | not be      |
      | User1             | Thursday | 12:30        | 16:00      | not be | No permission to update business information | be          |
      | Engine-Technician | Thursday | 12:30        | 16:00      | not be | No permission to update business information | be          |
      | owner             | Thursday | 13:00        | 11:00      | not be | Start time must be before end time           | be          |

  Scenario Outline: Remove existing business hours
    Given the user is logged in to an account with username "<username>"
    Given the business has a business hour on "Thursday" with start time "09:00" and end time "15:00"
    When the user tries to remove the business hour starting "Thursday" at "09:00"
    Then the business hour starting "Thursday" at "09:00" shall "<result>" exist
    Then an error message "No permission to update business information" shall "<result>" be raised

    Examples: 
      | username          | result |
      | owner             | not    |
      | User1             |        |
      | Engine-Technician |        |

  Scenario Outline: Add new time slot
    Given the user is logged in to an account with username "<username>"
    When the user tries to add a new "<type>" with start date "<startDate>" at "<startTime>" and end date "<endDate>" at "<endTime>"
    Then a new "<type>" shall "<result>" be added with start date "<startDate>" at "<startTime>" and end date "<endDate>" at "<endTime>"
    Then an error message "<error>" shall "<resultError>" raised

    Examples: 
      | username          | type     | startDate  | startTime | endDate    | endTime | result | error                                        | resultError |
      | owner             | holiday  | 2021-02-25 | 09:00     | 2021-02-26 | 09:00   | be     |                                              | not be      |
      | owner             | vacation | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | be     |                                              | not be      |
      | owner             | vacation | 2021-03-01 | 09:00     | 2021-03-15 | 09:00   | not be | Vacation times cannot overlap                | be          |
      | owner             | holiday  | 2021-03-16 | 16:00     | 2021-03-17 | 09:00   | not be | Holiday times cannot overlap                 | be          |
      | owner             | vacation | 2021-03-16 | 16:00     | 2021-03-17 | 09:00   | not be | Holiday and vacation times cannot overlap    | be          |
      | User1             | vacation | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | not be | No permission to update business information | be          |
      | Engine-Technician | vacation | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | not be | No permission to update business information | be          |
      | owner             | holiday  | 2021-07-26 | 09:00     | 2021-07-25 | 09:00   | not be | Start time must be before end time           | be          |
      | owner             | holiday  | 2021-01-01 | 09:00     | 2021-06-02 | 09:00   | not be | Holiday cannot start in the past             | be          |
      | owner             | vacation | 2021-01-01 | 09:00     | 2021-06-02 | 09:00   | not be | Vacation cannot start in the past            | be          |

  Scenario Outline: Update vacation
    Given the user is logged in to an account with username "<username>"
    When the user tries to change the "vacation" on "2021-02-28" at "09:00" to be with start date "<startDate>" at "<startTime>" and end date "<endDate>" at "<endTime>"
    Then the "vacation" shall "<result>" updated with start date "<startDate>" at "<startTime>" and end date "<endDate>" at "<endTime>"
    Then an error message "<error>" shall "<resultError>" raised

    Examples: 
      | username          | startDate  | startTime | endDate    | endTime | result | error                                        | resultError |
      | owner             | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | be     |                                              | not be      |
      | owner             | 2021-03-16 | 16:00     | 2021-03-17 | 09:00   | not be | Holiday and vacation times cannot overlap    | be          |
      | User1             | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | not be | No permission to update business information | be          |
      | Engine-Technician | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | not be | No permission to update business information | be          |
      | owner             | 2021-07-26 | 09:00     | 2021-07-25 | 09:00   | not be | Start time must be before end time           | be          |
      | owner             | 2021-01-01 | 09:00     | 2021-06-02 | 09:00   | not be | Vacation cannot start in the past            | be          |

  Scenario Outline: Update holiday
    Given the user is logged in to an account with username "<username>"
    When the user tries to change the "holiday" on "2021-03-15" at "16:00" to be with start date "<startDate>" at "<startTime>" and end date "<endDate>" at "<endTime>"
    Then the "holiday" shall "<result>" updated with start date "<startDate>" at "<startTime>" and end date "<endDate>" at "<endTime>"
    Then an error message "<error>" shall "<resultError>" raised

    Examples: 
      | username          | startDate  | startTime | endDate    | endTime | result | error                                        | resultError |
      | owner             | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | be     |                                              | not be      |
      | owner             | 2021-03-01 | 16:00     | 2021-03-07 | 09:00   | not be | Holiday and vacation times cannot overlap    | be          |
      | User1             | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | not be | No permission to update business information | be          |
      | Engine-Technician | 2021-09-01 | 09:30     | 2021-09-15 | 09:20   | not be | No permission to update business information | be          |
      | owner             | 2021-07-26 | 09:00     | 2021-07-25 | 09:00   | not be | Start time must be before end time           | be          |
      | owner             | 2021-01-01 | 09:00     | 2021-06-02 | 09:00   | not be | Holiday cannot start in the past             | be          |

  Scenario Outline: Remove existing time slot
    Given the user is logged in to an account with username "<username>"
    When the user tries to remove an existing "<type>" with start date "<startDate>" at "<startTime>" and end date "<endDate>" at "<endTime>"
    Then the "<type>" with start date "<startDate>" at "<startTime>" shall "<result>" exist
    Then an error message "No permission to update business information" shall "<result>" be raised

    Examples: 
      | username          | type     | startDate  | startTime | endDate    | endTime | result |
      | owner             | vacation | 2021-02-28 | 09:00     | 2021-03-10 | 16:00   | not    |
      | User1             | vacation | 2021-02-28 | 09:00     | 2021-03-10 | 16:00   |        |
      | Engine-Technician | vacation | 2021-02-28 | 09:00     | 2021-03-10 | 16:00   |        |
      | owner             | holiday  | 2021-03-15 | 16:00     | 2021-03-17 | 09:00   | not    |
      | User1             | holiday  | 2021-03-15 | 16:00     | 2021-03-17 | 09:00   |        |
      | Engine-Technician | holiday  | 2021-03-15 | 16:00     | 2021-03-17 | 09:00   |        |
