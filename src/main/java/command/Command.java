//@@author TomLBZ

package command;

import command.action.UnknownAction;
import command.action.Action;
import constants.Constants;
import constants.HelpText;
import data.Data;
import exceptions.InvalidCommandException;

/**
 * The type Command.
 */
public class Command implements Help {

    /**
     * The Args.
     */
    public ParamNode args;
    /**
     * The Flattened args.
     */
    public ParamNode[] flattenedArgs;
    /**
     * The Name.
     */
    public String name;
    private final HelpText helpText;
    /**
     * The Action.
     */
    public Action action;
    /**
     * The Result.
     */
    public String result = "";

    /**
     * Instantiates a new Command.
     *
     * @param args the args
     */
    public Command(ParamNode args) {
        this.args = args;
        name = args.name;
        flattenedArgs = new ParamNode[0];
        if (args.thisData != null) {
            flattenedArgs = args.thisData.flatten().toArray(flattenedArgs);
        }
        helpText = Constants.helpMap.getOrDefault(name, HelpText.UNKNOWN); // later change to help.
        initiateAction();
    }

    private boolean isArgsValid() {
        String[] targetArgs = Constants.compulsoryParamMap.get(name);
        if (targetArgs == null) {
            return true; // does not need any parameter
        } else {
            for (ParamNode node : flattenedArgs) {
                for (String arg : targetArgs) {
                    if (arg.equals(node.name)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private void initiateAction() {
        action = Constants.actionMap.getOrDefault(name, new UnknownAction());
    }

    /**
     * Execute.
     *
     * @param data the data
     */
    public void execute(Data data) {
        try {
            if (isArgsValid()) {
                action.prepare(args);
                result = action.act(data);
            } else {
                throw new InvalidCommandException();
            }
        } catch (Exception e) {
            StringBuilder builder = new StringBuilder(e.getMessage());
            String[] syntaxes = getSyntax();
            for (String syntax : syntaxes) {
                builder.append(Constants.SPACE.repeat(4)).append(syntax).append(Constants.WIN_NEWLINE);
            }
            builder.append(Constants.NOTES).append(Constants.WIN_NEWLINE);
            String[] notes = getNotes();
            for (String note : notes) {
                builder.append(note).append(Constants.WIN_NEWLINE);
            }
            result = builder.toString().replace(Constants.WIN_NEWLINE.repeat(2), Constants.WIN_NEWLINE);
        }
    }

    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    public boolean isBye() {
        return result.equals(Constants.messageMap.get(Constants.BYE));
    }

    /**
     * Is fancy boolean.
     *
     * @return the boolean
     */
    public boolean isFancy() {
        return result.equals(Constants.messageMap.get(Constants.FANCY));
    }

    /**
     * Is plain boolean.
     *
     * @return the boolean
     */
    public boolean isPlain() {
        return result.equals(Constants.messageMap.get(Constants.PLAIN));
    }

    /**
     * Is TimerChanged boolean.
     * @return the boolean
     */
    public boolean isSnoozed() {
        return result.equals(Constants.messageMap.get(Constants.SNOOZE));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return helpText.description;
    }

    @Override
    public String[] getSyntax() {
        return helpText.syntax;
    }

    @Override
    public String[] getNotes() {
        return helpText.notes;
    }

    @Override
    public String[] getUsages() {
        return helpText.usages;
    }

    @Override
    public String getHelp() {
        return helpText.toString();
    }

    @Override
    public String toString() {
        return args.toString();
    }

}