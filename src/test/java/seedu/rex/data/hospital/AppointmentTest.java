package seedu.rex.data.hospital;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppointmentTest {

    @Test
    void getDate_todayDate_todayDate() {
        Appointment appointment = new Appointment(LocalDate.now());
        assertEquals(LocalDate.now(), appointment.getDate());
    }

    @Test
    void getPatient_samplePatient_samplePatient() {
        Appointment appointment = new Appointment(LocalDate.now());
        Patient patient = new Patient(null, null, null);
        appointment.setPatient(patient);
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    void setPatient_samplePatient_samplePatient() {
        Appointment appointment = new Appointment(LocalDate.now());
        Patient patient = new Patient(null, null, null);
        appointment.setPatient(patient);
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    void getDoctor_sampleDoctor_sampleDoctor() {
        Appointment appointment = new Appointment(LocalDate.now());
        Doctor doctor = new Doctor("hi");
        appointment.setDoctor(doctor);
        assertEquals(doctor, appointment.getDoctor());
    }

    @Test
    void setDoctor_sampleDoctor_sampleDoctor() {
        Appointment appointment = new Appointment(LocalDate.now());
        Doctor doctor = new Doctor("hi");
        appointment.setDoctor(doctor);
        assertEquals(doctor, appointment.getDoctor());
    }

    @Test
    void isBooked_booked_true() {
        Appointment appointment = new Appointment(LocalDate.now());
        appointment.bookPatient(new Patient(null, null, null));
        assertTrue(appointment.isBooked());
    }

    @Test
    void isBooked_notBooked_false() {
        Appointment appointment = new Appointment(LocalDate.now());
        assertFalse(appointment.isBooked());
    }

    @Test
    void bookPatient_samplePatient_isBookedTrueAndSamplePatient() {
        Appointment appointment = new Appointment(LocalDate.now());
        Patient patient = new Patient(null, null, null);
        appointment.bookPatient(patient);
        assertEquals(patient, appointment.getPatient());
        assertTrue(appointment.isBooked());
    }

    @Test
    void removeBooking_removed_nullPatientAndIsBookedFalse() {
        Appointment appointment = new Appointment(LocalDate.now());
        Patient patient = new Patient(null, null, null);
        appointment.bookPatient(patient);
        appointment.removeBooking();
        assertFalse(appointment.isBooked());
        assertNull(appointment.getPatient());
        assertNull(appointment.getDoctor());
    }

    @Test
    void testToString_bookedAppointment_correctString() {
        Appointment appointment = new Appointment(LocalDate.now());
        Patient patient = new Patient(null, null, null);
        appointment.bookPatient(patient);
        assertEquals(LocalDate.now() + " | booked | null | null", appointment.toString());
    }

    @Test
    void testToString_notBookedAppointment_correctString() {
        Appointment appointment = new Appointment(LocalDate.now());
        assertEquals(LocalDate.now() + " | available", appointment.toString());
    }

    @Test
    void bookDoctor_sampleDoctor_sampleDoctor() {
        Appointment appointment = new Appointment(LocalDate.now());
        Doctor doctor = new Doctor("test");
        appointment.bookDoctor(doctor);
        assertEquals(doctor, appointment.getDoctor());
    }
}