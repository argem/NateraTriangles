import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class RestAssuredHelper {
    static String trianglePostfix = "/triangle";

    public static Response getResponse(String separator, String[] sides){
        RestAssured.baseURI = Constants.API_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("separator", separator);
        requestParams.put("input", String.join(separator, sides));
        request.header("X-User", Constants.USER_TOKEN);
        request.contentType(ContentType.JSON);
        request.body(requestParams.toJSONString());
        return request.post(trianglePostfix);
    }

//    public Response getResponse(String separator, String[] sides){
//        RestAssured.baseURI = Constants.API_URL;
//        RequestSpecification request = RestAssured.given();
//        JSONObject requestParams = new JSONObject();
//        requestParams.put("separator", separator);
//        requestParams.put("input", String.join(separator, sides));
//        request.header("X-User", Constants.USER_TOKEN);
//        request.contentType(ContentType.JSON);
//        request.body(requestParams.toJSONString());
//        return request.post(trianglePostfix);
//    }
}
