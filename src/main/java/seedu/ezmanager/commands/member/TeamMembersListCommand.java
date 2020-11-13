package seedu.ezmanager.commands.member;

import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;

import java.util.ArrayList;

public class TeamMembersListCommand extends Command {

    private String name;
    private boolean isHomeView;
    private int projectIndex;

    public TeamMembersListCommand(boolean isHomeView, int projectIndex) {
        this.isHomeView = isHomeView;
        this.projectIndex = projectIndex;
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        if (teamMembers.size() == 0) {
            return "No team members have been added.";
        }
        String listOfMembers = "List of members:              Project(s) Assigned:";
        int i = 0;
        int amountOfPadding = 0;
        if (isHomeView) {
            for (TeamMember member : teamMembers) {
                if (member.getName().length() > 26) {
                    listOfMembers += "\n" + (i + 1) + ". " + member.getName().substring(0,26) + " ";
                } else {
                    listOfMembers += "\n" + String.format("%-30s", (i + 1) + ". " + member.getName());;
                }
                if (!member.getAssignedProjects().isEmpty()) {
                    for (int j = 0; j < member.getAssignedProjects().size(); j++) {
                        Project assignedProject = member.getAssignedProjects().get(j);
                        if (j == 0) {
                            listOfMembers += (j + 1) + ") " + assignedProject.getProjectName();
                        } else {
                            amountOfPadding = 33 + assignedProject.getProjectName().length();
                            listOfMembers += "\n" + String.format("%1$" + amountOfPadding + "s", (j + 1) + ") "
                                    + assignedProject.getProjectName());
                        }
                    }
                } else {
                    listOfMembers += "Not assigned to a project";
                }
                listOfMembers += System.lineSeparator();
                i++;
            }
        } else {
            ArrayList<TeamMember> teamMembersInSelectedProject = projects.get(projectIndex).getTeamMembers();
            for (TeamMember member : teamMembersInSelectedProject) {
                listOfMembers += "\n" + (i + 1) + ". " + member.getName();
                i++;
            }
        }
        return listOfMembers;
    }

    public Boolean isExit() {
        return false;
    }
}
