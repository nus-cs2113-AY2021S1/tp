package seedu.eduke8.option;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionListTest extends Eduke8Test {
    private static final int DEFAULT_OPTION_COUNT = 2;

    @Test
    void getCount_twoOptions_returnsCountOfTwo() {
        OptionList optionList = createTestOptionList();

        assertEquals(DEFAULT_OPTION_COUNT, optionList.getCount());
    }

    @Test
    void getInnerList_sameOption_expectsTrue() {
        OptionList optionList = createTestOptionList();
        OptionList optionListTest = createTestOptionList();

        String optionListDescription = optionList.getInnerList().get(0).getDescription();
        String optionListTestDescription = optionListTest.getInnerList().get(0).getDescription();

        assertEquals(optionListDescription, optionListTestDescription);
    }

    @Test
    void find_twoOptions_returnOptionOne() throws Eduke8Exception {
        OptionList optionList = createTestOptionList();
        Displayable optionOne = optionList.find(PLACEHOLDER_OPTION_ONE_DESCRIPTION);

        assertEquals(PLACEHOLDER_OPTION_ONE_DESCRIPTION, optionOne.getDescription());
    }

    @Test
    void findCorrectOptionIndex_notCorrectOption_expectEduke8Exception() {
        OptionList optionList = createTestOptionList();

        assertThrows(Eduke8Exception.class, optionList::findCorrectOptionIndex);
    }

    @Test
    void find_optionNotFound_expectEduke8Exception() {
        OptionList optionList = createTestOptionList();

        assertThrows(Eduke8Exception.class, () -> optionList.find(NONSENSE_DESCRIPTION));
    }
}
