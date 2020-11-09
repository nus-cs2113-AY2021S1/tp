//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;
import exceptions.CommandException;

import java.util.ArrayList;

/**
 * The type Take action.
 */
public class TakeAction extends Action {
    protected ArrayList<Integer> indices;
    protected ArrayList<String> codes;
    protected boolean isBlind = false;
    int successes = 0;
    protected String blindSearch = Constants.SELECTED;

    @Override
    public String act(Data data) throws Exception {
        successes = 0;
        boolean isEmptySelection = false;
        String flag = data.flag;
        ArrayList<Item> targetBackup = data.target;
        StringBuilder builder = new StringBuilder();
        if (isBlind) {
            StringBuilder testContent = new StringBuilder();
            data.getTarget(blindSearch).forEach(x -> {
                if (x instanceof SingleModule) {
                    if (modifyObject(x)) {
                        testContent.append(getObjectInfo(x)).append(Constants.WIN_NEWLINE);
                    } else {
                        testContent.append(Constants.MODIFY_FAILED)
                                .append(x.getName()).append(Constants.WIN_NEWLINE);
                    }
                }
            });
            if (testContent.toString().length() > 0) {
                builder.append("You did not specify modules, looking for your selected modules...")
                        .append(Constants.WIN_NEWLINE).append(testContent.toString());
            } else {
                isEmptySelection = true;
                builder.append(getEmptySelectionMessage());
            }
        } else {
            if (!indices.isEmpty()) {
                for (int i : indices) {
                    if (i < 0 || i > data.target.size() - 1) {
                        throw new IndexOutOfBoundsException(Constants.INDEX_OUT);
                    }
                    if (data.target.get(i) instanceof SingleModule) {
                        Item module = data.target.get(i);
                        module.immediateData = String.valueOf(i + 1);
                        if (modifyObject(module)) {
                            builder.append("Module ").append(i + 1).append(": ")
                                    .append(getObjectInfo(module)).append(Constants.WIN_NEWLINE);
                        } else {
                            builder.append(Constants.MODIFY_FAILED)
                                    .append(((SingleModule) module).moduleCode).append(Constants.WIN_NEWLINE);
                        }
                    } else {
                        builder.append("Item ").append(i + 1).append(" is not a module, "
                                + "therefore cannot be taken or untaken").append(Constants.WIN_NEWLINE);
                    }
                }
            }
            if (!codes.isEmpty()) {
                data.mods.stream().filter(x -> codes.contains(((SingleModule) x).moduleCode)).forEach(x -> {
                    if (modifyObject(x)) {
                        builder.append("Module: ").append(getObjectInfo(x)).append(Constants.WIN_NEWLINE);
                    } else {
                        builder.append(Constants.MODIFY_FAILED)
                                .append(x.getName()).append(Constants.WIN_NEWLINE);
                    }
                });
            }
        }
        data.setFlag(flag);
        data.target = targetBackup;
        String result = super.act(data);
        String execution = builder.toString();
        if (execution.equals(Constants.ZERO_LENGTH_STRING)) {
            execution = execution.concat(Constants.NOT_FOUND);
        }
        if (isEmptySelection) {
            return execution;
        } else if (successes == 0) {
            return Constants.TAKEN_NOT_MODIFIABLE + Constants.WIN_NEWLINE + execution;
        } else {
            return result.replace(Constants.TEXT_PLACEHOLDER, execution);
        }
    }

    protected boolean modifyObject(Item item) {
        if (((SingleModule)item).isCompleted) {
            return false;
        }
        ((SingleModule)item).isTaken = true;
        ((SingleModule)item).grade = "T";
        successes++;
        return true;
    }

    protected String getObjectInfo(Item item) {
        return item.getName() + ": now taken";
    }

    protected String getEmptySelectionMessage() {
        return Constants.TAKEN_CHANGED_FAILED + Constants.WIN_NEWLINE;
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        isBlind = false;
        blindSearch = Constants.SELECTED;
        indices = new ArrayList<>();
        codes = new ArrayList<>();
        super.prepare(args);
        if (args.thisData == null || flattenedArgs.length < 1) {
            safetyCheck();
            return;
        }
        String[] identifiers = args.thisData.toFlatString().split(Constants.SPACE);
        for (String id : identifiers) {
            try {
                indices.add(Integer.parseInt(id) - 1);
            } catch (Exception e) {
                char ch = id.toUpperCase().toCharArray()[0];
                if (id.length() == 1 && Character.isLetter(ch)) {
                    indices.add((int) ch - Constants.LETTER_OFFSET - 1);
                } else {
                    codes.add(id.toUpperCase());
                }
            }
        }
        boolean isCodes = codes != null && codes.size() > 0;
        boolean isIndices = indices != null && indices.size() > 0;
        if (!isCodes && !isIndices) {
            throw new CommandException();
        }
    }

    protected void safetyCheck() throws Exception {
        isBlind = true;
    }

    protected String superAct(Data data) throws Exception {
        return super.act(data);
    }

    protected void superPrepare(ParamNode args) throws Exception {
        super.prepare(args);
    }
}
