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
    public static final String ADD_NOUN_MSG = "Added the following noun: ";
    public static final String ADD_VERB_MSG = "Added the following verb: ";
    public static final String ADD_ADJECTIVE_MSG = "Added the following adjective: ";
    public static final String LIST_WORDS_MSG = "Here are the words stored in the program:";
    public static final String EMPTY_WORDLIST_MSG = "Currently, there are no words in the words list.";
    public static final String EMPTY_INPUT_MSG = "Please enter a word and its description to save!";
    public static final String EMPTY_DESC_MSG = "Please enter the word's description after typing 'd\\'!";
    public static final String INVALID_NOUN_MSG = "Please use correct command format! Type 'help noun' for more info.";
    public static final String INVALID_VERB_MSG = "Please use correct command format! Type 'help verb' for more info.";
    public static final String INVALID_ADJ_MSG = "Please use correct command format! Type 'help adj' for more info.";

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

    /** Bunny filter messages. */
    public static final String NUMBER_BUNNY_FILTERED_MSG = "number bunny filtered: ";
    public static final String FILTER_BUNNY_COMMAND_MISSING_FILTER_OPTIONS_MSG =
            "filter bunny command missing filter options.";
    public static final String NO_BUNNY_MATCHING_FILTER_OPTIONS_MSG = "No bunny matching filter options";
    public static final String BUNNY_INDEX_NOT_IN_LIST_MSG = "Bunny index is not in list.";
    public static final String DIVIDER_COMMAND_OF_WRONG_FORMAT_MSG = "Divider command of wrong format";
    public static final String DIVIDER_INDEX_INDICATED_OUT_OF_BOUNDS_MSG = "Divider index indicated out of bounds";

    /** Bunny listing messages. */
    public static final String LIST_BUNNIES_EMPTY_MSG = "List of bunnies is empty.";
    public static final String TOTAL_BUNNIES_IN_LIST_MSG = "Total Bunnies in list: ";

    /** Bunny save and load messages. */
    public static final String BUNNY_LIST_SAVED_MSG = "Bunny list saved!";
    public static final String FAILED_TO_SAVE_BUNNY_LIST_MSG = "Failed to save bunny list";
    public static final String WRITING_TO_BUNNY_FILE_MSG = "Writing to bunny file";
    public static final String NUM_BUNNY_LOADED_MSG = "%1$s of %2$s bunnies loaded!";

    /** Bunny random selection message. */
    public static final String BUNNY_RANDOMLY_SELECTED_MSG = "Random Bunny: \n";

    /** New character added messages. */
    public static final String ADD_CHARACTER_MSG = "New character added!";
    public static final String LIST_CHARACTER_MSG = "Here are the characters stored in the program:";
    public static final String BUNNY_CHARACTER_FORMAT = "Character command wrong format";

    /** Filter messages. */
    public static final String INVALID_FILTER_CODE = "invalid filter type in the code";
    public static final String NO_FILTER_RESULT = "Nothing was found in the filter list!!";
    public static final String EMPTY_STRING_TAG = "At least an empty string tag is provided.";
    public static final String FILTER_MESSAGE = "Printing all %d word(s) in your filter list:\n";
    public static final String FILTER_MESSAGE_LIMIT = "Printing the first %d out of %d word(s) "
            + "from your filter list:\n";
    public static final String FILTER_TYPE_TAG_MISSING = "Please provide a filter tag \"\\by\"";
    public static final String PRINT_LIMIT_NOT_FOUND = "There are cases that\n"
            + "- You didn't specify the print limit\n" + "- Your limit is not an integer\n"
            + "The program will print out all the words in your filter list.\n";
    public static final String FILTER_UNKNOWN_TYPE = "Filter type was not recognized in the command. "
            + "Available filter types are: type, start, include.";
    public static final String LONG_FILTER_LIST_MESSAGE = "Your filter list has more than %d word(s)\n"
            + "Do you want to print all the words in the filter list? y/n\n";
    public static final String PROVIDE_STRING_TAG = "You must provide some string tags in the command";
    public static final String FILTER_WORDS_EMPTY_TARGET = "The command must provide some combination of"
            + " the following tags: -noun, -verb, -adjective";
    public static final String AVAILABLE_WORD_TYPE = "Word type is not recognized. "
            + "Supported tags: -noun, -verb, -adjective";
    public static final String INVALID_LIST_FILTER_ANSWER = "Your answer is not y/n. "
            + "The filter list will not be printed."
            + "You can use \"list filter words\" command to print out the filter result.";
    public static final String INVALID_LIMIT_LIST_FILTER_WORDS =
            "Your print limit is not valid so no words will be printed out.\n"
                    + "You can use \"list filter words\" command to print out the filter result.";
    public static final String INVALID_PRINT_LIMIT_MESSAGE = "Your limit is invalid.";

    /** Reminder messages. */
    public static final String NO_WRITING_DUE = "You don't have any writings due that day!!!";
    public static final String CONTINUE_WRITINGS = "On %s, you should continue on the following writing(s):\n";
    public static final String PARSE_DATETIME_EXCEPTION =
            "Cannot parse date and time. Please enter date in a correct format (dd/MM/yyyy)"
                    + " with valid day, month and year.";
    public static final String INVALID_REMINDER_DATE_EXCEPTION = "The date you entered is before today. "
            + "Please give another date in your next try.";

    /** Command not recognised message. */
    public static final String COMMAND_UNRECOGNISED_MSG = "Command is not recognised by Fluffle. "
            + "Please type \"help\" for a list of available command";

    /** Writings parsing messages. */
    public static final String ASSERTION_ID_ERROR = "This ID is invalid, please provide an ID from rage 0-1000";

    public static final String SUCCESSFUL_ADD_WRITING_TO_DATABASE = "Done! We have added your writing to our storage! "
            + "You can type \"stats\" " + "for future reference!";
    public static final String INSTRUCTION_FOR_ADDING_NEW_WRITINGS = "Now you can type your content, "
            + "terminate by typing \"end\"";
    public static final String TYPE_COMMAND_INSTRUCTION = "Please indicate your type by typing in \"type\" command";
    public static final String ASKING_FOR_TITLE = "Please let us know the title of your writing";
    public static final String ASKING_FOR_TYPE = "Please let us know your type of writings, either poem or essay";
    public static final String ASKING_FOR_TOPIC = "Please indicate your topic below";
    public static final String ASKING_FOR_REMINDER = "Please indicate the date you want to continue with this writing "
            + "by keying the date in the format \"dd/MM/yyyy\"";
    public static final String EMPTY_WRITING_MESSAGE = "The storage is currently empty, please type "
            + "\"start\" command to add";

    /** User interactive messages. */
    public static final String HELP_MESSAGE = "Please type in \"help\" command for information!";

    /** Clear messages. */
    public static final String CLEAR_SUCCESS_MESSAGE = "Nice! The %s \"%s\" has just been deleted from the database!\n";
    public static final String CLEAR_DATA_MESSAGE = "We have clear all data in the writings list";

    /** Stop execution message. */
    public static final String STOP_START_COMMAND = "start command stopped\nPlease give it another try.";

    /** Change line divider message. */
    public static final String CHANGING_LINE_DIVIDER_MSG = "Changing line divider...";
}
