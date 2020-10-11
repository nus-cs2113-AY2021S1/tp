package seedu.duke.command.addcommand;

import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.exception.IncorrectDirectoryLevelException;
import seedu.duke.util.DateTime;

import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.Message.messageAddTaskSuccess;

public class AddTaskCommand extends AddCommand {
    public static final String COMMAND_WORD = "addt";
    private String moduleCode;
    private String description;
    private DateTime deadline;
    private int priority;

    public AddTaskCommand(String moduleCode, String description, DateTime deadline, int priority) {
        this.moduleCode = moduleCode;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public AddTaskCommand(String moduleCode, String description, DateTime deadline) {
        // Dummy value for missing priority
        this(moduleCode,description, deadline, -1);
    }


    @Override
    public CommandResult execute() {
        try {
            Module parentModule = DirectoryTraverser.getModuleDirectory(moduleCode);
            Task toAdd = new Task(parentModule, description, deadline, priority);
            parentModule.getTasks().add(toAdd);
            return new CommandResult(messageAddTaskSuccess(description));
        } catch (TaskManager.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }
}
