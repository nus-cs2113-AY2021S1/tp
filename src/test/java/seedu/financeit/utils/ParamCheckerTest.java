package seedu.financeit.utils;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.testutil.TestUtil;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParamCheckerTest {
    private ParamChecker paramCheckerUnderTest;
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
        paramCheckerUnderTest = TestUtil.createParamChecker(testPacket);
        try {
            paramCheckerUnderTest.checkAndReturnDate(testParam);
            fail();
        } catch (ParseFailParamException exception) {
            assertEquals(
                "Failed to parse the following param: " + testParam,
                exception.getMessage()
            );
        }
        assertCorrectErrorMessage(paramCheckerUnderTest, ParamChecker.getErrorMessageDateDateTimeException());
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
        paramCheckerUnderTest = TestUtil.createParamChecker(testPacket);
        try {
            paramCheckerUnderTest.checkAndReturnTime(testParam);
            fail();
        } catch (ParseFailParamException exception) {
            assertEquals(
                "Failed to parse the following param: " + testParam,
                exception.getMessage()
            );
        }
        assertCorrectErrorMessage(paramCheckerUnderTest, ParamChecker.getErrorMessageTimeDateTimeException());
    }

    @Test
    public void testTimeWrongParse() {
        String testParam = "/time";
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
            paramCheckerUnderTest = TestUtil.createParamChecker(testPacket);
            try {
                paramCheckerUnderTest.checkAndReturnTime(testParam);
                fail();
            } catch (ParseFailParamException exception) {
                System.out.println(errorInput[i]);
                assertEquals(
                    "Failed to parse the following param: " + testParam,
                    exception.getMessage()
                );
            }
            assertCorrectErrorMessage(paramCheckerUnderTest, ParamChecker.getErrorMessageTimeInvalidFormat());
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
            paramCheckerUnderTest = TestUtil.createParamChecker(testPacket);
            try {
                paramCheckerUnderTest.checkAndReturnDate(testParam);
                fail();
            } catch (ParseFailParamException exception) {
                System.out.println(errorInput[i]);
                assertEquals(
                    "Failed to parse the following param: " + testParam,
                    exception.getMessage()
                );
            }
            assertCorrectErrorMessage(paramCheckerUnderTest, ParamChecker.getErrorMessageDateInvalidFormat());
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
            int index = Integer.parseInt(errorInput[i]);
            testPacket = TestUtil.createCommandPacket(
                "",
                new String[][]{
                    new String[]{testParam, errorInput[i]}
                }
            );
            paramCheckerUnderTest = TestUtil.createParamChecker(testPacket);
            try {
                paramCheckerUnderTest.checkAndReturnIndex(testParam, testList);
                fail();
            } catch (ParseFailParamException exception) {
                assertEquals(
                    "Failed to parse the following param: " + testParam,
                    exception.getMessage()
                );
            }
            assertCorrectErrorMessage(
                paramCheckerUnderTest,
                ParamChecker.getErrorMessageListIndexOutOfBounds(
                    ParamChecker.getMessageListRangeIndex(testList.size()),
                    index));
        }
    }

    public void assertCorrectErrorMessage(ParamChecker paramChecker, String expectedErrorMessage) {
        assertEquals(
            paramChecker.getErrorMessage(),
            expectedErrorMessage
        );
    }
}
