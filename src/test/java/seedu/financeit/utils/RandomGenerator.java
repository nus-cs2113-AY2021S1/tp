package seedu.financeit.utils;

import java.util.Random;

public class RandomGenerator {

    //@@author Artemis-Hunt-reused
    //Reused from https://www.baeldung.com/java-random-string with minor additions
    private static final int MAX_LEN = 50;
    /**
     * Generates a random string of MAX_LEN 50,
     *
     * @return Random string containing alphanumeric characters
     *         and some special characters.
     */

    public static String generateRandomString() {
        int lengthOfStringGenerated = (int) (Math.random() * MAX_LEN) / 2;
        int[] leftLimit = new int[] {60, 32};
        int[] rightLimit = new int[] {126, 58};
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(lengthOfStringGenerated);
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < lengthOfStringGenerated; j++) {
                int randomLimitedInt = leftLimit[i] + (int)
                        (random.nextFloat() * (rightLimit[i] - leftLimit[i] + 1));
                buffer.append((char) randomLimitedInt);
            }
        }


        String generatedString = buffer.toString();
        return generatedString;
    }

    public static double generateRandomPositiveDouble() {
        int firstDigit = (int) (Math.random() * 10);
        int multiplier = (int) Math.pow(100, firstDigit % 3);
        return Math.random() * multiplier;
    }

    public static int generateRandomPositiveIntUntilLimit(int limit) {
        return (int) (Math.random() * limit);
    }

    public static int generateRandomDayOfMonth() {
        int dayOfMonth = generateRandomPositiveIntUntilLimit(31);
        while(dayOfMonth == 0) {
            dayOfMonth = generateRandomPositiveIntUntilLimit(31);
        }
        return dayOfMonth;
    }

}
