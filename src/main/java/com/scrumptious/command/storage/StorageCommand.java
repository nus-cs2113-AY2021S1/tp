package com.scrumptious.command.storage;

import com.scrumptious.command.Command;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

public abstract class StorageCommand extends Command {

    protected ProjectManager projectManager;
    
    /**
     * Creates a new abstract command.
     *
     * @param parameters Parameters provided by the user.
     * @param shouldSave Specifies whether a save() should be invoked after the execution of the command.
     */
    
    public StorageCommand(Hashtable<String, String> parameters, ProjectManager projectManager, boolean shouldSave) {
        super(parameters, shouldSave);
        this.projectManager = projectManager;
    }
}
