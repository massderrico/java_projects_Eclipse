Feature: Make appointment
  As a customer, I wish to be able to make an appointment so that I can schedule a service

  Background: 
    Given a Carshop system exists
    Given the system's time and date is "2021-02-01+09:00"
    Given an owner account exists in the system
    Given a business exists in the system
    Given the following customers exist in the system:
      | username  | password |
      | customer1 | 12345678 |
      | customer2 | 12345678 |
      | customer3 | 12345678 |
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
    Given all garages has the following opening hours
      | day       | startTime | endTime |
      | Monday    | 9:00      | 16:00   |
      | Tuesday   | 9:00      | 16:00   |
      | Wednesday | 9:00      | 16:00   |
      | Thursday  | 9:00      | 16:00   |
    Given the business has the following holidays
      | startDate  | endDate    | startTime | endTime |
      | 2021-02-25 | 2021-03-07 | 0:00      | 23:59   |
    Given the following appointments exist in the system:
      | customer  | serviceName        | optServices        | date       | timeSlots              |
      | customer1 | engine-check-basic | transmission-check | 2021-02-22 | 9:00-10:00,10:10-11:25 |

  Scenario Outline: A customer attempts to make various valid appointments for services
    Given "customer1" is logged in to their account
    When "customer1" schedules an appointment on "<date>" for "<serviceName>" at "<startTime>"
    Then "customer1" shall have a "<serviceName>" appointment on "<date>" from "<startTime>" to "<endTime>"
    Then there shall be 1 more appointment in the system

    Examples: 
      # row 1: appointment in regular available slot
      # row 2: appointment in another garage
      | serviceName  | date       | startTime | endTime |
      | engine-check | 2021-02-02 | 9:40      | 10:40   |
      | tire-change  | 2021-02-22 | 9:00      | 11:00   |

  Scenario Outline: A customer attempts to make various valid appointments for service combos
    Given "customer1" is logged in to their account
    When "customer1" schedules an appointment on "<date>" for "<serviceName>" with "<optionalServices>" at "<startTimes>"
    Then "customer1" shall have a "<serviceName>" appointment on "<date>" from "<startTimes>" to "<endTimes>"
    Then there shall be 1 more appointment in the system

    Examples: 
      # row 1: appointment in regular available slot
      # row 2: appointment in another garage
      | serviceName              | optionalServices         | date       | startTimes       | endTimes         |
      | engine-check-basic       | transmission-check       | 2021-02-02 | 9:00,10:10       | 10:00,11:25      |
      | electronics-repair-basic | engine-check,tire-change | 2021-02-22 | 9:00,10:10,11:30 | 9:50,11:10,13:30 |

  Scenario Outline: A customer attempts to make various invalid appointments for services
    Given "customer1" is logged in to their account
    When "customer1" schedules an appointment on "<date>" for "<serviceName>" at "<startTime>"
    Then the system shall report "There are no available slots for <serviceName> on <date> at <startTime>"
    Then there shall be 0 more appointment in the system

    Examples: 
      # row 1: slot is occupied by existing appointment
      # row 2: slot is during holiday
      # row 3: slot is not is not during the business hour of the garage (friday)
      # row 4: slot is not during a business hour (saturday)
      # row 5: slot is the past
      | serviceName  | date       | startTime |
      | engine-check | 2021-02-22 | 9:40      |
      | engine-check | 2021-02-26 | 9:00      |
      | engine-check | 2021-02-19 | 9:00      |
      | engine-check | 2021-02-06 | 9:00      |
      | engine-check | 2020-12-31 | 9:00      |

  Scenario Outline: A customer attempts to make various invalid appointments for service combos
    Given "customer1" is logged in to their account
    When "customer1" schedules an appointment on "<date>" for "<serviceName>" with "<optionalServices>" at "<startTimes>"
    Then the system shall report "<errorMessage>"
    Then there shall be 0 more appointment in the system

    Examples: 
      # row 1: slot is occupied by existing appointment
      # row 2: slots of services is overlapping
      # row 3: slot is during holiday
      # row 4: slot is not is not during the business hour of the garage (friday)
      # row 5: slot is not during a business hour (saturday)
      # row 6: slot is in the past
      | serviceName        | optionalServices   | date       | startTimes | errorMessage                                                              |
      | engine-check-basic | transmission-check | 2021-02-22 | 9:30,10:30 | There are no available slots for engine-check-basic on 2021-02-22 at 9:30 |
      | engine-check-basic | transmission-check | 2021-02-02 | 9:00,9:30  | Time slots for two services are overlapping                               |
      | engine-check-basic | transmission-check | 2021-02-26 | 9:00,10:00 | There are no available slots for engine-check-basic on 2021-02-26 at 9:00 |
      | engine-check-basic | transmission-check | 2021-02-19 | 9:30,10:30 | There are no available slots for engine-check-basic on 2021-02-19 at 9:30 |
      | engine-check-basic | transmission-check | 2021-02-06 | 9:00,10:00 | There are no available slots for engine-check-basic on 2021-02-06 at 9:00 |
      | engine-check-basic | transmission-check | 2020-01-02 | 9:00,10:00 | There are no available slots for engine-check-basic on 2020-01-02 at 9:00 |

  Scenario Outline: An user with other role attempts to make an appointment
    Given "<username>" is logged in to their account
    When "<username>" schedules an appointment on "2020-02-02" for "engine-check" at "9:40"
    Then the system shall report "Only customers can make an appointment"
    Then there shall be 0 more appointment in the system

    Examples: 
      | username          |
      | owner             |
      | Tire-Technician   |
      | Fluids-Technician |
