@Regress

Feature: Authentication. Negative

  Background: Delete all triangles
    Given Delete all triangles


  Scenario: 1. Post triangle without authentication token
    Given Post triangle without authenticate token
    When Response contains 401 status code
    Then Count of saved triangles should be 0


  Scenario: 2. Post triangle with wrong authentication token
    Given Post triangle with wrong authenticate token
    When Response contains 401 status code
    Then Count of saved triangles should be 0