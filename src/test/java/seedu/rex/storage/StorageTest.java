package seedu.rex.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Doctor;
import seedu.rex.data.hospital.Patient;

import java.util.ArrayList;

public class StorageTest {

    @Test
    public void loadAppointments_newFilePath_success() throws RexException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        assertEquals(appointments, new Storage("test_data").loadAppointments());
    }

    @Test
    public void loadPatients_newFilePath_success() throws RexException {
        ArrayList<Patient> patients = new ArrayList<>();
        assertEquals(patients, new Storage("test_data").loadPatients());
    }

    @Test
    public void loadDoctors_newFilePath_success() throws RexException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        assertEquals(doctors, new Storage("test_data").loadDoctors());
    }

}
