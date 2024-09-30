package baseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.Config;
import util.DriverCreator;
import util.LoggingUtil;

public class BaseTest {
    private static final String BASE_URL = Config.getProperties("host");

    @BeforeMethod
    public void initDriver() {
        DriverCreator.getDriver().navigate().to(BASE_URL);
        LoggingUtil.log("WebDriver initialized and navigate to: " + BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        DriverCreator.quitBrowser();
        LoggingUtil.log("Thread resources closed");
    }
}
