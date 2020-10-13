package seedu.eduke8.option;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

class OptionListTest {
    private static final String PLACEHOLDER_OPTION_ONE_DESCRIPTION = "Option one";
    private static final String PLACEHOLDER_OPTION_TWO_DESCRIPTION = "Option two";
    private static final int DEFAULT_OPTION_COUNT = 1;

    @Test
    void getCount_oneOption_returnsCountOfOne() {
        OptionList optionList = createOptionList();

        assertEquals(DEFAULT_OPTION_COUNT, optionList.getCount());
    }

    @Test
    void getInnerList_oneOption_expectsTrue() {
        OptionList optionList = createOptionList();
        ArrayList<Displayable> options = new ArrayList<>();

        Option optionOne = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);
        options.add(optionOne);
        OptionList optionListTest = new OptionList(options);

        String optionListDescription = optionList.getInnerList().get(0).getDescription();
        String optionListTestDescription = optionListTest.getInnerList().get(0).getDescription();

        assertEquals(optionListDescription, optionListTestDescription);
    }

    @Test
    void add_oneOption_returnsCountOfTwo() {
        OptionList optionList = createOptionList();
        Option optionThree = new Option(PLACEHOLDER_OPTION_TWO_DESCRIPTION);

        optionList.add(optionThree);
        assertEquals(DEFAULT_OPTION_COUNT + 1, optionList.getCount());
    }

    @Test
    void delete_oneOption_returnsCountOfZero() {
        OptionList optionList = createOptionList();

        optionList.delete(0);
        assertEquals(DEFAULT_OPTION_COUNT - 1, optionList.getCount());
    }

    @Test
    void find_twoOptions_returnOptionOne() {
        OptionList optionList = createOptionList();
        Displayable optionOne = optionList.find(PLACEHOLDER_OPTION_ONE_DESCRIPTION);

        assertEquals(PLACEHOLDER_OPTION_ONE_DESCRIPTION, optionOne.getDescription());
    }

    private OptionList createOptionList() {
        ArrayList<Displayable> options = new ArrayList<>();
        Option optionOne = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);

        options.add(optionOne);
        OptionList optionList = new OptionList(options);

        return optionList;
    }
}
