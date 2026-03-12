package authmanager;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.PropReader;

import static io.restassured.RestAssured.*;
public class TokenGenerator {
	
	static String accesstokenvalue;
	static Instant expiry_time;
	
	public static String getToken() throws IOException
	{
		if(accesstokenvalue==null||Instant.now().isAfter(expiry_time))
		{
			System.out.println("Renewing the token !!!");
			Response resp = renewToken();
			JsonPath jp = resp.jsonPath();
			 accesstokenvalue = jp.getString("access_token");
			System.out.println(accesstokenvalue);
			
			int expirytimeinseconds = jp.getInt("expires_in");
			expiry_time=Instant.now().plusSeconds(expirytimeinseconds-300);
			}
		else
		{
			System.out.println("Token is good to use and need not be generated");
		}
		
		return accesstokenvalue;
	}
	

public static Response renewToken() throws IOException
{
	HashMap<String,String> hp=new HashMap<String,String>();
	hp.put("grant_type", "refresh_token");
	hp.put("refresh_token",PropReader.readPropFile("refresh_token"));
	hp.put("client_id", PropReader.readPropFile("client_id"));
	hp.put("client_secret", PropReader.readPropFile("client_secret"));
	
	baseURI="https://accounts.spotify.com";
	
	Response response = given()
	.header("Content-Type","application/x-www-form-urlencoded")
	.formParams(hp)
	.log().all()
	.when()
	.post("api/token")
	.then()
	.log().all()
	.extract()
	.response();
	
	if(response.statusCode()!=200)
	{
		throw new RuntimeException("Token generated API got failed...");
	}
	return response;
	
	}

public static void main(String[] args) throws IOException
{
	TokenGenerator.renewToken();
	
}
}
