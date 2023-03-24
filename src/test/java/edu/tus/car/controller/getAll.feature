Feature:`To test the get endpoint.
	
Background: Setup the base path.
	Given url 'http://localhost:8081'
	
Scenario: To get all the data from application in JSON format
    Given path '/api/cars'
    And request 
    When method get # Send the get request
    Then status 200 # Send the get request
    And print response
    And match response == 
    """
     [
  {
    "id": 1,
    "make": "Mercedes",
    "model": "E220",
    "year": 2020,
    "color": "Black"
  },
  {
    "id": 2,
    "make": "Volksvagen",
    "model": "Arteon",
    "year": 2021,
    "color": "Silver"
  },
  {
    "id": 3,
    "make": "Audi",
    "model": "A6",
    "year": 2021,
    "color": "Red"
  }
]
"""