package seedu.duke.command.task;

import seedu.duke.sprint.Sprint;
import seedu.duke.ui.Messages;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TASK_ID;


public class TaskCommand {
    public void addTaskCommand(Hashtable<String, String> tasks, ArrayList<Project> projectList)
            throws DukeException {

        String title;
        String description;
        String priority;

        if (!tasks.containsKey(TITLE) || !tasks.containsKey(DESCRIPTION)
                || !tasks.containsKey(PRIORITY)) {
            throw new DukeException("Missing parameters.");
        }

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
        try {
            Project proj = projectList.get(0);
            proj.getProjectBacklog().addTask(title, description, priority);
            Task addedTask = proj.getProjectBacklog().getTask(proj.getProjectBacklog().getNextId() - 1);
            Ui.showToUserLn("Task successfully created.");
            Ui.showToUserLn(addedTask.toString());

        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }

    }

    public void deleteTaskCommand(ArrayList<String> taskIdString, ArrayList<Project> projectList) {
        try {
            Project proj = projectList.get(0);
            if (taskIdString.isEmpty()) {
                Ui.showError("Missing parameters.");
            }
            for (String id : taskIdString) {
                try {
                    int taskId = Integer.parseInt(id);
                    if (proj.getProjectBacklog().checkTaskExist(taskId)) {
                        Task task = proj.getProjectBacklog().getTask(taskId);
                        Ui.showToUserLn("The corresponding task "
                                + task.getTitle()
                                + "has been removed from project.");
                        proj.getProjectBacklog().removeTask(taskId);
                        ArrayList<Sprint> allSprints = proj.getAllSprints().getSprintList();
                        for (Sprint sprint : allSprints) {
                            if (sprint.checkTaskExist(taskId)) {
                                sprint.removeSprintTask(taskId);
                            }
                        }
                    } else {
                        Ui.showError(Messages.MESSAGE_INVALID_ID);
                    }
                } catch (NumberFormatException e) {
                    Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }

    public void viewTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {

        try {
            Project proj = projectList.get(0);
            if (taskId.isEmpty()) {
                Ui.showError("Missing parameters.");
            }
            Ui.showToUserLn("The details of the tasks are as follows: ");
            for (String id : taskId) {
                Task task;
                try {
                    int backlogId = Integer.parseInt(id);
                    if (backlogId < proj.getProjectBacklog().backlogTasks.size()) {
                        task = proj.getProjectBacklog().getTask(backlogId);
                        Ui.showToUserLn(task.toString());
                        //Ui.showToUserLn("\t Title: " + task.getTitle());
                    } else {
                        Ui.showError(Messages.MESSAGE_INVALID_ID);
                    }
                } catch (NumberFormatException e) {
                    Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }

    public void changeTaskPriorityCommand(Hashtable<String, String> tasks, ArrayList<Project> projectList)
            throws DukeException {

        Task task;
        int id;
        String priority;

        if (!tasks.containsKey(TASK_ID) || !tasks.containsKey(PRIORITY)) {
            throw new DukeException("Missing parameters.");
        }

        if (tasks.get(TASK_ID) != null) {
            id = Integer.parseInt(tasks.get(TASK_ID));
        } else {
            throw new DukeException("Task ID entered is invalid!");
        }
        if (tasks.get(PRIORITY) != null) {
            priority = tasks.get(PRIORITY).trim();
        } else {
            throw new DukeException("Please enter a priority!");
        }
        try {
            Project proj = projectList.get(0);
            try {
                task = proj.getProjectBacklog().getTask(id);
                task.setPriority(priority);
                Ui.showToUserLn("The task " + task.getTitle() + "has its priority changed to:");
                Ui.showToUserLn("\t" + task.getPriority());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task ID entered is out of bounds!");
            } catch (IllegalArgumentException e) {
                throw new DukeException("Priority entered is invalid!");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }

    public void doneTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {

        try {
            Project proj = projectList.get(0);
            for (String id : taskId) {
                Task task;
                try {
                    int backlogId = Integer.parseInt(id);
                    if (backlogId <= proj.getProjectBacklog().backlogTasks.size()) {
                        task = proj.getProjectBacklog().getTask(backlogId);
                        task.setAsDone();
                        Ui.showToUserLn(task.getTitle() + "has been marked as done.");
                    } else {
                        Ui.showError(Messages.MESSAGE_INVALID_ID);
                    }
                } catch (NumberFormatException e) {
                    Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
                } catch (IndexOutOfBoundsException e) {
                    Ui.showError("There are no projects! Please create a project first.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }
}

