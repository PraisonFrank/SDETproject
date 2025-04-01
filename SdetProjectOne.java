package com.testing.sdetProjectOne;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SdetProjectOne{
	
	@Test
	public void headersTest() {
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification req = RestAssured.given();
		req.baseUri("https://api.openweathermap.org")
		   .basePath("/data/2.5/weather")
		   .queryParam("lat", "13.035781")
		   .queryParam("lon", "77.597008")
		   .queryParam("appid", "69a520feb6bee8f7570c7329d18607d9");

		Response res = req.get();
        
        // Extracting response body
        ResponseBody resBody = res.getBody();
        String responseString = resBody.asString();
        JsonPath js = resBody.jsonPath();  // JSON Path parsing

        // Print full response for debugging
        System.out.println("Full Response: " + responseString);

        // Extracting JSON values using JsonPath
        String country = js.getString("sys.country");
        String locationName = js.getString("name");
        Integer locationId = js.get("id");
        Integer timezone = js.get("timezone");
        String base = js.getString("base");

        // Validate Status code
        Assert.assertEquals(res.getStatusCode(), 200, "Status code is not 200");
        // Validate Country India
        Assert.assertEquals(country, "IN", "Country is not India");
        // Validate Location Name
        Assert.assertEquals(locationName, "Kanija Bhavan", "Location name is incorrect");
        // Validate Location ID
        Assert.assertEquals(locationId, 6695236, "Location ID is incorrect");
        // Validate Timezone
        Assert.assertEquals(timezone, 19800, "Timezone is incorrect");
        // Validate base
        Assert.assertEquals(base, "stations", "Base is incorrect");
    }
}

