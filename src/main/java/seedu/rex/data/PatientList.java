package seedu.rex.data;

import seedu.rex.data.patient.Patient;

import java.util.ArrayList;

public class PatientList {

    private static final String PATIENT_NUMBER_ERROR = "Invalid patient number.";

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
}
