package baseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.ConfigUtil;
import util.ListenerRP;
import util.LoggingUtil;
import api.DashboardApiDictionary;

@Listeners(ListenerRP.class)
public class BaseApiTest {
    private static final String BASE_URL = ConfigUtil.getConfigProperty("host");
    private static final String AUTH_ENDPOINT = "/uat/sso/oauth/token";

    protected RequestSpecification requestSpecification;
    protected DashboardApiDictionary dashboardApi;
    private String accessToken;

    @BeforeMethod
    public void setUp() {
        accessToken = getAccessToken();
        System.out.println("Access Token: " + accessToken);
        RestAssured.baseURI = BASE_URL;
        requestSpecification = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
        dashboardApi = new DashboardApiDictionary(requestSpecification);
    }

    private String getAccessToken() {
        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic dWk6dWltYW4=")
                .formParam("grant_type", "password")
                .formParam("username", ConfigUtil.getConfigProperty("username"))
                .formParam("password", ConfigUtil.getConfigProperty("password"))
                .when()
                .post(AUTH_ENDPOINT);

        if (response.statusCode() != 200) {
            LoggingUtil.log("Failed to get access token");
        }

        return response.jsonPath().getString("access_token");
    }
}