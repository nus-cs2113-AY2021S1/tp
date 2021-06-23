package logic.commands;

import static ui.CommonUi.EMPTY_STRING;

/**
 * A class representing result shown to user after executing the requested command.
 */
public class CommandResult {
    private String feedbackMessage;
    private ExecutionResult status;

    /**
     * Constructs CommandResult object.
     *
     * @param feedbackMessage Feedback message after executing command.
     * @param status Execution status.
     */
    public CommandResult(String feedbackMessage, ExecutionResult status) {
        this.feedbackMessage = feedbackMessage;
        this.status = status;
    }

    /**
     * Constructs CommandResult object with status OK.
     *
     * @param feedbackMessage Feedback message after executing command.
     */
    public CommandResult(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
        status = ExecutionResult.OK;
    }

    /**
     * Constructs CommandResult object with empty feedback message and status OK.
     */
    public CommandResult() {
        feedbackMessage = EMPTY_STRING;
        status = ExecutionResult.OK;
    }

    /**
     * Gets execution status.
     *
     * @return Execution status.
     */
    public ExecutionResult getStatus() {
        return status;
    }

    /**
     * Gets feedback message to user.
     *
     * @return Feedback message to user.
     */
    public String getFeedbackMessage() {
        return feedbackMessage;
    }
}
