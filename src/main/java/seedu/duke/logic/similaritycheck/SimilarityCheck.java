package seedu.duke.logic.similaritycheck;

import seedu.duke.model.bus.BusStops;
import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Represents the similarity checker that provides the list of possible locations that have names similar to the one
 * entered by the user.
 */
public class SimilarityCheck {

    private static final double THRESHOLD = 0.60;

    /**
     * Returns all possible similar locations based on a threshold.
     *
     * @param userLoc the input location given by the user.
     * @return ArrayList of possible locations.
     */
    public static ArrayList<String> similarLoc(String userLoc) {
        ArrayList<String> possibleLocations = new ArrayList<>();
        for (BusStops info: EnumSet.allOf(BusStops.class)) {
            if (getSimilarity(info.getName().toLowerCase(), userLoc.toLowerCase()) > THRESHOLD) {
                possibleLocations.add(info.getName());
            }
        }
        return possibleLocations;
    }

    /**
     * Returns the similarity between 2 given location names.
     *
     * @param givenLoc the location name in the list of bus stops.
     * @param inputLoc the location entered by the user.
     * @return the similarity as a double value less than or equal to 1.
     */
    private static double getSimilarity(String givenLoc, String inputLoc) {
        if (givenLoc.length() > inputLoc.length()) {
            return 1 - editDistance(givenLoc, inputLoc) / (double) givenLoc.length();
        }

        return 1 - editDistance(inputLoc, givenLoc) / (double) inputLoc.length();
    }

    /**
     * Calculates the levenshtein distance between 2 given location names and returns the number od additions, deletions
     * or replacements to be made for both names to be the same.
     *
     * @param loc1 location with bigger length.
     * @param loc2 location with smaller length.
     * @return the levenshtein distance as an int.
     */
    //Source: http://rosettacode.org/wiki/Levenshtein_distance#Java
    private static int editDistance(String loc1, String loc2) {
        loc1 = loc1.toLowerCase();
        loc2 = loc2.toLowerCase();

        int [] costs = new int [loc2.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= loc1.length(); i++) {
            costs[0] = i;
            int before = i - 1;
            for (int j = 1; j <= loc2.length(); j++) {
                int now = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        loc1.charAt(i - 1) == loc2.charAt(j - 1) ? before : before + 1);
                before = costs[j];
                costs[j] = now;
            }
        }
        return costs[loc2.length()];
    }

}
