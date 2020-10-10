package seedu.rex.data;

import seedu.rex.data.hospital.Patient;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientList {

    private final ArrayList<Patient> patients;

    /**
     * Initializes patients ArrayList.
     */
    public PatientList() {
        patients = new ArrayList<>();
    }

    /**
     * Initializes patients ArrayList using parameter.
     *
     * @param patients Patients list to use.
     */
    public PatientList(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    /**
     * Returns size of patients.
     *
     * @return Length of patients.
     */
    public int getSize() {
        return patients.size();
    }

    /**
     * Returns patient using index.
     *
     * @param index Index of patient.
     * @return patient at that index.
     */
    public Patient getPatientUsingIndex(int index) {
        return patients.get(index);
    }

    /**
     * Adds patient to patients ArrayList.
     *
     * @param patient patient to add.
     */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Adds a new patient with given details to <code>patients</code> ArrayList.
     * @param name Name of the patient.
     * @param nric NRIC of the patient.
     * @param dateOfBirth Patient's date of birth.
     */
    public void addNewPatient(String name, String nric, LocalDate dateOfBirth) {
        addPatient(new Patient(name, nric, dateOfBirth));
    }
}
