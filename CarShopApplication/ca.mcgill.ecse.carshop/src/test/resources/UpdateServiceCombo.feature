Feature: Update Service Combo
  As a business owner, I wish to update my existing services combo in my business so that I can keep my customers up to date.

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
    Given the following service combos exist in the system:
      | name               | mainService  | services                        | mandatory  |
      | engine-check-basic | engine-check | engine-check,transmission-check | true,false |

  Scenario Outline: Update a service combo successfully
    Given the Owner with username "owner" is logged in
    When "owner" initiates the update of service combo "engine-check-basic" to name "<name>", main service "<mainService>" and services "<services>" and mandatory setting "<mandatory>"
    Then the service combo "engine-check-basic" shall be updated to name "<name>"
    Then the service combo "<name>" shall contain the services "<services>" with mandatory setting "<mandatory>"
    Then the main service of the service combo "<name>" shall be "<mainService>"
    Then the service "<mainService>" in service combo "<name>" shall be mandatory

    Examples: 
      | name                     | mainService        | services                                           | mandatory       |
      | transmission-check-basic | transmission-check | engine-check,transmission-check                    | true,true       |
      | engine-check-basic       | engine-check       | engine-check,transmission-check                    | true,true       |
      | engine-check-basic       | engine-check       | engine-check,transmission-check,electronics-repair | true,true,false |

  Scenario Outline: Update a service combo with invalid parameters
    Given the following service combos exist in the system:
      | name                     | mainService        | services                                    | mandatory       |
      | electronics-repair-combo | electronics-repair | electronics-repair,engine-check,tire-change | true,true,false |
    Given the Owner with username "owner" is logged in
    When "owner" initiates the update of service combo "engine-check-basic" to name "<name>", main service "<mainService>" and services "<services>" and mandatory setting "<mandatory>"
    Then an error message with content "<error>" shall be raised
    Then the service combo "engine-check-basic" shall preserve the following properties:
      | name               | mainService  | services                        | mandatory  |
      | engine-check-basic | engine-check | engine-check,transmission-check | true,false |

    Examples: 
      | name                     | mainService        | services                                          | mandatory        | error                                            |
      | engine-repair-basic      | engine-repair      | engine-check,engine-repair                        | true,true        | Service engine-repair does not exist             |
      | tire-change-combo        | tire-change        | engine-check,transmission-check                   | false,true       | Main service must be included in the services    |
      | electronics-repair-basic | electronics-repair | electronics-repair                                | true             | A service Combo must contain at least 2 services |
      | electronics-repair-basic | electronics-repair | electronics-repair,engine-check,tire-change       | false,true,false | Main service must be mandatory                   |
      | electronics-repair-basic | electronics-repair | electronics-repair,engine-check,electronics-check | true,false,true  | Service electronics-check does not exist         |
      | electronics-repair-combo | electronics-repair | electronics-repair,engine-check                   | true,true        | Service combo electronics-repair-combo already exists  |

  Scenario Outline: Unauthorized attempt to update a service combo
    Given the following customers exist in the system:
      | username  | password |
      | customer1 |  1234567 |
      | customer2 |  8901234 |
    Given the user with username "<username>" is logged in
    When "<username>" initiates the update of service combo "engine-check-basic" to name "engine-check-basic", main service "engine-check" and services "engine-check,transmission-check" and mandatory setting "true,true"
    Then an error message with content "<error>" shall be raised
    Then the service combo "engine-check-basic" shall preserve the following properties:
      | name               | mainService  | services                        | mandatory  |
      | engine-check-basic | engine-check | engine-check,transmission-check | true,false |

    Examples: 
      | username        | error                                            |
      | customer1       | You are not authorized to perform this operation |
      | customer2       | You are not authorized to perform this operation |
      | Tire-Technician | You are not authorized to perform this operation |
