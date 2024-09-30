package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverCreator {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private DriverCreator() {

    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    private static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(Config.getProperties("headlessMode"))) {
            options.addArguments("--headless");
        }

        driver.set(new ChromeDriver(options));
        WebDriver webDriver = driver.get();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //TODO should be refactored
        wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(30)));
    }

    public static WebDriverWait getWait() {
        return wait.get();
    }

    public static void quitBrowser() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
            wait.remove();
        }
    }
}
