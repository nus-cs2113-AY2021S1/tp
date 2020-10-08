package seedu.duke.utility;

public class Ui {

    public static void printLogo() {
        String logo = " __          __  _______ _____ _    _ _   _ ________   _________ \n"
                + " \\ \\        / /\\|__   __/ ____| |  | | \\ | |  ____\\ \\ / /__   __|\n"
                + "  \\ \\  /\\  / /  \\  | | | |    | |__| |  \\| | |__   \\ V /   | |   \n"
                + "   \\ \\/  \\/ / /\\ \\ | | | |    |  __  | . ` |  __|   > <    | |   \n"
                + "    \\  /\\  / ____ \\| | | |____| |  | | |\\  | |____ / . \\   | |   \n"
                + "     \\/  \\/_/    \\_\\_|  \\_____|_|  |_|_| \\_|______/_/ \\_\\  |_|   \n";

        System.out.println(logo);
    }

    public void hello() {
        printLine();
        printLogo();
        printLine();
        System.out.println("Welcome to WatchNext");
        System.out.println("Type 'help' to get started!\n");
    }

    private static void printLine() {
        System.out.println("________________________________________________________________________________");
    }

    public static void printByeMessage() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printHelp() {
        printLine();
        // TODO load from txt file if possible instead of writing out one whole chunk in the future
        printLogo();
        System.out.println("The following options are available:");
        System.out.println("`help` - Views help\n"
                + " \n"
                + "'add` - Adds a show\n"
                + " \n"
                + "`edit` - Edits your show details\n"
                + " \n"
                + "`rating` - Modifies rating of your show\n"
                + "\n"
                + "`list` - Displays all your shows in list\n"
                + "\n"
                + "`delete` - Deletes your show\n"
                + " \n"
                + "`deleterating` - Deletes rating of your show\n"
                + "\n"
                + "`changerating` - Changes rating of your show\n"
                + "\n"
                + "`save` - Saves your shows\n"
                + "\n"
                + "`bye` - Exits the program\n");
        printLine();
    }

    public static void printLineIcon() {
        System.out.println("Enter a command: ");
    }

    public static void printShowList(ShowList showList) {
        printLine();
        // Iterate through Hashmap of ShowList here...
    }

    public static void editShowRating(ShowList showList) {
        printLine();
        // Iterate through Hashmap of ShowList here...
    }

    public static void deleteShow(ShowList showList) {
        printLine();
        // Iterate through Hashmap of ShowList here...
    }
  
    public static void printIOException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_IO_EXCEPTION);
    }
}
