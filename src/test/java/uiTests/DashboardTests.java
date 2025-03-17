package uiTests;

import baseTest.BaseTest;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import step.DashboardsStep;
import step.LoginStep;
import util.ConfigUtil;

import java.util.List;

public class DashboardTests extends BaseTest {
    final LoginStep loginStep = new LoginStep();
    final DashboardsStep dashboardsStep = new DashboardsStep();

    @Test
    public void verifyIsSearchFieldReturnCorrectDashboard() {
        User user = User.builder().username(ConfigUtil.getConfigProperty("username"))
                .password(ConfigUtil.getConfigProperty("password"))
                .build();

        loginStep.login(user);
        dashboardsStep.goToDashboards();
        Assert.assertTrue(dashboardsStep.verifyIsDashboardsPageLoaded(), "Dashboards page is not loaded");

        dashboardsStep.searchDashboardsByName("test2");

        List<String> dashboardNames = dashboardsStep.getDashboardNames();
        Assert.assertEquals(dashboardNames.size(), 1, "Found more then one dashboard");
        Assert.assertEquals(dashboardNames.get(0), "test2", "Found dashboard isn't correct");
    }
}
