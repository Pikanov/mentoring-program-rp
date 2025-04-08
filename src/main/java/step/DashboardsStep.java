package step;

import com.epam.reportportal.annotations.Step;
import page.DashboardPage;
import page.component.NavigationSideBar;

import java.util.List;

public class DashboardsStep {
    DashboardPage dashboardPage = new DashboardPage();

    @Step
    public void goToDashboards() {
        new NavigationSideBar().moveToDashboards();
    }

    @Step
    public Boolean verifyIsDashboardsPageLoaded() {
        return dashboardPage.isLoginPageLoaded() & dashboardPage.isTitlePresent();
    }

    @Step
    public void searchDashboardsByName(final String name) {
        dashboardPage.inputSearchField(name);
    }

    @Step
    public List<String> getDashboardNames() {
       return dashboardPage.getDashboardNames();
    }
}
