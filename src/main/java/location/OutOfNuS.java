package location;

public class OutOfNuS extends Location {
    public OutOfNuS(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "(Out of NUS): " + name;
    }
}
