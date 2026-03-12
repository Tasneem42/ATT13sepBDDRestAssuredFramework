package steps;

import com.spotify.pojo.Playlist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.DateAndTimeProvider;
import utility.PropReader;
import utility.Specbuilders_V2;
import  static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.Assert;

public class PlaylistSteps {
	
	 RequestSpecification reqs;
	 Response resp;
	 static String playlistID;
	 
	@Given("Create palylist api payload")
	public void create_palylist_api_payload() throws IOException {
		
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName(PropReader.readPropFile("name")+DateAndTimeProvider.getCurrentDateAndTime());
		reqPlaylist.setDescription(PropReader.readPropFile("description"));
		reqPlaylist.setPublic(false);
		   reqs = given(Specbuilders_V2.reqSpec())
		
		         .body(reqPlaylist);
		
	}

	@When("user calls with POST http request for create playlist")
	public void user_calls_with_po_st_http_request_for_create_playlist() {
		
	 resp = reqs.when()
		.post("users/31czxpocnkz2ybdbifue2iwyzff4/playlists");
	    
	}

	@Then("API call executed with status code {int}")
	public void api_call_executed_with_status_code1(Integer stscode) {
		Playlist playlistObject = resp.as(Playlist.class);
		
		String playlistname = playlistObject.getName();
		resp.then()
		.body("name", Matchers.equalTo(playlistname))
		.spec(Specbuilders_V2.respSpec(stscode));
		 playlistID = playlistObject.getId();
		
	System.out.println(playlistID);

	}

	@Given("Get playlist api payload")
	public void get_playlist_api_payload() throws IOException {
		reqs=given(Specbuilders_V2.reqSpec())
		.pathParam("pid",playlistID);
		
	   
	}

	@When("user calls with GET http request")
	public void user_calls_with_get_http_request() {
		resp=reqs.when()
		.get("playlists/{pid}");
	   
	}
	@Then("API call executes with status code {int}")
	public void api_call_executes_with_status_code(Integer int1) {
		
		resp.then()
		.spec(Specbuilders_V2.respSpec(int1));
		Playlist palylistobj = resp.as(Playlist.class);
		
		String playlistidfromresponse = palylistobj.getId();
		Assert.assertEquals(playlistidfromresponse, playlistID);
	}
	  

	@Given("update playlist api payload")
	public void update_playlist_api_payload() throws IOException {
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName(PropReader.readPropFile("name")+"updated"+DateAndTimeProvider.getCurrentDateAndTime());
		reqPlaylist.setDescription(PropReader.readPropFile("description"));
		reqPlaylist.setPublic(false);
		reqs=given(Specbuilders_V2.reqSpec())
		.body(reqPlaylist)
		.pathParam("upid", playlistID);
	   
	}

	@When("user calls with PUT http request")
	public void user_calls_with_put_http_request() {
		resp=reqs.when()
		
		.put("playlists/{upid}");
		
	    
	}
	
	@Then("API call should execute with status code {int}")
	public void api_call_should_execute_with_status_code(Integer int1) {
		resp.then()
		.statusCode(int1);
	}
	
}
