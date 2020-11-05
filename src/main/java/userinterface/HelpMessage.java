package userinterface;

import studyit.Mode;
import studyit.StudyIt;

public class HelpMessage extends Ui {
    private static final String generalCommands = "Here are the general commands available:\n"
            + "help                 - prints out help message\n"
            + "location             - tells you your current mode\n"
            + "cd <MODE_INDEX/NAME> - changes the program to the corresponding mode\n"
            + "highlight            - prints out the important items you stored\n"
            + "exit                 - exit the program/mode you are currently at\n";

    public static final String currentModes = "These are the modes you can go to:\n"
            + "1 menu       - main menu\n"
            + "2 bookmark   - bookmark internet links\n"
            + "3 timetable  - plan your study schedule\n"
            + "4 academic   - track your academic details\n"
            + "5 flashcard  - flashcards to revise your study materials\n";

    private static final String academicCommands = "Here are the academic commands available:\n"
            + "list star                      - prints the list of starred components\n"
            + "---------CONTACTS--------\n"
            + "add contact c/<CONTACT>        - adds a contact\n"
            + "     m/<MOBILE> e/<EMAIL>\n"
            + "list contact                   - prints the list of contact currently stored\n"
            + "delete contact INDEX_NUMBER    - deletes contact at specified index\n"
            + "star contact INDEX_NUMBER      - marks the contact as star\n"
            + "----------GRADE----------\n"
            + "add grade n/<MODULE_NAME>      - adds a grade\n"
            + "     m/<MC> g/<GRADE>\n"
            + "check cap                      - prints the current CAP based on grade stored\n"
            + "list grade                     - prints the list of grades currently stored\n"
            + "delete grade <INDEX_NUMBER>    - deletes grade at specified index\n"
            + "su grade <INDEX_NUMBER>        - SU the grade at specified index\n"
            + "star grade <INDEX_NUMBER>      - marks the grade as star";

    private static final String bookmarkCommands = "Here are the bookmark commands available:\n"
            + "bm <CATEGORY_INDEX>          - changes mode from bookmark main into a category \n"
            + "back                         - go back to bookmark main\n"
            + "add <LINK>                   - add bookmark link into a specific category\n"
            + "add <LINK> t-> <TITLE>       - add optional title to your link!\n"
            + "cat <CATEGORY_NAME>          - add category to bookmark list\n"
            + "rm <LINK_INDEX>              - remove a bookmark link into a specific category\n"
            + "delete <CATEGORY_INDEX>      - remove a bookmark category\n"
            + "list                         - prints the list of categories and\n"
            + "                               respective list of links\n"
            + "list star                    - prints the list of starred bookmarks\n"
            + "star <LINK_NUMBER>           - mark the bookmark link as star";

    private static final String timetableCommands =  "Here are the timetable commands available:\n"
            + "add class      - adds a class to the timetable\n"
            + "add activity   - adds an activity to the timetable\n"
            + "show link      - displays the links/venues of events occurring\n"
            + "                 in the next 2 hours\n"
            + "show schedule  - displays schedule";

    private static final String flashcardCommands = "Here are the flashcard commands available:\n"
            + "add     - adds a question and answer to the flashcard deck\n"
            + "list    - shows the flashcards that have been added\n"
            + "delete  - deletes the flashcard corresponding to the card index entered by user\n"
            + "test    - user can attempt to answer a random question from the flashcard deck\n"
            + "find    - searches and returns flashcards containing user's desired search term\n"
            + "back    - exit test mode and go back to flashcard main";

    public static void printHelpMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(generalCommands);
        System.lineSeparator();
        System.out.println(currentModes);
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

        System.out.println(LINE_DIVIDER);
    }

    public static void printBookmarkHelp() {
        System.out.println(bookmarkCommands);
    }

    public static void printTimetableHelp() {
        System.out.println(timetableCommands);
    }

    public static void printAcademicHelp() {
        System.out.println(academicCommands);
    }

    public static void printFlashcardHelp() {
        System.out.println(flashcardCommands);
    }
}
