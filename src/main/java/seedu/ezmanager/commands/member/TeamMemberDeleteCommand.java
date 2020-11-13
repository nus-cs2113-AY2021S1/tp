package seedu.ezmanager.commands.member;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.EzLogger;
import java.util.logging.Level;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;
import static seedu.ezmanager.Util.INDEX_NON_INTEGER;
import static seedu.ezmanager.Util.INVALID_TEAM_MEMBER_ID;
import static seedu.ezmanager.Util.EMPTY_TEAM_MEMBERS_LIST;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.Parser.getHashValue;

/**
 * Delete TeamMember object from program or from a specified Project object.
 */
public class TeamMemberDeleteCommand extends Command {
    private int memberIndex;
    HashMap<String, String> params;
    private int projectIndex;

    public TeamMemberDeleteCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        this.params = params;
        this.parse();
        this.projectIndex = projectIndex;
    }

    /**
     * Parse user parameter inputs for execution.
     *
     * @throws EzExceptions Invalid Team Member ID when parameter values entered is not an integer.
     */
    public void parse() throws EzExceptions {
        try {
            memberIndex = Integer.parseInt(getHashValue(params, "m")) - 1;
        } catch (NumberFormatException e) {
            EzLogger.log(Level.WARNING, "Input not an integer");
            throw new EzExceptions(INDEX_NON_INTEGER);
        }
    }

    /**
     * Removes specified member from the program or from a specified project depending on the view
     * [In Home View] Removes member from the program entirely, including every project and every task
     * he/she is assigned to.
     * [In Project View] Removes member from the project, including the task he/she is assigned to.
     * Prints member removed message.
     *
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Print message of member being removed from either the program entirely or the project.
     * @throws EzExceptions if TeamMembers list is empty or if TeamMember ID provided is outside
     *     the range of the TeamMembers list
     */
    @Override
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (teamMembers.size() == 0) {
            EzLogger.log(Level.WARNING, "Empty TeamMember List");
            throw new EzExceptions(EMPTY_TEAM_MEMBERS_LIST);
        }
        try {
            TeamMember memberToBeRemoved;
            // Removing of Members in HomeView
            if (projectIndex == -1) {
                memberToBeRemoved = removeMemberFromHomeView(projects, teamMembers);
                return Ui.printMemberRemovedInHomeViewMessage(memberToBeRemoved.getName());
            // Removing of members in ProjectView
            } else {
                Project project = projects.get(projectIndex);
                memberToBeRemoved = removeMemberFromCurrentProjectView(project);
                return Ui.printMemberRemovedInProjectViewMessage(memberToBeRemoved.getName(), project.getProjectName());
            }
        } catch (IndexOutOfBoundsException e) {
            EzLogger.log(Level.WARNING, "Invalid TeamMember ID");
            throw new EzExceptions(INVALID_TEAM_MEMBER_ID);
        }
    }

    private TeamMember removeMemberFromHomeView(ArrayList<Project> projects,
                                        ArrayList<TeamMember> teamMembers) {
        assert projectIndex == -1 : "projectIndex should equal to -1 since it is in home view";
        TeamMember memberToBeRemoved = teamMembers.get(memberIndex);
        teamMembers.remove(memberIndex);
        EzLogger.log(Level.INFO, "TeamMember removed");
        removeMemberFromEachProject(projects, memberToBeRemoved);
        return memberToBeRemoved;
    }


    private void removeMemberFromEachProject(ArrayList<Project> projects, TeamMember memberToBeRemoved) {
        for (Project project : projects) {
            for (int i = 0; i < project.getTeamMembers().size(); i++) {
                TeamMember member = project.getTeamMembers().get(i);
                if (member == memberToBeRemoved) {
                    project.getTeamMembers().remove(i);
                    i--;
                }
            }
            removeMemberFromTask(project, memberToBeRemoved);
        }
        EzLogger.log(Level.INFO, "TeamMember removed from each assigned project");
    }

    private TeamMember removeMemberFromCurrentProjectView(Project project) {
        assert projectIndex >= 0 : "projectIndex should be minus one "
                + "of the current project it was selected";
        TeamMember memberToBeRemoved = project.getTeamMembers().get(memberIndex);
        EzLogger.log(Level.INFO, "To be removed TeamMember Retrieved");
        project.getTeamMembers().remove(memberIndex);
        removeMemberFromTask(project, memberToBeRemoved);
        memberToBeRemoved.getAssignedProjects().remove(project);
        EzLogger.log(Level.INFO, "TeamMember removed from current project");
        return memberToBeRemoved;
    }

    private void removeMemberFromTask(Project project, TeamMember memberToBeRemoved) {
        for (Task task : project.getTaskList()) {
            for (int j = 0; j < task.getMembers().size(); j++) {
                TeamMember member = task.getMembers().get(j);
                if (member == memberToBeRemoved) {
                    task.getMembers().remove(j);
                    j--;
                }
            }
        }
        EzLogger.log(Level.INFO, "TeamMember removed from each assigned task");
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
