@Regress

Feature: Positive tests

  Background: Delete all triangles
    Given Delete all triangles


  Scenario: 1. Test triangle adding
    Given Post triangle with sides 1, 1, 1
    Then The triangle has been saved


  Scenario Outline: 2. Test triangle perimeter
    Given Post triangle with sides <firstSide>, <secondSide>, <thirdSide>
    When The triangle has been saved
    Then The triangle has correct perimeter value

    Examples:
      | firstSide | secondSide | thirdSide |
      | 1         | 1          | 1         |
      | 1.5       | 1.5        | 1.5       |
      | 99999999  | 99999999   | 99999999  |
      | 3         | 3          | 2         |
      | 3         | 3          | 5         |
      | 3         | 4          | 5         |


  Scenario Outline: 3. Test triangle area
    Given Post triangle with sides <firstSide>, <secondSide>, <thirdSide>
    When The triangle has been saved
    Then The triangle has correct area value

    Examples:
      | firstSide | secondSide | thirdSide |
      | 1         | 1          | 1         |
      | 1.5       | 1.5        | 1.5       |
      | 99999999  | 99999999   | 99999999  |
      | 3         | 3          | 2         |
      | 3         | 3          | 5         |
      | 3         | 4          | 5         |

  Scenario: 4. Test 10 triangle adding
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
    Then Count of saved triangles should be 10


  Scenario: 5. Post triangle with not default separator
    Given Post triangle with sides 1, 1, 1 using separator |
    When The triangle has been saved
    Then The triangle has correct perimeter value
    And The triangle has correct area value


  Scenario: 6. Post triangle without separator
    Given Post triangle with sides 1, 1, 1 without separator
    When The triangle has been saved
    Then The triangle has correct perimeter value
    And The triangle has correct area value


  Scenario: 7. Delete triangle
    Given Post triangle with sides 1, 1, 1
    When The triangle has been saved
    And Delete the triangle
    Then The triangle doesn't exist on server