package com.scrumptious.command.member;

import com.scrumptious.command.Command;
import com.scrumptious.model.member.Member;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

public abstract class MemberCommand extends Command {

    ProjectManager projectManager;

    public MemberCommand(Hashtable<String, String> parameters, ProjectManager projectManager, boolean shouldSave) {
        super(parameters, shouldSave);
        this.projectManager = projectManager;
    }

    public abstract void execute();

    public abstract void logExecution(String userMessage);

    /**
     * Checks if a member is already added to a project.
     * @param proj Project currently being used.
     * @param member Member who has to be checked for association with proj.
     * @return true if proj contains member
     */
    protected boolean checkMember(Project proj, String member) {
        boolean flag;
        flag = proj.getProjectMember().containMember(new Member(member));
        return flag;
    }
}
