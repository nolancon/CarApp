Feature:`To test the get endpoint by ID.
	
Background: Setup the base path.
	Given url 'http://localhost:8081'
	
Scenario: To get car with ID 1 and compare response.
    Given path '/api/cars/1'
    And request
    When method get # Send the get request
    Then status 200 # Send the get request
    And print response
    And match response == 
    """
  {
    "id": 1,
    "make": "Mercedes",
    "model": "E220",
    "year": 2020,
    "color": "Black"
  }
"""

Scenario: To get car with ID 8, which does not exist .
    Given path '/api/cars/8'
    And request
    When method get # Send the get request
    Then status 204 # Send the get request