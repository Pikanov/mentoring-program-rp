package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Dashboard;
import util.LoggingUtil;

public class DashboardApiDictionary {
    private static final String BASE_ENDPOINT = "/api/v1/{projectName}/dashboard";
    private static final String DASHBOARD_BY_ID_ENDPOINT = BASE_ENDPOINT + "/{dashboardId}";

    private final RequestSpecification requestSpecification;

    public DashboardApiDictionary(final RequestSpecification specification) {
        this.requestSpecification = specification;
    }

    public Response getDashboard(final String projectName, final Long dashboardId) {
        LoggingUtil.log("Sending GET request to: " + DASHBOARD_BY_ID_ENDPOINT + " with projectName: "
                + projectName + " and dashboardId: " + dashboardId);

        Response response = requestSpecification
                .pathParam("projectName", projectName)
                .pathParam("dashboardId", dashboardId)
                .when()
                .get(DASHBOARD_BY_ID_ENDPOINT);

        LoggingUtil.log("Response: " + response.asString());
        return response;
    }

    public Response createDashboard(final String projectName, final Dashboard dashboard) {
        LoggingUtil.log("Sending POST request to: " + BASE_ENDPOINT + " with projectName: "
                + projectName + " and body: " + dashboard);

        Response response = requestSpecification
                .pathParam("projectName", projectName)
                .body(dashboard)
                .when()
                .post(BASE_ENDPOINT);

        LoggingUtil.log("Response: " + response.asString());
        return response;
    }

    public Response updateDashboard(final String projectName, final Long dashboardId, final Dashboard dashboard) {
        LoggingUtil.log("Sending PUT request to: " + DASHBOARD_BY_ID_ENDPOINT + " with projectName: "
                + projectName + ", dashboardId: " + dashboard.getId() + " and body: " + dashboard);

        Response response = requestSpecification
                .pathParam("projectName", projectName)
                .pathParam("dashboardId", dashboardId)
                .body(dashboard)
                .when()
                .put(DASHBOARD_BY_ID_ENDPOINT);

        LoggingUtil.log("Response: " + response.asString());
        return response;
    }

    public Response getAllDashboards(final String projectName) {
        LoggingUtil.log("Sending GET request to fetch all dashboards for project: " + projectName);

        Response response = requestSpecification.pathParam("projectName", projectName)
                .when()
                .get(BASE_ENDPOINT);

        LoggingUtil.log("Response: " + response.asString());
        return response;
    }

    public Response deleteDashboard(final String projectName, final Long dashboardId) {
        LoggingUtil.log("Sending DELETE request to: " + DASHBOARD_BY_ID_ENDPOINT + " with projectName: "
                + projectName + " and dashboardId: " + dashboardId);

        Response response = requestSpecification
                .pathParam("projectName", projectName)
                .pathParam("dashboardId", dashboardId)
                .when()
                .delete(DASHBOARD_BY_ID_ENDPOINT);

        LoggingUtil.log("Response: " + response.asString());
        return response;
    }
}
