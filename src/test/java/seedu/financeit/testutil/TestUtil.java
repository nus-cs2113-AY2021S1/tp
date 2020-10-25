package seedu.financeit.testutil;

import seedu.financeit.common.CommandPacket;

import java.util.HashMap;

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
}
