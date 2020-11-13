package seedu.ravi.command.edit;

import seedu.ravi.command.Command;
import seedu.ravi.ui.TextUi;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String FORMAT_EDIT_TASK = COMMAND_WORD + " -t <task_index> <task_name> [-by <deadline>]";
    public static final String FORMAT_EDIT_MODULE = COMMAND_WORD + " -m <module_code> <new_module_code>";
    public static final String FORMAT = FORMAT_EDIT_TASK + "\n" + FORMAT_EDIT_MODULE;
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP = EditTaskCommand.HELP + EditModuleCommand.HELP;
}
