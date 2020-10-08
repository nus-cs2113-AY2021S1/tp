package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimeData {
    public ArrayList<Anime> animeDataList;

    public AnimeData(ArrayList<Anime> animeDataList) {
        this.animeDataList = animeDataList;
    }

    public Anime getAnime(Integer animeIndex) throws IndexOutOfBoundsException, NullPointerException {
        try {
            return animeDataList.get(animeIndex);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        } catch (NullPointerException e) {
            throw e;
        }

    }

    public int getSize() {
        return animeDataList.size();
    }

    public void printAll() {
        int i = 1;
        for (Anime anime : animeDataList) {
            System.out.println("---------------------------------");
            System.out.println("Index: " + i);
            System.out.println("Name: " + anime.getAnimeName());
            System.out.println("Episodes: " + anime.getTotalEpisodes());
            System.out.println("Release Date:" + anime.getReleaseDateInString());
            System.out.println("Rating: " + anime.getRating());
            System.out.println("Genre: " + Arrays.toString(anime.getGenre()));
            i += 1;
        }
    }
}
