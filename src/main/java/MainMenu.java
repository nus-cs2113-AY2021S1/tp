import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class MainMenu extends Ui {
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    private static LocalDateTime now = LocalDateTime.now();
    private static String nowFormatted = dateTimeFormat.format(now);

    private static final String WELCOME_MESSAGE = LINE_DIVIDER + System.lineSeparator() +
            "Welcome to Study It! Your personal study assistant!" +
            "\n\n" +
            "Current date & time : " + nowFormatted +
            "\n\n" +
            "Let's get productive! :)" +
            "\n" +
            LINE_DIVIDER;


    public static void printWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }
}
