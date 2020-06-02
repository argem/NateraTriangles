package stepdefinitions;

import Helpers.Constants;
import Helpers.RestAssuredHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthenticationSteps {
    Response response;

    @Given("Post triangle without authenticate token")
    public void postTriangleWithoutToken() {
        String[] sides = {"1", "1", "1"};
        RequestSpecification request = RestAssuredHelper.getRequestWithoutToken();
        response = RestAssuredHelper.postTriangle(request, sides);
    }

    @Given("Post triangle with wrong authenticate token")
    public void postTriangleWithWrongToken() {
        String[] sides = {"1", "1", "1"};
        RequestSpecification request = RestAssuredHelper.getRequestWithToken("wrongTokenIsHere");
        response = RestAssuredHelper.postTriangle(request, sides);
    }

    @And("Response contains {int} status code")
    public void responseContainsStatusCode(int code) {
        int statusCode = response.getStatusCode();
        assert statusCode == code : "Response status is '" + statusCode + "'. But expected '" + code + "'. Error message '" + response.body().asString() + "'";
    }

    @And("Get not existing triangle")
    public void getNotExistingTriangle() {
        RequestSpecification request = RestAssuredHelper.getRequestWithToken(Constants.USER_TOKEN);
        response = RestAssuredHelper.getTriangle(request, "notExistingTriangle");
    }

}
