package seedu.rex.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Doctor;
import seedu.rex.data.hospital.Patient;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeletePatientCommandTest {
    PatientList patients;
    DoctorList doctors;
    AppointmentList appointments;
    Ui ui;
    Storage storage;
    Patient patient;
    Doctor doctor;
    Appointment appointment;

    public void init() {
        storage = new Storage("data");
        try {
            patients = new PatientList(storage.loadPatients());
        } catch (Exception e) {

        }

        doctors = new DoctorList();
        appointments = new AppointmentList();
        ui = new Ui();


        //patient = new Patient("kiathwe", "s9123456z", LocalDate.parse("1997-12-12"));
        doctor = new Doctor("bob");
        appointment = new Appointment(LocalDate.parse("2020-12-12"));

        patients.addNewPatient("kiathwe", "s9123459z", LocalDate.parse("1997-12-12"));
        doctors.addDoctor(doctor);
        appointments.addAppointment(appointment);
        appointment.setDoctor(doctor);
        appointment.setPatient(patients.getPatientFromNric("s9123459z"));
        appointment.setBooked(true);


    }

    
    @Test
    public void execute() {
        init();
        System.out.println(patients.getSize());
        System.out.println(appointments.getAppointmentByIndex(0).toString());

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand("delete s9123459z");
        try {
            deletePatientCommand.execute(patients, doctors, appointments, ui, storage);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
        System.out.println(patients.getSize());
        assertEquals(0, patients.getSize());
        assertEquals(false, appointment.isBooked());




    }
}