package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        addAnime();
    }

    //Sample Usage of Anime Class [To Be Deleted]
    private static void addAnime() {
        System.out.println("===Running Sample Anime Class===");
        String[] releaseDate = { "2020", "12", "30" };
        String[] genre = { "Science", "Action", "Dance" };
        String animeName = "Adventures of Adventurers";
        int rating = 65;
        int avgEpisodeLength = 30;
        int totalEpisodes = 24;
        Anime testAnime = new Anime(animeName, releaseDate, rating, genre, avgEpisodeLength, totalEpisodes);
        System.out.println("Release Date in String: " + testAnime.getReleaseDateInString());
        System.out.println("===End of Sample Anime Class===");
    }

}

