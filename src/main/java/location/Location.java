package location;

enum locationType {
    BUILDING,
    HOSTEL,
    LT,
    OUT_OF_NUS
}

/**
 * Represents location objects.
 * Contains String name, which is the name of location, and type of location.
 */
public class Location {
    protected String name;
    protected locationType type;
    // private int[] coordinates = new int[2];  // will implement in version 2.0

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected String printType() {
        String str;
        switch (type) {
        case BUILDING:
            str = "Building";
            break;
        case HOSTEL:
            str = "Hostel";
            break;
        case LT:
            str = "Lecture Theatre";
            break;
        case OUT_OF_NUS:
            str = "Out of NUS";
            break;
        default:
            str = "ERROR";
        }

        return str;
    }

    public String toString() {
        return "(" + printType() + ")" + name;
    }
}
