package seedu.rex.data.hospital;

import java.util.Date;
import java.util.Vector;

/**
 * Patient class.
 */
public class Patient {

    private String name;
    private String nric;
    private Date dateOfBirth;
    private final Vector<Appointment> appointmentHistory;

    Patient(String name, String nric, Date dateOfBirth) {
        setName(name);
        setNric(nric);
        setDateOfBirth(dateOfBirth);
        this.appointmentHistory = new Vector<>();

        // will create an empty appt history
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
}
