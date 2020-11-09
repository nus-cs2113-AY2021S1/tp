//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Item;
import data.Data;
import data.jobs.Task;
import exceptions.CommandException;
import messages.MessageOptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The type List action.
 */
public class ListAction extends Action {

    private boolean isAsc = false;
    private boolean isDesc = false;
    private String stringDate = "";

    @Override
    public String act(Data data) throws Exception {
        StringBuilder builder = new StringBuilder(Constants.LIST_HEAD);
        ArrayList<Item> target = new ArrayList<>(data.getTarget());

        if (!stringDate.equals("")) { // this means it's in specific mode
            LocalDateTime dateTime = Item.parseDateTime(stringDate);
            if (dateTime != null) {
                LocalDate date = dateTime.toLocalDate();
                data.target = target.stream().filter(x -> x instanceof Task && x.isDated
                        && date.compareTo(x.getDate()) == 0).collect(Collectors.toCollection(ArrayList::new));
                for (Item item : data.target) {
                    builder.append(item.toString()).append(Constants.WIN_NEWLINE);
                }
            }
        } else {
            if (isAsc) {
                target.removeIf(x -> !x.isDated);
                target.sort(Comparator.comparing(Item::getDateTime));
            }
            if (isDesc) {
                target.removeIf(x -> !x.isDated);
                target.sort((x, y) -> -x.getDateTime().compareTo(y.getDateTime()));
            }
            data.target = target;
            for (Item item : target) {
                builder.append(item.toString()).append(Constants.WIN_NEWLINE);
            }
        }


        if (builder.toString().equals(Constants.LIST_HEAD)) {
            builder.append(Constants.NOT_FOUND);
        } else {
            data.indexOption = MessageOptions.INDEXED_NUM;
        }
        return builder.toString();
    }

    /**
     * Picking up optional parameter and check if user entered.
     *
     * @param args the args
     * @throws Exception to handle prepare exceptions.
     */
    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        int len = flattenedArgs.length;
        if (len == 0) {
            isDesc = false;
            isAsc = false;
            stringDate = "";
        } else {
            stringDate = flattenedArgs[0].toFlatString();
            String[] optionalParams = Constants.optionalParamMap.get(args.name);
            String optionalParam = optionalParams[0];
            String asc = optionalParams[1];
            String desc = optionalParams[2];
            String spec = optionalParams[3];
            boolean isDated = stringDate.contains(optionalParam);

            if (isDated) {
                stringDate = stringDate.replace(optionalParam, Constants.ZERO_LENGTH_STRING).trim();
                String[] options = stringDate.split(Constants.SPACE);
                String option = options[0];
                if (option.equals(spec) && options.length > 1) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < options.length; i++) {
                        builder.append(options[i]).append(Constants.SPACE);
                    }
                    stringDate = builder.toString();
                } else if (option.equals(asc)) {
                    isAsc = true;
                    isDesc = false;
                    stringDate = "";
                } else if (option.equals(desc)) {
                    isDesc = true;
                    isAsc = false;
                    stringDate = "";
                } else {
                    isAsc = false;
                    isDesc = false;
                    stringDate = "";
                    throw new Exception();
                }
            } else if (stringDate.trim().length() == 0) {
                stringDate = "";
            } else {
                throw new CommandException();
            }
        }
    }

}
