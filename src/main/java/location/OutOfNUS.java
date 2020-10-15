package location;

public class OutOfNUS extends Location {
    public OutOfNUS(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "(Out of NUS): " + name;
    }
}
