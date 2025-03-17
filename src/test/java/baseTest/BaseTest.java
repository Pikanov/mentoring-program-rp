package baseTest;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.ConfigUtil;
import util.ListenerRP;
import util.LoggingUtil;
import util.SelenideConfig;

@Listeners(ListenerRP.class)
public class BaseTest {
    private static final String BASE_URL = ConfigUtil.getConfigProperty("host");

    @BeforeMethod
    public void initDriver() {
        SelenideConfig.chromeDriverConfigSetup();
        LoggingUtil.log("Opening URL: " + BASE_URL);
        Selenide.open(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        Selenide.closeWebDriver();
        LoggingUtil.log("Browser closed");
    }
}