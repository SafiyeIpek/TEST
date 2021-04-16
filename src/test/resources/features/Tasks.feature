
@wip
Feature: Creating post


  Scenario Outline: User should be able to make a post
    Given the user logged in as "<Different Users>"
    When the user make a post
    Then the server should return "Created" response
    And response should be in "json" format
    Examples:
      | Different Users  |
      | Leanne Graham    |
      | Ervin Howell     |
      | Clementine Bauch |

  Scenario: User should be able to update the post with put request
    When the user update the post
    Then the server should return "OK" response
    And response should be in "json" format

  Scenario: User should be able to update the post with patch request
    When the user patch the post
    Then the server should return "OK" response
    And response should be in "json" format

  Scenario: User should be able to delete the post
    When the user delete the post
    Then the server should return "OK" response
    And response should be in "json" format

  Scenario: Sending requests to wrong endpoints
    When using wrong endpoints
    Then the server should return "Not Found" response


  Scenario: User should be able make comment
    When the user make the comment
    Then the server should return "Created" response
    And response should be in "json" format





