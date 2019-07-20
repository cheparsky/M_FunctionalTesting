@test
Feature: Positive scenarios

  Background:
    * call read('classpath:APITests/commonFunctions.feature')
    * url 'http://cafetownsend-angular-rails.herokuapp.com'

  Scenario: api-positive-001 Login test

    * path 'login'
    * params sessionParams
    * configure headers = sessionHeaders
    When request
    And method get
    Then status 200
    And match response.authorized == 'true'


  Scenario: api-positive-002 Employee list test

    * path 'employees'
    * configure headers = employeesHeaders
    When request
    And method get
    Then status 200
    And match response[0].id == '#notnull'