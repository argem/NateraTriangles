import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class TriangleTests {

    @Test
    public void verifyThatWeCanFindUnitedStatesOfAmericaUsingTheCodeUS() {
        String[] sides = {"1", "1", "1"};
        Response response = RestAssuredHelper.getResponse(";", sides);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");
//        RestAssured.config().getHeaderConfig().
        RestAssured.given().header("X-User", "b99cd455-a925-4295-8643-771b8a4bf1e4").
                queryParam("input", "3;4;5").
                when().post("https://qa-quiz.natera.com/triangle").

                then().assertThat().statusCode(200).
                and().body("RestResponse.result.name", is("United States of America"));
    }

}
