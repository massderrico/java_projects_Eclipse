Feature: Define Service Combo
  As a business owner, I wish to add service combos to my business so that my customers can make appointments for them.

  Background: 
    Given a Carshop system exists
    Given an owner account exists in the system
    Given a business exists in the system
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

  Scenario Outline: Define a service combo successfully
    Given the Owner with username "owner" is logged in
    When "owner" initiates the definition of a service combo "<name>" with main service "<mainService>", services "<services>" and mandatory setting "<mandatory>"
    Then the service combo "<name>" shall exist in the system
    Then the service combo "<name>" shall contain the services "<services>" with mandatory setting "<mandatory>"
    Then the main service of the service combo "<name>" shall be "<mainService>"
    Then the service "<mainService>" in service combo "<name>" shall be mandatory
    Then the number of service combos in the system shall be "1"

    Examples: 
      | name                     | mainService        | services                                    | mandatory       |
      | engine-check-basic       | engine-check       | engine-check,transmission-check             | true,false      |
      | electronics-repair-basic | electronics-repair | electronics-repair,engine-check,tire-change | true,true,false |

  Scenario Outline: Define a service combo with invalid parameters
    Given the following service combos exist in the system:
      | name               | mainService  | services                        | mandatory  |
      | engine-check-basic | engine-check | engine-check,transmission-check | true,false |
    Given the Owner with username "owner" is logged in
    When "owner" initiates the definition of a service combo "<name>" with main service "<mainService>", services "<services>" and mandatory setting "<mandatory>"
    Then an error message with content "<error>" shall be raised
    Then the service combo "<name>" shall not exist in the system
    Then the number of service combos in the system shall be "1"

    Examples: 
      | name                     | mainService        | services                                          | mandatory        | error                                            |
      | electronics-repair-basic | electronics-repair | electronics-repair,engine-check,electronics-check | true,false,true  | Service electronics-check does not exist         |
      | tire-change-combo        | tire-change        | engine-check,transmission-check                   | false,true       | Main service must be included in the services    |
      | electronics-repair-basic | electronics-repair | electronics-repair,engine-check,tire-change       | false,true,false | Main service must be mandatory                   |
      | electronics-repair-basic | electronics-repair | electronics-repair                                | true             | A service Combo must contain at least 2 services |
      | electronics-check-combo  | electronics-check  | electronics-check,electronics-repair              | true,false       | Service electronics-check does not exist         |

  Scenario Outline: Define an existing service combo
    Given the following service combos exist in the system:
      | name               | mainService  | services                        | mandatory  |
      | engine-check-basic | engine-check | engine-check,transmission-check | true,false |
    Given the Owner with username "owner" is logged in
    When "owner" initiates the definition of a service combo "<name>" with main service "<mainService>", services "<services>" and mandatory setting "<mandatory>"
    Then an error message with content "<error>" shall be raised
    Then the service combo "<name>" shall preserve the following properties:
      | name               | mainService  | services                        | mandatory  |
      | engine-check-basic | engine-check | engine-check,transmission-check | true,false |
    Then the number of service combos in the system shall be "1"

    Examples: 
      | name               | mainService  | services                                           | mandatory        | error                                           |
      | engine-check-basic | engine-check | engine-check,transmission-check,electronics-repair | true,false,false | Service combo engine-check-basic already exists |

  Scenario Outline: Unauthorized attempt to define a service combo
    Given the following customers exist in the system:
      | username  | password |
      | customer1 |  1234567 |
      | customer2 |  8901234 |
    Given the user with username "<username>" is logged in
    When "<username>" initiates the definition of a service combo "engine-check-basic" with main service "engine-check", services "engine-check,transmission-check" and mandatory setting "true,false"
    Then an error message with content "<error>" shall be raised
    Then the service combo "engine-check-basic" shall not exist in the system
    Then the number of service combos in the system shall be "0"

    Examples: 
      | username        | error                                            |
      | customer1       | You are not authorized to perform this operation |
      | customer2       | You are not authorized to perform this operation |
      | Tire-Technician | You are not authorized to perform this operation |
