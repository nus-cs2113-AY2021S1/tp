package seedu.financeit.utils;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.testutil.TestUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void testInvalidDayOfMonth() {
        String testParam = "/day";
        ArrayList<String> invalidIntegersAsStrings = new ArrayList<>(
                List.of("-1", "0", "32", String.valueOf(Integer.MAX_VALUE))
        );
        for (String invalidIntegerAsString: invalidIntegersAsStrings) {
            testPacket = TestUtil.createCommandPacket(
                    "",
                    new String[][]{{testParam, invalidIntegerAsString}}
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                ParamChecker.getInstance().checkAndReturnDayOfMonth(testParam);
                fail("Input that did not throw error when it was supposed to: " + invalidIntegerAsString);
            } catch (ParseFailParamException exception) {
                //Error message generated inside ParamChecker
                assertCorrectErrorMessage(ParamChecker.getInstance(),
                        ParamChecker.getErrorMessageDayOfMonthOutOfBounds());
                //Error thrown by ParamChecker, printed outside of ParamChecker
                assertEquals("Failed to parse the following param: " + testParam,
                        exception.getMessage());
            }
        }
    }

    @Test
    public void testValidDayOfMonth() {
        String testParam = "/day";
        ArrayList<String> validDayOfMonthAsStrings = new ArrayList<>(
                List.of("1", "29", "31")
        );
        for (String validDayOfMonthAsString: validDayOfMonthAsStrings) {
            testPacket = TestUtil.createCommandPacket(
                    "",
                    new String[][]{
                            {testParam, validDayOfMonthAsString}
                    }
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                int dayOfMonth = ParamChecker.getInstance().checkAndReturnDayOfMonth(testParam);
                assertEquals(validDayOfMonthAsString, String.valueOf(dayOfMonth));
            } catch (Exception exception) {
                fail("Exception occurred when there shouldn't be! " + exception.getMessage());
            }
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
                ParamChecker.getInstance().checkForDuplicateParamTypes(testParam, testPacket.getParamMap());
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

    @Test
    public void testValidInteger() {
        String testParam = "/test";
        ArrayList<String> validIntegersAsStrings = new ArrayList<>(
                List.of(String.valueOf(Integer.MIN_VALUE), "-1", "0", "1", String.valueOf(Integer.MAX_VALUE))
        );

        for (String validIntegerAsString: validIntegersAsStrings) {
            testPacket = TestUtil.createCommandPacket(
                    "",
                    new String[][]{
                            {testParam, validIntegerAsString},
                    }
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                int intChecked = ParamChecker.getInstance().checkAndReturnInt(testParam);
                assertEquals(validIntegerAsString, String.valueOf(intChecked));
            } catch (Exception exception) {
                fail("Exception occurred when there shouldn't be! " + exception.getMessage());
            }
        }
    }

    @Test
    public void testInvalidInteger() {
        String testParam = "/test";
        ArrayList<String> invalidInputsAsStrings = new ArrayList<>(
                List.of("435.23", "4e5", "(*^_)+_+()(", "", "  ",
                        "456468456594876549867459", "-9823749238742983479842")
        );


        for (String invalidInput : invalidInputsAsStrings) {
            testPacket = TestUtil.createCommandPacket(
                    "",
                    new String[][]{
                            {testParam, invalidInput},
                    }
            );
            ParamChecker.getInstance().setPacket(testPacket);
            try {
                int intChecked = ParamChecker.getInstance().checkAndReturnInt(testParam);
                fail("Input that did not throw error when it was supposed to: " + invalidInput);
            } catch (Exception exception) {
                //Error message generated inside ParamChecker
                assertCorrectErrorMessage(ParamChecker.getInstance(),
                        ParamChecker.getErrorMessageNumberFormatException(invalidInput, ""));
                //Error thrown by ParamChecker, printed outside of ParamChecker
                assertEquals("Failed to parse the following param: " + testParam,
                        exception.getMessage());
            }
        }
    }

    /**
     * Asserts that the correct error message has been set.
     * @param paramChecker Actual ParamChecker instance used for testing, after checking some value
     * @param expectedErrorMessage Expected error message
     */
    public void assertCorrectErrorMessage(ParamChecker paramChecker, String expectedErrorMessage) {
        assertEquals(
            expectedErrorMessage,
            paramChecker.getErrorMessage()
        );
    }
}
