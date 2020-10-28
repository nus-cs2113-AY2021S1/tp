package userinterface;

import userinterface.Ui;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class MainMenu extends Ui {
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE d MMM yyyy");
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
    private static LocalDateTime currentDateTime = LocalDateTime.now();
    private static String currentDateFormatted = dateFormat.format(currentDateTime);
    private static String currentTimeFormatted = timeFormat.format(currentDateTime);

    private static final String WELCOME_MESSAGE = LINE_DIVIDER + System.lineSeparator()
            + "Welcome to Study It! Your personal study assistant!"
            + "\n\n"
            + "Today's date: " + currentDateFormatted
            + "\nThe time now is " + currentTimeFormatted
            + "\n\n"
            + "Let's get productive!"
            + "\n"
            + LINE_DIVIDER;

    public static void printWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }
}