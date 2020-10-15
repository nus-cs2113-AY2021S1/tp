package location;

enum locationType {
    BUILDING,
    HOSTEL,
    LT
}

public class Location {
    protected String name;
    protected locationType type;
    // private int[] coordinates = new int[2];  // will implement in version 2.0

    public Location(String name) {
        this.name = name;
    }

    public String printType() {
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
            default:
                str = "invalid location type";
        }

        return str;
    }

    public String toString() {
        return "(" + printType() + ")" + name;
    }
}
