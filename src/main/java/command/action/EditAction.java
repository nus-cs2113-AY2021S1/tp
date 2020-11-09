//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;
import data.jobs.Deadline;
import data.jobs.Event;
import data.jobs.Task;
import data.jobs.ToDo;
import exceptions.CommandException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class EditAction extends Action {

    private class Operation {

        public boolean isMod;
        public String modCode;
        public int itemIndex;
        public ArrayList<String> operations;
        public String operationResult;

        public void defaultOperation() {
            isMod = false;
            modCode = null;
            itemIndex = -1;
            operations = new ArrayList<>();
        }

        public void normalOperation(boolean isMod, String input) throws Exception {
            this.isMod = isMod;
            boolean isNumeric = isNumeric(input);
            if (isNumeric) {
                modCode = null;
                itemIndex = Integer.parseInt(input) - 1;
                if (itemIndex < 0) {
                    itemIndex = -1;
                }
            } else {
                if (isMod) {
                    modCode = input.trim();
                } else {
                    throw new Exception("A task must be referenced by an index!" + Constants.WIN_NEWLINE);
                }
            }
            operations = new ArrayList<>();
        }

        public Operation(boolean isMod, ArrayList<String> strings) throws Exception {
            if (strings == null || strings.size() == 0) {
                this.defaultOperation();
            }
            assert strings != null;
            String input = strings.get(0);
            strings.remove(0);
            normalOperation(isMod, input);
            if (strings.size() == 0) {
                throw new Exception(Constants.NO_OPERATION_POSSIBLE);
            }
            operations.addAll(strings);
        }

        private boolean isNumeric(String input) {
            if (input == null || input.trim().isEmpty()) {
                return false;
            }
            char[] chars = input.trim().toCharArray();
            boolean isNum = true;
            for (char c: chars) {
                isNum = Character.isDigit(c);
                if (!isNum) {
                    break;
                }
            }
            return isNum;
        }

        public Item operate(Item item) throws Exception {
            if (isMod && item instanceof SingleModule) {
                return operateMod((SingleModule) item);
            } else {
                return operateTask((Task) item);
            }
        }

        private SingleModule operateMod(SingleModule mod) throws Exception {
            StringBuilder builder = new StringBuilder("Working on Module: " + mod.moduleCode + Constants.WIN_NEWLINE);
            mod.immediateData = null;
            if (!mod.isCompleted) {
                for (String operation : operations) {
                    String op = operation.replace(Constants.LINE_UNIT, Constants.SPACE).trim();
                    boolean operated = true;
                    if (op.contains(Constants.EQUALS)) {
                        String[] split = op.split(Constants.EQUALS);
                        if (split.length != 2) {
                            throw new Exception("Space are not allowed in the \"field=new_value\" parameter!"
                                    + Constants.WIN_NEWLINE);
                        }
                        split[0] = split[0].toLowerCase();
                        if (Arrays.stream(Constants.GRADE_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                            boolean isBroken = false;
                            if (split[1].length() >= 1) {
                                String tmpGrade = split[1].toUpperCase().substring(0, Math.min(2, split[1].length()));
                                for (String grade : Constants.VALID_GRADES) {
                                    if (grade.equals(tmpGrade)) {
                                        mod.grade = tmpGrade;
                                        isBroken = true;
                                        break;
                                    }
                                }
                            }
                            if (!isBroken) {
                                StringBuilder validBuilder = new StringBuilder();
                                for (String grade : Constants.VALID_GRADES) {
                                    validBuilder.append(grade).append(Constants.SPACE);
                                }
                                String validGrades = validBuilder.toString();
                                throw new Exception("Grade must be one of [" + validGrades + "]!"
                                        + Constants.WIN_NEWLINE);
                            }
                            mod.isTaken = true; // must be taken in order to have a grade
                            op = split[0] + Constants.SPACE + Constants.EQUALS + Constants.SPACE + mod.grade
                                    + " AND taken = true;" + Constants.WIN_NEWLINE
                                    + "(The module must be taken in order to have a grade)";
                        } else if (Arrays.stream(Constants.SU_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                            if (split[1].contains("s")) {
                                mod.moduleSU = "S";
                            } else {
                                mod.moduleSU = "U";
                            }
                            op = split[0] + Constants.SPACE + Constants.EQUALS + Constants.SPACE + mod.moduleSU;
                        } else if (Arrays.stream(Constants.SELECTED_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                            mod.isSelected = split[1].toLowerCase().contains("t");
                            op = split[0] + Constants.SPACE + Constants.EQUALS + Constants.SPACE + mod.isSelected;
                        } else if (Arrays.stream(Constants.TAKEN_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                            mod.isTaken = split[1].toLowerCase().contains("t");
                            op = split[0] + Constants.SPACE + Constants.EQUALS + Constants.SPACE + mod.isTaken;
                        } else if (Arrays.stream(Constants.TASK_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                            mod.immediateData = split[1];
                        } else {
                            builder.append("\"").append(op).append("\" is not a valid operation on module ")
                                    .append(mod.moduleCode).append(", and is skipped.").append(Constants.WIN_NEWLINE);
                            operated = false;
                        }
                    } else {
                        operated = false;
                        builder.append("\"").append(op).append("\" is not a valid operation on module ")
                                .append(mod.moduleCode).append(", and is skipped.").append(Constants.WIN_NEWLINE);
                    }
                    if (operated) {
                        builder.append(mod.moduleCode).append(": ").append(op)
                                .append(Constants.CMD_END).append(Constants.WIN_NEWLINE);
                    }
                }
            }
            operationResult = builder.toString();
            if (operationResult.length() == 0) {
                throw new Exception(Constants.NO_OPERATION_POSSIBLE);
            }
            return mod;
        }

        private Task operateTask(Task task) throws Exception {
            StringBuilder builder = new StringBuilder("Working on Task: " + task.toString() + Constants.WIN_NEWLINE);
            for (String operation : operations) {
                String op = operation.replace(Constants.LINE_UNIT, Constants.SPACE).trim();
                boolean operated = true;
                if (op.contains(Constants.EQUALS)) {
                    String[] split = op.split(Constants.EQUALS);
                    if (split.length != 2) {
                        throw new Exception("Space are not allowed in the \"field=new_value\" parameter!"
                                + Constants.WIN_NEWLINE);
                    }
                    split[0] = split[0].toLowerCase();
                    if (Arrays.stream(Constants.DESCRIPTION_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                        task.setDescription(split[1]);
                    } else if (Arrays.stream(Constants.TYPE_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                        String dateTime = task.getStringFromDateTime(LocalDateTime.now());
                        if (task.isDated) {
                            dateTime = task.getDateTimeString();
                        }
                        switch (split[1].toLowerCase()) {
                        case "deadline": // same as "ddl"
                        case "ddl": // same as "d"
                        case "d":
                            Deadline ddl = new Deadline(task.getDescription(), dateTime);
                            ddl.isSelected = task.isSelected;
                            ddl.isWeekly = task.isWeekly;
                            if (task.getIsDone()) {
                                ddl.markAsDone();
                            }
                            task = ddl;
                            break;
                        case "event": // same as "e"
                        case "e":
                            Event event = new Event(task.getDescription(), dateTime);
                            event.isSelected = task.isSelected;
                            event.isWeekly = task.isWeekly;
                            if (task.getIsDone()) {
                                event.markAsDone();
                            }
                            task = event;
                            break;
                        case "todo": // same as "t"
                        case "t":
                            ToDo todo = new ToDo(task.getDescription());
                            todo.isSelected = task.isSelected;
                            todo.isWeekly = task.isWeekly;
                            if (task.getIsDone()) {
                                todo.markAsDone();
                            }
                            task = todo;
                            break;
                        default:
                            operated = false;
                            break;
                        }
                    } else if (Arrays.stream(Constants.DATE_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                        if (task.isDated) {
                            LocalDateTime localDateTime = Item.parseDateTime(split[1]);
                            if (localDateTime != null) {
                                task.updateDateTime(localDateTime);
                            } else {
                                task.setTimeString(split[1]);
                            }
                        } else {
                            operated = false;
                        }
                    } else if (Arrays.stream(Constants.SELECTED_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                        task.isSelected = split[1].toLowerCase().contains("t");
                        op = split[0] + Constants.SPACE + Constants.EQUALS + Constants.SPACE + task.isSelected;
                    } else if (Arrays.stream(Constants.WEEKLY_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                        task.isWeekly = split[1].toLowerCase().contains("t");
                        op = split[0] + Constants.SPACE + Constants.EQUALS + Constants.SPACE + task.isWeekly;
                    } else if (Arrays.stream(Constants.DONE_ALIAS).anyMatch(s -> s.equals(split[0]))) {
                        if (split[1].toLowerCase().contains("t")) {
                            task.markAsDone();
                        } else {
                            task.markAsUndone();
                        }
                        op = split[0] + Constants.SPACE + Constants.EQUALS + Constants.SPACE + task.getIsDone();
                    } else {
                        builder.append("\"").append(op).append("\" is not a valid operation on task ")
                                .append(task.getDescription()).append(", and is skipped.")
                                .append(Constants.WIN_NEWLINE);
                        operated = false;
                    }
                } else {
                    builder.append("\"").append(op).append("\" is not a valid operation on task ")
                            .append(task.getDescription()).append(", and is skipped.").append(Constants.WIN_NEWLINE);
                    operated = false;
                }
                if (operated) {
                    builder.append(task.getDescription()).append(": ").append(op).append(Constants.CMD_END)
                            .append(Constants.WIN_NEWLINE);
                }
            }
            operationResult = builder.toString();
            if (operationResult.length() == 0) {
                throw new Exception(Constants.NO_OPERATION_POSSIBLE);
            }
            return task;
        }
    }

    private ArrayList<Operation> operations;

    private Item findMod(ArrayList<Item> mods, ArrayList<Item> targets, int index, String code) throws Exception {
        if (code != null) {
            for (Item item : mods) {
                if (item.getName().equals(code)) {
                    return item;
                }
            }
        } else {
            if (index < 0 || index >= targets.size()) {
                throw new Exception(Constants.INDEX_OUT);
            }
            Item target = targets.get(index);
            if (target instanceof SingleModule) {
                return target;
            } else {
                return mods.get(index);
            }
        }
        throw new Exception("The module you specified is either completed or non-existent." + Constants.WIN_NEWLINE);
    }

    private Item findTask(ArrayList<Item> targets, int index) throws Exception {
        if (index < 0 || index >= targets.size()) {
            throw new Exception("Linked Task Index out of Range! " + Constants.WIN_NEWLINE
                    + "Try \"detail [code]\" to see linked tasks for this module." + Constants.WIN_NEWLINE);
        }
        Item target = targets.get(index);
        if (!(target instanceof SingleModule)) {
            return target;
        }
        throw new Exception("The item with the specified index is not a Task." + Constants.WIN_NEWLINE);
    }

    @Override
    public String act(Data data) throws Exception {
        String defaultResult = super.act(data);
        StringBuilder stringBuilder = new StringBuilder();
        if (operations == null || operations.size() == 0) {
            throw new Exception(Constants.NO_OPERATION_POSSIBLE);
        } else {
            ArrayList<Item> targets = data.getTarget();
            for (Operation operation : operations) {
                Item target;
                if (operation.isMod) {
                    target = findMod(data.mods, targets, operation.itemIndex, operation.modCode);
                } else {
                    target = findTask(targets, operation.itemIndex);
                }
                Item operatedTarget = operation.operate(target);
                if (operatedTarget instanceof SingleModule) {
                    updateTask((SingleModule)operatedTarget);
                }
                data.updateItem(target, operatedTarget);
                stringBuilder.append(operation.operationResult);
            }
        }
        return defaultResult.replace(Constants.TEXT_PLACEHOLDER, stringBuilder.toString());
    }

    private void updateTask(SingleModule mod) throws Exception {
        if (mod.immediateData == null) {
            return;
        }
        String[] splitStrings = mod.immediateData.split("<");
        if (splitStrings.length != 2) {
            throw new CommandException();
        }
        String indexString = splitStrings[1].replace(">", Constants.ZERO_LENGTH_STRING);
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (Exception e) {
            throw new NumberFormatException("The format of index seems wrong..." + Constants.WIN_NEWLINE);
        }
        if (index < 0 || index > mod.taskList.size() - 1) {
            throw new Exception("Linked Task Index out of Range! " + Constants.WIN_NEWLINE
                    + "Try \"detail [module code]\" to see linked tasks for this module." + Constants.WIN_NEWLINE);
        }
        if (mod.taskList.get(index) == null) {
            throw new Exception("The specified linked task does not exist" + Constants.WIN_NEWLINE);
        }
        String cmd = splitStrings[0];
        switch (cmd) {
        case "del":
            mod.taskList.remove(index - 1);
            break;
        case "done":
            ((Task)mod.taskList.get(index - 1)).markAsDone();
            break;
        case "undone":
            ((Task)mod.taskList.get(index - 1)).markAsUndone();
            break;
        default:
            throw new CommandException();
        }
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        operations = new ArrayList<>();
        super.prepare(args);
        for (ParamNode arg : flattenedArgs) {
            if (arg.thisData == null) {
                throw new CommandException();
            }
            if (arg.name.equals(Constants.MOD)) {
                String[] strings = arg.thisData.toFlatString().split(Constants.SPACE);
                operations.add(new Operation(true, new ArrayList<>(Arrays.asList(strings))));
            } else if (arg.name.equals(Constants.TASK)) {
                String[] strings = arg.thisData.toFlatString().split(Constants.SPACE);
                operations.add(new Operation(false, new ArrayList<>(Arrays.asList(strings))));
            } else {
                throw new CommandException();
            }
        }
    }
}
