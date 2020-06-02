package stepdefinitions;

import Figures.Triangle;
import Helpers.RestAssuredHelper;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Arrays;


public class TriangleAPISteps {
    Triangle triangle;

    @Given("Post triangle with sides {double}, {double}, {double}")
    public void postTriangle(double side1, double side2, double side3) {
        String[] sides = {Double.toString(side1), Double.toString(side2), Double.toString(side3)};
        Response response = RestAssuredHelper.postTriangle(";", sides);
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            triangle = new Gson().fromJson(response.getBody().print(), Triangle.class);
        }
    }

    @Given("Post triangle with sides {double}, {double}, {double} using separator {word}")
    public void postTriangleUsingSeparator(double side1, double side2, double side3, String separator) {
        String[] sides = {Double.toString(side1), Double.toString(side2), Double.toString(side3)};
        Response response = RestAssuredHelper.postTriangle(separator, sides);
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            triangle = new Gson().fromJson(response.getBody().print(), Triangle.class);
        }
    }

    @Given("Post triangle with sides {double}, {double}, {double} without separator")
    public void postTriangleWithoutSeparator(double side1, double side2, double side3) {
        String[] sides = {Double.toString(side1), Double.toString(side2), Double.toString(side3)};
        Response response = RestAssuredHelper.postTriangleWithoutSeparator(";", sides);
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            triangle = new Gson().fromJson(response.getBody().print(), Triangle.class);
        }
    }

    @Given("Delete all triangles")
    public void deleteTriangles() {
        Triangle[] triangles = RestAssuredHelper.getTriangles();
        Arrays.stream(triangles).forEach(triangle -> RestAssuredHelper.deleteTriangle(triangle.getId()));
    }

    @When("Delete the triangle")
    public void deleteTheTriangle() {
        RestAssuredHelper.deleteTriangle(triangle.getId());
    }

    @When("The triangle doesn't exist on server")
    public void theTriangleDoesntExist() {
        Triangle savedTriangle = RestAssuredHelper.getTriangle(triangle.getId());
        assert savedTriangle == null : "The triagle with id '"+triangle.getId()+"' has been found on server";
    }

    @When("The triangle has been saved")
    public void triangleHasBeenSaved() {
        boolean saved;
        if (triangle == null) {
            saved = false;
        } else {
            saved = RestAssuredHelper.hasTriangleSaved(triangle.getId());
        }
        assert saved : "The triangle did not found on server";
    }

    @When("The triangle has not been saved")
    public void triangleHasNotBeenSaved() {
        Triangle[] triangles = RestAssuredHelper.getTriangles();
        Triangle foundTriangle = Arrays.stream(triangles).filter(savedTriangle -> savedTriangle.getId().equals(triangle.getId())).findAny().orElse(null);
        assert foundTriangle == null : "Triangle with id '" + foundTriangle.getId() + "' has been found";
    }

    @And("The triangle has correct perimeter value")
    public void triangleHasCorrectPerimeterValue() {
        double responsePerimeter = RestAssuredHelper.getTrianglePerimeter(triangle.getId());
        double trianglePerimeter = triangle.getPerimeter();
        assert trianglePerimeter == responsePerimeter : "The triangle has not correct perimeter value. Expected '" + trianglePerimeter + "',  actual '" + responsePerimeter + "'";
    }

    @And("The triangle has correct area value")
    public void triangleHasCorrectAreaValue() {
        double responseArea = RestAssuredHelper.getTriangleArea(triangle.getId());
        double triangleArea = triangle.getArea();
        assert triangleArea == responseArea : "The triangle has not correct area value. Expected '" + triangleArea + "',  actual '" + responseArea + "'";
    }

    @And("Count of saved triangles should be {int}")
    public void TrianglesCountShouldBe(int count) {
        Triangle[] triangles = RestAssuredHelper.getTriangles();
        assert count == triangles.length : "Count of saved triangles '" + triangles.length + "' doesn't eaqual to expected count '" + count + "'";
    }

    @And("Count of saved triangles shouldn't be {int}")
    public void TrianglesCountShouldntBe(int count) {
        Triangle[] triangles = RestAssuredHelper.getTriangles();
        assert count != triangles.length : "Count of saved triangles '" + triangles.length + "' doesn't eaqual to expected count '" + count + "'";
    }
}
