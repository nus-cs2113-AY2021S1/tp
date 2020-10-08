package seedu.duke.commands;

import static seedu.duke.common.Messages.LINE;

public class InvalidCommand extends Command {

    public final String feedbackToUser;

    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute() {
        ui.showToUser(LINE + this.feedbackToUser);
    }

}
