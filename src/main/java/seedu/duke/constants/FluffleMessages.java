package seedu.duke.constants;

public class FluffleMessages {
    public static final String NEWLINE = System.lineSeparator();

    /** Greeting message formats. */
    public static final String FAREWELL_GREETING =
            "Bye %1$s! Hope to see you again soon!" + NEWLINE;
    public static final String HELLO_GREETING =
            "Hello User! Welcome to Fluffle!" + NEWLINE + "What can I do for you?" + NEWLINE;
    public static final String ASK_FOR_NAME =
            "What is your name?" + NEWLINE;
    /**
     * Settings loader messages.
     */
    public static final String ERROR_READING_FILE_ON_LINE_MSG_FORMAT =
            "Error reading settings file! Error on line:" + NEWLINE + "%1$s";
    public static final String LOADING_SETTINGS_MSG = "Loading userSettings.txt save file...";
    public static final String SETTINGS_FILE_EMPTY_MSG = "userSettings.txt save file empty" + NEWLINE
            + "No previous settings loaded";
    public static final String SETTINGS_FILE_NOT_FOUND_MSG = "userSettings.txt save file not found" + NEWLINE
            + "Creating new file...";

    /** File creator function messages.  */
    public static final String NEW_FILE_CREATED_MSG_FORMAT = "New file created";
    public static final String IO_ERROR_WHEN_MAKING_FILE_MSG = "IO error when making file!";
    public static final String FILE_ALREADY_EXISTS_MSG = "File already exists.";
    public static final String FILE_CREATED_PATH_MSG = "File created";
    public static final String DIRECTORY_CREATED_SUCCESSFULLY_MSG = "Directory created successfully";
    public static final String COULD_NOT_CREATE_DIRECTORY_MSG = "Sorry, could not create specified directory";
    public static final String FILE_PATH_TO_DIRECTORY_INVALID_MSG = "File path to directory invalid!";
    public static final String FILE_NOT_FOUND_MSG = "File does not exist.";
    public static final String FILE_AUTO_CREATED_MSG = "Auto creating new file";
    public static final String FILE_SAVE_ERROR_MSG = "Error saving file!";

    /** New word added messages. */
    public static final String ADD_NOUN_MSG = "Added the following noun:";
    public static final String ADD_VERB_MSG = "Added the following verb:";
    public static final String ADD_ADJECTIVE_MSG = "Added the following adjective:";
    public static final String LIST_WORDS_MSG = "Here are the words stored in the program:";
    public static final String EMPTY_INPUT_MSG = "Please enter a word and its description to save!";
    public static final String EMPTY_DESC_MSG = "Please enter the word's description after typing 'd\\'!";
    public static final String INCORRECT_NOUN_MSG = "Please use the correct command format! Type 'help noun' for more info.";
    public static final String INCORRECT_VERB_MSG = "Please use the correct command format! Type 'help verb' for more info.";
    public static final String INCORRECT_ADJ_MSG = "Please use the correct command format! Type 'help adj' for more info.";

    /** Three Words Generator messages. */
    public static final String THREE_WORDS_MSG = "Here are three words selected randomly from the word bank:";
    public static final String THREE_WORDS_ERROR_MSG = "Word bank has less than three words, please add more words!";

    /** New bunny added messages. */
    public static final String ADD_BUNNY_MSG = "New bunny added!";
    public static final String LIST_BUNNY_MSG = "Here are the bunnies stored in the program:";
    public static final String BUNNY_WRONG_FORMAT = "Bunny command wrong format";
    public static final String BUNNY_MISSING_IDEA_MSG = "Bunny command missing idea?";

    /** Bunny deletion message. */
    public static final String BUNNY_DELETED_MSG = "Bunny deleted: \n";

    /** Bunny random selection message. */
    public static final String BUNNY_RANDOMLY_SELECTED_MSG = "Random Bunny: \n";

    /** New character added messages. */
    public static final String ADD_CHARACTER_MSG = "New character added!";
    public static final String LIST_CHARACTER_MSG = "Here are the characters stored in the program:";
    public static final String BUNNY_CHARACTER_FORMAT = "Character command wrong format";

    /** Command not recognised message. */
    public static final String COMMAND_UNRECOGNISED_MSG = "Command not recognised?";

    /** Writings parsing messages. */
    public static final String ASSERTION_ID_ERROR = "This ID is invalid, please provide an ID from rage 0-1000";

    public static final String SUCCESSFUL_ADD_WRITING_TO_DATABASE = "Done! We have added your writing to our storage! "
            + "You can type \"stats\" " + "for future reference!";
    public static final String INSTRUCTION_FOR_ADDING_NEW_WRITINGS = "Now you can type your content, "
            + "terminate by typing \"end\"";
    public static final String TYPE_COMMAND_INSTRUCTION = "Please indicate your type by typing in \"type\" command";
    public static final String ASKING_FOR_TITLE = "Please let us know the title of your writing";
    public static final String ASKING_FOR_TYPE = "Please let us know your type of writings, either poem or essay";
    public static final String ASKING_FOR_TOPIC = "Please indicate your topic by typing in \"topic\" command";
    public static final String EMPTY_WRITING_MESSAGE = "The storage is currently empty, please type "
            + "\"start\" command to add";
    public static final String CLEAR_DATA_MESSAGE = "We have clear all data in the writings list";

    /** User interactive messages. */
    public static final String HELP_MESSAGE = "Please type in \"help\" command for information!";
}
