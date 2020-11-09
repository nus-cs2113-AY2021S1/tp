package com.scrumptious.command.help;

import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;

public class MemberHelpCommand extends HelpCommand {
    public MemberHelpCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        logExecution();
        Ui.showToUserLn("1. Add members");
        Ui.showToUserLn("   Format: member /add <username> [<username> ...]");
        Ui.showToUserLn("   Example: member /add john mary");
        Ui.showToUserLn("2. Remove members");
        Ui.showToUserLn("   Format: member /del <username> [<username> ...]");
        Ui.showToUserLn("   Example: member /del john mary");
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Viewed member help.");
    }
}
