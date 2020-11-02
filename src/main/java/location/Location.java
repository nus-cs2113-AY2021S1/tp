package location;


/**
 * Represents location objects.
 * Contains String name, which is the name of location, and type of location.
 */
public class Location {
    protected String name;
    protected LocationType type;
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

    /**
     * All location class objects should have this methods, except BusStop.
     *
     * @return the string to be wrote in the file.
     */
    public String fileString() {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object instanceof Location) {
            isEqual = (this.name.equals(((Location) object).name));
        }

        return isEqual;
    }
}
