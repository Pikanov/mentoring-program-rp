package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Base64;

public class LoggingUtil {

	private static final Logger LOGGER = LogManager.getLogger();

	private LoggingUtil() {

	}

	public static void log(String message) {
		LOGGER.info(message);
	}

	public static void log(File file, String message) {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
	}

	public static void log(byte[] bytes, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", Base64.getEncoder().encodeToString(bytes), message);
	}

	public static void logBase64(String base64, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
	}
}
