package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.command.project.CreateProjectCommand;
import com.scrumptious.command.project.ListProjectCommand;
import com.scrumptious.command.project.SelectProjectCommand;
import com.scrumptious.command.project.ViewProjectCommand;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static com.scrumptious.command.CommandSummary.TITLE;
import static com.scrumptious.command.CommandSummary.DESCRIPTION;
import static com.scrumptious.command.CommandSummary.DURATION;
import static com.scrumptious.command.CommandSummary.SPRINT_DURATION;
import static com.scrumptious.command.CommandSummary.VIEW;
import static com.scrumptious.command.CommandSummary.CREATE;
import static com.scrumptious.command.CommandSummary.SELECT;
import static com.scrumptious.command.CommandSummary.LIST;

public class ProjectParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws ScrumptiousException {
        switch (action.toLowerCase()) {
        //Creates a new project and adds it to the ProjectManager
        case CREATE:
            if (!parameters.containsKey(TITLE) || !parameters.containsKey(DESCRIPTION)
                    || !parameters.containsKey(DURATION) || !parameters.containsKey(SPRINT_DURATION)) {
                throw new ScrumptiousException("Missing parameters.");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new ScrumptiousException("No title.");
            }
            if (parameters.get(DESCRIPTION).isBlank()) {
                throw new ScrumptiousException("No description.");
            }
            if (parameters.get(DURATION).isBlank() || !ParserManager.isStringIntParsable(parameters.get(DURATION))) {
                throw new ScrumptiousException("Please give a number for duration.");
            }
            if (parameters.get(SPRINT_DURATION).isBlank()
                    || !ParserManager.isStringIntParsable(parameters.get(SPRINT_DURATION))) {
                throw new ScrumptiousException("Please give a number for sprint duration.");
            } else {
                return new CreateProjectCommand(parameters, projectListManager);
            }
            // View the current selected project, and the details of the same.
        case VIEW:
            if (!parameters.isEmpty()) {
                throw new ScrumptiousException("Invalid action!");
            } else {
                return new ViewProjectCommand(parameters, projectListManager);
            }
            // Select the project on which all commands are executed.
        case SELECT:

            if (parameters.get("0") == null) {
                throw new ScrumptiousException("Please enter a project id and do not include any dashes.");
            }
            if (!ParserManager.isStringIntParsable(parameters.get("0"))) {
                throw new ScrumptiousException("Please give a project number.");
            }
            // Get index of the project to select
            try {
                int index = Integer.parseInt(parameters.get("0"));
                // check if required project index present
                if (index <= projectListManager.size() && index > 0) {
                    return new SelectProjectCommand(parameters, projectListManager);
                } else {
                    throw new ScrumptiousException("Invalid index, no corresponding project exists.");
                }
            } catch (NumberFormatException e) {
                throw new ScrumptiousException("Invalid input.");
            }
            // Show a list of all projects added, with id to select it.
        case LIST:
            if (projectListManager.isEmpty()) {
                throw new ScrumptiousException("There are no projects added.");
            } else {
                assert projectListManager.getSelectedProject() != null : "Project exists, but is null type\n.";
                return new ListProjectCommand(parameters, projectListManager);
            }
        default:
            throw new ScrumptiousException("Invalid action!");
        }
    }
}
