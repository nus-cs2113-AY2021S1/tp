//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;

/**
 * The type Action.
 */
public class Action {

    /**
     * The Args.
     */
    protected ParamNode args;
    /**
     * The Flattened args.
     */
    protected ParamNode[] flattenedArgs;

    /**
     * Instantiates a new Action.
     */
    public Action() {
        args = null;
    }

    /**
     * Instantiates a new Action.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public Action(ParamNode args) throws Exception {
        prepare(args);
    }

    /**
     * Act or execute the action.
     *
     * @param data the data
     * @return the string
     */
    public String act(Data data) throws Exception {
        return Constants.messageMap.getOrDefault(args.name, Constants.INVALID);
    }

    /**
     * Prepare parameters for task to act.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public void prepare(ParamNode args) throws Exception {
        this.args = args;
        flattenedArgs = new ParamNode[0];
        if (args.thisData != null) {
            flattenedArgs = args.thisData.flatten().toArray(flattenedArgs);
        }
    }

    /**
     * Replace strings string.
     *
     * @param source the source
     * @param text   the text
     * @param count  the count
     * @return the string
     */
    protected String replaceStrings(String source, String text, int count) {
        String output = source.replace(Constants.TEXT_PLACEHOLDER, text);
        return output.replace(Constants.NUMBER_PLACEHOLDER, String.valueOf(count));
    }

    /**
     * Gets index.
     *
     * @param input the input
     * @return the index
     */
    protected int getIndex(String input) {
        if (input == null || input.length() == 0) {
            return -1;
        }
        try {
            return Integer.parseInt(input) - 1;
        } catch (Exception e) {
            char character = input.toCharArray()[0];
            if (Character.isLetter(character)) {
                character = Character.toUpperCase(character);
                return (int)character - Constants.LETTER_OFFSET - 1;
            }
            return -1;
        }
    }
}
