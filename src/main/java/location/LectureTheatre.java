package location;

public class LectureTheatre extends Location{
    private String nearestBuilding;

    public LectureTheatre(String name, String nearestBuilding) {
        super(name);
        this.type = locationType.LT;
        this.nearestBuilding = nearestBuilding;
    }

    @Override
    public String toString() {
        return "(Lecture Theatre): " + name + "\nNearest building: " + nearestBuilding;
    }
}
