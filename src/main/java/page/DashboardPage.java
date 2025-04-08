package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage extends BasePage {

    private final SelenideElement title = $(By.xpath("//*[@title='All Dashboards']"));
    private final SelenideElement searchField = $(By.xpath("//*[@placeholder='Search by name']"));
    private final ElementsCollection dashboardsNameList =
            $$(By.xpath("//*[contains(@class,'dashboardTable__name')]"));

    public DashboardPage() {
        super();
    }

    public Boolean isTitlePresent() {
        waitForElementToAppear(title);
        return title.isDisplayed();
    }

    public void inputSearchField(final String name) {
        waitForElementToAppear(searchField);
        searchField.setValue(name).pressEnter();
    }

    public List<String> getDashboardNames() {
        waitForElementsToAppear(dashboardsNameList);
        return dashboardsNameList.texts();
    }
}
