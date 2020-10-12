package seedu.duke.logic.parser;

public class RouteParser extends Parser {

    private String rawMessage;

    public RouteParser(String message) {
        super(message);
    }

    public String[] getLocations() {
        String[] components = super.splitCommands(2, "/to");
        return new String[]{components[0], components[1]};
    }
}
