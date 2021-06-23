package logic.commands.workout.workoutmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import exceptions.SchwarzeneggerException;
import logic.parser.WorkoutManagerParser;
import models.PastRecordList;
import workout.workoutsession.WorkoutSession;

import java.util.ArrayList;

import static logic.commands.ExecutionResult.OK;
import static ui.workout.workoutmanager.WorkoutManagerUi.NEW_SUCCESS;
import static ui.workout.workoutmanager.WorkoutManagerUi.START_NEW_SESSION;

//@@author wgzesg
/**
 * A representation of the command for create commands in workout manager.
 */
public class NewWS extends Command {

    /**
     * Creates new workout session.
     *
     * @param args User's input
     * @return Status OK and feedback message.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        super.execute(args);
        ArrayList<String> tags = WorkoutManagerParser.getInstance().parseTags(args);
        String filePath = PastRecordList.getInstance().add(tags);
        WorkoutSession ws = new WorkoutSession(filePath, true, -1);
        logger.info("New workout session created");

        ui.showToUser(START_NEW_SESSION);

        ws.workoutSessionStart();

        logger.info("Ended workout session");
        return new CommandResult(NEW_SUCCESS, OK);
    }
}
