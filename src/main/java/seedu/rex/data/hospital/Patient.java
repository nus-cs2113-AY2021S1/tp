package seedu.rex.data.hospital;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Patient class.
 */
public class Patient {

    private String name;
    private String nric;
    private LocalDate dateOfBirth;

    public Patient(String name, String nric, LocalDate dateOfBirth) {
        setName(name);
        setNric(nric);
        setDateOfBirth(dateOfBirth);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    /**
     * Returns the <code>Patient</code> object as a String for printing or writing to a file.
     *
     * @return Patient's details as a String in a particular format.
     */
    @Override
    public String toString() {
        return name + " | " + nric + " | " + dateOfBirth.format(DateTimeFormatter.ISO_DATE);
    }
}
