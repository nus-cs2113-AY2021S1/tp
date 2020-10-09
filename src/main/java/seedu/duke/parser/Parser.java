package seedu.duke.parser;

import seedu.duke.command.MemberCommand;
import seedu.duke.command.ProjectCommand;
import seedu.duke.command.SprintCommand;
import seedu.duke.command.TaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.BYE;
import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.DELETE;
import static seedu.duke.command.CommandSummary.PROJECT;
import static seedu.duke.command.CommandSummary.TASK;
import static seedu.duke.command.CommandSummary.MEMBER;
import static seedu.duke.command.CommandSummary.SPRINT;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.DONE;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.ASSIGN;
import static seedu.duke.command.CommandSummary.TASK_ID;


public class Parser {
    //Groups of 3: (command) (action) (options)
    private static final Pattern CMD_PATTERN = Pattern.compile("(\\w+)\\s\\/(\\w+)\\s(.+)");
    //Groups of 2: (option name) (option value)
    private static final Pattern ARGS_PATTERN = Pattern.compile("-(\\w+)\\s([^-]+)");
    private final Hashtable<String, String> parameters = new Hashtable<>();
    private  ArrayList<String> params = new ArrayList<>();


    public void parser(Ui ui, ArrayList<Project> projectList) {
        String userInput = ui.readLine();

        if (userInput.equals(BYE)) {
            System.out.println(BYE);
            System.exit(0);
        }

        Matcher cmdMatcher = CMD_PATTERN.matcher(userInput);
        if (cmdMatcher.matches()) { //Need to check if it matches, groupCount will always show 3
            String command = cmdMatcher.group(1); //capture first group (command)
            String action = cmdMatcher.group(2); //capture 2nd group (action)
            String rawArgs = cmdMatcher.group(3); //capture 3rd group (options)
            Matcher parameterMatcher = ARGS_PATTERN.matcher(rawArgs); //match the option

            if (!rawArgs.contains("-")) {
                String[] arguments = rawArgs.split(" ");
                params.addAll(Arrays.asList(arguments));
            } else {
                while (parameterMatcher.find()) { //go through each occurrence of options
                    //put the options into the hashtable (similar to dictionary)
                    parameters.put(parameterMatcher.group(1), parameterMatcher.group(2));
                }
            }

            switch (command.toLowerCase()) {
            case PROJECT:
                switch (action.toLowerCase()) {
                case CREATE:
                    try {
                        new ProjectCommand().createProjectCommand(parameters, ui, projectList);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case MEMBER:
                switch (action.toLowerCase()) {
                case ADD:
                    new MemberCommand().addMemberCommand(params, ui, projectList);
                    break;
                case DELETE:
                    new MemberCommand().deleteMemberCommand(params, ui, projectList);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case TASK:
                switch (action.toLowerCase()) {
                case ADD:
                    try {
                        new TaskCommand().addTaskCommand(parameters, ui, projectList);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                case DELETE:
                    new TaskCommand().deleteTaskCommand(params, ui, projectList);
                    break;
                case VIEW:
                    new TaskCommand().viewTaskCommand(params, ui, projectList);
                    break;
                case PRIORITY:
                    try {
                        new TaskCommand().changeTaskPriorityCommand(TASK_ID, PRIORITY, parameters);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                case DONE:
                    new TaskCommand().doneTaskCommand(params);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case SPRINT:
                switch (action.toLowerCase()) {
                case CREATE:
                    new SprintCommand().createSprintCommand(params);
                    break;
                case ADD:
                    new SprintCommand().addSprintTaskCommand(params);
                    break;
                case DELETE:
                    new SprintCommand().deleteSprintTaskCommand(params);
                    break;
                case VIEW:
                    new SprintCommand().viewSprintCommand(params);
                    break;
                case ASSIGN:
                    new SprintCommand().assignSprintTaskCommand(params);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            default:
                System.out.println("Invalid command!");
            }
        } else {
            System.out.println("Invalid command!");
        }
    }
}