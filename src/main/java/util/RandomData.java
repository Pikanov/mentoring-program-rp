package util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    // Константи для пояснення магічних чисел
    private static final int RANDOM_STRING_MIN_LENGTH = 5;
    private static final int RANDOM_STRING_MAX_LENGTH = 5;
    private static final int RANDOM_INTEGER_MIN_LENGTH = 5;
    private static final int RANDOM_INTEGER_MAX_LENGTH = 10;
    private static final int RANDOM_ALPHANUMERIC_LENGTH = 10;

    public String getRandomString() {
        return RandomStringUtils.randomAlphabetic(RANDOM_STRING_MIN_LENGTH, RANDOM_STRING_MAX_LENGTH);
    }

    public final String getRandomString(final String name) {
        return name + "_test";
    }

    public String getRandomInteger() {
        return RandomStringUtils.randomNumeric(RANDOM_INTEGER_MIN_LENGTH, RANDOM_INTEGER_MAX_LENGTH);
    }

    public String getRandomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(RANDOM_ALPHANUMERIC_LENGTH);
    }
}
