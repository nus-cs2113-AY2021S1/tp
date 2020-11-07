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
            return 1 - editDistance(givenLoc, inputLoc) / (double) givenLoc.length();
        }

        return 1 - editDistance(inputLoc, givenLoc) / (double) inputLoc.length();
    }

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
