package page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import util.ConfigUtil;
import util.LoggingUtil;

import java.time.Duration;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;

public class BasePage {

    private static final int DEFAULT_TIME_OUT = 30;
    private static final int POLLING_INTERVAL = 500;
    private static final long DEFAULT_WAIT = 3000;

    private JavascriptExecutor javascriptExecutor;

    public BasePage() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            this.javascriptExecutor = (JavascriptExecutor) Selenide.webdriver().driver().getWebDriver();
        }
    }

    public Boolean isLoginPageLoaded() {
        return Configuration.baseUrl.equals(ConfigUtil.getConfigProperty("host"));
    }

    public void waitForPageLoad(final SelenideElement body) {
        body.shouldBe(visible, Duration.ofSeconds(DEFAULT_TIME_OUT));
    }

    public void waitForElementToAppear(final SelenideElement element) {
        element.shouldBe(visible, Duration.ofSeconds(DEFAULT_TIME_OUT));
    }

    public void waitForElementsToAppear(final ElementsCollection elements) {
        Selenide.sleep(DEFAULT_WAIT);
        elements.first().shouldBe(visible, Duration.ofSeconds(DEFAULT_TIME_OUT));
    }

    public static boolean waitForElementToBeClickable(final SelenideElement element) {
        FluentWait<SelenideElement> wait = new FluentWait<>(element)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .ignoring(TimeoutException.class);

        try {
            return wait.until(el ->
                    el.is(visible)
                            && el.is(enabled)
                            && el.is(cssValue("cursor", "pointer"))
                            && el.is(interactable)
            );
        } catch (TimeoutException e) {
            LoggingUtil.log("Element: " + element + " is not clickable within the timeout period.");
            return false;
        }
    }

    public SelenideElement waitForElementWithFluentWait(final SelenideElement element) {
        FluentWait<SelenideElement> wait = new FluentWait<>(element)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .ignoring(TimeoutException.class);

        return wait.until(el -> el);
    }

    public void scrollToElement(final SelenideElement element) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isElementInView(final SelenideElement element) {
        return (Boolean) javascriptExecutor.executeScript(
                "var rect = arguments[0].getBoundingClientRect();"
                        + "return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= (window.innerHeight "
                        + "|| document.documentElement.clientHeight) "
                        + "&& rect.right <= (window.innerWidth "
                        + "|| document.documentElement.clientWidth));",
                element);
    }


    public void jsClick(final SelenideElement element) {
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    public void dragAndDropElement(final SelenideElement source, final SelenideElement target) {
        Actions actions = new Actions(Selenide.webdriver().driver().getWebDriver());
        actions.dragAndDrop(source.toWebElement(), target.toWebElement()).perform();
    }

}
