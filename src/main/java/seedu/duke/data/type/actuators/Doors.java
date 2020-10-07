package seedu.duke.data.type.actuators;


public class Doors extends Actuators {

    public Doors(String name, String location, String power) {
        super(name, location, power);
    }

    public String getType() {
        return "Doors";
    }

}
