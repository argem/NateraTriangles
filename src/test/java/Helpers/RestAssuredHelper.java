package Helpers;

import Figures.Triangle;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Map;

public class RestAssuredHelper {
    static String trianglePostfix = "/triangle";

    private static RequestSpecification getRequest() {
        RestAssured.baseURI = Constants.API_URL;
        RequestSpecification request = RestAssured.given();
        request.header("X-User", Constants.USER_TOKEN);
        request.contentType(ContentType.JSON);
        return request;
    }

    public static RequestSpecification getRequestWithoutToken() {
        RestAssured.baseURI = Constants.API_URL;
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        return request;
    }

    public static RequestSpecification getRequestWithToken(String token) {
        RestAssured.baseURI = Constants.API_URL;
        RequestSpecification request = RestAssured.given();
        request.header("X-User", token);
        request.contentType(ContentType.JSON);
        return request;
    }

    public static Response postTriangle(String separator, String[] sides) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("separator", separator);
        requestParams.put("input", String.join(separator, sides));
        RequestSpecification request = getRequest();
        request.body(requestParams.toJSONString());
        Response response = request.post(trianglePostfix);
        return response;
    }

    public static Response postTriangleWithoutSeparator(String separator, String[] sides) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("input", String.join(separator, sides));
        RequestSpecification request = getRequest();
        request.body(requestParams.toJSONString());
        Response response = request.post(trianglePostfix);
        return response;
    }

    public static Response postTriangle(RequestSpecification request, String[] sides) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("separator", ";");
        requestParams.put("input", String.join(";", sides));
        request.body(requestParams.toJSONString());
        Response response = request.post(trianglePostfix);
        return response;
    }

    public static Triangle[] getTriangles() {
        RequestSpecification request = getRequest();
        Response response = request.get(Constants.API_URL + "/triangle/all");
        Triangle[] triangles = new Gson().fromJson(response.getBody().print(), Triangle[].class);
        return triangles;
    }

    public static void deleteTriangle(String id) {
        RequestSpecification request = getRequest();
        Response response = request.delete(Constants.API_URL + "/triangle/" + id);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public static Triangle getTriangle(String id) {
        RequestSpecification request = getRequest();
        Response response = getTriangle(request, id);
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            return new Gson().fromJson(response.getBody().print(), Triangle.class);
        } else {
            return null;
        }

    }

    public static Response getTriangle(RequestSpecification request,String id) {
        return request.get(Constants.API_URL + "/triangle/" + id);
    }

    public static boolean hasTriangleSaved(String id) {
        Triangle triangle = getTriangle(id);
        return triangle != null;
    }

    public static double getTrianglePerimeter(String id) {
        RequestSpecification request = getRequest();
        Response response = request.get(Constants.API_URL + "/triangle/" + id + "/perimeter");
        Map<String, Double> area = new Gson().fromJson(response.getBody().print(), Map.class);
        return area.get("result");
    }

    public static double getTriangleArea(String id) {
        RequestSpecification request = getRequest();
        Response response = request.get(Constants.API_URL + "/triangle/" + id + "/area");
        Map<String, Double> area = new Gson().fromJson(response.getBody().print(), Map.class);
        return area.get("result");
    }

}
