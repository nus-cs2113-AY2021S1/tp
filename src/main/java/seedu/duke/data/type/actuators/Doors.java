package seedu.duke.data.type.actuators;


public class Doors extends Actuators {

    public Doors(String name, String location) {
        super(name, location);
    }

    public String getType() {
        return "Doors";
    }

}
