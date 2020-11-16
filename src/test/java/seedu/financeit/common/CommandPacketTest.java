package seedu.financeit.common; /**
 * Organises data from input string into distinct data types and structure.
 * For scalability purposes, a hash table is implemented to map param type to its corresponding params.
 */

import java.util.HashMap;

public class CommandPacketTest {
    private String commandString = null;
    private HashMap paramMap = null;
    private String rawInput;

    // Constructors
    public CommandPacketTest(String taskType, HashMap paramMap) {
        this.commandString = taskType;
        this.paramMap = paramMap;
    }

    // Param type refers to /.* (eg. /a)
    // Param refers to string following the param type
    public String getParam(String paramType) {
        if (!paramMap.containsKey(paramType)) {
            return null;
        } else {
            return (String) paramMap.get(paramType);
        }
    }

    public String[] getParamTypes() {
        return (String[]) paramMap.keySet().toArray(new String[0]);
    }

    public String getCommandString() {
        return this.commandString.trim();
    }

    public HashMap getParamMap() {
        return (HashMap)this.paramMap.clone();
    }

    public void addParamToMap(String paramType, String paramString) {
        this.paramMap.put(paramType, paramString);
    }

    @Override
    public String toString() {
        return String.format("%s, %s\n", this.commandString, this.paramMap);
    }
}
