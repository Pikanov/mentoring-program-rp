package attributes.definitions;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import util.ConfigUtil;
import util.DriverCreator;
import util.LoggingUtil;

public class Hooks {
    private static final String BASE_URL = ConfigUtil.getConfigProperty("host");
    private final TestContext testContext;

    public Hooks() {
        this.testContext = new TestContext();
    }

    @Before
    public void setUp(Scenario scenario) {
        LoggingUtil.log("Starting scenario: " + scenario.getName());
        testContext.setCurrentUser(null);
        DriverCreator.getDriver().navigate().to(BASE_URL);
        LoggingUtil.log("WebDriver initialized and navigated to: " + BASE_URL);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggingUtil.log("Scenario FAILED: " + scenario.getName());
        } else {
            LoggingUtil.log("Scenario PASSED: " + scenario.getName());
        }
        DriverCreator.quitBrowser();
        LoggingUtil.log("Thread resources closed");
    }
}