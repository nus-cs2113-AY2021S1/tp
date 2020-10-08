package seedu.duke.utility;

/*TODO include more parser classes (storage.parser, command.parser etc in the future)
*  Use save/load function to load help commands to user instead
* */


public class InputParser {

    private boolean isBye;

    public InputParser() {
        this.isBye = false;
    }

    public boolean isByeTime() {
        return isBye;
    }

    /*
    * In case i need to use it for future commands, might need to see how the others do their commands
    * */
    private static java.util.ArrayList<String> tokenizeToStringArray(String input, String[] argumentDelimiters) {
        int argumentDelimitersIndex = 0;
        String[] inputSplit = input.split(" ");
        java.util.ArrayList<String> argumentArray = new java.util.ArrayList<>();
        StringBuilder argument = new StringBuilder();
        for (int index = 0; index < inputSplit.length; index++) {
            if (argumentDelimitersIndex >= argumentDelimiters.length) {
                argument.append(inputSplit[index]).append(" ");
            } else if (inputSplit[index].equals(argumentDelimiters[argumentDelimitersIndex])) {
                argumentArray.add(argument.toString().trim());
                argumentDelimitersIndex++;
                argument = new StringBuilder();
            } else {
                argument.append(inputSplit[index]).append(" ");
            }
        }
        argumentArray.add(argument.toString().trim());
        return argumentArray;
    }


    public void parseInput(String input) {
        String command = input.split(" ")[0];

        switch (command.toLowerCase()) {

        case "bye":
            Ui.printByeMessage();
            this.isBye = true;
            return;

        case "help":
            Ui.printHelp();
            return;

        case "add":

            return;

        case "edit":

            return;

        case "rating":

            return;

        case "list":

            return;

        case "delete":

            return;

        case "deleterating":

            return;

        case "changerating":

            return;

        case "save":

            return;
        case "":

            return;

        default:

        }
    }
}

