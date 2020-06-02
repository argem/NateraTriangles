@Regress

Feature: Negative tests

  Background: Delete all triangles
    Given Delete all triangles


  Scenario: 1. Test 11 triangle adding
    Given Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    And Post triangle with sides 1, 1, 1
    Then Count of saved triangles should be 10


  Scenario Outline: 2. Test adding not triangles
    Given Post triangle with sides <firstSide>, <secondSide>, <thirdSide>
    When The triangle has not been saved

    Examples:
      | firstSide | secondSide | thirdSide |
      | 1         | 2          | 3         |
      | 15        | 15         | 30        |
      | 500000    | 500000     | 1000000   |
      | 1         | 2          | 4         |
      | 15        | 15         | 31        |
      | 500000    | 500000     | 1000001   |


  Scenario: 3. Test getting not existing triangle
    Given Get not existing triangle
    Then Response contains 404 status code