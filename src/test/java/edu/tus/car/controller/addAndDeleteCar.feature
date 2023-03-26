Feature: To test the add, get and post endpoints.
	
Background: Setup the base path.
	Given url 'http://localhost:8081'
	
Scenario: To successfully add and delete a new car in the database.
		* def bmw = []
		
		# Function to find the BMW
   	* def fun =
   	"""
   	function(element){
   		if (element.make == "BMW") karate.appendTo(bmw, element);
   		
   	}
   	"""
	# Get all cars in DB before adding BMW, there are 3
	Given path '/api/cars' 
  And request 
    When method get
    Then status 200
    * assert response.length == 3
	
		Given path '/api/cars' 
		And request {"make": "BMW","model": "320","year": "2021","color": "Green"}
   	And headers {Accept : 'application/json', Content-Type: 'application/json'}
   	When method post
   	Then status 201
   	
   	# Get all cars in after adding BMW, there are 4
   	Given path '/api/cars/'
    And request
    When method get
    Then status 200
    * assert response.length == 4
    # Find the id of the BMW from the response
    * karate.forEach(response, fun)
    And print bmw
    * assert bmw.length == 1
    * def id = bmw[0].id
    
    # Get the newly added BMW by its discovered id
    Given path '/api/cars/' + id
    And request
    When method get
    Then status 200

   	# Delete the BMW
   	Given path '/api/cars/' + id
    And request
    When method delete
    Then status 200
   	
   	# Get all cars after deletion, there are 3 
   	Given path '/api/cars'
    And request 
    When method get
    Then status 200
    * assert response.length == 3

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
  
Scenario: To delete car with ID 99, which does not exist .
 	Given path '/api/cars/99'
  And request
    When method delete
    Then status 404