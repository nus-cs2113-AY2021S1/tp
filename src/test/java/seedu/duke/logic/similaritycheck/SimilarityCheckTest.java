package seedu.duke.logic.similaritycheck;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityCheckTest {

    @Test
    void similarLoc_oneSimilarLocation_returnsArraylist() {
        ArrayList<String> similarLocs = new ArrayList<>();
        similarLocs.add("Opp Kent Ridge MRT station");
        String userInput = "Opp kent Ridge mrT";
        ArrayList<String> returnedList = new ArrayList<>(SimilarityCheck.similarLoc(userInput));
        assertEquals(similarLocs, returnedList);
    }

    @Test
    void similarLoc_manySimilarLocations_returnsArraylist() {
        ArrayList<String> similarLocs = new ArrayList<>(Arrays.asList("University Town", "University Hall"));
        String userInput = "University";
        ArrayList<String> returnedList = new ArrayList<>(SimilarityCheck.similarLoc(userInput));
        assertEquals(similarLocs, returnedList);
    }

}