Feature: Update garage opening hours
  As a technician, I want to update the garage opening hours so that the customers can make appointments

  Background: 
    Given a Carshop system exists
    Given an owner account exists in the system with username "owner" and password "ownerPass"
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
    Given the business has the following opening hours:
      | day       | startTime | endTime |
      | Monday    | 9:00      | 17:00   |
      | Tuesday   | 9:00      | 17:00   |
      | Wednesday | 9:00      | 17:00   |
      | Thursday  | 9:00      | 17:00   |
      | Friday    | 9:00      | 15:00   |

  Scenario Outline: Add new opening hours successfully
    Given the user is logged in to an account with username "<username>"
    When the user tries to add new business hours on "<day>" from "<startTime>" to "<endTime>" to garage belonging to the technician with type "<type>"
    Then the garage belonging to the technician with type "<type>" should have opening hours on "<day>" from "<startTime>" to "<endTime>"

    Examples: 
      | username                | day       | startTime | endTime | type         |
      | Tire-Technician         | Monday    | 10:00     | 16:00   | Tire         |
      | Engine-Technician       | Tuesday   | 10:00     | 16:00   | Engine       |
      | Transmission-Technician | Wednesday | 10:00     | 16:00   | Transmission |
      | Electronics-Technician  | Thursday  | 10:00     | 16:00   | Electronics  |
      | Fluids-Technician       | Friday    | 10:00     | 15:00   | Fluids       |

  Scenario Outline: Remove opening hours successfully
    Given there are opening hours on "<day>" from "<startTime>" to "<endTime>" for garage belonging to the technician with type "<type>"
    Given the user is logged in to an account with username "<username>"
    When the user tries to remove opening hours on "<day>" from "<startTime>" to "<endTime>" to garage belonging to the technician with type "<type>"
    Then the garage belonging to the technician with type "<type>" should not have opening hours on "<day>" from "<startTime>" to "<endTime>"

    Examples: 
      | username                | day       | startTime | endTime | type         |
      | Tire-Technician         | Monday    | 10:00     | 16:00   | Tire         |
      | Engine-Technician       | Tuesday   | 10:00     | 16:00   | Engine       |
      | Transmission-Technician | Wednesday | 10:00     | 16:00   | Transmission |
      | Electronics-Technician  | Thursday  | 10:00     | 16:00   | Electronics  |
      | Fluids-Technician       | Friday    | 10:00     | 15:00   | Fluids       |

  Scenario Outline: Add new business hour with invalid parameters
    Given there are opening hours on "Monday" from "10:00" to "16:00" for garage belonging to the technician with type "Tire"
    Given the user is logged in to an account with username "<username>"
    When the user tries to add new business hours on "<day>" from "<startTime>" to "<endTime>" to garage belonging to the technician with type "<type>"
    Then the garage belonging to the technician with type "<type>" should not have opening hours on "<day>" from "<startTime>" to "<endTime>"
    Then an error message "<error>" shall be raised

    Examples: 
      | username                | day      | startTime | endTime | type         | error                                                              |
      | Tire-Technician         | Saturday | 10:00     | 16:00   | Tire         | The opening hours are not within the opening hours of the business |
      | Tire-Technician         | Monday   | 10:00     | 13:00   | Tire         | The opening hours cannot overlap                                   |
      | Transmission-Technician | Tuesday  | 10:00     | 18:00   | Transmission | The opening hours are not within the opening hours of the business |
      | Electronics-Technician  | Thursday | 10:00     | 9:00    | Electronics  | Start time must be before end time                                 |

  Scenario Outline: Unauthorized attempt to add new business hours
    Given the following customers exist in the system:
      | username  | password |
      | customer1 |  1234567 |
    Given the user is logged in to an account with username "<username>"
    When the user tries to add new business hours on "<day>" from "<startTime>" to "<endTime>" to garage belonging to the technician with type "<type>"
    Then the garage belonging to the technician with type "<type>" should not have opening hours on "<day>" from "<startTime>" to "<endTime>"
    Then an error message "<error>" shall be raised

    Examples: 
      | username                | day    | startTime | endTime | type         | error                                            |
      | customer1               | Monday | 10:00     | 16:00   | Tire         | You are not authorized to perform this operation |
      | Transmission-Technician | Monday | 10:00     | 16:00   | Tire         | You are not authorized to perform this operation |
      | owner                   | Monday | 10:00     | 16:00   | Transmission | You are not authorized to perform this operation |

  Scenario Outline: Unauthorized attempt to remove business hours
    Given the following customers exist in the system:
      | username  | password |
      | customer1 |  1234567 |
    Given there are opening hours on "Monday" from "10:00" to "16:00" for garage belonging to the technician with type "Tire"
    Given the user is logged in to an account with username "<username>"
    When the user tries to remove opening hours on "<day>" from "<startTime>" to "<endTime>" to garage belonging to the technician with type "<type>"
    Then the garage belonging to the technician with type "<type>" should have opening hours on "<day>" from "<startTime>" to "<endTime>"
    Then an error message "<error>" shall be raised

    Examples: 
      | username                | day    | startTime | endTime | type | error                                            |
      | customer1               | Monday | 10:00     | 16:00   | Tire | You are not authorized to perform this operation |
      | Transmission-Technician | Monday | 10:00     | 16:00   | Tire | You are not authorized to perform this operation |
      | owner                   | Monday | 10:00     | 16:00   | Tire | You are not authorized to perform this operation |
