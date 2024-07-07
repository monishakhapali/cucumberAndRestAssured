Feature: Validating Place APIs
  Scenario Outline: Verify if the place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" using "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
   # We want these 3 aspects of the payload to come dynamically
  Examples:
    |name    |language |address       |
    |Aahouse |English  |World center  |
    |bbhouse |Spanish  |Sea center  |

