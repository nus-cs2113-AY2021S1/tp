package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.ui.Ui;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HintCommandTest {
    @Test
    public void execute_normal_hintWasShown() {
        Hint hint = new Hint("test");
        Command hintCommand = new HintCommand(hint);

        Option option1 = new Option("test1");
        ArrayList<Displayable> optionsArrayList = new ArrayList();
        optionsArrayList.add(option1);

        OptionList options = new OptionList(optionsArrayList);

        hintCommand.execute(options, new Ui());

        assertTrue(hint.wasShown());
    }
}
