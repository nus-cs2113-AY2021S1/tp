package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.command.member.AddMemberCommand;
import com.scrumptious.command.member.DeleteMemberCommand;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static com.scrumptious.command.CommandSummary.ADD;
import static com.scrumptious.command.CommandSummary.DELETE;

public class MemberParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws ScrumptiousException {
        if (!parameters.containsKey("0")) {
            throw new ScrumptiousException("Missing name.");
        } else if (projectListManager.size() == 0) {
            throw new ScrumptiousException("You currently have no projects created.");
        }
        switch (action.toLowerCase()) {
        case ADD:
            return new AddMemberCommand(parameters, projectListManager);
        case DELETE:
            return new DeleteMemberCommand(parameters, projectListManager);
        default:
            throw new ScrumptiousException("Invalid action!");
        }
    }
}
