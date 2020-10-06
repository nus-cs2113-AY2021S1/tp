package seedu.duke.ui;

import seedu.duke.book.BookList;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;

import java.util.Scanner;

public class TextUi {
    private static final String LOGO = "________                __                .__  _____       \n"
            + "\\_____  \\  __ __  _____/  |_  ____   _____|__|/ ____\\__.__.\n"
            + " /  / \\  \\|  |  \\/  _ \\   __\\/ __ \\ /  ___/  \\   __<   |  |\n"
            + "/   \\_/.  \\  |  (  <_> )  | \\  ___/ \\___ \\|  ||  |  \\___  |\n"
            + "\\_____\\ \\_/____/ \\____/|__|  \\___  >____  >__||__|  / ____|\n"
            + "       \\__>                      \\/     \\/          \\/    ";

    private static final String WELCOME_MESSAGE = "Welcome to Quotesify!";
    private static final String GOODBYE_MESSAGE = "Have a nice day!";
    private static final String SUCCESSFUL_ADD = "Alright! It has been added!";

    private final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
    }

    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public String getUserCommand() {
        System.out.println("What would you like to do with Quotesify?");
        return in.nextLine().trim();
    }

    public void printSuccessfulAddCommand() {
        System.out.println(SUCCESSFUL_ADD);
    }

    public void printAllQuotes(QuoteList quotes) {
        System.out.println("Here are your quotes:");
        System.out.println(quotes);
    }
}