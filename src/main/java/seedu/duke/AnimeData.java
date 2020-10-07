package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimeData {
    public ArrayList<Anime> animeList;

    public AnimeData(ArrayList<Anime> animeList) {
        this.animeList = animeList;
    }

    public AnimeData() {
        this.animeList = new ArrayList<>();
    }

    public Anime getAnime(Integer animeIndex) {
        return animeList.get(animeIndex);
    }

    public int getSize() {
        return animeList.size();
    }

    public void printAll() {
        for (Anime anime : animeList) {
            System.out.println("Name: " + anime.getAnimeName());
            System.out.println("Episodes: " + anime.getTotalEpisodes());
            System.out.println("Release Date:" + anime.getReleaseDateInString());
            System.out.println("Rating: " + anime.getRating());
            System.out.println("Genre: " + Arrays.toString(anime.getGenre()));
        }
    }
}
