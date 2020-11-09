//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;
import data.jobs.Deadline;
import data.jobs.Event;
import data.jobs.Task;
import data.jobs.ToDo;
import exceptions.CommandException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoadLinkedTasksAction extends Action {

    private String moduleCode;
    private ArrayList<String> tasksStrings;

    @Override
    public String act(Data data) throws Exception {
        SingleModule module = null;
        for (Item mod : data.mods) {
            if (((SingleModule) mod).moduleCode.equals(moduleCode)) {
                module = (SingleModule) mod;
                break;
            }
        }
        if (module == null) {
            throw new Exception(Constants.NOT_FOUND.replace("item", "module"));
        }
        StringBuilder builder = new StringBuilder("Added Tasks: ").append(Constants.WIN_NEWLINE);
        for (String task : tasksStrings) {
            Task myTask;
            if (task.contains(Constants.TODO_ICON)) {
                task = task.replace(Constants.TODO_ICON, Constants.ZERO_LENGTH_STRING).trim();
                boolean isDone = false;
                if (task.contains(Constants.TICK_ICON)) {
                    isDone = true;
                    task = task.replace(Constants.TICK_ICON, Constants.ZERO_LENGTH_STRING).trim();
                } else {
                    task = task.replace(Constants.CROSS_ICON, Constants.ZERO_LENGTH_STRING).trim();
                }
                myTask = new ToDo(task);
                if (isDone) {
                    myTask.markAsDone();
                }
            } else if (task.contains(Constants.DDL_ICON)) {
                task = task.replace(Constants.DDL_ICON, Constants.ZERO_LENGTH_STRING).trim();
                myTask = getTask(task, "by:");
            } else if (task.contains(Constants.EVENT_ICON)) {
                task = task.replace(Constants.EVENT_ICON, Constants.ZERO_LENGTH_STRING).trim();
                myTask = getTask(task, "at:");
            } else {
                throw new Exception("The task_string parameter has wrong format!" + Constants.WIN_NEWLINE);
            }
            module.taskList.add(myTask);
            builder.append(myTask.toString()).append(Constants.WIN_NEWLINE);
        }
        return builder.append("for module: ").append(moduleCode)
                .append(Constants.CMD_END).append(Constants.WIN_NEWLINE).append("Try \"detail ")
                .append(moduleCode).append("\" to check it out!").append(Constants.WIN_NEWLINE).toString();
    }

    private Task getTask(String task, String identity) throws Exception {
        boolean isDone = false;
        boolean isDeadline = identity.equals("by:");
        String errIdentity = isDeadline ? Constants.DEADLINE : Constants.EVENT;
        if (!task.contains(identity)) {
            throw new Exception("Inner command \"" + errIdentity + "\" has wrong format!" + Constants.WIN_NEWLINE);
        }
        if (task.contains(Constants.TICK_ICON)) {
            isDone = true;
            task = task.replace(Constants.TICK_ICON, Constants.ZERO_LENGTH_STRING).trim();
        } else {
            task = task.replace(Constants.CROSS_ICON, Constants.ZERO_LENGTH_STRING).trim();
        }
        task = task.replace(Constants.PARAM_LEFT, Constants.ZERO_LENGTH_STRING)
                .replace(Constants.PARAM_RIGHT, Constants.ZERO_LENGTH_STRING).trim();
        String[] separated = task.split(identity);
        String description = separated[0].trim();
        String date = separated[1].trim();
        Task myTask = isDeadline ? new Deadline(description, date) : new Event(description, date);
        if (isDone) {
            myTask.markAsDone();
        }
        return myTask;
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        moduleCode = "";
        tasksStrings = new ArrayList<>();
        super.prepare(args);
        if (flattenedArgs == null || flattenedArgs.length != 1) {
            throw new CommandException();
        }
        String[] argStrings = flattenedArgs[0].toFlatString().split(Constants.SPACE);
        if (argStrings.length != 2) {
            throw new CommandException();
        }
        moduleCode = argStrings[0].toUpperCase();
        if (moduleCode.matches("([A-Z])+([0-9])+[A-Z]*")) {
            String[] tasks = argStrings[1].split(Constants.COMMA);
            for (String task : tasks) {
                tasksStrings.add(task.replace(Constants.LINE_UNIT, Constants.SPACE));
            }
            if (tasksStrings.size() == 0) {
                throw new Exception("The second parameter has wrong format!" + Constants.WIN_NEWLINE);
            }
        } else {
            throw new Exception("The first parameter must be a module code!" + Constants.WIN_NEWLINE);
        }
    }
}
