package seedu.financeit.utils;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.testutil.TestUtil;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class to verify param checking capability of the ParamChecker class.
 */
public class ParamCheckerTest {
    CommandPacket testPacket;

    @Test
    public void testDateCorrectParseWrongDate() {
        String testParam = "/date";
        testPacket = TestUtil.createCommandPacket(
            "",
            new String[][]{
                new String[]{testParam, "202020"}
            }
        );
        ParamChecker.getInstance().setPacket(testPacket);
        try {
            ParamChecker.getInstance().checkAndReturnDate(testParam);
            fail();
        } catch (ParseFailParamException exception) {
            assertEquals(
                "Failed to parse the following param: " + testParam,
                exception.getMessage()
            );
        }
        assertCorrectErrorMessage(ParamChecker.getInstance(), ParamChecker.getErrorMessageDateDateTimeException());
    }

    @Test
    public void testTimeCorrectParseWrongTime() {
        String testParam = "/time";
        testPacket = TestUtil.createCommandPacket(
            "",
            new String[][]{
                new String[]{testParam, "2500"}
            }
        );
        ParamChecker.getInstance().setPacket(testPacket);
        try {
            ParamChecker.getInstance().checkAndReturnTime(testParam);
            fail();
        } catch (ParseFailParamException exception) {
            assertEquals(
                "Failed to parse the following param: " + testParam,
                exception.getMessage()
            );
        }
        assertCorrectErrorMessage(ParamChecker.getInstance(), ParamChecker.getErrorMessageTimeDateTimeException());
    }

    @Test
    public void testTimeWrongParse() {
        String testParam = "/time";
        String[] errorInput = {
            "abcd",
            ".,/-=",
            "this is a test",
            "d d d d d ",
            "sdffds",
        };

        for (int i = 0; i < errorInput.length; i++) {
            testPacket = TestUtil.createCommandPacket(
                "",
                new String[][]{
                    new String[]{testParam, errorInput[i]}
                }
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                ParamChecker.getInstance().checkAndReturnTime(testParam);
                fail();
            } catch (ParseFailParamException exception) {
                System.out.println(errorInput[i]);
                assertEquals(
                    "Failed to parse the following param: " + testParam,
                    exception.getMessage()
                );
            }
            assertCorrectErrorMessage(ParamChecker.getInstance(), ParamChecker.getErrorMessageTimeInvalidFormat());
        }
    }

    @Test
    public void testDateWrongParse() {
        String testParam = "/date";
        String[] errorInput = {
            "abcd",
            ".,/-=",
            "this is a test",
            "d d d d d ",
            "sdffds"
        };

        for (int i = 0; i < errorInput.length; i++) {
            testPacket = TestUtil.createCommandPacket(
                "",
                new String[][]{
                    new String[]{testParam, errorInput[i]}
                }
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                ParamChecker.getInstance().checkAndReturnDate(testParam);
                fail();
            } catch (ParseFailParamException exception) {
                assertEquals(
                    "Failed to parse the following param: " + testParam,
                    exception.getMessage()
                );
            }
            assertCorrectErrorMessage(ParamChecker.getInstance(), ParamChecker.getErrorMessageDateInvalidFormat());
        }
    }

    @Test
    public void testIndexOutOfBoundsList() {
        String testParam = "/id";
        String[] errorInput = {
            "-1",
            "23",
        };
        ArrayList testList = new ArrayList(Arrays.asList(errorInput));

        for (int i = 0; i < errorInput.length; i++) {
            testPacket = TestUtil.createCommandPacket(
                "",
                new String[][]{
                    new String[]{testParam, errorInput[i]}
                }
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                ParamChecker.getInstance().checkAndReturnIndex(testParam, testList);
                fail();
            } catch (ParseFailParamException exception) {
                assertEquals(
                    "Failed to parse the following param: " + testParam,
                    exception.getMessage()
                );
            }
            int index = Integer.parseInt(errorInput[i]);
            assertCorrectErrorMessage(
                ParamChecker.getInstance(),
                ParamChecker.getErrorMessageListIndexOutOfBounds(
                    ParamChecker.getMessageListRangeIndex(testList.size())));
        }
    }

    @Test
    public void testDuplicateEntryTypes() {
        String testParam = "/id";
        String[] errorInput = {
            "1",
            "2",
        };

        for (int i = 0; i < errorInput.length; i++) {
            testPacket = TestUtil.createCommandPacket(
                "",
                new String[][]{
                    new String[]{testParam, errorInput[i]},
                    // Second input to testParam "/id"
                    new String[]{testParam, errorInput[i] + 2}
                }
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                ParamChecker.getInstance().checkAndReturnDuplicateParamTypes(testParam, testPacket.getParamMap());
                fail();
            } catch (ParseFailParamException exception) {
                assertEquals(
                    "Failed to parse the following param: " + testParam,
                    exception.getMessage()
                );
            }
            assertCorrectErrorMessage(
                ParamChecker.getInstance(),
                ParamChecker.getMessageMultipleParamToParamType(testParam, testPacket.getParamMap()));
        }
    }

    public void assertCorrectErrorMessage(ParamChecker paramChecker, String expectedErrorMessage) {
        assertEquals(
            paramChecker.getErrorMessage(),
            expectedErrorMessage
        );
    }
}
