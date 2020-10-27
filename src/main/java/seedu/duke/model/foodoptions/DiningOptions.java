package seedu.duke.model.foodoptions;

public class DiningOptions {

    String name;
    String location;
    String operatingHrs;

    DiningOptions(String[] data) {
        name = data[0];
        location = data[1];
        operatingHrs = data[2];
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + ":\n" + location + "\n" + operatingHrs;
    }
}
