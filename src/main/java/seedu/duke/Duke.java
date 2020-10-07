package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Watchlist> watchlists;
    private static Ui ui;


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui = new Ui();
        quickStart();
        addVoice();
        addAnime();

        watchlists = new ArrayList<>();
        createWatchlist("-n Anime-2020");      // Sample usage [Parameter to be updated to use user input]
        for (Watchlist watchlist : watchlists) {        // Verification of Watchlist content
            System.out.println(watchlist.toString());
        }

        createAnimeList();
        getCommand();
        ui.bye();
    }

    private static void quickStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello welcome to AniChan\n" + logo);

        Scanner input = new Scanner(System.in);

        System.out.println("What might your name be?");
        String name = input.nextLine();
        System.out.println("What might your dob be?");
        String dob = input.nextLine();
        System.out.println("What might your gender be?");
        String gender = input.nextLine();

        UserProfile newProfile = new UserProfile(name, dob, gender);
        System.out.println(newProfile);
    }

    /**
     * Prints the main menu of the application
     * and requests for command.
     *
     */
    private static void getCommand() {
        // Request for first command
        System.out.println("What would you like to do today?");
        Scanner input = new Scanner(System.in);

        while (true) {
            ui.showMainMenu();

            String fullCommand = input.nextLine();
            String[] fullCommandSplit = fullCommand.split(" ", 2);

            String description = "";
            String command = fullCommandSplit[0];
            if (fullCommandSplit.length > 1) {
                description = fullCommandSplit[1];
            }

            switch (command) {
            case "addprofile":
                addProfile(description);
                break;
            case "editprofile":
                editProfile(description);
                break;
            case "browse":
                browseAnime(description);
                break;
            case "watchlist":
                createWatchlist(description);
                break;
            case "add":
                addToWatchlist(description);
                break;
            case "bookmark":
                bookmarkAnime(description);
                break;
            case "help":
                showHelp();
                break;
            case "exit":
                return;
            default:
                ui.showInvalidCommand();
            }

        }
    }

    private static void addVoice() {
        VoiceActor yoshitsuguMatsuoka = new VoiceActor("Yoshitsugu Matsuoka");
        VoiceActor brycePapenbrook = new VoiceActor("Bryce Papenbrook");
        Character kirito = new Character("Kirito");
        Character somaYukihira = new Character("Soma Yukihira");

        // Many to many relationship
        yoshitsuguMatsuoka.addCharacter(kirito); // Japanese va
        yoshitsuguMatsuoka.addCharacter(somaYukihira);
        brycePapenbrook.addCharacter(kirito); // English va

        kirito.addVoiceActor(yoshitsuguMatsuoka);
        kirito.addVoiceActor(brycePapenbrook);
        somaYukihira.addVoiceActor(yoshitsuguMatsuoka);

        kirito.printVoiceActors();
        yoshitsuguMatsuoka.printCharacters();
    }

    // Sample usage of Anime Class [To Be Deleted]
    private static void addAnime() {
        System.out.println("===Running Sample Anime Class===");
        String[] releaseDate = {"2020", "12", "30"};
        String[] genre = {"Science", "Action", "Dance"};
        String animeName = "Adventures of Adventurers";
        int rating = 65;
        int avgEpisodeLength = 30;
        int totalEpisodes = 24;
        Anime testAnime = new Anime(animeName, releaseDate, rating, genre, avgEpisodeLength, totalEpisodes);
        System.out.println("Release Date in String: " + testAnime.getReleaseDateInString());
        System.out.println("===End of Sample Anime Class===");
    }

    private static void createWatchlist(String description) {
        String[] descriptionSplit = description.split(" ", 2);
        String commandOption = descriptionSplit[0];
        String watchlistName = descriptionSplit[1];
        Watchlist newWatchlist = new Watchlist(watchlistName);
        watchlists.add(newWatchlist);
    }

    /**
     * Adds a new user profile.
     */
    private static void addProfile(String description) {
        // Code to be added

        // Print for testing
        System.out.println("New profile added!");
    }

    /**
     * Edits an existing user profile.
     */
    private static void editProfile(String description) {
        // Code to be added

        // Print for testing
        System.out.println("Profile edited!");
    }

    /**
     * Browses the list of anime.
     */
    private static void browseAnime(String description) {
        // Code to be added

        // Print for testing
        System.out.println("Anime browsed!");
    }

    /**
     * Adds an anime to current watchlist.
     */
    private static void addToWatchlist(String description) {
        // Code to be added

        // Print for testing
        System.out.println("Anime added");
    }

    /**
     * Bookmarks an anime.
     */
    private static void bookmarkAnime(String description) {
        // Code to be added

        // Print for testing
        System.out.println("Anime bookmarked");
    }

    /**
     * Shows help function.
     */
    private static void showHelp() {
        // Code to be added

        // Print for testing
        System.out.println("Help showed");
    }

    //Sample Usage of AnimeList Class [To Be Deleted]
    private static void createAnimeList() {
        System.out.println("===Running Sample Anime List Class===");
        ArrayList<Anime> animeStorageList = new ArrayList<>();
        String[] releaseDate = {"2020", "12", "30"};
        String[] genre = {"Science", "Action", "Dance"};
        String animeName = "Adventures of Adventurers";
        int rating = 65;
        int avgEpisodeLength = 30;
        int totalEpisodes = 24;
        Anime testAnime = new Anime(animeName, releaseDate, rating, genre, avgEpisodeLength, totalEpisodes);

        String[] releaseDate2 = {"1997", "4", "15"};
        String[] genre2 = {"Adventure", "Action", "Dance"};
        String animeName2 = "Actions in Action";
        int rating2 = 80;
        int avgEpisodeLength2 = 20;
        int totalEpisodes2 = 13;
        Anime testAnime2 = new Anime(animeName2, releaseDate2, rating2, genre2, avgEpisodeLength2, totalEpisodes2);

        animeStorageList.add(testAnime);
        animeStorageList.add(testAnime2);

        AnimeData animeData = new AnimeData(animeStorageList);
        animeData.printAll();

        System.out.println("===End of Sample Anime List Class===");
    }
}
