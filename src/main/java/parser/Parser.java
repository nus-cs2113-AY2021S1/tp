package parser;

import access.Access;

import commands.AddCardCommand;
import commands.AddChapterCommand;
import commands.AddCommand;
import commands.AddModuleCommand;
import commands.BackAdminCommand;
import commands.BackCommand;
import commands.BackModuleCommand;
import commands.Command;
import commands.EditCardCommand;
import commands.EditChapterCommand;
import commands.EditCommand;
import commands.EditModuleCommand;
import commands.ExcludeCommand;
import commands.ExitCommand;
import commands.GoChapterCommand;
import commands.GoCommand;
import commands.GoModuleCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import commands.ListCardCommand;
import commands.ListCommand;
import commands.ListDueCommand;
import commands.PreviewCommand;
import commands.RateCommand;
import commands.RemoveCardCommand;
import commands.RemoveChapterCommand;
import commands.RemoveCommand;
import commands.RemoveModuleCommand;
import commands.RescheduleCommand;
import commands.ReviseCommand;
import commands.ShowRateCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import storage.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static common.Messages.ADMIN;
import static common.Messages.CARD;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_DATE_FORMAT;
import static common.Messages.MESSAGE_EXTRA_ARGS;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MESSAGE_INVALID_ACCESS;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_INVALID_COMMAND_TYPE;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MESSAGE_MISSING_INDEX;
import static common.Messages.MESSAGE_NON_INTEGER;
import static common.Messages.MESSAGE_NO_NAME;
import static common.Messages.MESSAGE_NO_QUESTION_AND_ANSWER;
import static common.Messages.MESSAGE_NO_QUESTION_OR_ANSWER;
import static common.Messages.MODULE;

public class Parser {
    private static final String QUESTION_ANSWER_PREFIX = " \\| ";
    private static final String QUESTION_PREFIX = "q:";
    private static final String ANSWER_PREFIX = "a:";

    public static Command parse(String fullCommand, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(fullCommand);
        String commandType = commandTypeAndArgs[0].trim().toLowerCase();
        String commandArgs = commandTypeAndArgs[1].trim();

        System.out.println("Command Type: " + commandType);

        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return prepareList(commandArgs, access);
        case AddCommand.COMMAND_WORD:
            return prepareAdd(commandArgs, access);
        case RemoveCommand.COMMAND_WORD:
            return prepareRemove(commandArgs, access);
        case ReviseCommand.COMMAND_WORD:
            return prepareRevise(commandArgs, access);
        case ExitCommand.COMMAND_WORD:
            return prepareExit(commandArgs);
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(commandArgs);
        case EditCommand.COMMAND_WORD:
            return prepareEdit(commandArgs, access);
        case BackCommand.COMMAND_WORD:
            return prepareBack(commandArgs, access);
        case GoCommand.COMMAND_WORD:
            return prepareGo(commandArgs, access);
        case ListDueCommand.COMMAND_WORD:
            return prepareListDue(commandArgs);
        case HistoryCommand.COMMAND_WORD:
            return prepareHistory(commandArgs);
        case RateCommand.COMMAND_WORD:
            return prepareRate(commandArgs);
        case ShowRateCommand.COMMAND_WORD:
            return prepareShowRate(commandArgs);
        case PreviewCommand.COMMAND_WORD:
            return preparePreview(commandArgs);
        case ExcludeCommand.COMMAND_WORD:
            return prepareExclude(commandArgs);
        case RescheduleCommand.COMMAND_WORD:
            return prepareReschedule(commandArgs);
        default:
            throw new InvalidInputException(MESSAGE_INVALID_COMMAND_TYPE);
        }
    }

    private static Command prepareShowRate(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS,
                    ShowRateCommand.COMMAND_WORD) + ShowRateCommand.MESSAGE_USAGE);
        }
        return new ShowRateCommand();
    }

    private static RateCommand prepareRate(String commandArgs)
            throws InvalidInputException, IncorrectAccessLevelException {
        int chapterIndex;
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + RateCommand.MESSAGE_USAGE);
        }
        try {
            chapterIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_NON_INTEGER, CHAPTER)
                    + RateCommand.MESSAGE_USAGE);
        }
        return new RateCommand(chapterIndex);
    }

    private static Command prepareHistory(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            LocalDate date = java.time.LocalDate.now();
            commandArgs = date.toString();
        }

        try {
            LocalDate date = LocalDate.parse(commandArgs);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_DATE_FORMAT + HistoryCommand.MESSAGE_USAGE);
        }

        return new HistoryCommand(commandArgs);
    }

    private static Command prepareGo(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        String level = getLevel(access);
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + GoCommand.MESSAGE_USAGE);
        }

        switch (level) {
        case ADMIN:
            return new GoModuleCommand(commandArgs);
        case MODULE:
            return new GoChapterCommand(commandArgs);
        default:
            throw new IncorrectAccessLevelException("Go command can only be called "
                    + "at admin and module level.");
        }
    }

    private static Command prepareBack(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        String level = getLevel(access);
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + BackCommand.MESSAGE_USAGE);
        }

        switch (level) {
        case CHAPTER:
            return new BackModuleCommand();
        case MODULE:
            return new BackAdminCommand();
        default:
            throw new IncorrectAccessLevelException("Back command can only be called "
                    + "at module and chapter level.");
        }
    }

    private static String[] splitCommandTypeAndArgs(String userCommand) {
        String[] commandTypeAndParams = userCommand.trim().split(" ", 2);
        if (commandTypeAndParams.length != 2) {
            commandTypeAndParams = new String[]{commandTypeAndParams[0], ""};
        }
        return commandTypeAndParams;
    }

    private static Command prepareList(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ListCommand.COMMAND_WORD)
                    + ListCommand.MESSAGE_USAGE);
        }

        if (access.isAdminLevel()) {
            return new ListCommand();
        } else if (access.isModuleLevel()) {
            return new ListCommand();
        } else if (access.isChapterLevel()) {
            return new ListCardCommand();
        } else {
            assert !access.isChapterLevel() && !access.isAdminLevel() && !access.isModuleLevel() : access.getLevel();
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    ListCommand.COMMAND_WORD));
        }
    }

    private static Command prepareAdd(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        String level = getLevel(access);
        switch (level) {
        case ADMIN:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + AddModuleCommand.MODULE_MESSAGE_USAGE);
            }
            return prepareAddModule(commandArgs);
        case MODULE:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + AddChapterCommand.CHAPTER_MESSAGE_USAGE);
            }
            return prepareAddChapter(commandArgs);
        default:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + AddCardCommand.MESSAGE_USAGE);
            }
            return prepareAddCard(commandArgs);
        }
    }

    private static Command prepareAddCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(QUESTION_ANSWER_PREFIX, 2);
            String question = parseQuestion(args[0]);
            String answer = parseAnswer(args[1]);
            if (question.isEmpty() || answer.isEmpty()) {
                throw new InvalidInputException(MESSAGE_NO_QUESTION_OR_ANSWER
                        + AddCardCommand.MESSAGE_USAGE);
            }
            return new AddCardCommand(question, answer);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.COMMAND_WORD)
                    + AddCardCommand.MESSAGE_USAGE));
        }
    }

    private static Command prepareAddChapter(String commandArgs) {
        return new AddChapterCommand(commandArgs);
    }

    private static Command prepareAddModule(String commandArgs) {
        return new AddModuleCommand(commandArgs);
    }

    private static String getLevel(Access access) throws IncorrectAccessLevelException {
        String level;
        if (access.isAdminLevel()) {
            level = ADMIN;
        } else if (access.isModuleLevel()) {
            level = MODULE;
        } else if (access.isChapterLevel()) {
            level = CHAPTER;
        } else {
            assert !access.isChapterLevel() && !access.isAdminLevel() && !access.isModuleLevel() : access.getLevel();
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    AddCommand.COMMAND_WORD));
        }
        return level;
    }

    private static Command prepareRemove(String commandArgs, Access access) throws InvalidInputException,
            IncorrectAccessLevelException {
        int removeIndex;
        String type = getType(access);
        String messageUsage = String.format(RemoveCommand.MESSAGE_USAGE, type, type.toUpperCase());
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, type)
                    + messageUsage);
        }
        try {
            removeIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, type)
                    + messageUsage);
        }
        switch (type) {
        case MODULE:
            return new RemoveModuleCommand(removeIndex);
        case CHAPTER:
            return new RemoveChapterCommand(removeIndex);
        default:
            return new RemoveCardCommand(removeIndex);
        }
    }

    private static String getType(Access access) throws IncorrectAccessLevelException {
        String type;
        if (access.isAdminLevel()) {
            type = MODULE;
        } else if (access.isModuleLevel()) {
            type = CHAPTER;
        } else if (access.isChapterLevel()) {
            type = CARD;
        } else {
            throw new IncorrectAccessLevelException("Command called at the wrong level.\n");
        }
        return type;
    }

    private static Command prepareEdit(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (access.isAdminLevel() && commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + EditModuleCommand.MESSAGE_USAGE);
        }
        if (access.isModuleLevel() && commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + EditChapterCommand.MESSAGE_USAGE);
        }
        if (access.isChapterLevel() && commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + EditCardCommand.MESSAGE_USAGE);
        }

        if (access.isAdminLevel()) {
            return prepareEditModule(commandArgs);
        } else if (access.isModuleLevel()) {
            return prepareEditChapter(commandArgs);
        } else if (access.isChapterLevel()) {
            return prepareEditCard(commandArgs);
        } else {
            assert !access.isChapterLevel() && !access.isAdminLevel() && !access.isModuleLevel() : access.getLevel();
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    EditCommand.COMMAND_WORD));
        }
    }

    private static Command prepareEditModule(String commandArgs)
            throws InvalidInputException, IncorrectAccessLevelException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, MODULE)
                        + EditModuleCommand.MESSAGE_USAGE);
            }

            if (args[1].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_NO_NAME, MODULE)
                        + EditModuleCommand.MESSAGE_USAGE);
            }

            if (containsCardPrefix(args[1].trim().toLowerCase())) {
                throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                        ADMIN, CHAPTER));
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;
            return new EditModuleCommand(editIndex, args[1].trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, MODULE)
                    + EditModuleCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditModuleCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEditChapter(String commandArgs)
            throws InvalidInputException, IncorrectAccessLevelException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, CHAPTER)
                        + EditChapterCommand.MESSAGE_USAGE);
            }

            if (args[1].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_NO_NAME, CHAPTER)
                        + EditChapterCommand.MESSAGE_USAGE);
            }

            if (containsCardPrefix(args[1].trim().toLowerCase())) {
                throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                        MODULE, CHAPTER));
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;
            return new EditChapterCommand(editIndex, args[1].trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, CHAPTER)
                    + EditChapterCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditChapterCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEditCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, CARD)
                        + EditCardCommand.MESSAGE_USAGE);
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            String[] questionAndAnswer = args[1].trim().split(QUESTION_ANSWER_PREFIX, 2);
            String question = parseQuestion(questionAndAnswer[0]);
            String answer = parseAnswer(questionAndAnswer[1]);

            if (question.isEmpty() && answer.isEmpty()) {
                throw new InvalidInputException(MESSAGE_NO_QUESTION_AND_ANSWER + EditCardCommand.MESSAGE_USAGE);
            }

            return new EditCardCommand(editIndex, question, answer);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, CARD)
                    + EditCardCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditCardCommand.MESSAGE_USAGE);
        }
    }

    private static boolean containsCardPrefix(String arg) {
        return arg.contains("q:") || arg.contains("a:");
    }

    private static String parseQuestion(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(QUESTION_PREFIX))) {
            throw new InvalidInputException("There needs to be a \"q:\" prefix before the question.\n"
                    + "Example: " + AddCommand.COMMAND_WORD + AddCommand.CARD_PARAMETERS + "\n"
                    + "         " + EditCommand.COMMAND_WORD + EditCommand.CARD_PARAMETERS);
        }

        return arg.substring(2).trim();
    }

    private static String parseAnswer(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(ANSWER_PREFIX))) {
            throw new InvalidInputException("There needs to be a \"a:\" prefix before the answer.\n"
                    + "Example: " + AddCommand.COMMAND_WORD + AddCommand.CARD_PARAMETERS + "\n"
                    + "         " + EditCommand.COMMAND_WORD + EditCommand.CARD_PARAMETERS);
        }

        return arg.substring(2).trim();
    }

    private static Command prepareRevise(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (access.isAdminLevel() || access.isChapterLevel()) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    access.getLevel(), MODULE));
        } else if (commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, CHAPTER)
                    + ReviseCommand.MESSAGE_USAGE);
        }
        int chapterIndex;
        try {
            chapterIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_NON_INTEGER, CHAPTER)
                    + ReviseCommand.MESSAGE_USAGE);
        }
        return new ReviseCommand(chapterIndex);
    }

    private static Command prepareExit(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ExitCommand.COMMAND_WORD));
        }
        return new ExitCommand();
    }

    private static Command prepareHelp(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, HelpCommand.COMMAND_WORD));
        }
        return new HelpCommand();
    }

    public static String parseQuestionInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.QUESTION_PREFIX))) {
            throw new InvalidFileFormatException("Questions in the file should begin with [Q].");
        }

        String question = arg.substring(3).trim();
        if (question.isEmpty()) {
            throw new InvalidFileFormatException("There should be a question after [Q] in the file.");
        }

        return question;
    }

    public static String parseAnswerInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.ANSWER_PREFIX))) {
            throw new InvalidFileFormatException("Answers in the file should begin with [A].");
        }

        String answer = arg.substring(3).trim();
        if (answer.isEmpty()) {
            throw new InvalidFileFormatException("There should be a answer after [A] in the file.");
        }

        return answer;
    }

    public static String parsePreIntervalInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.PREVIOUS_INTERVAL_PREFIX))) {
            throw new InvalidFileFormatException("Previous intervals in the file should begin with [P].");
        }

        String preInterval = arg.substring(3).trim();
        if (preInterval.isEmpty()) {
            throw new InvalidFileFormatException("There should be a interval after [P] in the file.");
        }

        return preInterval;
    }

    private static Command prepareListDue(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ListDueCommand.COMMAND_WORD));
        }
        return new ListDueCommand();
    }

    public static String parseTaskNameInFile(String arg) throws InvalidFileFormatException {
        String name = arg.trim();
        if (name.isEmpty()) {
            throw new InvalidFileFormatException("There should be a name of the completed task.");
        }

        return name;
    }

    public static String parsePercentInFile(String arg) throws InvalidFileFormatException {
        String percent = arg.trim().substring(0,3);
        if (percent.isEmpty()) {
            throw new InvalidFileFormatException(
                    "There should be a number to indicate how many tasks have completed.");
        }
        return percent;
    }

    public static String parseRatingInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.RATING_PREFIX))) {
            throw new InvalidFileFormatException("Answers in the file should begin with [R].");
        }

        String rating = arg.substring(3).trim();
        if (rating.isEmpty()) {
            throw new InvalidFileFormatException("There should be a rating after [R] in the file.");
        }

        return rating;
    }

    private static Command preparePreview(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_EXTRA_ARGS + PreviewCommand.MESSAGE_USAGE);
        }
        return new PreviewCommand();
    }

    private static Command prepareExclude(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + ExcludeCommand.MESSAGE_USAGE);
        }
        return new ExcludeCommand(commandArgs);
    }

    private static Command prepareReschedule(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, CHAPTER)
                        + RescheduleCommand.MESSAGE_USAGE);
            }

            if (args[1].trim().isEmpty()) {
                throw new InvalidInputException("The due date is missing.\n"
                        + RescheduleCommand.MESSAGE_USAGE);
            }

            int index = Integer.parseInt(args[0].trim()) - 1;
            LocalDate dueDate = LocalDate.parse(args[1].trim());
            if (dueDate.isBefore(LocalDate.now())) {
                throw new InvalidInputException("You cannot enter a due date that is before today.\n");
            }
            return new RescheduleCommand(index, dueDate);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, CHAPTER)
                    + RescheduleCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RescheduleCommand.COMMAND_WORD) + RescheduleCommand.MESSAGE_USAGE);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_DATE_FORMAT + RescheduleCommand.MESSAGE_USAGE);
        }
    }
}


