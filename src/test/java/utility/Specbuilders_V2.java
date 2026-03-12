package utility;

import java.io.IOException;

import authmanager.TokenGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specbuilders_V2 {
	
	/*
	 * public static RequestSpecification reqSpec(String token) {
	 * //RequestSpecBuilder return new RequestSpecBuilder()
	 * .setBaseUri("https://api.spotify.com") .setBasePath("v1")
	 * .addHeader("Content-Type", "application/json") .addHeader("Authorization",
	 * "Bearer "+token) .log(LogDetail.ALL) .build();
	 * 
	 * }
	 */
	
	@Step
	public static RequestSpecification reqSpec() throws IOException
	{
	return new RequestSpecBuilder()

		.setBaseUri("https://api.spotify.com")
		.setBasePath("v1")
		.addHeader("Content-Type", "application/json")
		.addHeader("Authorization", "Bearer "+TokenGenerator.getToken())
		.log(LogDetail.ALL)
		.addFilter(new AllureRestAssured())
		.build();
	}
	
	@Step
	public static ResponseSpecification respSpec(int stscode)
			{
				//ResponseSpecBuilder
				return new ResponseSpecBuilder()
				.expectStatusCode(stscode)
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL)
				.build();
	}
	
}

