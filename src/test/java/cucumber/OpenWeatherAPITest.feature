Feature: Weather API check

  Background:
    Given Read Weather API endpoint URL

  Scenario Outline: API check - Weather API
    And Compose query by adding "<parameter>" in "<value>" value
    And Request API
    Then Check that response status is "<status>"
    And Check fields in response "<expected_values>"

  Examples:
      | parameter | value | status | expected_values |
      | q | Zaragoza | 200 | weather |
      | q | Zaragoza,ES | 200 | Zaragoza |
      | id | 3104324 | 200 |3104324 |
      | lat;lon | 41.6521;-0.881 | 200 | 41.65 |
      | zip | 50001 | 200 | temp_min |