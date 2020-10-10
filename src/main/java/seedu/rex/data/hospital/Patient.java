package seedu.rex.data.hospital;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 * Patient class.
 */
public class Patient {

    private String name;
    private String nric;
    private LocalDate dateOfBirth;
    private final Vector<Appointment> appointmentHistory;

    public Patient(String name, String nric, LocalDate dateOfBirth) {
        setName(name);
        setNric(nric);
        setDateOfBirth(dateOfBirth);
        this.appointmentHistory = new Vector<>();

        // will create an empty appointment history
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

    public Vector<Appointment> getAppointmentHistory() {
        return appointmentHistory;
    }

    private enum Gender {
        MALE, FEMALE
    }

    /**
     * Returns the <code>Patient</code> object as a String for printing or writing to a file.
     * @return Patient's details as a String in a particular format.
     */
    @Override
    public String toString() {
        return name + ", " + nric + ", " + dateOfBirth.format(DateTimeFormatter.ISO_DATE);
    }
}
