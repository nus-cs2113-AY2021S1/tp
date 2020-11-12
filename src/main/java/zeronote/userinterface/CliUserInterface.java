package zeronote.userinterface;

import java.util.Scanner;

import zeronote.exceptions.FileSavingException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.storage.Storage;
import zeronote.userinterface.command.CliCommand;
import zeronote.userinterface.command.Exit;

// @@author neilbaner

public class CliUserInterface {
    private AppState appState;

    private boolean toQuit = false;
    private PersonalMessageGenerator msgGenerator;
    private CliMessages messages;
    private Scanner keyboardScanner;

    public CliUserInterface() {
        messages = new CliMessages();
        keyboardScanner = new Scanner(System.in);
    }

    private void loadState() {
        Storage storage = new Storage();
        appState = storage.readFromFile();
    }

    private void saveState() throws FileSavingException {
        Storage storage = new Storage();
        storage.saveToFile(appState);
    }

    private void printPrompt() {
        String modeString = "";
        String notebookTitle;
        String sectionTitle;
        String pageTitle;
        try {
            notebookTitle = appState.getCurrentNotebook().getTitle();
        } catch (NullPointerException e) {
            notebookTitle = "";
        }
        try {
            sectionTitle = appState.getCurrentSection().getTitle();
        } catch (NullPointerException e) {
            sectionTitle = "";
        }
        try {
            pageTitle = appState.getCurrentPage().getTitle();
        } catch (NullPointerException e) {
            pageTitle = "";
        }
        if (notebookTitle.length() > 10) {
            notebookTitle = notebookTitle.substring(0, 7) + "...";
        }
        if (sectionTitle.length() > 10) {
            sectionTitle = sectionTitle.substring(0, 7) + "...";
        }
        if (pageTitle.length() > 10) {
            pageTitle = pageTitle.substring(0, 7) + "...";
        }
        switch (appState.getAppMode()) {
        case TIMETABLE:
            modeString = modeString + "T";
            break;
        case NOTEBOOK_SHELF:
            modeString = modeString + "N";
            break;
        case NOTEBOOK_BOOK:
            modeString = modeString + "N/" + notebookTitle;
            break;
        case NOTEBOOK_SECTION:
            modeString = modeString + "N/" + notebookTitle + "/" + sectionTitle;
            break;
        case NOTEBOOK_PAGE:
            modeString = modeString + "N/" + notebookTitle + "/" + sectionTitle + "/" + pageTitle;
            break;
        default:
            modeString = "";
            break;
        }
        modeString = modeString + ":$ ";
        System.out.print(modeString);
        System.out.flush();
    }

    //@@author yAOwzers

    /**
     * Checks if the user had already input their name into Zer0Note for the personalised message
     * generator feature.
     *
     * @throws ZeroNoteException when the user enters an invalid input.
     */
    private void checkNameOfUser() throws ZeroNoteException {
        if (appState.getUserName().equals("")) {
            messages.printFillInNameOfUserMessage();
            String userName = keyboardScanner.nextLine();
            appState.setUserName(userName);
            saveState();
            msgGenerator = new PersonalMessageGenerator(userName);
            msgGenerator.greetFirstTimeUser();
        } else {
            String userName = appState.getUserName();
            msgGenerator = new PersonalMessageGenerator(userName);
            msgGenerator.greetUser();
        }
    }

    //@@author neilbaner
    public void run() {
        loadState();
        startUI();
        String userInput;
        try {
            checkNameOfUser();
            msgGenerator = new PersonalMessageGenerator(appState.getUserName());
        } catch (ZeroNoteException e) {
            e.printErrorMessage();
            messages.printLineSeparator();
        }
        while (!toQuit) {
            printPrompt();
            userInput = keyboardScanner.nextLine();
            try {
                if (userInput.equals(Exit.COMMAND_WORD)) {
                    toQuit = true;
                }
                messages.printLineSeparator();
                executeCommand(userInput);
                messages.printLineSeparator();
            } catch (ZeroNoteException e) {
                e.printErrorMessage();
                messages.printLineSeparator();
            }
        }
    }

    private void executeCommand(String userInput) throws ZeroNoteException {
        InputParser parser = new InputParser();
        CliCommand command = parser.getCommandFromInput(userInput, appState);
        command.execute();
        if (command.printsPersonalMessage()) {
            messages.printLineSeparator();
            String message = msgGenerator.generatePersonalisedMessage();
            System.out.println(message);
        }
        if (command.isTriggerAutoSave()) {
            saveState();
        }
    }

    // @@author chuckiex3
    private void startUI() {
        System.out.println("Welcome to");
        System.out.println(" _ _ _                  _ _ _   _    _            _");
        System.out.println("|_ _  |   _ _    _  _  |  _  | |  \\ | |   _ _   _| |_    _ _");
        System.out.println("  /  /  /  _  \\ | |/_\\ | | | | |   \\| |  /   \\ |_   _| /   _ \\");
        System.out.println(" /  /_  |  _ _/ | |    | |_| | |  |\\  | |  [] |  | |_  |  _ _/");
        System.out.println("|_ _ _|  \\ _ _| |_|    |_ _ _| | _| \\_|  \\ _ /   |_ _|  \\ _ _|");
        System.out.println("You are now in timetable mode");
    }
}
