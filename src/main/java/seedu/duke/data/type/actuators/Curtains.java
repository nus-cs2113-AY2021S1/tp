package seedu.duke.data.type.actuators;

public class Curtains extends Actuators {

    public Curtains(String name, String location, String power) {
        super(name, location, power);
    }

    public String getType() {
        return "Curtain";
    }

}
