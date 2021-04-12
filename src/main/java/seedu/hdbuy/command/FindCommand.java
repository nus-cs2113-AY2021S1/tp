package seedu.hdbuy.command;

import seedu.hdbuy.api.ApiRepository;
import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.EmptyParameterException;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.common.exception.NoSearchException;
import seedu.hdbuy.data.SearchedUnits;
import seedu.hdbuy.data.UserInput;
import seedu.hdbuy.ui.TextUi;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FindCommand extends Command {

    @Override public void execute() {
        try {
            LinkedHashMap<QueryKey, String> inputs = UserInput.getInputs();
            if (inputs.isEmpty()) {
                HdBuyLogger.warning("Unable to execute find command due to an empty filter");
                throw new EmptyParameterException();
            } else {
                TextUi.showParameters(inputs);
                SearchedUnits.clearSearchedUnits();
                ApiRepository.fetchUnits(inputs);
                ArrayList<Unit> units = SearchedUnits.getSearchedUnits();
                if (units.isEmpty()) {
                    throw new NoFlatsException();
                }
                TextUi.showUnits(units);
                UserInput.clearInputs();
            }
        } catch (EmptyParameterException e) {
            TextUi.showEmptyParameter(e);
        } catch (NoFlatsException | NoSearchException e) {
            TextUi.showExceptionMessage(e);
        }
    }
}
