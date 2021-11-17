Feature: Update Service
  As a business owner, I wish to update my existing services in my business so that I can keep my customers up to date.

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

  Scenario Outline: Update a service successfully
    Given the following services exist in the system:
      | name        | duration | garage |
      | tire-change |      120 | Tire   |
    Given the Owner with username "owner" is logged in
    When "owner" initiates the update of the service "tire-change" to name "<name>", duration "<duration>", belonging to the garage of "<garage>" technician
    Then the service "tire-change" shall be updated to name "<name>", duration "<duration>"
    Then the service "<name>" shall belong to the garage of "<garage>" technician

    Examples: 
      | name         | duration | garage |
      | tire-change  |       90 | Tire   |
      | tire-fix     |      120 | Tire   |
      | fluids-check |      120 | Fluids |

  Scenario Outline: Update a service with invalid parameters
    Given the following services exist in the system:
      | name               | duration | garage       |
      | tire-change        |      120 | Tire         |
      | transmission-check |       75 | Transmission |
    Given the Owner with username "owner" is logged in
    When "owner" initiates the update of the service "tire-change" to name "<name>", duration "<duration>", belonging to the garage of "<garage>" technician
    Then an error message with content "<error>" shall be raised
    Then the service "tire-change" shall still preserve the following properties:
      | name        | duration | garage |
      | tire-change |      120 | Tire   |

    Examples: 
      | name               | duration | garage | error                                     |
      | tire-change        |        0 | Tire   | Duration must be positive                 |
      | transmission-check |       90 | Tire   | Service transmission-check already exists |

  Scenario Outline: Unauthorized attempt to update a service
    Given the following customers exist in the system:
      | username  | password |
      | customer1 |  1234567 |
      | customer2 |  8901234 |
    Given the following services exist in the system:
      | name        | duration | garage |
      | tire-change |      120 | Tire   |
    Given the user with username "<username>" is logged in
    When "<username>" initiates the update of the service "tire-change" to name "tire-change", duration "10", belonging to the garage of "Tire" technician
    Then an error message with content "<error>" shall be raised
    Then the service "tire-change" shall still preserve the following properties:
      | name        | duration | garage |
      | tire-change |      120 | Tire   |

    Examples: 
      | username        | error                                            |
      | customer1       | You are not authorized to perform this operation |
      | customer2       | You are not authorized to perform this operation |
      | Tire-Technician | You are not authorized to perform this operation |
