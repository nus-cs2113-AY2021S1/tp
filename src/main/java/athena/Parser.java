package athena;

/**
 * Handles parsing of user input.
 */
public class Parser {

    public static final String COMMAND_WORD_DELIMITER = " ";
    public static final String NAME_DELIMITER = "n/";
    public static final String TIME_DELIMITER = "t/";
    public static final String DURATION_DELIMITER = "d/";
    public static final String DEADLINE_DELIMITER = "D/";
    public static final String RECURRENCE_DELIMITER = "r/";
    public static final String IMPORTANCE_DELIMITER = "i/";
    public static final String ADDITIONAL_NOTES_DELIMITER = "a/";

    /**
     * Get parameters description.
     * @param taskInformation String representing task information
     * @param delimiter String representing parameter delimiter
     * @param paramPosition Integer representing position of parameter
     * @param defaultValue String representing default value
     * @return Description of parameter
     */
    public static String getParameterDesc(String taskInformation, String delimiter, int paramPosition, String defaultValue) {
        String param;
        if (paramPosition == -1) {
            param = defaultValue;
        } else {
            String[] retrieveParamInfo = taskInformation.split(delimiter, 2);
            String retrievedParamInfo = retrieveParamInfo[1];
            int paramNextSlash = retrievedParamInfo.indexOf("/");
            if (paramNextSlash == -1) {
                param = retrievedParamInfo;
            } else {
                param = retrievedParamInfo.substring(0, (paramNextSlash - 2));
            }
        }
        return param;
    }

    /**
     * Parses user input and recognises what type of command
     * and parameters the user typed.
     * @param input String representing user input
     * @return new Command object based on what the user input is
     */
    public static Command parse(String input) {
        String[] commandAndDetails = input.split(COMMAND_WORD_DELIMITER, 2);
        String commandType = commandAndDetails[0];
        String taskInfo = "";
        if (commandAndDetails.length > 1) {
            taskInfo = commandAndDetails[1];
        }
        Command command = null;

        int namePos = taskInfo.indexOf(NAME_DELIMITER);
        int timePos = taskInfo.indexOf(TIME_DELIMITER);
        int durationPos = taskInfo.indexOf(DURATION_DELIMITER);
        int deadlinePos = taskInfo.indexOf(DEADLINE_DELIMITER);
        int recurrencePos = taskInfo.indexOf(RECURRENCE_DELIMITER);
        int importancePos = taskInfo.indexOf(IMPORTANCE_DELIMITER);
        int addNotesPos = taskInfo.indexOf(ADDITIONAL_NOTES_DELIMITER);

        if (commandType.equals("add")) {
            String nullDefault = "";
            String name = getParameterDesc(taskInfo, NAME_DELIMITER, namePos, nullDefault);
            String time = getParameterDesc(taskInfo, TIME_DELIMITER, timePos, nullDefault);
            String durationDefault = "1 hour";
            String duration = getParameterDesc(taskInfo, DURATION_DELIMITER, durationPos, durationDefault);
            String deadlineDefault = "No deadline";
            String deadline = getParameterDesc(taskInfo, DEADLINE_DELIMITER, deadlinePos, deadlineDefault);
            String recurrenceDefault = "Once-off, happening today";
            String recurrence = getParameterDesc(taskInfo, RECURRENCE_DELIMITER, recurrencePos, recurrenceDefault);
            String importanceDefault = "medium";
            String importance = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, importanceDefault);
            String notesDefault = "No notes";
            String notes = getParameterDesc(taskInfo, ADDITIONAL_NOTES_DELIMITER, addNotesPos, notesDefault);
            command = new AddCommand(name, time, duration, deadline, recurrence, importance, notes);
        } else if (commandType.equals("edit")) {
            int indexNextSlash = taskInfo.indexOf("/");
            int index = Integer.parseInt(taskInfo.substring(0, (indexNextSlash - 2)));
            String nullValue = "";
            String name = getParameterDesc(taskInfo, NAME_DELIMITER, namePos, nullValue);
            String time = getParameterDesc(taskInfo, TIME_DELIMITER, timePos, nullValue);
            String duration = getParameterDesc(taskInfo, DURATION_DELIMITER, durationPos, nullValue);
            String deadline = getParameterDesc(taskInfo, DEADLINE_DELIMITER, deadlinePos, nullValue);
            String recurrence = getParameterDesc(taskInfo, RECURRENCE_DELIMITER, recurrencePos, nullValue);
            String importance = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, nullValue);
            String notes = getParameterDesc(taskInfo, ADDITIONAL_NOTES_DELIMITER, addNotesPos, nullValue);
            command = new EditCommand(index, name, time, duration, deadline, recurrence, importance, notes);
        } else if (commandType.equals("list")) {
            String nullValue = "";
            String importance = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, nullValue);
            command = new ListCommand(importance);
        } else if (commandType.equals("done")) {
            int taskIndex = Integer.parseInt(taskInfo);
            command = new DoneCommand(taskIndex);
        } else if (commandType.equals("delete")) {
            int taskIndex = Integer.parseInt(taskInfo);
            command = new DeleteCommand(taskIndex);
        } else if (commandType.equals("help")) {
            command = new HelpCommand();
        } else {
            if (commandType.equals("bye")) {
                command = new ExitCommand();
            }
        }
        return command;
    }
}