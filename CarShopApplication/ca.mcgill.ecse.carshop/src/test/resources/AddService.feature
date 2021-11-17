Feature: Add Service
  As a business owner, I wish to add services to my business so that my customers can make appointments for them.

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

  Scenario Outline: Add a service successfully
    Given the Owner with username "owner" is logged in
    When "owner" initiates the addition of the service "<name>" with duration "<duration>" belonging to the garage of "<garage>" technician
    Then the service "<name>" shall exist in the system
    Then the service "<name>" shall belong to the garage of "<garage>" technician
    Then the number of services in the system shall be "1"

    Examples: 
      | name               | duration | garage       |
      | tire-change        |      120 | Tire         |
      | transmission-check |       75 | Transmission |
      | engine-check       |       60 | Engine       |
      | electronics-repair |       50 | Electronics  |

  Scenario Outline: Add a service with invalid parameters
    Given the Owner with username "owner" is logged in
    When "owner" initiates the addition of the service "<name>" with duration "<duration>" belonging to the garage of "<garage>" technician
    Then an error message with content "<error>" shall be raised
    Then the service "<name>" shall not exist in the system
    Then the number of services in the system shall be "0"

    Examples: 
      | name        | duration | garage | error                     |
      | tire-change |        0 | Tire   | Duration must be positive |
      | tire-change |       -1 | Tire   | Duration must be positive |

  Scenario Outline: Add an existing service
    Given the following services exist in the system:
      | name        | duration | garage |
      | tire-change |      120 | Tire   |
    Given the Owner with username "owner" is logged in
    When "owner" initiates the addition of the service "<name>" with duration "<duration>" belonging to the garage of "<garage>" technician
    Then an error message with content "<error>" shall be raised
    Then the service "<name>" shall still preserve the following properties:
      | name        | duration | garage |
      | tire-change |      120 | Tire   |
    Then the number of services in the system shall be "1"

    Examples: 
      | name        | duration | garage | error                       |
      | tire-change |      100 | Tire   | Service tire-change already exists |
      | tire-change |       50 | Tire   | Service tire-change already exists |
      | tire-change |       75 | Tire   | Service tire-change already exists |

  Scenario Outline: Unauthorized attempt to add a service
    Given the following customers exist in the system:
      | username  | password |
      | customer1 |  1234567 |
      | customer2 |  8901234 |
    Given the user with username "<username>" is logged in
    When "<username>" initiates the addition of the service "tire-change" with duration "100" belonging to the garage of "Tire" technician
    Then an error message with content "<error>" shall be raised
    Then the service "tire-change" shall not exist in the system
    Then the number of services in the system shall be "0"

    Examples: 
      | username        | error                                            |
      | customer1       | You are not authorized to perform this operation |
      | customer2       | You are not authorized to perform this operation |
      | Tire-Technician | You are not authorized to perform this operation |
