import io.restassured.response.Response;
import org.junit.Assert;

public class TriangleAPIStepsImplementation {
    public void postTriangle(int side1, int side2, int side3) {
        String[] sides = {"1", "1", "1"};

        Response response = RestAssuredHelper.getResponse(";", sides);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
