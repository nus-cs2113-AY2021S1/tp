package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.sprintcommands.AddSprintTaskCommand;
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
        ui.showToUser("Dummy Project created.");

        //member /add james amy
        List<String> members = Arrays.asList("james", "amy", "bob");
        proj.getMembers().addMember(members);
        ui.showToUser("Dummy Members added.");

        //task /add -title <title> -desc <description> -priority <category>
        ProjectBacklog backlog = proj.getProjectBacklog();
        for (int i = 0; i < 6; i++) {
            Task task = new Task("Task" + (i + 1) + "Title", "Task" + (i + 1) + "Desc", "LOW");
            backlog.addTask(task);
            //backlog.viewTask(Integer.toString(i + 1), ui);
        }
        ui.showToUser("Dummy Tasks added.");

        String command = "sprint";
        String action = "create";
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("goal", "sprint1goal");
        parameters.put("start", "20201001");
        Command cmd = new CreateSprintCommand(parameters);
        ui.showToUser("\n--------- CreateSprint ---------");
        for (int i = 0; i < 7; i++) {
            parameters.remove("goal");
            parameters.put("goal", "goal" + (i + 1));
            cmd.execute(proj, ui);
        }

        ui.showToUser("\n--------- ViewSprint ---------");
        parameters = new Hashtable<>();
        cmd = new ViewSprintCommand(parameters);
        cmd.execute(proj, ui);

        ui.showToUser("\n--------- AddSprintTask ---------");
        parameters = new Hashtable<>();
        parameters.put("0", "1"); // (index, taskid)
        parameters.put("1", "2");
        parameters.put("2", "4");
        parameters.put("3", "6");

        cmd = new AddSprintTaskCommand(parameters);
        cmd.execute(proj, ui);

    }
}