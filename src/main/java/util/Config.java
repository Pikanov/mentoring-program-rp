package util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final static String CONFIG_PROPERTIES = "config.properties";
    private static Config config;
    private final Properties properties;

    private Config() {
        properties = new Properties();
        loadProperties(CONFIG_PROPERTIES);
    }

    private static Config getConf() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public void loadProperties(String fileName) {
        try (InputStream stream = Config.class.getClassLoader().getResourceAsStream(fileName)) {
            if (stream == null) {
                LoggingUtil.log("File not Found: " + fileName);
            }
            properties.load(stream);
        } catch (IOException e) {
            LoggingUtil.log("Error during file reading: " + fileName);
            throw new RuntimeException(e);
        }
    }

    public static String getProperties(String key) {
        return getConf().properties.getProperty(key);
    }
}

