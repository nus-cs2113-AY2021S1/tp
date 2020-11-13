package seedu.ezmanager.project;

import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project implements Comparable<Project> {
    protected String projectName;
    protected boolean isDone;
    private ArrayList<Task> tasks;
    private String projectDescription;
    private LocalDate projectDeadline;
    private ArrayList<TeamMember> teamMembers;

    public Project(String projectName) {
        this.projectName = projectName;
        this.isDone = false;
        this.tasks = new ArrayList<>();
        this.teamMembers = new ArrayList<>();
        this.projectDescription = "<project description empty>";
        this.projectDeadline = null;
    }


    public void sortTasksList() {
        //Collections.sort(tasks);
    }

    public int compareTo(Project project) {
        if (getProjectDeadline() == null || project.getProjectDeadline() == null) {
            return 0;
        }
        return getProjectDeadline().compareTo(project.getProjectDeadline());
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }


    public int getNumberOfTask() {
        return tasks.size();
    }

    /**
     * Counts the total number of tasks in the current project.
     * @return Number of tasks marked as done.
     */
    public int getNumberOfFinishedTask() {
        int finishedTaskCounter = 0;
        for (Task task : tasks) {
            if (task.isDone()) {
                finishedTaskCounter++;
            }
        }
        return finishedTaskCounter;
    }

    public void addDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getDescription() {
        return projectDescription;
    }

    public String getProjectName() {
        return projectName;
    }

    public void addProjectDeadline(LocalDate date) {
        this.projectDeadline = date;
    }

    public LocalDate getProjectDeadline() {
        return this.projectDeadline;
    }

    public ArrayList<TeamMember> getTeamMembers() {
        return this.teamMembers;
    }

    public void addTeamMemberToProject(TeamMember addedMember) {
        teamMembers.add(addedMember);
    }

    /**
     * Returns details of the specified task.
     *
     * @return Details of Project
     */
    @Override
    public String toString() {
        if (!projectDescription.equals("") & projectDeadline != null) {
            return "Description: " + projectDescription + " | Deadline: " + projectDeadline;
        } else if (!projectDescription.equals("") & projectDeadline == null) {
            return "Description: " + projectDescription + " | <project deadline empty>";
        } else if (projectDeadline != null) {
            return "<project description empty> | Deadline: " + projectDeadline;
        } else {
            return "<project description empty> | <project deadline empty>";
        }
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }


    /*
        Returns a string that will be saved in the text file.
        @returns String
    */
    public String saveFormat() {

        String tasksLines = "startTasks \n";
        if (tasks.size() > 0) {
            for (Task task : tasks) {
                tasksLines += (task.saveFormat() + "\n");
            }
        }
        tasksLines += "endTasks";

        String membersLines = "pMS\n";
        if (teamMembers.size() > 0) {
            for (TeamMember member : teamMembers) {
                membersLines += member.getName() + "\n";
            }
        }
        membersLines += "pME";

        String projectNameLine = "Project " + projectName;
        String statusLine = "status " + isDone;
        String projectDescriptionLine = "projectDescription " + projectDescription;
        String projectDeadlineLine = "projectDeadline " + projectDeadline;
        return projectNameLine + "\n" + statusLine + "\n" + projectDescriptionLine + "\n"
                + projectDeadlineLine + "\n" + tasksLines + "\n" + membersLines + "\n";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isProjectDone() {
        return isDone;
    }
}
