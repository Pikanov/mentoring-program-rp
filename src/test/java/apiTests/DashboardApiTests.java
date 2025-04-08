package apiTests;

import baseTest.BaseApiTest;
import model.Dashboard;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RandomData;

public class DashboardApiTests extends BaseApiTest {

    @Test
    public void testCreateDashboard() {
        var projectName = "testproject";
        var dashboardData = Dashboard.builder()
                .name(RandomData.getRandomString())
                .description(RandomData.getRandomString())
                .build();

        var createDashboardResponse = dashboardApi.createDashboard(projectName, dashboardData);
        Assert.assertEquals(createDashboardResponse.statusCode(), HttpStatus.SC_CREATED,
                "Dashboard creation failed.");
        Assert.assertNotNull(createDashboardResponse.jsonPath().getString("id"),
                "Dashboard ID is null.");

        var getDashboardsList = dashboardApi.getAllDashboards(projectName)
                .jsonPath()
                .getList("content", Dashboard.class);
        Assert.assertNotNull(getDashboardsList, "Dashboard IDs list is null.");

        var getLastDashboardId = getDashboardsList.stream()
                .map(Dashboard::getId)
                .reduce((first, second) -> second)
                .orElseThrow(() -> new AssertionError("Failed to get last dashboard ID"));

        var deleteResponse = dashboardApi.deleteDashboard(projectName, getLastDashboardId);
        Assert.assertEquals(deleteResponse.statusCode(), HttpStatus.SC_OK, "Dashboard deletion failed.");
    }

    @Test
    public void testRequiredFieldValidationOfDashboardCreation() {
        var projectName = "testproject";
        var dashboardInvalidData = Dashboard.builder()
                .name(null)
                .description(null)
                .build();

        var response = dashboardApi.createDashboard(projectName, dashboardInvalidData);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST, "Dashboard created.");
    }

    @Test
    public void testUpdateDashboard() {
        String projectName = "testproject";
        var dashboardData = Dashboard.builder()
                .name(RandomData.getRandomString())
                .description(RandomData.getRandomString())
                .build();

        var updatedDashboardData = Dashboard.builder()
                .name(RandomData.getRandomString())
                .description(RandomData.getRandomString())
                .build();

        var createDashboardResponse = dashboardApi.createDashboard(projectName, dashboardData);
        Assert.assertEquals(createDashboardResponse.statusCode(), HttpStatus.SC_CREATED,
                "Dashboard creation failed.");
        Assert.assertNotNull(createDashboardResponse.jsonPath().getString("id"),
                "Dashboard ID is null.");

        var getDashboardList = dashboardApi.getAllDashboards(projectName)
                .jsonPath()
                .getList("content", Dashboard.class);
        Assert.assertNotNull(getDashboardList, "Dashboard IDs list is null.");

        var getLastCreatedDashboardId = getDashboardList.stream()
                .map(Dashboard::getId)
                .reduce((first, second) -> second)
                .orElseThrow(() -> new AssertionError("Failed to get last dashboard ID"));

        var updateDashboardResponse = dashboardApi.updateDashboard(projectName,
                getLastCreatedDashboardId, updatedDashboardData);
        Assert.assertEquals(updateDashboardResponse.statusCode(), HttpStatus.SC_OK, "Dashboard update failed.");

        var getUpdatedDashboard = dashboardApi.getDashboard(projectName, getLastCreatedDashboardId)
                .then()
                .extract()
                .as(Dashboard.class);
        Assert.assertEquals(getUpdatedDashboard.getName(), updatedDashboardData.getName(),
                "Dashboard name was not updated correctly.");
        Assert.assertEquals(getUpdatedDashboard.getDescription(), updatedDashboardData.getDescription(),
                "Dashboard description was not updated correctly.");
    }
}
