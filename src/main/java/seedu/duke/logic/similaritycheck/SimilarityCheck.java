package seedu.duke.logic.similaritycheck;

import seedu.duke.model.bus.BusStops;
import java.util.ArrayList;
import java.util.EnumSet;

public class SimilarityCheck {

    private static final double THRESHOLD = 0.60;

    public static ArrayList<String> similarLoc(String userLoc) {
        ArrayList<String> possibleLocations = new ArrayList<>();
        for (BusStops info: EnumSet.allOf(BusStops.class)) {
            if (getSimilarity(info.getName().toLowerCase(), userLoc.toLowerCase()) > THRESHOLD) {
                possibleLocations.add(info.getName());
            }
        }
        return possibleLocations;
    }


    private static double getSimilarity(String givenLoc, String inputLoc) {
        if (givenLoc.length() > inputLoc.length()) {
            return (givenLoc.length() - editDistance(givenLoc, inputLoc)) / (double) givenLoc.length();
        }

        return (inputLoc.length() - editDistance(inputLoc, givenLoc)) / (double) inputLoc.length();
    }

    //Source: http://rosettacode.org/wiki/Levenshtein_distance#Java
    private static int editDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

}
