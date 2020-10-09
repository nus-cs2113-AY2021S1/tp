package seedu.duke.data.actuators;

public class Curtains extends Actuators {

    public Curtains(String name, String location, String power) {
        super(name, location, power);
    }

    public String getType() {
        return "Curtain";
    }

}
