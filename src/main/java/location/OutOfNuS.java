package location;

public class OutOfNuS extends Location {
    public OutOfNuS(String name) {
        super(name);
    }

    /**
     * Prepares string to be printed in a list.
     *
     * @return object to be printed in a certain format.
     */
    @Override
    public String toString() {
        return "(Out of NUS): " + name;
    }

    /**
     * Convert the information about this location to a string that is to be stored in a file.
     *
     * @return the result string to be stored.
     */
    public String fileString() {
        return "OUT/" + name;
    }
}
