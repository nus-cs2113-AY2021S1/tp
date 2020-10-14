package seedu.duke.parser;

import seedu.duke.command.member.MemberCommand;
import seedu.duke.command.project.ProjectCommand;
import seedu.duke.command.sprint.AllocateSprintTaskCommand;
import seedu.duke.command.sprint.ViewSprintCommand;
import seedu.duke.command.sprint.AddSprintTaskCommand;
import seedu.duke.command.sprint.CreateSprintCommand;
import seedu.duke.command.sprint.DeleteSprintTaskCommand;
import seedu.duke.command.task.TaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

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


public class Parser {
    //Groups of 3: (command) (action) (options)
    private static final Pattern CMD_PATTERN = Pattern.compile("(\\w+)\\s\\/(\\w+)\\s(.+)");
    //Groups of 2: (option name) (option value)
    private static final Pattern ARGS_PATTERN = Pattern.compile("-(\\w+)\\s([^-]+)");
    private final Hashtable<String, String> parameters = new Hashtable<>();
    private  ArrayList<String> params = new ArrayList<>();


    public String parser(String userInput, ArrayList<Project> projectList) {

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
                        new ProjectCommand().createProjectCommand(parameters, projectList);
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
                    new MemberCommand().addMemberCommand(params, projectList);
                    break;
                case DELETE:
                    new MemberCommand().deleteMemberCommand(params, projectList);
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
                        new TaskCommand().addTaskCommand(parameters, projectList);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                case DELETE:
                    new TaskCommand().deleteTaskCommand(params, projectList);
                    break;
                case VIEW:
                    new TaskCommand().viewTaskCommand(params, projectList);
                    break;
                case PRIORITY:
                    try {
                        new TaskCommand().changeTaskPriorityCommand(parameters, projectList);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                case DONE:
                    new TaskCommand().doneTaskCommand(params, projectList);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action!");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case SPRINT:
                Hashtable<String, String> HCparameters = new Hashtable<>();

                switch (action.toLowerCase()) {
                case CREATE:
                    HCparameters.put("goal", "fakegoal");
                    HCparameters.put("start", "20201010");
                    new CreateSprintCommand(HCparameters, projectList).execute();
                    break;
                case ADD:
                    HCparameters.put("0", "1");
                    HCparameters.put("1", "2");
                    HCparameters.put("2", "4");
                    HCparameters.put("3", "6");
                    new AddSprintTaskCommand(HCparameters, projectList).execute();
                    break;
                case DELETE:
                    HCparameters.put("0", "2");
                    new DeleteSprintTaskCommand(HCparameters, projectList).execute();
                    break;
                case VIEW:
                    new ViewSprintCommand(HCparameters, projectList).execute();
                    break;
                case ASSIGN:
                    HCparameters.put("taskid", "1");
                    HCparameters.put("0", "amy");
                    HCparameters.put("1", "john");
                    new AllocateSprintTaskCommand(HCparameters, projectList).execute();
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action!");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            default:
                return "Invalid command!";
            }
        } else {
            return "Invalid command!";
        }
        return null;
    }
}