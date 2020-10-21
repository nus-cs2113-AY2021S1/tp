package location;

/**
 * Represents lecture theatre objects.
 * Contains a String nearestBuilding which represents the name of nearest building.
 */
public class LectureTheatre extends Location {
    private final String nearestBuilding;

    public LectureTheatre(String name, String nearestBuilding) {
        super(name);
        this.type = LocationType.LT;
        this.nearestBuilding = nearestBuilding;
    }

    /**
     * Prepares string to be printed in a list.
     *
     * @return object to be printed in a certain format.
     */
    @Override
    public String toString() {
        return "location: (Lecture Theatre): " + name + "\nNearest building: " + nearestBuilding;
    }

    /**
     * Convert the information about this LT to a string that is to be stored in a file.
     *
     * @return the result string to be stored.
     */
    public String fileString() {
        return "L/" + name + "/" + nearestBuilding;
    }
}
