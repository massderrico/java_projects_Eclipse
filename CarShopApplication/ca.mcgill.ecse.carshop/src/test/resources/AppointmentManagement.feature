Feature: Appointment management process

  Background: 
    Given a Carshop system exists
    Given the system's time and date is "2021-04-01+09:00"
    Given an owner account exists in the system
    Given a business exists in the system
    Given the following customers exist in the system:
      | username  | password |
      | customer1 | 12345678 |
    Given the business has the following opening hours
      | day       | startTime | endTime |
      | Monday    | 8:00      | 18:00   |
      | Tuesday   | 8:00      | 18:00   |
      | Wednesday | 8:00      | 18:00   |
      | Thursday  | 8:00      | 18:00   |
      | Friday    | 8:00      | 18:00   |
      | Saturday  | 8:00      | 17:00   |
      | Sunday    | 8:00      | 17:00   |
    Given the following technicians exist in the system:
      | username                | password | type         |
      | Tire-Technician         | pass1    | Tire         |
      | Engine-Technician       | pass2    | Engine       |
      | Transmission-Technician | pass3    | Transmission |
      | Electronics-Technician  | pass4    | Electronics  |
      | Fluids-Technician       | pass5    | Fluids       |
    Given each technician has their own garage
    Given all garages has the following opening hours
      | day       | startTime | endTime |
      | Monday    | 8:00      | 17:00   |
      | Tuesday   | 8:00      | 17:00   |
      | Wednesday | 8:00      | 17:00   |
      | Thursday  | 8:00      | 17:00   |
      | Friday    | 8:00      | 17:00   |
      | Saturday  | 8:00      | 17:00   |
      | Sunday    | 8:00      | 17:00   |
    Given the business has the following holidays
      | startDate  | endDate    | startTime | endTime |
      | 2021-04-18 | 2021-12-18 | 10:00     | 23:59   |
    Given a "vacation" time slot exists with start time "2021-04-14" at "12:00" and end time "2021-04-16" at "13:00"
    Given "customer1" has 0 no-show records
    Given the following services exist in the system:
      | name               | duration | garage       |
      | tire-change        |       10 | Tire         |
      | transmission-check |       75 | Transmission |
      | engine-check       |       20 | Engine       |
      | electronics-repair |       10 | Electronics  |
    Given the following service combos exist in the system:
      | name                     | mainService        | services                                                       | mandatory             |
      | transmission-check-combo | transmission-check | tire-change,transmission-check,electronics-repair,engine-check | false,true,true,false |
    Given the following appointments exist in the system:
      | customer  | serviceName              | optServices                                 | date       | timeSlots                                       |
      | customer1 | transmission-check-combo | tire-change,electronics-repair,engine-check | 2021-04-08 | 13:00-14:15,14:20-14:30,14:40-14:50,15:00-15:20 |

  # the When statements include when the action is executed at the end
  # e.g., the statement
  #    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
  # refers to the action that user issues the "make an appointment for ..." command on 2021-04-04 at 09:00
  # this allows setting the system time to this specified date and enables thorough testing the behavior of the software
  Scenario: Change the appointment for a service at least one day ahead
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to change the service in the appointment to "tire-change" at "2021-04-09+09:10"
    Then the appointment shall be booked
    Then the service in the appointment shall be "tire-change"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Change the appointment for a service on its day
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to change the service in the appointment to "tire-change" at "2021-04-10+09:10"
    Then the appointment shall be booked
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Change date and time of appointment for a service at least one day ahead
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-11" and time to "11:00" at "2021-04-09+09:30"
    Then the appointment shall be booked
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-11" with start time "11:00" and end time "11:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Change the date and time of appointment for a service on its day
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-11" and time to "11:00" at "2021-04-10+09:30"
    Then the appointment shall be booked
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Cancel the appointment for a service at least one day ahead
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to cancel the appointment at "2021-04-09+09:00"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 1 appointment

  Scenario: Cancel the appointment for a service on its day
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to cancel the appointment at "2021-04-10+09:00"
    Then the appointment shall be booked
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Change the appointment for a service combo at least one day ahead
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to add the optional service "engine-check" to the service combo with start time "12:20" in the appointment at "2021-04-09+09:00"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair,engine-check" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00,12:20" and end time "11:15,12:10,12:40"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Change the appointment for a service combo on its day
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to add the optional service "engine-check" to the service combo with start time "12:20" in the appointment at "2021-04-10+09:00"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00" and end time "11:15,12:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Change the date and time of appointment for a service combo at least one day ahead
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-11" and start time to "11:00,13:00" at "2021-04-09+09:30"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-11" with start time "11:00,13:00" and end time "12:15,13:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Change the date and time of appointment for a service combo on its day
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-11" and start time to "11:00,13:00" at "2021-04-10+09:30"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00" and end time "11:15,12:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer attempts to cancel the appointment for a service combo at least one day ahead
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to cancel the appointment at "2021-04-09+09:00"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 1 appointment

  Scenario: Customer attempts to cancel the appointment for a service combo on its day
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to cancel the appointment at "2021-04-10+09:00"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00" and end time "11:15,12:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer arrives and the appointment for service completes without any changes
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+10:00"
    When the owner ends the appointment at "2021-04-10+10:20"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 1 appointment

  Scenario: Customer arrives and the appointment for service combo completes without any changes
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+10:00"
    When the owner ends the appointment at "2021-04-10+12:10"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 1 appointment

  Scenario: Customer arrives to the appointment and updates the service combo
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+10:00"
    When "customer1" attempts to add the optional service "engine-check" to the service combo with start time "12:20" in the appointment at "2021-04-10+10:05"
    Then the appointment shall be in progress
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair,engine-check" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00,12:20" and end time "11:15,12:10,12:40"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer arrives to the appointment early and the owner attempts to start the appointment
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+09:55"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00" and end time "11:15,12:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer does not show up for the appointment
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When the owner attempts to register a no-show for the appointment at "2021-04-10+12:11"
    Then the user "customer1" shall have 1 no-show records
    Then the system shall have 1 appointment

  Scenario: Change date of appointment with service while the appointment is in progress
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+10:00"
    When "customer1" attempts to update the date to "2021-04-11" and time to "11:00" at "2021-04-10+10:05"
    Then the appointment shall be in progress
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Cancel appointment while the appointment is in progress
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+10:00"
    When "customer1" attempts to cancel the appointment at "2021-04-10+10:05"
    Then the appointment shall be in progress
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Start appointment while the appointment is in progress
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+10:00"
    When the owner starts the appointment at "2021-04-10+10:05"
    Then the appointment shall be in progress
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Register no-show for customer while the appointment is in progress
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-10+10:01"
    When the owner attempts to register a no-show for the appointment at "2021-04-10+10:10"
    Then the appointment shall be in progress
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: End appointment while the appointment is not in progress
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When the owner attempts to end the appointment at "2021-04-10+09:10"
    Then the appointment shall be booked
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service and new time overlaps with another appointment
    When "customer1" makes a "electronics-repair" appointment for the date "2021-04-08" and time "14:25" at "2021-04-04+09:00"
    When "customer1" attempts to change the service in the appointment to "tire-change" at "2021-04-07+09:00"
    Then the appointment shall be booked
    Then the service in the appointment shall be "electronics-repair"
    Then the appointment shall be for the date "2021-04-08" with start time "14:25" and end time "14:35"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service and new time is outside business hours
    When "customer1" makes a "tire-change" appointment for the date "2021-04-08" and time "16:45" at "2021-04-04+09:00"
    When "customer1" attempts to change the service in the appointment to "engine-check" at "2021-04-07+09:00"
    Then the appointment shall be booked
    Then the service in the appointment shall be "tire-change"
    Then the appointment shall be for the date "2021-04-08" with start time "16:45" and end time "16:55"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service and new time overlaps vacation
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-16" and time to "12:00" at "2021-04-07+09:30"
    Then the appointment shall be booked
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service and new date is on a holiday
    When "customer1" makes a "engine-check" appointment for the date "2021-04-10" and time "10:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-18" and time to "12:00" at "2021-04-07+09:30"
    Then the appointment shall be booked
    Then the service in the appointment shall be "engine-check"
    Then the appointment shall be for the date "2021-04-10" with start time "10:00" and end time "10:20"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service combo and new time overlaps with another appointment
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-08" and start time "11:20,12:40" at "2021-04-04+09:00"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "14:20" in the appointment at "2021-04-07+09:00"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-08" with start time "11:20,12:40" and end time "12:35,12:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service combo and new time overlaps with another combo item booking
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-08" and start time "11:20,12:40" at "2021-04-04+09:00"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "12:40" in the appointment at "2021-04-07+09:00"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-08" with start time "11:20,12:40" and end time "12:35,12:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service combo and new time is outside business hours
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-08" and start time "15:20,16:40" at "2021-04-04+09:00"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "16:55" in the appointment at "2021-04-07+09:00"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-08" with start time "15:20,16:40" and end time "16:35,16:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service combo and new time overlaps vacation
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-16" and start time to "10:00,12:00" at "2021-04-07+09:30"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00" and end time "11:15,12:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Update appointment time for service combo and new date is on a holiday
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-10" and start time "10:00,12:00" at "2021-04-04+09:00"
    When "customer1" attempts to update the date to "2021-04-18" and start time to "10:00,12:00" at "2021-04-07+09:30"
    Then the appointment shall be booked
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-10" with start time "10:00,12:00" and end time "11:15,12:10"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer arrives to the appointment and adds an optional service to the service combo and new time overlaps an existing appointment
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-08" and start time "11:20,12:40" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-08+11:20"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "14:20" in the appointment at "2021-04-07+11:21"
    Then the appointment shall be in progress
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-08" with start time "11:20,12:40" and end time "12:35,12:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer arrives to the appointment and adds an optional service to the service combo and new time overlaps with another combo item booking
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-08" and start time "11:20,12:40" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-08+11:20"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "12:40" in the appointment at "2021-04-07+11:21"
    Then the appointment shall be in progress
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-08" with start time "11:20,12:40" and end time "12:35,12:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer arrives to the appointment and adds an optional service to the service combo and new time is outside business hours
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-08" and start time "15:20,16:40" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-08+15:20"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "16:55" in the appointment at "2021-04-08+15:21"
    Then the appointment shall be in progress
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-08" with start time "15:20,16:40" and end time "16:35,16:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer arrives to the appointment and adds an optional service to the service combo and new time overlaps vacation
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-14" and start time "10:00,11:40" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-14+10:02"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "11:55" in the appointment at "2021-04-14+10:05"
    Then the appointment shall be in progress
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-14" with start time "10:00,11:40" and end time "11:15,11:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments

  Scenario: Customer arrives to the appointment and adds an optional service to the service combo and new time overlaps holiday
    When "customer1" makes a "transmission-check-combo" appointment with service "electronics-repair" for the date "2021-04-18" and start time "08:00,09:40" at "2021-04-04+09:00"
    When the owner starts the appointment at "2021-04-18+08:03"
    When "customer1" attempts to add the optional service "tire-change" to the service combo with start time "09:55" in the appointment at "2021-04-18+08:10"
    Then the appointment shall be in progress
    Then the service combo in the appointment shall be "transmission-check-combo"
    Then the service combo shall have "transmission-check,electronics-repair" selected services
    Then the appointment shall be for the date "2021-04-18" with start time "08:00,09:40" and end time "09:15,09:50"
    Then the username associated with the appointment shall be "customer1"
    Then the user "customer1" shall have 0 no-show records
    Then the system shall have 2 appointments
