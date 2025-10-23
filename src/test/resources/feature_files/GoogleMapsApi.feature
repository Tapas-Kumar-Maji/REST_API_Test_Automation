Feature: Validating Google Maps Apis

	Background: 

	@AddPlace
	Scenario Outline: Validate AddPlaceApi
		Given User has AddPlaceApi payload with "<Name>", "<Language>", "<Address>"
		When User calls "AddPlace" API using "post" http request
		Then The "AddPlace" Api response is success with status code 200
		And "status" field in response body is "OK"
		And "scope" field in response body is "APP"
		And Verify place_id maps to "<Name>" using "GetPlace" API
		
	Examples:
	|	Name		|	Language	|	Address	|
	|	Sofia Nix	|	Spanish		|	Medeline, Columbia	|
	|	Juliana Vega|	Espanol		|	Mexico City, Mexico	|
	
	
	@DeletePlace
	Scenario: Validate DeletePlaceApi
		Given User has DeletePlaceApi payload
		When User calls "DeletePlace" API using "post" http request
		Then The "DeletePlace" Api response is success with status code 200
		And "status" field in response body is "OK"
		