public class HelpMessage extends Ui {
    private static final String generalCommands = "Here are the general commands available:\n"
            + "help                 - prints out help message\n"
            + "location             - tells you your current mode\n"
            + "cd <mode index/name> - changes the program to the corresponding mode\n"
            + "exit                 - exit the program/mode you are currently at\n";

    private static final String currentModes = "These are the modes you can go to:\n"
            + "1 menu       - main menu\n"
            + "2 bookmark   - bookmark internet links\n"
            + "3 timetable  - plan your study schedule\n"
            + "4 academic   - track your academic details\n"
            + "5 flashcard  - flashcards to revise your study materials";

    private static final String academicCommands = "Here are the academic commands available:\n"
            + "add contact c/CONTACT  m/MOBILE  e/EMAIL  - adds a contact\n"
            + "check contact                             - prints the list of contact currently stored\n"
            + "add grade n/MODULE NAME  m/MC  g/GRADE    - adds a grade\n"
            + "check grade                               - prints the current CAP based on grade stored\n"
            + "list grade                                - prints the list of grades currently stored\n";


    private static final String bookmarkCommands = "Here are the bookmark commands available:\n"
            + "bm NUMBER     - changes mode from bookmark main into a category \n"
            + "add LINK      - add bookmark link into a specific category\n"
            + "rm NUMBER     - remove a bookmark link into a specific category\n"
            + "list          - prints the list of categories and respective list of links\n"
            + "back          - go back to bookmark main\n";




    public static void printHelpMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(generalCommands);
        System.lineSeparator();

        Mode currentMode = StudyIt.getCurrentMode();
        if (currentMode == Mode.BOOKMARK) {
            printBookmarkHelp();
        } else if (currentMode == Mode.TIMETABLE) {
            printTimetableHelp();
        } else if (currentMode == Mode.ACADEMIC) {
            printAcademicHelp();
        } else if (currentMode == Mode.FLASHCARD) {
            printFlashcardHelp();
        }

        System.lineSeparator();
        System.out.println(currentModes);
        System.out.println(LINE_DIVIDER);
    }

    //TODO: Fill this up after you're done
    public static void printBookmarkHelp() {
        System.out.println(bookmarkCommands);
    }

    //TODO: Fill this up after you're done
    public static void printTimetableHelp() {
    }

    //TODO: Fill this up after you're done
    public static void printAcademicHelp() {
        System.out.println(academicCommands);
    }

    //TODO: Fill this up after you're done
    public static void printFlashcardHelp() {

    }
}
