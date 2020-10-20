package seedu.rex.data.hospital;

import java.time.format.DateTimeFormatter;

/**
 * Doctor class.
 */
public class Doctor {
    private String name;

    public Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
