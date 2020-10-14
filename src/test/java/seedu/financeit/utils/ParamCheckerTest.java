package seedu.financeit.utils;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.testutil.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParamCheckerTest {
    private ParamChecker paramCheckerUnderTest;
    CommandPacket testPacket;

    @Test
    public void testCorrectParseWrongDate() {
        String testParam = "/date";
        testPacket = TestUtil.createCommandPacket(
            "",
            new String[][]{
                new String[]{testParam, "200505"}
            }
        );
        paramCheckerUnderTest = TestUtil.createParamChecker(testPacket);
        assertParseFailParam(paramCheckerUnderTest, testParam);
        assertCorrectErrorMessage(paramCheckerUnderTest, ParamChecker.getErrorMessageDateDateTimeException());
    }

    public void assertParseFailParam(ParamChecker paramChecker, String param){
        paramCheckerUnderTest = TestUtil.createParamChecker(testPacket);
        try {
            paramChecker.checkAndReturnDate(param);
            fail();
        } catch (ParseFailParamException exception) {
            assertEquals(
                "Failed to parse the following param: " + param,
                exception.getMessage()
            );
        }
    }

    public void assertCorrectErrorMessage(ParamChecker paramChecker, String expectedErrorMessage) {
        assertEquals(
            paramChecker.getErrorMessage(),
            expectedErrorMessage
        );
    }
}
