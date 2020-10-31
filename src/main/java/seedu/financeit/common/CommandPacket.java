package seedu.financeit.common;

import java.util.HashMap;
import java.util.Set;

/**
 * Organises data from input string into distinct data types and structure.
 * For scalability purposes, a hashmap is implemented to map param type to its corresponding params.
 */
public class   CommandPacket {
    private String commandString;
    private HashMap paramMap;

    // Constructors
    public CommandPacket(String taskType, HashMap paramMap) {
        this.commandString = taskType;
        this.paramMap = paramMap;
    }

    public CommandPacket() {
    }

    // Param type refers to /.* (eg. /a)
    // Param refers to string following the param type
    public String getParam(String paramType) {
        if (!paramMap.containsKey(paramType)) {
            return null;
        }

        return (String) paramMap.get(paramType);
    }

    public Set<String> getParamTypes() {
        return paramMap.keySet();
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

    public void removeParamsFromMap(String... paramTypes) {
        for (String paramType: paramTypes) {
            this.paramMap.remove(paramType);
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s\n", this.commandString, this.paramMap);
    }
}
