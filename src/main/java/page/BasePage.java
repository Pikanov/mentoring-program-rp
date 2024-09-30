package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverCreator;
import util.LoggingUtil;

import java.time.Duration;

public class BasePage {
    private final WebDriver driver = DriverCreator.getDriver();
    private final WebDriverWait wait = DriverCreator.getWait();

    public BasePage() {
        waitForPageLoad();
        initElements();
    }

    protected void initElements() {
        PageFactory.initElements(driver, this);
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }

    public void waitForElementToAppear(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            LoggingUtil.log("Element doesn't appear");
        }
    }
}