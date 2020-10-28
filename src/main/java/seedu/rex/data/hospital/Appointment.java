package seedu.rex.data.hospital;

import java.time.LocalDate;

/**
 * Appointment class.
 */
public class Appointment {
    private final LocalDate date;
    private Patient patient;
    private Doctor doctor;
    private Boolean isBooked;

    public Appointment(LocalDate date) {
        this.date = date;
        isBooked = false;
    }

    public LocalDate getDate() {
        return date;
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

    /**
     * Removes booking.
     */
    public void removeBooking() {
        this.patient = null;
        this.doctor = null;
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

        if (bookedStatus.contentEquals("booked")) {
            return date + " | " + bookedStatus + " | " + patientNric + " | " + doctorName;
        } else {
            return date + " | " + bookedStatus;
        }
    }

    /**
     * Books doctor to appointment.
     *
     * @param doctor Doctor to book.
     */
    public void bookDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
