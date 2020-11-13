package seedu.duke.command;

import seedu.duke.model.DayMap;
import seedu.duke.Trakcal;
import seedu.duke.model.GraphDrawing;
import seedu.duke.model.GraphProperty;

import java.util.HashMap;

import static seedu.duke.Trakcal.logging;

//@@author 1-Karthigeyan-1
/**
 * Displays the graph of illustrating the net calorie gain/loss throughout the period.
 */
public class GraphCommand extends Command {
    public static final int MAXIMUM_DAYS = 7;

    public GraphCommand() {
        this.canBeChained = true;
    }

    boolean isMapValid(DayMap dayMap) {
        return dayMap != null;
    }

    @Override
    public void execute() {
        logging.writeToLogInfo("Executing graph command.");
        GraphProperty graphProperties = new GraphProperty(dayMap, (int) Trakcal.profile.getCalories());
        graphProperties.setProperties();
        System.out.println(new GraphDrawing(graphProperties).drawGraph());
    }
}
