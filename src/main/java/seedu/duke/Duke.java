package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.sprintcommands.CreateSprintCommand;
import seedu.duke.commands.sprintcommands.ViewSprintCommand;
import seedu.duke.data.Member;
import seedu.duke.data.Project;
import seedu.duke.data.ProjectBacklog;
import seedu.duke.data.Task;
import seedu.duke.ui.TextUi;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Duke {

    private static TextUi ui = new TextUi();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.showWelcomeScreen();

        //project /create -title FakeTitle -desc FakeDescription -duration 90 -sd 10
        Project proj = new Project("ProjTitle", "ProjDescription", "60", "10");
        ui.showToUser("Project created.");

        //member /add james amy
        List<String> members = Arrays.asList("james", "amy", "bob");
        proj.getMembers().addMember(members);
        ui.showToUser("Members added.");

        //task /add -title <title> -desc <description> -priority <category>
        Task task1 = new Task("Task1Title", "Task1Desc", "LOW");
        Task task2 = new Task("Task2Title", "Task2Desc", "MED");
        Task task3 = new Task("Task3Title", "Task3Desc", "HIGH");
        ProjectBacklog backlog = proj.getProjectBacklog();
        backlog.addTask(task1);
        backlog.addTask(task2);
        backlog.addTask(task3);

        String command = "sprint";
        String action = "create";
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "sprint1goal");
        parameters.put("start", "20201212");
        Command cmd = new CreateSprintCommand(parameters);
        ui.showToUser("\n--------- CreateSprint ---------");
        for (int i = 0; i < 7; i++) {
            parameters.remove("goal");
            parameters.put("goal", "goal" + (i + 1));
            cmd.execute(proj, ui);
        }

        ui.showToUser("\n--------- ViewSprint ---------");
        cmd = new ViewSprintCommand(new Hashtable<>());
        cmd.execute(proj, ui);
    }
}