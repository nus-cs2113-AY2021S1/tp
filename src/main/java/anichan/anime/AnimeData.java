package anichan.anime;

import anichan.exception.AniException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages the all anime data.
 */
public class AnimeData {
    private static final int RESET_ANIME_ID = 0;

    private static final String INDEX_HEADER = "Index: ";
    private static final String NAME_HEADER = "Name: ";
    private static final String EPISODES_HEADER = "Episodes: ";
    private static final String RELEASE_DATE_HEADER = "Release Date: ";
    private static final String RATING_HEADER = "Rating: ";
    private static final String GENRE_HEADER = "Genre: ";
    private ArrayList<Anime> animeDataList;


    public AnimeData(ArrayList<Anime> animeDataList) {
        Anime.setTotalAnime(RESET_ANIME_ID);
        this.animeDataList = animeDataList;
    }

    public AnimeData() throws AniException {
        Anime.setTotalAnime(RESET_ANIME_ID);
        AnimeStorage animeStorage = new AnimeStorage();
        loadAnimeData(animeStorage.readAnimeDatabase());
    }

    public void loadAnimeData(ArrayList<Anime> animeDataList) {
        this.animeDataList = animeDataList;
    }

    public Anime getAnime(Integer animeIndex) {
        return animeDataList.get(animeIndex);
    }

    public int getSize() {
        return animeDataList.size();
    }

    public ArrayList<Anime> getAnimeDataList() {
        return animeDataList;
    }

    /**
     * Builds and return anime information in a String format.
     *
     * @param animeIndex the anime index to return
     * @return anime information in a printable String format.
     */
    public String returnAnimeInfo(int animeIndex) {
        Anime anime = animeDataList.get(animeIndex);
        StringBuilder result = new StringBuilder();

        result.append(INDEX_HEADER + (animeIndex + 1));
        result.append(System.lineSeparator());
        result.append(NAME_HEADER + anime.getAnimeName());
        result.append(System.lineSeparator());
        result.append(EPISODES_HEADER + anime.getTotalEpisodes());
        result.append(System.lineSeparator());
        result.append(RELEASE_DATE_HEADER + anime.getReleaseDateInString());
        result.append(System.lineSeparator());
        result.append(RATING_HEADER + anime.getRating());
        result.append(System.lineSeparator());
        result.append(GENRE_HEADER + Arrays.toString(anime.getGenre()));

        return result.toString();
    }

}
