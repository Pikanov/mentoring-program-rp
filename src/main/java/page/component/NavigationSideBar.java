package page.component;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import page.BasePage;
import page.DashboardPage;
import page.LaunchesPage;

import static com.codeborne.selenide.Selenide.$;

public class NavigationSideBar extends BasePage {

    private static final String BASE_LOCATOR = "//*[contains(@class,'sidebarButton') and contains(text(),'%s')]";

    public <T> T navigateTo(final SelenideElement element, final Class<T> pageClass) {
        waitForElementToBeClickable(element);
        jsClick(element);
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Page not found: " + pageClass.getSimpleName(), e);
        }
    }


    public DashboardPage moveToDashboards() {
        SelenideElement dashboardsButton = $(By.xpath(String.format(BASE_LOCATOR, "Dashboards")));
        return navigateTo(dashboardsButton, DashboardPage.class);
    }

    public LaunchesPage moveToLaunches() {
        SelenideElement launchesButton = $(By.xpath(String.format(BASE_LOCATOR, "Launches")));
        return navigateTo(launchesButton, LaunchesPage.class);
    }
}
