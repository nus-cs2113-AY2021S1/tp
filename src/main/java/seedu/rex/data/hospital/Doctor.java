package seedu.rex.data.hospital;

/**
 * Doctor class.
 */
public class Doctor {
    private final String name;

    public Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns doctor's name.
     *
     * @return Doctor's name in String
     */
    @Override
    public String toString() {
        return name;
    }
}
