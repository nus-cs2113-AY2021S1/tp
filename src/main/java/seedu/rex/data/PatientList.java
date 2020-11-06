package seedu.rex.data;

import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Patient;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains ArrayList of patients.
 */
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
     *
     * @param name        Name of the patient.
     * @param nric        NRIC of the patient.
     * @param dateOfBirth Patient's date of birth.
     */
    public void addNewPatient(String name, String nric, LocalDate dateOfBirth) {
        addPatient(new Patient(name, nric, dateOfBirth));
    }

    /**
     * Edits an existing patient with given details to <code>patients</code> ArrayList.
     *
     * @param name        Name of the patient.
     * @param nric        NRIC of the patient.
     * @param dateOfBirth Patient's date of birth.
     */
    public int editExistingPatient(String name, String nric, LocalDate dateOfBirth) {
        int index = getExistingPatient(nric);
        patients.remove(index);
        Patient patient = (new Patient(name, nric, dateOfBirth));
        patients.add(index, patient);
        return index;
    }


    /**
     * Checks if the NRIC entered by the user already exists in the patient list.
     *
     * @param nric The NRIC entered by the user.
     * @return <code>true</code> if NRIC already exists; <code>false</code> otherwise.
     */
    public boolean isExistingPatient(String nric) {
        return getExistingPatient(nric) > -1;
    }

    /**
     * Finds NRIC entered by the user from the patient list.
     * Returns -1 if not found.
     *
     * @param nric The NRIC entered by the user.
     * @return index of the patient with the NRIC; -1 otherwise.
     */
    public int getExistingPatient(String nric) {
        assert nric != null && !nric.equals("") : "Cannot get patient of null nric";

        for (int i = 0; i < getSize(); i++) {
            if (getPatientUsingIndex(i).getNric().equals(nric)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets patient using NRIC.
     *
     * @param nric NRIC of patient.
     * @return patient with given NRIC.
     */
    public Patient getPatientFromNric(String nric) {
        for (Patient patient : patients) {
            if (patient.getNric().equals(nric)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Deletes Patient with the NRIC entered by the user.
     *
     * @param nric NRIC entered by the user
     * @return the deleted <code>Patient</code> Object
     */
    public Patient deletePatient(String nric) throws RexException {
        if (patients.size() == 0) {
            throw new RexException("No patients!");
        }
        int i;
        for (i = 0; i < getSize(); i++) {
            if (getPatientUsingIndex(i).getNric().equals(nric)) {
                return patients.remove(i);
            }
        }
        throw new RexException("Patient not found!");
    }

    /**
     * Returns list of patients.
     *
     * @return Patients list.
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }

}
