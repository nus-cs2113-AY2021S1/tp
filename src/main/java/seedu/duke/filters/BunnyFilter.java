package seedu.duke.filters;

import seedu.duke.bunny.Bunny;
import seedu.duke.exceptions.MissingFilterOptionsException;
import seedu.duke.exceptions.MissingParamsException;
import seedu.duke.exceptions.NoFilteredItemsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.duke.constants.Tags.GENRE_TAG;
import static seedu.duke.constants.Tags.IDEA_TAG;
import static seedu.duke.parsers.Parsers.parseSingleCharacterTaggedParamsFromUserInput;
import static seedu.duke.ui.UI.printFilteredBunny;

public class BunnyFilter {
    public static void filterBunny(String userInput, ArrayList<Bunny> bunniesList)
            throws MissingFilterOptionsException, NoFilteredItemsException {
        // for returning filter options parsed from the user input
        HashMap<String, String> filterOptions = new HashMap<>();
        String componentUserInput;
        String ideaSearchTerm = "";
        String genreSearchTerm = "";

        // flags for filters
        boolean hasAtLeastOneFilterOption = false;
        boolean containsIdeaSearchTerm = false;
        boolean containsGenreSearchTerm = false;

        // parse filter command into segments
        try {
            parseSingleCharacterTaggedParamsFromUserInput(userInput, filterOptions);
        } catch (MissingParamsException e) {
            throw new MissingFilterOptionsException();
        }

        if (filterOptions.containsKey(IDEA_TAG)) {
            containsIdeaSearchTerm = true;
            ideaSearchTerm = filterOptions.get(IDEA_TAG).trim();
            hasAtLeastOneFilterOption = true;
        }

        if (filterOptions.containsKey(GENRE_TAG)) {
            containsGenreSearchTerm = true;
            genreSearchTerm = filterOptions.get(GENRE_TAG).trim();
            hasAtLeastOneFilterOption = true;
        }


        if (!hasAtLeastOneFilterOption) {
            throw new MissingFilterOptionsException();
        }

        int i;
        HashMap<Integer, Bunny> numberedBunny = new HashMap<>();
        Set<Map.Entry<Integer, Bunny>> entries = numberedBunny.entrySet();
        HashMap<Integer, Bunny> filteredBunny = new HashMap<>();

        // add all the numbered tasks to a hashmap
        for (i = 1; i <= bunniesList.size(); i++) {
            numberedBunny.put(i, bunniesList.get(i - 1));
        }

        // make filters final for filtering
        String finalIdeaSearchTerm = ideaSearchTerm;
        String finalGenreSearchTerm = genreSearchTerm;

        Stream<Map.Entry<Integer, Bunny>> entriesStream = entries.stream();
        entriesStream.filter(containsIdeaSearchTerm ? entry -> entry.getValue().getIdea().contains(finalIdeaSearchTerm)
                : entry -> true)
                .filter(containsGenreSearchTerm ? entry -> entry.getValue().getGenre().contains(finalGenreSearchTerm)
                : entry -> true)
                .forEach(entry -> filteredBunny.put(entry.getKey(), entry.getValue()));

        if (filteredBunny.isEmpty()) {
            throw new NoFilteredItemsException();
        }

        printFilteredBunny(bunniesList.size(),  filteredBunny);
    }
}
