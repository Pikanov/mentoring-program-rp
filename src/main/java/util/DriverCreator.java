package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(Config.getProperties("headlessMode"))) {
            options.addArguments("--headless");
        }

        DRIVER_THREAD_LOCAL.set(new ChromeDriver(options));
        WebDriver webDriver = DRIVER_THREAD_LOCAL.get();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));

        WAIT_THREAD_LOCAL.set(new WebDriverWait(webDriver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS)));
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
