package seedu.rex.data.hospital;

import java.time.LocalDate;

/**
 * Appointment class.
 */
public class Appointment {

    private LocalDate date;
    private String time;
    private Patient patient;
    private Doctor doctor;
    private String notes;
    private Boolean isBooked;

    public Appointment(LocalDate date) {
        this.date = date;
        isBooked = false;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Checks if appointment is booked.
     *
     * @return If appointment is booked.
     */
    public Boolean isBooked() {
        return isBooked;
    }

    /**
     * Books an appointment for the patient.
     *
     * @param patient Patient to schedule appointment with.
     */
    public void bookPatient(Patient patient) {
        this.patient = patient;
        isBooked = true;
    }

    public void removeBooking() {
        this.patient = null;
        isBooked = false;
    }

    /**
     * Formats appointment to a string.
     *
     * @return String formatted.
     */
    @Override
    public String toString() {
        String date = this.date.toString();

        String bookedStatus = isBooked ? "booked" : "available";
        String patientNric = patient == null ? null : patient.getNric();
        String doctorName = doctor == null ? null : doctor.getName();

        return date + ", " + bookedStatus + ", " + patientNric + ", " + doctorName;
    }

    public void bookDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
