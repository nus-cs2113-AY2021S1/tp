package anichan.anime;

import anichan.exception.AniException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages the all anime data
 */
public class AnimeData {
    private ArrayList<Anime> animeDataList;


    public AnimeData(ArrayList<Anime> animeDataList) {
        Anime.setTotalAnime(0);
        this.animeDataList = animeDataList;
    }

    public AnimeData() throws AniException {
        Anime.setTotalAnime(0);
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

    public String returnAnimeInfo(int animeIndex) {
        Anime anime = animeDataList.get(animeIndex);
        StringBuilder result = new StringBuilder();

        result.append("Index: " + (animeIndex + 1));
        result.append(System.lineSeparator());
        result.append("Name: " + anime.getAnimeName());
        result.append(System.lineSeparator());
        result.append("Episodes: " + anime.getTotalEpisodes());
        result.append(System.lineSeparator());
        result.append("Release Date: " + anime.getReleaseDateInString());
        result.append(System.lineSeparator());
        result.append("Rating: " + anime.getRating());
        result.append(System.lineSeparator());
        result.append("Genre: " + Arrays.toString(anime.getGenre()));

        return result.toString();
    }

}
