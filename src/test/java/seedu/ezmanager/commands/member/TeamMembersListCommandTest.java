package seedu.ezmanager.commands.member;

import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamMembersListCommandTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers;

    /*
    @BeforeAll
    static void createTeamMembersList() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Project One");
        projects.add(projectOne);
        teamMembers = new ArrayList<>();
        TeamMember member1 = new TeamMember("John Doe");
        TeamMember member2 = new TeamMember("Sarah Hopkins");
        TeamMember member3 = new TeamMember("Brandon Ginger");
        TeamMember member4 = new TeamMember("Jonathan Joseph");
        teamMembers.add(member1);
        teamMembers.add(member2);
        teamMembers.add(member3);
        teamMembers.add(member4);
    }

    @Test
    void executeCommand_listWithoutAssignedProject_listMessage() throws DukeExceptions {
        TeamMembersListCommand command = new TeamMembersListCommand(true, 0);
        String expectedOutput = "List of members:\n"
                + "1. John Doe: \tNot assigned to a project\n"
                + "2. Sarah Hopkins: \tNot assigned to a project\n"
                + "3. Brandon Ginger: \tNot assigned to a project\n"
                + "4. Jonathan Joseph: \tNot assigned to a project";
        String actualOutput = command.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeCommand_listWithAssignedProject_listMessage() throws DukeExceptions {
        teamMembers.get(0).setAssignedProjectId(1);
        teamMembers.get(2).setAssignedProjectId(1);
        TeamMembersListCommand command = new TeamMembersListCommand(true, 0);
        String expectedOutput = "List of members:\n"
                + "1. John Doe: \tAssigned to project 1\n"
                + "2. Sarah Hopkins: \tNot assigned to a project\n"
                + "3. Brandon Ginger: \tAssigned to project 1\n"
                + "4. Jonathan Joseph: \tNot assigned to a project";
        String actualOutput = command.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput, actualOutput);
    }
    */
}