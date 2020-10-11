package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.TASK_ID;


public class TaskCommand {
    public void addTaskCommand(Hashtable<String, String> tasks, Ui ui, ArrayList<Project> projectList)
            throws DukeException {

        String title;
        String description;
        String priority;


        if (tasks.get(TITLE) != null) {
            title = tasks.get(TITLE);
        } else {
            throw new DukeException("Please enter a title!");
        }
        if (tasks.get(DESCRIPTION) != null) {
            description = tasks.get(DESCRIPTION);
        } else {
            throw new DukeException("Please enter a description!");
        }
        if (tasks.get(PRIORITY) != null) {
            priority = tasks.get(PRIORITY);
        } else {
            throw new DukeException("Please enter a priority!");
        }
        Project proj = projectList.get(0);
        Task task = new Task(title, description, priority);
        proj.backlog.addTask(task);

    }

    public void deleteTaskCommand(ArrayList<String> taskId, Ui ui, ArrayList<Project> projectList) {

        Project proj = projectList.get(0);
        for (String id : taskId) {
            try {
                int backlogId = Integer.parseInt(id) - 1;
                if (backlogId < proj.backlog.size()) {
                    ui.printTaskRemoved(proj.backlog.backlogTasks.get(backlogId));
                    proj.backlog.backlogTasks.remove(backlogId);
                } else {
                    ui.displayInvalidId();
                }
            } catch (NumberFormatException e) {
                ui.printError("Task id is not an integer.");
            }
        }
    }

    public void viewTaskCommand(ArrayList<String> taskId, Ui ui, ArrayList<Project> projectList) {

        Project proj = projectList.get(0);
        for (String id : taskId) {
            Task task = null;
            try {
                int backlogId = Integer.parseInt(id) - 1;
                if (backlogId < proj.backlog.backlogTasks.size()) {
                    task = proj.backlog.getTask(backlogId);
                    ui.displayTask(task);
                } else {
                    ui.displayInvalidId();
                }
            } catch (NumberFormatException e) {
                ui.printError("Task id is not an integer.");
            }
        }
    }

    public void changeTaskPriorityCommand(Hashtable<String, String> tasks, ArrayList<Project> projectList)
            throws DukeException {
        /*
        Example of how to use the hashtable and how to throw the exception.
         */
        Project proj = projectList.get(0);
        Task task;
        int id;
        String priority;
        if (tasks.get(TASK_ID) != null) {
            id = Integer.parseInt(tasks.get(TASK_ID));
        } else {
            throw new DukeException("invalid task id");
        }
        if (tasks.get(PRIORITY) != null) {
            priority = tasks.get(PRIORITY);
        } else {
            throw new DukeException("no priority");
        }

        task  = proj.backlog.getTask(id);
        task.setPriority(priority);


        /* Insert actual code for changing seedu.duke.task priority here.
        .
        .
        .
         */
    }

    public void doneTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {
        /*
           For testing purposes only, to be deleted.
         */
        Project proj = projectList.get(0);
        for (String id : taskId) {
            Task task = null;
            try {
                int backlogId = Integer.parseInt(id) - 1;
                if (backlogId < proj.backlog.backlogTasks.size()) {
                    task = proj.backlog.getTask(backlogId);
                    task.setAsDone();
                    Ui.displayTaskDone(id);
                } else {
                    Ui.displayInvalidId();
                }
            } catch (NumberFormatException e) {
                Ui.printError("Task id is not an integer.");
            }
        }

        /* Insert actual code for completing tasks here.
        .
        .
        .
         */
    }
}

