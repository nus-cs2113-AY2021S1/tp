package anichan.commands;

import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.logger.AniLogger;
import anichan.storage.StorageManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowseCommand extends Command {
    protected static final String ID_HEADER = " [Id: ";
    private static final String ID_CLOSER = "]";
    private static final String DOT_SPACE = ". ";
    private int sortType;
    private int order;
    private int page;
    private int indexToPrint;
    private int animePerPage;

    private static final int ANIME_PER_PAGE = 20;
    private static final int ID_SORT = 0;
    private static final int ORDER_DESCENDING = 0;
    private static final int NAME_SORT = 1;
    private static final int RATING_SORT = 2;
    private static final int RESET_SORT = 3;
    private static final int ORDER_ASCENDING = 1;

    private static final String LAST_ANIME_WARNING = "Printing Last Anime Series from source";
    private static final String BROWSE_PAGE_INDICATOR = "Browsing Page: ";
    private static final String OUT_OF_BOUND_PAGE_WARNING = "Getting page: Tried to start at index: ";
    private static final String OUT_OF_BOUND_PAGE_ERROR = "Invalid Page size!";
    private static final String ASSERT_SORT_TYPE = "sortType should be < 3";
    private static final String ASSERT_ORDER_TYPE = "order should be < 2";
    private static final String SORT_ID_DESCENDING = "Sorting by ID descending";
    private static final String SORT_ID_ASCENDING = "Sorting by ID ascending";
    private static final String SORT_NAME_ASCENDING = "Sorting by Name Ascending (A to Z)";
    private static final String SORT_NAME_DESCENDING = "Sorting by Name Descending (Z to A)";
    private static final String SORT_RATING_ASCENDING = "Sorting by Rating Ascending (low to high)";
    private static final String SORT_RATING_DESCENDING = "Sorting by Rating Descending (high to low)";

    private static final Logger LOGGER = AniLogger.getAniLogger(BrowseCommand.class.getName());

    public BrowseCommand() {
        this.sortType = ID_SORT;
        this.order = ORDER_ASCENDING;
        this.page = 1;
        this.indexToPrint = 0;
        animePerPage = ANIME_PER_PAGE;
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        ArrayList<Anime> usableList = animeData.getAnimeDataList();
        assert (sortType < 4) : ASSERT_SORT_TYPE;
        assert (order < 2) : ASSERT_ORDER_TYPE;
        sortBrowseList(usableList);
        String result = buildBrowseOutput(usableList);
        setSortType(RESET_SORT);
        sortBrowseList(usableList);
        return result;
    }

    private String buildBrowseOutput(ArrayList<Anime> usableList) throws AniException {
        if (indexToPrint >= usableList.size()) {
            LOGGER.log(Level.WARNING, OUT_OF_BOUND_PAGE_WARNING + indexToPrint);
            throw new AniException(OUT_OF_BOUND_PAGE_ERROR);
        }
        StringBuilder result = new StringBuilder();
        for (int i = indexToPrint; i < indexToPrint + animePerPage; i++) {
            Anime browseAnime = usableList.get(i);
            result.append((i + 1) + DOT_SPACE + browseAnime.getAnimeName());
            result.append(ID_HEADER + browseAnime.getAnimeID() + ID_CLOSER);
            result.append(System.lineSeparator());
            if (i + 1 >= usableList.size()) {
                LOGGER.log(Level.WARNING, LAST_ANIME_WARNING);
                break;
            }
        }
        result.append(BROWSE_PAGE_INDICATOR).append(page);
        return result.toString();
    }

    private void sortBrowseList(ArrayList<Anime> usableList) {
        if (sortType == ID_SORT && order == ORDER_DESCENDING) {
            LOGGER.log(Level.INFO, SORT_ID_DESCENDING);
            usableList.sort(Comparator.comparing(Anime::getAnimeID).reversed());
        } else if (sortType == NAME_SORT && order == ORDER_DESCENDING) {
            LOGGER.log(Level.INFO, SORT_NAME_ASCENDING);
            usableList.sort(Comparator.comparing(Anime::getAnimeName));
        } else if (sortType == NAME_SORT) {
            LOGGER.log(Level.INFO, SORT_NAME_DESCENDING);
            usableList.sort(Comparator.comparing(Anime::getAnimeName).reversed());
        } else if (sortType == RATING_SORT && order == ORDER_DESCENDING) {
            LOGGER.log(Level.INFO, SORT_RATING_ASCENDING);
            usableList.sort(Comparator.comparing(Anime::getRating));
        } else if (sortType == RATING_SORT) {
            LOGGER.log(Level.INFO, SORT_RATING_DESCENDING);
            usableList.sort(Comparator.comparing(Anime::getRating).reversed());
        } else if (sortType == RESET_SORT) {
            usableList.sort(Comparator.comparing(Anime::getAnimeID));
        } else {
            LOGGER.log(Level.INFO, SORT_ID_ASCENDING);
        }
    }

    public void setPage(int page) {
        this.page = Math.max(page, 1);
        indexToPrint = (page - 1) * getAnimePerPage();
    }

    public int getPage() {
        return page;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public int getSortType() {
        return sortType;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public int getAnimePerPage() {
        return animePerPage;
    }

    public void setAnimePerPage(int animePerPage) {
        this.animePerPage = animePerPage;
    }
}
