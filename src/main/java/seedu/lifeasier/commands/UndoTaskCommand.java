package seedu.lifeasier.commands;

import seedu.lifeasier.model.notes.NoteHistory;
import seedu.lifeasier.model.notes.NoteList;
import seedu.lifeasier.parser.Parser;
import seedu.lifeasier.storage.FileStorage;
import seedu.lifeasier.model.tasks.Task;
import seedu.lifeasier.model.tasks.TaskHistory;
import seedu.lifeasier.model.tasks.TaskList;
import seedu.lifeasier.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UndoTaskCommand extends Command {
    private static Logger logger = Logger.getLogger(UndoTaskCommand.class.getName());

    private static final int DEFAULT_EDIT_NUMBER = -999999;

    @Override
    public void execute(Ui ui, NoteList notes, TaskList tasks, FileStorage storage, Parser parser,
                        NoteHistory noteHistory, TaskHistory taskHistory) {
        if (taskHistory.getChangeCount() > 0) {
            Task oldCopyOfTask = taskHistory.getLastTask();
            int lastTaskEditNumber = oldCopyOfTask.getEditNumber();

            if (lastTaskEditNumber > 0) {
                logger.log(Level.INFO, "Iterating through TaskHistory...");
                for (int i = 0; i < tasks.getTaskCount(); i++) {
                    int editNumOfCurrTask =  tasks.getTask(i).getEditNumber();
                    if (editNumOfCurrTask == lastTaskEditNumber) {
                        logger.log(Level.INFO, "Revert Task to its previous copy");
                        tasks.setTask(i, oldCopyOfTask);
                    }
                }
                ui.showUndoTaskEditMessage();

            } else if (lastTaskEditNumber < 0) {
                logger.log(Level.INFO, "Start restoring a deleted Task");
                Task taskToRestore = taskHistory.copyTask(oldCopyOfTask, DEFAULT_EDIT_NUMBER);
                tasks.addTask(taskToRestore);
                ui.showUndoTaskDeleteMessage();
            }

            ui.showOldTaskMessage(taskHistory);
            taskHistory.popLastTask();
            storage.saveTasks();

        } else {
            ui.showInvalidUndoActionError();
        }
    }
}