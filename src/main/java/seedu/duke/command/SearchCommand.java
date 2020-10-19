package seedu.duke.command;

import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.storage.StorageManager;

import java.util.Arrays;

public class SearchCommand extends Command {
    protected static final String ASSERT_SEARCH_TERM_EMPTY = "Empty Search String!";
    protected static final String ASSERT_SEARCH_TYPE_WRONG = "Search type out of bounds";
    protected static final String ID_HEADER = "[ID:";
    protected static final String ID_CLOSER = "] ";
    protected static final String NO_RESULTS_FOUND = "No results found!";

    protected String searchTerm;
    protected String result;
    protected String searchGenre;
    protected int searchType;


    public SearchCommand() {
        searchGenre = "";
        result = "";
        searchType = 0;
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        assert(!searchTerm.isBlank()) : ASSERT_SEARCH_TERM_EMPTY;
        assert(!searchGenre.isBlank()) : ASSERT_SEARCH_TERM_EMPTY;
        assert(searchType < 2) : ASSERT_SEARCH_TYPE_WRONG;

        switch (searchType) {
        case 0:
            searchForAnime(animeData);
            break;
        case 1:
            searchForGenre(animeData);
            break;
        }

        if (result.isEmpty()) {
           return NO_RESULTS_FOUND;
        }
        return result;
    }

    private void searchForGenre(AnimeData animeData) {
        for (Anime anime : animeData.getAnimeDataList()) {
            if (Arrays.asList(anime.getGenre()).contains(searchGenre)) {
                result += ID_HEADER + anime.getAnimeID() + ID_CLOSER + anime.getAnimeName() + System.lineSeparator();
            }
        }
    }

    private void searchForAnime(AnimeData animeData) {
        for (Anime anime : animeData.getAnimeDataList()) {
            if (anime.getAnimeName().toLowerCase().contains(searchTerm)) {
                result += ID_HEADER + anime.getAnimeID() + ID_CLOSER + anime.getAnimeName() + System.lineSeparator();
            }
        }
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
        this.searchType = 0;
    }

    public void setSearchGenre(String searchGenre) {
        this.searchGenre = searchGenre;
        this.searchType = 1;
    }
}
