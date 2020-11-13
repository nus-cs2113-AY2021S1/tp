package seedu.ravi.util;

public class ExceptionMessage {
    public static final String EXCEPTION_HEADER = "@|bold,white,BG_RED Error!|@";

    public static final String MESSAGE_NUS_MODS_NOT_LOADED =
            "@|bold,red,BG_BLACK,NEGATIVE_ON NUSMods data could not be loaded!|@\n"
            + "ra.VI is meant to be able to work offline, and so is packaged with a \n"
            + "copy of NUSMods data. If you're reading this, the ra.VI jar file you are \n"
            + "using may be corrupted. Please try to download again when you have an \n"
            + "Internet connection. Sorry for the inconvenience.\n";

    public static final String MESSAGE_MODULE_NOT_FOUND =
            "Sorry, the module is @|bold,red,BG_BLACK not in your module list|@.\n"
            + "Try checking the spelling of your module code?\n";
    public static final String MESSAGE_MODULE_NOT_PROVIDED =
            "Sorry, the module is @|bold,red,BG_BLACK not provided by NUS currently|@.\n"
            + "Try checking the spelling of your module code?\n";
    public static final String MESSAGE_DUPLICATE_MODULE =
            "Sorry, the module @|bold,red,BG_BLACK already exists in your list|@, so I can't add it again.\n"
            + "Perhaps you meant a different module?\n";
    public static final String MESSAGE_TASK_NOT_FOUND =
            "Sorry, that @|bold,red,BG_BLACK task index is not valid|@.\n"
            + "Use @|bold,green,BG_BLACK 'list -t'|@ to look at your tasks, and their index.\n"
            + "You can't use the task index from the summary command!\n";
    public static final String MESSAGE_NO_EDIT_MODULE =
            "Sorry, @|bold,red,BG_BLACK please enter a proper module code|@ to edit.\n"
            + "It should be a module code of an actual NUS mod. E.g. CS1010.\n";
    public static final String MESSAGE_NO_EDIT_TASK =
            "Sorry, @|bold,red,BG_BLACK please enter a proper task index|@ to edit.\n"
            + "Use @|bold,green,BG_BLACK 'list -t'|@ to look at your tasks, and their index.\n"
            + "You can't use the task index from the summary command!\n";
    public static final String MESSAGE_NO_ADD_MODULE =
            "Sorry, @|bold,red,BG_BLACK please enter a new module code|@ to add.\n"
            + "It should be a module code of an actual NUS mod. E.g. CS1010.\n";
    public static final String MESSAGE_NO_ADD_TASK =
            "Sorry, @|bold,red,BG_BLACK please enter a new task|@ to add.\n"
            + "Use @|bold,green,BG_BLACK 'list -t'|@ to look at your tasks, and their index.\n"
            + "You can't use the task index from the summary command!\n";
    public static final String MESSAGE_INVALID_PARAMETERS =
            "Some @|bold,red,BG_BLACK invalid or missing parameters|@ were found!\n"
            + "Use @|bold,green,BG_BLACK 'help'|@ to find out about the parameters required: "
            + "@|bold,green,BG_BLACK 'help %s'|@\n";
    public static final String MESSAGE_LIST_EMPTY =
            "\nYour list is empty.\n";
    public static final String MESSAGE_STRING_IN_NUMBER =
            "The task index you have entered is @|bold,red,BG_BLACK either not an integer or is too long|@!\n"
            + "Use @|bold,green,BG_BLACK 'list -t'|@ to look at your tasks, and their index.\n"
            + "Please check your command! Use @|bold,green,BG_BLACK 'help %s'|@ for assistance.\n";
    public static final String MESSAGE_INVALID_COMMAND_WORD =
            "ra.VI @|bold,red,BG_BLACK does not recognise|@ that command.\n"
            + "Please use @|bold,green,BG_BLACK 'help'|@ to see our list of commands.\n";
    public static final String MESSAGE_INVALID_GRADE =
            "Your grade input @|bold,red,BG_BLACK isn't part of the NUS grading scheme|@. \n"
            + "For your reference: A+, A, A-, B+, B, B-, C+, C, C-, D+, F, CS, CU.\n";
    public static final String MESSAGE_LESSON_INVALID_TIME =
            "Sorry, the lesson you have attempted to add has an @|bold,red,BG_BLACK invalid start/end time|@.\n"
            + "Try using @|bold,green,BG_BLACK 'timetable -day'|@ or "
            + "@|bold,green,BG_BLACK 'timetable -week'|@ to look at your\n"
            + "current lessons first, or review your new lesson's start/end time.\n";
    public static final String MESSAGE_LESSON_NOT_FOUND =
            "Sorry, the lesson you have attempted to delete @|bold,red,BG_BLACK does not exist|@.\n"
            + "Use @|bold,green,BG_BLACK 'timetable (-week/-day)'|@ to look at your lessons and their index (ID).\n";
    public static final String MESSAGE_LESSON_OVERLAP =
            "Sorry, the lesson you have attempted to add @|bold,red,BG_BLACK overlaps with other lessons|@.\n"
            + "This is the lesson you're overlapping with: %s\n";
    public static final String MESSAGE_REPEAT_FREQUENCY_UNKNOWN =
            "Sorry, the lesson you have attempted to add @|bold,red,BG_BLACK does "
            + "not have the correct repeatability|@.\n"
            + "For your reference:\n"
            + "0 - This week only\n"
            + "1 - Every week\n"
            + "2 - Every even week\n"
            + "3 - Every odd week\n";
    public static final String TIMETABLE_NOT_INITIALISED =
            "Sorry, please enter a @|bold,red,BG_BLACK proper week number|@ as instructed.\n";
    public static final String MESSAGE_ADD_TASK_DATE_TIME_UNKNOWN =
            "Sorry, please enter a @|bold,red,BG_BLACK valid date and time|@.\n"
            + "\tRequired date time format: dd-MM-yyyy HHmm\n"
            + "\tExample: 30-12-2020 1600";
    public static final String MESSAGE_ADD_LESSON_DATE_TIME_UNKNOWN =
            "Sorry, please enter a @|bold,red,BG_BLACK valid start time and end time|@.\n"
            + "\tRequired date time format: HHmm HHmm (start, end)\n"
            + "\tExample: 1800 2000";
    public static final String MESSAGE_INVALID_CAP =
            "The CAP you entered is @|bold,red,BG_BLACK invalid|@.\n";
    public static final String MESSAGE_INVALID_CAP_ATTAINED =
            "The CAP calculated from your input is @|bold,red,BG_BLACK invalid|@.\n"
            + "For more information, use @|bold,green,BG_BLACK 'help cap'|@.\n";
    public static final String MESSAGE_INVALID_MC =
            "The number of MCs you entered is @|bold,red,BG_BLACK invalid|@.\n"
            + "For more information, use @|bold,green,BG_BLACK 'help grade'|@.\n";
    public static final String MESSAGE_INVALID_HELP_COMMAND =
            "The help command you have entered is @|bold,red,BG_BLACK invalid|@.\n"
            + "For more information, use @|bold,green,BG_BLACK 'help'|@.\n";

    /*
    
    public static final String MESSAGE_MISSING_DIRECTORY_NAME =
            "Please include the name of the directory you want to move to.\n"
                    + "Alternatively, enter .. to move to the parent directory instead.\n";

    public static final String MESSAGE_CATEGORY_NOT_FOUND = "Sorry, the category is not found.\n";
    public static final String MESSAGE_DUPLICATE_CATEGORY = "Sorry, the category already exists.\n";

    public static final String MESSAGE_DUPLICATE_TASK = "Sorry, the task already exists.\n";

    public static final String MESSAGE_TASK_FILE_NOT_FOUND = "Sorry, the file is not found.\n";
    public static final String MESSAGE_DUPLICATE_TASK_FILE = "Sorry, the file already exists.\n";
    public static final String MESSAGE_IMPLICIT_FILE_EXCEED_LIMIT =
            "Sorry, the name of the file you are adding exceeds 30 characters.\n"
                    + "Please enter a new file name.\n";

    public static final String MESSAGE_MISSING_PARAMETERS = "Sorry, some parameters seem to be missing.\n";

    public static final String MESSAGE_INVALID_PRIORITY = "Alert! Priority should be a number between 0 and 20.\n";
    public static final String MESSAGE_INVALID_DATETIME_FORMAT =
            "Sorry, the deadline you entered is not in a recognised datetime format.\n"
            + "Please make sure to follow the datetime format as such:\n\t"
            + DateTime.FORMAT + "\nExample: 06/06/2020 12:00am\n";
    public static final String MESSAGE_INVALID_DATE_FORMAT =
            "Sorry, the date you entered is not in a recognised date format.\n"
            + "Please make sure to follow the date format as such:\n\t"
            + DateTime.DATE_FORMAT + "\nExample: 07/07/2020\n";

    public static final String MESSAGE_LIST_NUMBER_NOT_FOUND =
            "The List number is not found on the list! Deletion is aborted.\n";

    public static final String MESSAGE_TRAVERSE_ERROR =
            "There seems to be an error when traversing. Moving back to Root...\n";
    public static final String MESSAGE_FAILED_DIRECTORY_TRAVERSAL = "Unable to traverse further.\n";
    public static final String MESSAGE_DIRECTORY_NOT_FOUND = "Sorry, the next directory could not be found.\n";
    public static final String MESSAGE_INCORRECT_DIRECTORY_LEVEL =
            "Sorry, unable to execute the command at the current directory level.\n"
                    + "Either move to the appropriate directory level, or enter the full directory path.\n";


    public static final String MESSAGE_DUPLICATE_PREFIX_FOUND =
            "There seems to be duplicate prefix(es) in your input...\n";

    public static final String MESSAGE_FILE_IO_EXCEPTION = "Oh no! There was an error in retrieving your file.\n";
    public static final String MESSAGE_FILE_SYSTEM_EXCEPTION = "Oh no! There was an error in retrieving your file. "
            + "Please check that your file is not currently running.\n";
    public static final String MESSAGE_INVALID_FILE_PATH = "Sorry, the file path entered is invalid.\n";
    public static final String MESSAGE_ADD_FILE_NOT_FOUND =
            "Sorry, the file does not exists. Please check the file path again.\n";
    public static final String MESSAGE_FILE_SECURITY_EXCEPTION =
            "Oh no! File access was denied by device's security program.\n";
    public static final String MESSAGE_FILE_NOT_FOUND_OPEN =
            "The following file(s) could not be opened as they cannot be found:\n";
    public static final String MESSAGE_FILE_NOT_FOUND_DELETE =
            "Deletion completed.\n"
                    + "However, note that the following file(s) could not be deleted completely from Nuke as they "
                    + "could not be found:\n";
    */
}
