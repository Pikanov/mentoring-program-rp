package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverCreator {
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> WAIT_THREAD_LOCAL = new ThreadLocal<>();

    private static final int IMPLICIT_WAIT_SECONDS = 30;
    private static final int EXPLICIT_WAIT_SECONDS = 30;

    private DriverCreator() {
    }

    public static WebDriver getDriver() {
        if (DRIVER_THREAD_LOCAL.get() == null) {
            initializeDriver();
        }
        return DRIVER_THREAD_LOCAL.get();
    }

    private static void initializeDriver() {
        String runMode = ConfigUtil.getConfigProperty("runMode");
        WebDriver webDriver;

        if ("remote".equalsIgnoreCase(runMode)) {
            webDriver = initializeRemoteDriver();
        } else {
            webDriver = initializeLocalDriver();
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));

        DRIVER_THREAD_LOCAL.set(webDriver);
        WAIT_THREAD_LOCAL.set(new WebDriverWait(webDriver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS)));
    }

    private static WebDriver initializeLocalDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(ConfigUtil.getConfigProperty("headlessMode"))) {
            options.addArguments("--headless");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver initializeRemoteDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        try {
            String remoteUrl = ConfigUtil.getConfigProperty("remoteUrl");
            return new RemoteWebDriver(new URL(remoteUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid remote URL: " + e.getMessage(), e);
        }
    }

    public static WebDriverWait getWait() {
        return WAIT_THREAD_LOCAL.get();
    }

    public static void quitBrowser() {
        WebDriver webDriver = DRIVER_THREAD_LOCAL.get();
        if (webDriver != null) {
            webDriver.quit();
            DRIVER_THREAD_LOCAL.remove();
            WAIT_THREAD_LOCAL.remove();
        }
    }
}
