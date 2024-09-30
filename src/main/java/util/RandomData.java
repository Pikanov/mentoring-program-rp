package util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public String getRandomString() {
        return RandomStringUtils.randomAlphabetic(5,5);
    }

    public final String getRandomString(final String name) {
        return name + "_test";
    }

    public String getRandomInteger() {
        return RandomStringUtils.randomNumeric(5,10);
    }

    public String getRandomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
