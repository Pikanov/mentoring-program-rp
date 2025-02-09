package util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TestDataReader {
    public static <T> List<T> getTestData(final String filePath, final Class<T[]> dataType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return List.of(objectMapper.readValue(new File(filePath), dataType));
        } catch (IOException e) {
            LoggingUtil.log("Error processing JSON data from file");
            return Collections.emptyList();
        }
    }
}
