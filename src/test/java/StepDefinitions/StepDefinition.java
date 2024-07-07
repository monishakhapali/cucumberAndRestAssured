package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;


import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    ResponseSpecification resspec;
    RequestSpecification req;
    Response response;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        req=given().spec(requestSpecification())
            .body(TestDataBuild.Addplacepayload(name,language,address));

    }
    @When("User calls {string} using {string} http request")
    public void user_calls_using_http_request(String resource, String method) {
        //When is the API call is made
        //Then is validation
        //Given when then are broken up for reusability.

        APIResources resouceAPI = APIResources.valueOf(resource);

        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST")) {
            response = req.when().post(resouceAPI.getResource());
        }else if(method.equalsIgnoreCase("GET")) {
            response = req.when().get(resouceAPI.getResource());
        }

    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.getString(key),value);
    }
}
