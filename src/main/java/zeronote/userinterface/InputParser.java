package zeronote.userinterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import zeronote.exceptions.EmptyPageException;
import zeronote.exceptions.IncorrectAppModeException;
import zeronote.exceptions.IncorrectDeadlineFormatException;
import zeronote.exceptions.IndexIncorrectFormatException;
import zeronote.exceptions.InvalidCommandException;
import zeronote.exceptions.InvalidNotebookException;
import zeronote.exceptions.InvalidPageException;
import zeronote.exceptions.InvalidSectionException;
import zeronote.exceptions.InvalidSelectCommandException;
import zeronote.exceptions.InvalidTagException;
import zeronote.exceptions.TaskTitleException;
import zeronote.exceptions.TaskWrongFormatException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.NotebookShelf;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.userinterface.command.CliCommand;
import zeronote.userinterface.command.Exit;
import zeronote.userinterface.command.Help;
import zeronote.userinterface.command.ModeSwitch;
import zeronote.userinterface.command.notebook.AddCommandNotebookMode;
import zeronote.userinterface.command.notebook.FindCommandNotebookMode;
import zeronote.userinterface.command.notebook.ListCommandNotebookMode;
import zeronote.userinterface.command.notebook.RemoveCommandNotebookMode;
import zeronote.userinterface.command.notebook.SelectCommandNotebookMode;
import zeronote.userinterface.command.notebook.TagCommandNotebookMode;
import zeronote.userinterface.command.timetable.AddCommandTimetableMode;
import zeronote.userinterface.command.timetable.DoneCommandTimetableMode;
import zeronote.userinterface.command.timetable.FindCommandTimetableMode;
import zeronote.userinterface.command.timetable.ListCommandTimetableMode;
import zeronote.userinterface.command.timetable.RemoveCommandTimetableMode;
import zeronote.userinterface.command.timetable.TagCommandTimetableMode;

public class InputParser {
    //@@author chuckiex3

    /**
     * Parses the user's input to extract the task title in TIMETABLE mode.
     *
     * @param input the input from the user.
     * @return the task title.
     * @throws TaskTitleException               when the user's input does not include a task title.
     * @throws IncorrectDeadlineFormatException when the user's input does not include the DEADLINE_DELIMITER.
     */
    public String parseTaskTitle(String input) throws ZeroNoteException {
        if (input.startsWith(AddCommandTimetableMode.TASK_DELIMITER) && input.contains(
                AddCommandTimetableMode.DEADLINE_DELIMITER)) {
            String taskTitle = input.substring(AddCommandTimetableMode.TASK_DELIMITER.length());
            int indexPos = taskTitle.indexOf(AddCommandTimetableMode.DEADLINE_DELIMITER);
            taskTitle = taskTitle.substring(0, indexPos).trim();

            if (taskTitle.isBlank()) {
                throw new TaskTitleException();
            }

            return taskTitle;
        } else {
            throw new TaskWrongFormatException();
        }
    }

    /**
     * Parses user's input to extract deadline in TIMETABLE mode.
     *
     * @param input the input from the user.
     * @return the deadline in the format dd-MM-yyyy hhMM, where time is in 24h format.
     * @throws IncorrectDeadlineFormatException when the deadline input is in the wrong format.
     */
    public String parseDeadline(String input) throws IncorrectDeadlineFormatException {
        if (input.contains(AddCommandTimetableMode.DEADLINE_DELIMITER)) {
            int dividerPos = input.indexOf(AddCommandTimetableMode.DEADLINE_DELIMITER);
            input = input.substring(dividerPos);
            String deadline = input.substring(AddCommandTimetableMode.DEADLINE_DELIMITER.length()).trim();
            if (deadline.isBlank()) {
                throw new IncorrectDeadlineFormatException();
            }
            if (!correctTimeFormat(deadline)) {
                throw new IncorrectDeadlineFormatException();
            }
            return deadline;
        } else {
            throw new IncorrectDeadlineFormatException();
        }
    }

    /**
     * Checks if [deadline] input by the user is in the correct format.
     *
     * @param by the string containing the deadline's due date and time.
     * @return true when the input is in the correct format, otherwise false.
     */
    private boolean correctTimeFormat(String by) {
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            LocalDate.parse(by, dateTime);
        } catch (DateTimeParseException d) {
            return false;
        }
        return true;
    }

    //@@author chuckiex3

    /**
     * Parses user's input to extract notebook title, section title or page number whenever applicable for
     * SelectCommandNotebookMode.
     *
     * @param argument a string of text that contains notebook title, section title or/and page number.
     * @param appState the state of the application.
     * @throws InvalidNotebookException      when the notebook the user wants to select does not exist.
     * @throws InvalidSectionException       when the section the user wants to select does not exist.
     * @throws InvalidPageException          when the page number the user wants to select does not exist.
     * @throws InvalidSelectCommandException when the select command types by the user is wrong.
     */
    public void extractParams(String argument, AppState appState) throws InvalidNotebookException,
            InvalidSectionException, InvalidPageException, InvalidSelectCommandException {
        if (argument.startsWith(AddCommandNotebookMode.NOTEBOOK_DELIMITER)) {
            extractNotebookParams(argument, appState);
        } else if ((argument.startsWith(AddCommandNotebookMode.SECTION_DELIMITER)) && (appState.getAppMode()
                == AppMode.NOTEBOOK_BOOK)) {
            extractSectionParams(argument, appState);
        } else if ((argument.startsWith(AddCommandNotebookMode.PAGE_DELIMITER)) && (appState.getAppMode()
                == AppMode.NOTEBOOK_SECTION)) {
            extractPageParams(argument, appState);
        } else if (argument.startsWith(SelectCommandNotebookMode.SHOW_ALL)) {
            appState.setAppMode(AppMode.NOTEBOOK_SHELF);
            System.out.println("now in " + appState.getAppMode() + " mode.");
        } else {
            throw new InvalidSelectCommandException(argument);
        }
    }

    /**
     * Extracts the notebook title, as well as the section title and page number, if provided.
     *
     * @param argument the user's input.
     * @param appState the current mode the user is in.
     * @throws InvalidNotebookException when the notebook title input by the user does not exist.
     * @throws InvalidSectionException  when the section title input by the user does not exist.
     * @throws InvalidPageException     when the page number input by the user does not exist.
     */
    public void extractNotebookParams(String argument, AppState appState) throws InvalidNotebookException,
            InvalidSectionException, InvalidPageException {
        int invalid = -1;
        Notebook notebook;
        String notebookTitle = parseNotebookTitle(argument);
        NotebookShelf notebookShelf = appState.getCurrentBookShelf();
        int notebookIndex = notebookShelf.findNotebook(notebookTitle);
        if (notebookIndex == invalid) {
            throw new InvalidNotebookException(notebookTitle);
        }
        notebook = notebookShelf.getNotebookAtIndex(notebookIndex);
        appState.setAppMode(AppMode.NOTEBOOK_BOOK);
        appState.setCurrentNotebook(notebook);
        System.out.println("now in " + appState.getAppMode() + ": " + appState.getCurrentNotebook().getTitle());
        if (argument.contains(AddCommandNotebookMode.SECTION_DELIMITER)) {
            extractSectionParams(argument, appState);
        }
    }

    /**
     * Parses the user's input to extract the section title, and the page number if provided by the user.
     *
     * @param argument the user's input.
     * @param appState the current mode the user is in.
     * @throws InvalidSectionException when the section title input by the user does not exist.
     * @throws InvalidPageException    when the page title input by the user does not exist.
     */
    public void extractSectionParams(String argument, AppState appState) throws InvalidSectionException,
            InvalidPageException {
        int invalid = -1;
        Notebook notebook = appState.getCurrentNotebook();
        String sectionTitle = parseSectionTitle(argument);
        int sectionIndex = notebook.findSection(sectionTitle);
        if (sectionIndex == invalid) {
            throw new InvalidSectionException(sectionTitle);
        }
        Section section = notebook.getSectionAtIndex(sectionIndex);
        appState.setAppMode(AppMode.NOTEBOOK_SECTION);
        appState.setCurrentSection(section);
        System.out.println("now in " + appState.getAppMode() + ": " + appState.getCurrentSection().getTitle());
        if (argument.contains(AddCommandNotebookMode.PAGE_DELIMITER)) {
            extractPageParams(argument, appState);
        }
    }

    //@@author chuckiex3

    /**
     * Parses the user's input to extract the page number, and content (only if applicable).
     *
     * @param argument the user's input.
     * @param appState the current mode the user is in.
     * @throws InvalidPageException when the page title input by the user does not exist.
     */
    public void extractPageParams(String argument, AppState appState) throws InvalidPageException {
        int invalid = -1;
        Section section = appState.getCurrentSection();
        String pageTitle = parsePageTitle(argument);
        int pageNum = section.findPage(pageTitle);
        if (pageNum == invalid) {
            throw new InvalidPageException(pageTitle);
        }
        appState.setCurrentPage(pageNum);
        Page page = section.getPageAtIndex(pageNum);
        appState.setAppMode(AppMode.NOTEBOOK_PAGE);
        System.out.println("now in " + appState.getAppMode() + ": " + appState.getCurrentPage().getTitle());
        page.printPage();
    }

    /**
     * Parses notebook title from the user's input.
     *
     * @param input the input from the user.
     * @return the notebook title input by the user.
     * @throws InvalidNotebookException when user's input is in the wrong format or blank.
     */
    public String parseNotebookTitle(String input) throws InvalidNotebookException {
        if (input.startsWith(AddCommandNotebookMode.NOTEBOOK_DELIMITER)) {
            String notebookTitle = input.substring(AddCommandNotebookMode.NOTEBOOK_DELIMITER.length()).trim();
            if (notebookTitle.contains(AddCommandNotebookMode.SECTION_DELIMITER)) {
                int indexPos = notebookTitle.indexOf(AddCommandNotebookMode.SECTION_DELIMITER);
                notebookTitle = notebookTitle.substring(0, indexPos).trim();
            }
            if (notebookTitle.isBlank()) {
                throw new InvalidNotebookException(notebookTitle);
            }
            return notebookTitle;
        } else {
            throw new InvalidNotebookException(input);
        }
    }

    /**
     * Parses section title from the user's input.
     *
     * @param input the user's input.
     * @return the section title input by the user.
     * @throws InvalidSectionException when the user's input does not contain the section delimiter, or when the
     *                                 section title is blank.
     */
    public String parseSectionTitle(String input) throws InvalidSectionException {
        try {
            int dividerPos = input.indexOf(AddCommandNotebookMode.SECTION_DELIMITER);
            input = input.substring(dividerPos);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSectionException(input);
        }

        if (input.startsWith(AddCommandNotebookMode.SECTION_DELIMITER)) {
            String sectionTitle = input.substring(AddCommandNotebookMode.SECTION_DELIMITER.length()).trim();
            if (sectionTitle.contains(AddCommandNotebookMode.PAGE_DELIMITER)) {
                int indexPos = sectionTitle.indexOf(AddCommandNotebookMode.PAGE_DELIMITER);
                sectionTitle = sectionTitle.substring(0, indexPos).trim();
            }
            if (sectionTitle.isBlank()) {
                throw new InvalidSectionException(sectionTitle);
            }
            return sectionTitle;
        } else {
            throw new InvalidSectionException(input);
        }
    }

    //@@author Lusi711

    /**
     * Parses the index number from the user's input.
     *
     * @param args the input from the user.
     * @return the integer index number.
     * @throws IndexIncorrectFormatException when the user's input is not a valid number.
     */
    public int parseTaskIndex(String args) throws IndexIncorrectFormatException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException nfe) {
            throw new IndexIncorrectFormatException();
        }
    }

    //@@author chuckiex3

    /**
     * Parses the page title input by the user.
     *
     * @param input the input from the user.
     * @return the page title input by the user.
     * @throws InvalidPageException when the user's input is in the wrong format, or when the page title is blank.
     */
    public String parsePageTitle(String input) throws InvalidPageException {
        try {
            int dividerPos = input.indexOf(AddCommandNotebookMode.PAGE_DELIMITER);
            input = input.substring(dividerPos);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidPageException(input);
        }

        if (input.startsWith(AddCommandNotebookMode.PAGE_DELIMITER)) {
            String pageTitle = input.substring(AddCommandNotebookMode.PAGE_DELIMITER.length()).trim();
            if (pageTitle.contains(AddCommandNotebookMode.CONTENT_DELIMITER)) {
                int indexPos = pageTitle.indexOf(AddCommandNotebookMode.CONTENT_DELIMITER);
                pageTitle = pageTitle.substring(0, indexPos).trim();
            }
            if (pageTitle.isBlank()) {
                throw new InvalidPageException(pageTitle);
            }
            return pageTitle;
        } else {
            throw new InvalidPageException(input);
        }
    }

    //@@author chuckiex3
    /**
     * Parses the page contents of the user's input.
     *
     * @param input the user's input.
     * @return contents in the page input by the user.
     * @throws InvalidPageException when the user's input does not contain the page content delimiter, or when there
     *                              is no content.
     */
    public String parsePageContent(String input) throws InvalidPageException, EmptyPageException {
        try {
            int dividerPos = input.indexOf(AddCommandNotebookMode.CONTENT_DELIMITER);
            input = input.substring(dividerPos);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidPageException(input);
        }

        if (input.startsWith(AddCommandNotebookMode.CONTENT_DELIMITER)) {
            String content = input.substring(AddCommandNotebookMode.CONTENT_DELIMITER.length()).trim();
            if (content.isBlank()) {
                throw new EmptyPageException();
            }
            return content;
        } else {
            throw new InvalidPageException(input);
        }
    }

    //@@author

    /**
     * Parses the keyword and tag.
     *
     * @param input the user's input.
     * @return an array containing the keyword (empty if not specified) and the tag.
     */
    public String[] parseTagDescription(String input) {
        return input.split(AddCommandTimetableMode.TASK_DELIMITER, 2);
    }


    public CliCommand getCommandFromInput(String userInput, AppState appState) throws ZeroNoteException {
        String trimmedInput = userInput.trim();
        String[] input = trimmedInput.split(" ", 2); // split input into command and arguments
        String commandWord = input[0];
        String argument = "";
        if (input.length > 1) {
            argument = input[1].trim();
        }
        switch (commandWord) {
        case AddCommandTimetableMode.COMMAND_WORD:
            if (appState.getAppMode() == AppMode.TIMETABLE) {
                return new AddCommandTimetableMode(argument, appState);
            } else {
                String titleToAdd;
                String contentToAdd;
                if (appState.getAppMode() == AppMode.NOTEBOOK_SHELF) {
                    if (argument.contains(AddCommandNotebookMode.SECTION_DELIMITER) || argument.contains(
                            AddCommandNotebookMode.PAGE_DELIMITER) || argument.contains(
                            AddCommandNotebookMode.CONTENT_DELIMITER)) {
                        throw new InvalidNotebookException(argument);
                    }
                    titleToAdd = parseNotebookTitle(argument);
                    return new AddCommandNotebookMode(titleToAdd, appState);
                } else if (appState.getAppMode() == AppMode.NOTEBOOK_BOOK) {
                    if (argument.contains(AddCommandNotebookMode.PAGE_DELIMITER) || argument.contains(
                            AddCommandNotebookMode.NOTEBOOK_DELIMITER) || argument.contains(
                            AddCommandNotebookMode.CONTENT_DELIMITER)) {
                        throw new InvalidSectionException(argument);
                    }
                    titleToAdd = parseSectionTitle(argument);
                    return new AddCommandNotebookMode(titleToAdd, appState);
                } else if (appState.getAppMode() == AppMode.NOTEBOOK_SECTION) {
                    if (!argument.contains(AddCommandNotebookMode.CONTENT_DELIMITER) || argument.contains(
                            AddCommandNotebookMode.NOTEBOOK_DELIMITER) || argument.contains(
                            AddCommandNotebookMode.SECTION_DELIMITER)) {
                        throw new InvalidPageException(argument);
                    }
                    titleToAdd = parsePageTitle(argument);
                    contentToAdd = parsePageContent(argument);
                    return new AddCommandNotebookMode(titleToAdd, contentToAdd, appState);
                } else {
                    throw new InvalidCommandException(userInput);
                }
            }
        case FindCommandTimetableMode.COMMAND_WORD:
            String[] splitParams = parseTagDescription(argument);
            if (appState.getAppMode() == AppMode.TIMETABLE && argument.contains("/t")) {
                return new FindCommandTimetableMode(splitParams[0].trim(), splitParams[1].trim(), appState);
            } else if (appState.getAppMode() == AppMode.TIMETABLE && !argument.contains("/t")) {
                return new FindCommandTimetableMode(splitParams[0].trim(), "", appState);
            } else if (argument.contains("/t")) {
                return new FindCommandNotebookMode(splitParams[0].trim(), splitParams[1].trim(), appState);
            } else {
                return new FindCommandNotebookMode(splitParams[0].trim(), "", appState);
            }
        case ListCommandTimetableMode.COMMAND_WORD:
            if (appState.getAppMode() == AppMode.TIMETABLE) {
                return new ListCommandTimetableMode(argument, appState);
            } else {
                return new ListCommandNotebookMode(argument, appState);
            }
        case SelectCommandNotebookMode.COMMAND_WORD:
            if (appState.getAppMode() != AppMode.TIMETABLE) {
                if (argument.contains(AddCommandNotebookMode.CONTENT_DELIMITER)) {
                    throw new InvalidSelectCommandException(argument);
                }
                return new SelectCommandNotebookMode(argument, appState);
            } else {
                throw new IncorrectAppModeException();
            }
        case TagCommandTimetableMode.COMMAND_WORD:
            splitParams = parseTagDescription(argument);
            if (appState.getAppMode() == AppMode.TIMETABLE) {
                int index = parseTaskIndex(splitParams[0].trim());
                try {
                    return new TagCommandTimetableMode(index, splitParams[1].trim(), appState);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidTagException(userInput);
                }
            } else {
                try {
                    return new TagCommandNotebookMode(splitParams[1].trim(), appState);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidTagException(userInput);
                }
            }
        case RemoveCommandTimetableMode.COMMAND_WORD:
            if (appState.getAppMode() == AppMode.TIMETABLE) {
                return new RemoveCommandTimetableMode(parseTaskIndex(argument), appState);
            } else {
                String notebookTitleToRemove = "";
                String sectionTitleToRemove = "";
                String pageTitleToRemove = "";
                if (argument.contains(AddCommandNotebookMode.NOTEBOOK_DELIMITER)) {
                    notebookTitleToRemove = parseNotebookTitle(argument);
                }
                if (argument.contains(AddCommandNotebookMode.SECTION_DELIMITER)) {
                    sectionTitleToRemove = parseSectionTitle(argument);
                }
                if (argument.contains(AddCommandNotebookMode.PAGE_DELIMITER)) {
                    pageTitleToRemove = parsePageTitle(argument);
                }
                return new RemoveCommandNotebookMode(notebookTitleToRemove, sectionTitleToRemove, pageTitleToRemove,
                        appState);
            }
        case Exit.COMMAND_WORD:
            return new Exit(argument, appState);
        case Help.COMMAND_WORD:
            return new Help(argument);
        case DoneCommandTimetableMode.COMMAND_WORD:
            if (appState.getAppMode() == AppMode.TIMETABLE) {
                return new DoneCommandTimetableMode(argument, appState);
            } else {
                throw new IncorrectAppModeException();
            }
        case ModeSwitch.COMMAND_WORD:
            return new ModeSwitch(argument, appState);
        default:
            throw new InvalidCommandException(userInput);
        }
    }
}
