package attributes;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = "com.epam.reportportal.cucumber.ScenarioReporter",
		features = "src/test/resources/features",
		glue = {"attributes.definitions", "context"}  // Додаємо пакет контексту
)
public class BasicRunTest extends AbstractTestNGCucumberTests {
}