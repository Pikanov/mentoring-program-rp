package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String TEST_DATA_PROPERTIES = "testdata.properties";

    private static ConfigUtil configInstance;
    private static ConfigUtil testDataInstance;
    private final Properties properties;

    private ConfigUtil(final String propertiesFile) {
        properties = new Properties();
        loadProperties(propertiesFile);
    }

    private static ConfigUtil getConfigInstance() {
        if (configInstance == null) {
            configInstance = new ConfigUtil(CONFIG_PROPERTIES);
        }
        return configInstance;
    }

    private static ConfigUtil getTestDataInstance() {
        if (testDataInstance == null) {
            testDataInstance = new ConfigUtil(TEST_DATA_PROPERTIES);
        }
        return testDataInstance;
    }

    private void loadProperties(final String fileName) {
        try (InputStream stream = ConfigUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (stream == null) {
                throw new RuntimeException("File not found: " + fileName);
            }
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties file: " + fileName, e);
        }
    }

    public static String getConfigProperty(final String key) {
        return getConfigInstance().properties.getProperty(key);
    }

    public static String getTestDataProperty(final String key) {
        return getTestDataInstance().properties.getProperty(key);
    }
}
