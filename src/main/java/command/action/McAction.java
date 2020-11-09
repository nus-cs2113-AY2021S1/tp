//@@author scjx123

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Item;
import data.SingleModule;
import data.Data;
import exceptions.CommandException;

import java.util.ArrayList;

public class McAction extends Action {
    private boolean isDetail = false;

    private String userInput = "";

    /**
     * Return a string based on whether user wants to print MC or list individual detail MC.
     *
     * @param data the data
     * @return builder which appends the resulting MC
     * @throws Exception No specific action
     */
    @Override
    public String act(Data data) throws Exception {
        StringBuilder builder = new StringBuilder(Constants.MC_HEAD);
        String flag = data.flag;
        ArrayList<Item> moduleList = new ArrayList<>(data.getTarget());
        if (moduleList.equals(data.tasks)) {
            moduleList = new ArrayList<>(data.mods);
        }
        if (isDetail) {
            for (Item item : moduleList) {
                SingleModule m = (SingleModule)item;
                builder.append(m.moduleCode).append(Constants.DETAILS_SIGNATURE)
                        .append(m.getModuleMC().trim()).append("MCs").append(Constants.WIN_NEWLINE);
            }
        } else {
            int sum = 0;
            data.getTarget();
            for (Item item : data.target) {
                SingleModule m = (SingleModule)item;
                sum += Integer.parseInt(m.getModuleMC().trim());
                assert sum != 0 : "sum should not be zero";
            }
            data.getTarget(flag);
            builder.append(sum).append(Constants.WIN_NEWLINE); //build a string
        }
        return builder.toString();
    }

    /**
     * Set isDetail flag if the user wish to print detailed MC.
     *
     * @param args the args
     * @throws Exception thrown when invalid command is entered
     */
    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        int len = flattenedArgs.length;

        if (len == 0) {
            isDetail = false;
            userInput = "";
        } else {
            userInput = flattenedArgs[0].toFlatString();
            String[] optionalParams = Constants.optionalParamMap.get(args.name);
            String detail = optionalParams[0];
            isDetail = userInput.equals(detail);

            if (isDetail) {
                if (len > 1) {
                    throw new CommandException();
                }
                isDetail = true;
                userInput = "";
            } else {
                throw new CommandException();
            }
        }
    }
}
