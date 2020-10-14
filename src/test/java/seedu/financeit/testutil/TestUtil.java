package seedu.financeit.testutil;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.utils.ParamChecker;

import java.util.HashMap;

public class TestUtil {
    public static ParamChecker createParamChecker(CommandPacket packet) {
        return new ParamChecker(packet);
    }

    public static CommandPacket createCommandPacket(String commandString, String[][] paramInput) {
        HashMap<String, String> paramMap = new HashMap<>();
        for (String[] paramSet : paramInput) {
            paramMap.put(paramSet[0], paramSet[1]);
        }
        return new CommandPacket(commandString, paramMap);
    }
}
