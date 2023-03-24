Feature: To test the delete endpoint by ID.
	
Background: Setup the base path.
	Given url 'http://localhost:8081'
	
Scenario: To delete car with ID 1.
    Given path '/api/cars/1'
    And request
    When method delete # Send the delete request
    Then status 200 # Send the get request

Scenario: To delete car with ID 8, which does not exist .
    Given path '/api/cars/8'
    And request
    When method delete # Send the get request
    Then status 404 # Send the get request