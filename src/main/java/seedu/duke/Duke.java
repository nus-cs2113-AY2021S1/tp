package seedu.duke;

import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserProfile;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Duke {
    private static final String USER_PROFILE_FILE_NAME = "userprofile.txt";
    private static final String WATCHLIST_FILE_NAME = "watchlist.txt";

    private final Ui ui;
    private final Parser parser;
    private final Storage storage;
    private UserProfile userProfile;
    private final Watchlist currentWatchlist;
    private final ArrayList<Watchlist> watchlists;

    private boolean shouldExit = false;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(USER_PROFILE_FILE_NAME, WATCHLIST_FILE_NAME);

        ui.printWelcomeMessage();
        userProfile = storage.readUserProfileFile(ui);
        watchlists = storage.readWatchlistFile(ui);

        if (watchlists.isEmpty()) {
            currentWatchlist = new Watchlist("Default");
        } else {
            currentWatchlist = watchlists.get(0);
        }
    }

    public void run() {
        if (userProfile == null) {
            userProfile = quickStart();
        }

        while (!shouldExit) {
            String userInput = ui.readUserInput(userProfile.getFancyName(), currentWatchlist.getName());
            try {
                getCommand(userInput);
            } catch (AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    // TODO: Organize the methods below to their respective class.
    // TEMPORARY, REMOVE ALL BELOW WHEN DONE REFACTORING!

    private static AnimeData animeData;
    private static Bookmark bookmark;
    private static final Scanner CONSOLE = new Scanner(System.in);

    private UserProfile quickStart() {
        UserProfile userProfile = null;
        boolean profileMade = false;
        while (!profileMade) {
            try {
                userProfile = createProfile();
                profileMade = true;
                storage.writeUserProfileFile(ui, userProfile);
            } catch (ParseException e) {
                ui.printErrorMessage("Is your date in dd/MM/yyyy format?");
            } catch (AniException e) {
                e.printStackTrace();
            }
        }

        return userProfile;
    }

    private UserProfile createProfile() throws ParseException, AniException {
        ui.printMessage("What's your name?");
        String name = ui.readQuickStartInput();
        ui.printMessage("Hello " + name + "! What might your date of birth be?");
        String dob = ui.readQuickStartInput();
        ui.printMessage("What might your gender be? (Male/Female/Others)");
        String gender = ui.readQuickStartInput();

        UserProfile newProfile = new UserProfile(name, dob, gender);
        ui.printMessage(newProfile.toString());
        return newProfile;
    }

    /**
     * Prints the main menu of the application
     * and requests for command.
     */
    private void getCommand(String fullCommand) throws AniException {
        String[] fullCommandSplit = parser.parseUserInput(fullCommand);
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
            ui.printGoodbyeMessage();
            shouldExit = true;
            return;
        default:
            throw new AniException("Unknown command");
        }
    }

    // Sample usage of Anime Class [To Be Deleted]
    private void addAnime() {
        System.out.println("===Running Sample Anime Class===");
        String[] releaseDate = {"2020", "12", "30"};
        String[] genre = {"Science", "Action", "Dance"};
        String animeName = "Adventures of Adventurers";
        int animeID = 0;
        int rating = 65;
        int avgEpisodeLength = 30;
        int totalEpisodes = 24;
        Anime testAnime = new Anime(animeName, releaseDate,
                rating, genre, avgEpisodeLength, totalEpisodes);
        System.out.println("Release Date in String: " + testAnime.getReleaseDateInString());
        System.out.println("===End of Sample Anime Class===");
    }

    private void createWatchlist(String description) {
        String[] descriptionSplit = description.split(" ", 2);
        String commandOption = descriptionSplit[0];
        String watchlistName = descriptionSplit[1];

        if (commandOption.equals("-n")) {
            Watchlist newWatchlist = new Watchlist(watchlistName);
            watchlists.add(newWatchlist);
        }

        storage.writeWatchlistFile(ui, watchlists);
        ui.printMessage("Watchlist created successfully!");
    }

    /**
     * Adds a new user profile.
     */
    private void addProfile(String description) {
        // Code to be added

        // Print for testing
        System.out.println("New profile added!");
    }

    /**
     * Edits an existing user profile.
     */
    private void editProfile(String description) {
        // Code to be added

        // Print for testing
        System.out.println("Profile edited!");
    }

    /**
     * Browses the list of anime.
     */
    public void browseAnime(String description) {
        Scanner input = new Scanner(System.in);
        //Browse Feature (Bare and un-refactored)
        System.out.println("Browse Options:"
                + " 1 - Just Browse / 2 - List from (A-Z) / 3 - List from (Z-A) / 4 - Sort "
                + "Rating Ascending / 5 - Sort Rating Descending");

        ArrayList<Anime> usableList = animeData.animeDataList;
        switch (description.trim()) {
        case "1":
            break;
        case "2":
            usableList.sort(Comparator.comparing(Anime::getAnimeName));
            break;
        case "3":
            usableList.sort(Comparator.comparing(Anime::getAnimeName).reversed());
            break;
        case "4":
            usableList.sort(Comparator.comparing(Anime::getRating));
            break;
        case "5":
            usableList.sort(Comparator.comparing(Anime::getRating).reversed());
            break;
        default:
            System.out.println("Invalid Option!");
            return;
        }

        int browseIndex = 0;
        boolean isBrowsing = true;
        //Looping Mechanism
        System.out.println("Happy Browsing!");
        for (Anime anime : usableList) {
            if (!isBrowsing) {
                break;
            }
            browseIndex++;
            System.out.println("\t" + browseIndex + ". " + anime.getAnimeName());

            //Print 20 first then wait for user input
            if (browseIndex % 20 == 0) {
                while (true) {
                    System.out.println("Enter ID : More info on that series");
                    System.out.println("Enter 'c': Continue browsing");
                    System.out.println("Enter 'q': Stop browsing");
                    String browseOption = input.nextLine();

                    if (browseOption.matches("-?\\d+(\\.\\d+)?")) {
                        int indexObtained = Integer.parseInt(browseOption);
                        System.out.println("Requested List ID: " + indexObtained);
                        if (indexObtained > browseIndex || indexObtained < 1) {
                            System.out.println("Out of Range query!");
                        } else {
                            Anime browseAnime = usableList.get(indexObtained - 1);
                            System.out.println("ID          :   " + browseAnime.getAnimeID());
                            System.out.println("Title       :   " + browseAnime.getAnimeName());
                            System.out.println("Rating      :   " + browseAnime.getRating());
                            System.out.println("Episodes    :   " + browseAnime.getTotalEpisodes());
                            System.out.println("Release Date:   " + browseAnime.getReleaseDateInString());
                        }
                    }
                    if (browseOption.trim().equals("c")) {
                        break;
                    }
                    if (browseOption.trim().equals("q")) {
                        isBrowsing = false;
                        break;
                    }
                }
            }
        }
        System.out.println("Total results found: " + browseIndex);
        //Put the list back in its original form
        if (!description.trim().equals("1")) {
            usableList.sort(Comparator.comparing(Anime::getAnimeID));
        }
    }

    /**
     * Adds an anime to current watchlist.
     */
    private void addToWatchlist(String description) {
        String[] descriptionSplit = description.split(" ", 2);

        try {
            String commandOption = descriptionSplit[0];
            String animeName = descriptionSplit[1];

            if (commandOption.equals("-a") && animeName != null && !animeName.trim().isEmpty()) {
                // currently only adding anime to default watchlist before
                // implementing the selecting of watchlist
                Watchlist currentWatchlist = watchlists.get(0);
                currentWatchlist.addAnimeToList(animeName);
            } else {
                ui.showInvalidDescription("addToWatchlist");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showInvalidDescription("addToWatchlist");
        }
    }

    /**
     * Bookmarks an anime.
     */
    private void bookmarkAnime(String description) {
        if (description.contains(" ")) {
            String[] descriptionSplit = description.split(" ", 2);
            // Code to be added
            String commandOption = descriptionSplit[0];
            String commandArgument = descriptionSplit[1];
            if (commandOption.equals("-a")) {
                if (isInteger(commandArgument)) {
                    int animeDataListIndex = Integer.parseInt(commandArgument);
                    Anime anime = animeData.getAnimeByID(animeDataListIndex);
                    System.out.println("Saving " + anime.getAnimeID() + ". "
                            + anime.getAnimeName() + " " + " to bookmark.");
                    bookmark.addAnimeBookmark(anime.getAnimeName());
                } else {
                    ArrayList<Anime> findList = animeData.findName(commandArgument);
                    for (Anime anime : findList) {
                        System.out.println("\t" + anime.getAnimeID() + ". " + anime.getAnimeName());
                    }
                }
            } else if (commandOption.equals("-d")) {
                int bookmarkIndex = Integer.parseInt(commandArgument);
                String animeName = bookmark.getAnimeBookmarkByIndex(bookmarkIndex - 1);
                System.out.println("Removing " + animeName + "! :(");
                bookmark.removeAnimeBookmark(bookmarkIndex - 1);
            } else {
                int bookmarkIndex = Integer.parseInt(commandOption);
                String[] commandArgumentSplit = commandArgument.split(" ", 2);
                String commandOption2 = commandArgumentSplit[0];
                String commandArgument2 = commandArgumentSplit[1];
                if (commandOption2.equals("-e")) {
                    int episode = Integer.parseInt(commandArgument2);
                    bookmark.editAnimeBookmarkEpisode(bookmarkIndex - 1, episode);
                    String animeName = bookmark.getAnimeBookmarkByIndex(bookmarkIndex - 1);
                    System.out.println("Editing " + animeName + " to have " + episode + " episode");
                }
            }
        } else {
            if (description.equals("-l")) {
                String bookmarks = bookmark.animeListInString();
                System.out.println(bookmarks);
            }
        }
    }

    /**
     * Check if input string provided is a valid string.
     * False if string contains alpha, null and 0 length
     * @param str : String to check
     * @return
     */
    public boolean isInteger(String str) {
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Shows help function.
     */
    private void showHelp() {
        // Code to be added

        // Print for testing
        System.out.println("Help showed");
    }

    //Sample Usage of AnimeList Class [To Be Deleted]
    private void createAnimeList() {
        System.out.println("===Running Sample Anime List Class===");
        ArrayList<Anime> animeStorageList = new ArrayList<>();
        String[] releaseDate = {"2020", "12", "30"};
        String[] genre = {"Science", "Action", "Dance"};
        String animeName = "Adventures of Adventurers";
        int animeID = 1;
        int rating = 65;
        int avgEpisodeLength = 30;
        int totalEpisodes = 24;
        Anime testAnime = new Anime(animeName, releaseDate, rating, genre,
                avgEpisodeLength, totalEpisodes);

        String[] releaseDate2 = {"1997", "4", "15"};
        String[] genre2 = {"Adventure", "Action", "Dance"};
        String animeName2 = "Actions in Action";
        animeID = 2;
        int rating2 = 80;
        int avgEpisodeLength2 = 20;
        int totalEpisodes2 = 13;
        Anime testAnime2 = new Anime(animeName2, releaseDate2, rating2, genre2,
                avgEpisodeLength2, totalEpisodes2);

        animeStorageList.add(testAnime);
        animeStorageList.add(testAnime2);

        AnimeData animeData = new AnimeData(animeStorageList);
        animeData.printAll();

        System.out.println("===End of Sample Anime List Class===");
    }
}
