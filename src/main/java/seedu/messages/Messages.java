package seedu.messages;

import static seedu.font.Colors.ANSI_CYAN;
import static seedu.font.Colors.ANSI_PURPLE;
import static seedu.font.Colors.ANSI_RESET;
import static seedu.font.Colors.ANSI_YELLOW;


public class Messages {
    public static final String LS = System.lineSeparator();

    public static final String INVALID_PRIORITY = "Invalid priority!" + LS;
    public static final String INVALID_COMMAND = "Invalid command!" + LS;
    public static final String UNKNOWN_COMMAND = "Unknown command!" + LS;
    public static final String INVALID_TASK_NUMBER = "Invalid task number!" + LS;
    public static final String INVALID_DATETIME = "Invalid datetime!" + LS;
    public static final String MAX_NUM_TASK = "Max number of tasks reached!" + LS;

    public static final String WELCOME_MESSAGE = " ========================================================== " + LS
            + "||                                                        ||" + LS
            + "||         " + ANSI_YELLOW + "Welcome to" + ANSI_RESET + "                                     ||" + LS
            + "||         " + ANSI_CYAN + "    ____  __      _   ____  _______" + ANSI_RESET + "            ||" + LS
            + "||         " + ANSI_CYAN + "   / __ \\/ /___ _/ | / / / / / ___/" + ANSI_RESET + "            ||" + LS
            + "||         " + ANSI_CYAN + "  / /_/ / / __ `/  |/ / / / /\\__ \\ " + ANSI_RESET + "            ||" + LS
            + "||         " + ANSI_CYAN + " / ____/ / /_/ / /|  / /_/ /___/ / " + ANSI_RESET + "            ||" + LS
            + "||         " + ANSI_CYAN + "/_/   /_/\\__,_/_/ |_/\\____//____/  " + ANSI_RESET
            + ANSI_CYAN+ "v1.0" + ANSI_RESET + "        ||" + LS
            + "||                                                        ||";
    public static final String HELP_MESSAGE = " ================================================================================================== " + LS
            + "||                                                                                                ||" + LS
            + "||       " + "List of available commands:" + "                                                              ||" + LS
            + "||       " + "Note:" + "                                                                                    ||" + LS
            + "||       " + "1. Square bracket input is optional." + "                                                     ||" + LS
            + "||       " + "- help: show list of available commands" + "                                                  ||" + LS
            + "||       " + "format: " + ANSI_PURPLE + "help" + ANSI_RESET + "                                                                             ||" + LS
            + "||       " + "- add: add a task" + "                                                                        ||" + LS
            + "||       " + "format: " + ANSI_PURPLE
            + "add DESCRIPTION [d/dd-MM-yyyy] [st/HHmm] [et/HHmm] [p/NUMBER]" + ANSI_RESET + "                    ||" + LS
            + "||       " + "- edit: edit a task" + "                                                                      ||" + LS
            + "||       " + "format: " + ANSI_PURPLE
            + "edit INDEX [des/DESCRIPTION] [d/dd-MM-yyyy] [st/HHmm] [et/HHmm] [p/NUMBER]" + ANSI_RESET + "       ||" + LS
            + "||       " + "- list: show list of tasks" + "                                                               ||" + LS
            + "||       " + "format: " + ANSI_PURPLE + "list" + ANSI_RESET + "                                                                             ||" + LS
            + "||       " + "- search: find task(s) with keyword in description" + "                                       ||" + LS
            + "||       " + "format: " + ANSI_PURPLE + "search KEYWORD(S)" + ANSI_RESET + "                                                                ||" + LS
            + "||       " + "- delete remove particular task by index" + "                                                 ||" + LS
            + "||       " + "format: " + ANSI_PURPLE + "delete INDEX" + ANSI_RESET + "                                                                     ||" + LS
            + "||       " + "- clear: remove all tasks" + "                                                                ||" + LS
            + "||       " + "format: " + ANSI_PURPLE + "clear" + ANSI_RESET + "                                                                            ||" + LS
            + "||       " + "- bye: exit the program" + "                                                                  ||" + LS
            + "||       " + "format: " + ANSI_PURPLE + "bye" + ANSI_RESET + "                                                                              ||" + LS
            + "||                                                                                                ||" + LS
            + " ================================================================================================== " + LS;

    public static final String BYE_MESSAGE = "Bye! See you again!";
    public static final String LIST_MESSAGE = "Here is your list of tasks:";
    public static final String ADD_MESSAGE = "Task added.";
    public static final String EDIT_MESSAGE = "Task edited.";
    public static final String CLEAR_MESSAGE = "All tasks cleared." + LS;
    public static final String DELETE_MESSAGE = "Task deleted." + LS;
    public static final String SEARCH_NOT_FOUND_MESSAGE = "No matched item found." + LS;
    public static final String SEARCH_FOUND_MESSAGE = "Here is your list of tasks"
            + " which contain the word/letters:";
}
