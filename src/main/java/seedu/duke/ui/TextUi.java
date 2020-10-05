package seedu.duke.ui;

public class TextUi {
    private static final String LOGO = "________                __                .__  _____       \n"
            + "\\_____  \\  __ __  _____/  |_  ____   _____|__|/ ____\\__.__.\n"
            + " /  / \\  \\|  |  \\/  _ \\   __\\/ __ \\ /  ___/  \\   __<   |  |\n"
            + "/   \\_/.  \\  |  (  <_> )  | \\  ___/ \\___ \\|  ||  |  \\___  |\n"
            + "\\_____\\ \\_/____/ \\____/|__|  \\___  >____  >__||__|  / ____|\n"
            + "       \\__>                      \\/     \\/          \\/    ";

    private static final String WELCOME_MESSAGE = "Welcome to Quotesify!";
    private static final String GOODBYE_MESSAGE = "Have a nice day!";

    public void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
    }

    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }
}
