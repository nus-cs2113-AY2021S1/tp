package seedu.rex.data;

import org.junit.jupiter.api.Test;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppointmentListTest {

    @Test
    void isEmpty_empty_true() {
        AppointmentList appointmentList = new AppointmentList();
        assertTrue(appointmentList.isEmpty());
    }

    @Test
    void isEmpty_1Appointment_false() {
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.addAppointment(new Appointment(LocalDate.now()));
        assertFalse(appointmentList.isEmpty());
    }

    @Test
    void getSize_1Appointment_1() {
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.addAppointment(new Appointment(LocalDate.now()));
        assertEquals(appointmentList.getSize(), 1);
    }

    @Test
    void getAppointmentByIndex_sampleAppointment_sampleAppointment() throws RexException {
        AppointmentList appointmentList = new AppointmentList();
        Appointment appointment = new Appointment(LocalDate.now());
        appointmentList.addAppointment(appointment);
        assertEquals(appointmentList.getAppointmentByIndex(0), appointment);
    }

    @Test
    void getAppointmentByIndex_invalidIndex_expectException() {
        AppointmentList appointmentList = new AppointmentList();
        Appointment appointment = new Appointment(LocalDate.now());
        appointmentList.addAppointment(appointment);
        assertThrows(RexException.class, () -> appointmentList.getAppointmentByIndex(1));
    }
}