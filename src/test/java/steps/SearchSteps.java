package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.Specbuilders_V2;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import static io.restassured.RestAssured.*;


public class SearchSteps {
	RequestSpecification reqs;
	Response res;
	String nameofsong;
	
	@Given("Get a search song payload")
	public void get_a_search_song_payload(DataTable dataTable) throws IOException {
		List<Map<String, String>> data = dataTable.asMaps();
		Map<String, String> firstline = data.get(0);
		nameofsong = firstline.get("songname");
		String typevalue= firstline.get("type");
		String artistvalue= firstline.get("artist");
		reqs = given(Specbuilders_V2.reqSpec())
		.queryParams("q", nameofsong, "type", typevalue,"artist",artistvalue );
		
		
	}

	@When("user calls with GET request")
	public void user_calls_with_get_request() {
		 res = reqs.when()
		.get("search");
	    
	}

	@Then("API executes with status code {int}")
	public void api_executes_with_status_code(Integer int1) {
	    res.then()
	    .spec(Specbuilders_V2.respSpec(int1))
	    .assertThat()
	    .body(Matchers.containsString(nameofsong));
	    
	    
	}




}
