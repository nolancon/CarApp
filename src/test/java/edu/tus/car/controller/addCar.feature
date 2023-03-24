Feature: To test the post endpoint.
	
Background: Setup the base path.
	Given url 'http://localhost:8081'
	
Scenario: To successfully add a new car to the database.
	Given path '/api/cars'
	And request {"make": "BMW","model": "320","year": "2021","color": "Green"}
   	And headers {Accept : 'application/json', Content-Type: 'application/json'}
   	When method post
   	Then status 201

Scenario: To fail to add car with invalid model.
	Given path '/api/cars'
	And request {"make": "Audi","model": "A1","year": "2021","color": "Green"}
   	And headers {Accept : 'application/json', Content-Type: 'application/json'}
   	When method post
   	Then status 400
   	And response.errorMessage = "Invalid combination of make and model"

Scenario: To fail to add car with invalid make.
	Given path '/api/cars'
	And request {"make": "Ford","model": "320","year": "2021","color": "Green"}
   	And headers {Accept : 'application/json', Content-Type: 'application/json'}
   	When method post
   	Then status 400
   	And response.errorMessage = "Invalid combination of make and model"

Scenario: To fail to car with invalid year.
	Given path '/api/cars'
	And request {"make": "Mercedes","model": "E220","year": "2019","color": "Green"}
   	And headers {Accept : 'application/json', Content-Type: 'application/json'}
   	When method post
   	Then status 400
   	And response.errorMessage = "Cannot accept cars older that 2020" 
   	
Scenario: To fail to add a blue car.
	Given path '/api/cars'
	And request {"make": "Mercedes","model": "E220","year": "2022","color": "Blue"}
   	And headers {Accept : 'application/json', Content-Type: 'application/json'}
   	When method post
   	Then status 400
   	And response.errorMessage = "Only accepting Red, Green, Black or Silver cars" 