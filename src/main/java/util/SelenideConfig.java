package util;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class SelenideConfig {

    public static final int TIMEOUT = 10000;

    public static void chromeDriverConfigSetup() {
        String runMode = System.getProperty("runMode", System.getenv("RUN_MODE")
                != null ? System.getenv("RUN_MODE") : "local");
        String remoteUrl = System.getProperty("remoteUrl", System.getenv("REMOTE_URL")
                != null ? System.getenv("REMOTE_URL") : "http://localhost:4444/wd/hub");
        boolean headless = Boolean.parseBoolean(System.getProperty("headlessMode", System.getenv("HEADLESS_MODE")
                != null ? System.getenv("HEADLESS_MODE") : "false"));

        Configuration.browser = "chrome";
        Configuration.timeout = TIMEOUT;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        if (headless) {
            options.addArguments("--headless=new");
        }

        if ("remote".equalsIgnoreCase(runMode)) {
            Configuration.remote = remoteUrl;
            options.setCapability("browserName", "chrome");
        } else {
            WebDriverManager.chromedriver().setup();
        }

        Configuration.browserCapabilities = options;
    }
}
