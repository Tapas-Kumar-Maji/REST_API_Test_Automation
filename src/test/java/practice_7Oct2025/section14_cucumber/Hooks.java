package practice_7Oct2025.section14_cucumber;

import java.io.FileNotFoundException;

import io.cucumber.java.Before;

public class Hooks {

    /**
     * If tags = "@DeletePlace" in Runner class, then this method runs.
     * 
     * @throws FileNotFoundException
     */
    @Before
     public void beforeEachScenario() throws FileNotFoundException {
	 
	 if (GoogleMapsApi_StepDefinition.placeId != null)
	     return;
	
	GoogleMapsApi_StepDefinition step = new GoogleMapsApi_StepDefinition();
	step.user_has_add_place_api_payload_with("Kira Mia", "English", "Nigga street");
	step.user_calls_using_http_request("AddPlace", "post");
	step.the_api_response_is_success_with_status_code("AddPlace", 200);
	GoogleMapsApi_StepDefinition.placeId =
		step
		.response
		.jsonPath()
		.getString("place_id");
	
     }
    
     /**
      * Deletes each newly added place after creation, if not deleted.
      */
     // @Before
     public void cleanUp() {

	 if (GoogleMapsApi_StepDefinition.placeId != null) {
	     GoogleMapsApi_StepDefinition step = new GoogleMapsApi_StepDefinition();
	     step.user_has_delete_place_api_payload();
	     step.user_calls_using_http_request("DeletePlace", "post");
	     step.the_api_response_is_success_with_status_code("DeletePlace", 200);
	     step.field_in_response_body_is("status", "OK");
	 }

     }
}
