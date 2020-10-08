package seedu.duke;

public class Ui {

    /**
     * Greets new user upon opening up application.
     */
    public void greetFirstTime() {
        System.out.println("Welcome to AniChan!");
        System.lineSeparator();
        System.out.println("Let's get you started.");
        System.out.println("Please enter your particulars:");
    }

    /**
     * Greets existing user upon opening up application.
     */
    public void greetExisting(UserProfile userProfile) {
        String userName = userProfile.toString();
        System.out.println("Welcome Back, " + userName);
        System.out.println();
    }

    /**
     * Prints out available commands.
     */
    public void showMainMenu() {
        System.out.println("1. Add a profile (addprofile)");
        System.out.println("2. Edit a profile (editprofile)");
        System.out.println("3. Browse through all anime (browse)");
        System.out.println("4. Create a watchlist (watchlist)");
        System.out.println("5. Add to watchlist (add)");
        System.out.println("6. Bookmark an anime (bookmark)");
        System.out.println("7. Help (help)");
        System.out.println("8. Exit (exit)");
        System.out.println();
    }

    /**
     * Prints out a line telling a user that
     * an invalid command was given.
     */
    public void showInvalidCommand() {
        System.out.println("Please enter a valid command!");
        System.out.println();
    }

    /**
     * Prints out bye message.
     */
    public void bye() {
        System.out.println("Sayonara!");
    }
}
