package anichan.anime;

import anichan.exception.AniException;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimeData {
    private ArrayList<Anime> animeDataList;
    //private ArrayList<Anime> animeOriginalDataList;

    public AnimeData(ArrayList<Anime> animeDataList) {
        Anime.setTotalAnime(0);
        this.animeDataList = animeDataList;
        //this.animeOriginalDataList = new ArrayList<>(this.animeDataList);
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

    public void printAll() {
        int i = 1;
        for (Anime anime : animeDataList) {
            System.out.println("---------------------------------");
            System.out.println("Index: " + i);
            System.out.println("Name: " + anime.getAnimeName());
            System.out.println("Episodes: " + anime.getTotalEpisodes());
            System.out.println("Release Date: " + anime.getReleaseDateInString());
            System.out.println("Rating: " + anime.getRating());
            System.out.println("Genre: " + Arrays.toString(anime.getGenre()));
            i += 1;
        }
    }

    public void printOne(int animeIndex) {
        Anime anime = animeDataList.get(animeIndex);
        System.out.println("---------------------------------");
        System.out.println("Index: " + animeIndex);
        System.out.println("Name: " + anime.getAnimeName());
        System.out.println("Episodes: " + anime.getTotalEpisodes());
        System.out.println("Release Date: " + anime.getReleaseDateInString());
        System.out.println("Rating: " + anime.getRating());
        System.out.println("Genre: " + Arrays.toString(anime.getGenre()));
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

    public ArrayList<Anime> findName(String description) {
        ArrayList<Anime> findList = new ArrayList<>();
        System.out.println("Looking for \"" + description + "\"");
        for (Anime anime : animeDataList) {
            if (anime.getAnimeName().contains(description)) {
                findList.add(anime);
            }
        }
        return findList;
    }
}
