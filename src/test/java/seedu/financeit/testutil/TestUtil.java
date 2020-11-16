package seedu.financeit.testutil;

import seedu.financeit.common.CommandPacket;

import java.util.HashMap;

/**
 * Class that curates utility functions that assist the test methods throughout the test classes.
 */
public class TestUtil {
    public static CommandPacket createCommandPacket(String commandString, String[][] paramInput) {
        HashMap<String, String> paramMap = new HashMap<>();
        if (paramInput != null) {
            for (String[] paramSet : paramInput) {
                paramMap.put(paramSet[0], paramSet[1]);
            }
        }
        return new CommandPacket(commandString, paramMap);
    }

    /**
     * Creates a command packet. Assumes that paramTypes and paramArguments are of the same length.
     *
     * @param commandString command to execute e.g. "new" or "edit"
     * @param paramTypes Array of param types e.g. ["/desc", "/amt"]
     * @param paramArguments Array of param arguments associated with paramTypes
     * @return CommandPacket created
     */
    public static CommandPacket createCommandPacket(String commandString,
                                                    String[] paramTypes, String[] paramArguments) {
        HashMap<String, String> paramMap = new HashMap<>();
        if (paramTypes != null) {
            for (int i = 0; i < paramTypes.length; i++) {
                paramMap.put(paramTypes[i], paramArguments[i]);
            }
        }
        return new CommandPacket(commandString, paramMap);
    }
}
