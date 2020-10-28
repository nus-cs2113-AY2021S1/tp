package seedu.financeit.utils;

import java.util.Random;

public class RandomGenerator {

    //@@author Artemis-Hunt-reused
    //Reused from https://www.baeldung.com/java-random-string with minor additions

    /**
     * Generates a random string of MAX_LEN 50,
     * May possibly generate an invalid paramType e.g. /sd(&(f.
     *
     * @return Random string containing alphanumeric characters
     *         and standard special characters.
     */
    public static String generateRandomString() {
        final int MAX_LEN = 50;
        int lengthOfStringGenerated = (int) (Math.random() * MAX_LEN);
        int leftLimit = 32; // SPACE
        int rightLimit = 126; // '~'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(lengthOfStringGenerated);
        for (int i = 0; i < lengthOfStringGenerated; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public static double generateRandomDouble() {
        int firstDigit = (int) (Math.random() * 10);
        int multiplier = (int) Math.pow(1, firstDigit);
        return Math.random() * multiplier;
    }

    public static int generateRandomPositiveIntUntilLimit(int limit) {
        return (int) (Math.random() * limit);
    }
}
