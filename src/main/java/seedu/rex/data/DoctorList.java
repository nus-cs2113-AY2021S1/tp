package seedu.rex.data;

import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Doctor;

import java.util.ArrayList;

/**
 * Contains ArrayList of doctors.
 */
public class DoctorList {

    private final ArrayList<Doctor> doctors;

    /**
     * Initializes doctors ArrayList.
     */
    public DoctorList() {
        doctors = new ArrayList<>();
    }

    /**
     * Initializes doctors ArrayList using parameter.
     *
     * @param doctors Doctors list to use.
     */
    public DoctorList(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * Returns size of doctors.
     *
     * @return Length of doctors.
     */
    public int getSize() {
        return doctors.size();
    }

    /**
     * Returns doctor using index.
     *
     * @param index Index of doctor.
     * @return doctor at that index.
     */
    public Doctor getDoctorUsingIndex(int index) {
        return doctors.get(index);
    }

    /**
     * Adds doctor to doctors ArrayList.
     *
     * @param doctor doctor to add.
     */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Returns list of doctors.
     *
     * @return List of doctors.
     */
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * Checks if doctor exists.
     *
     * @param name Name of doctor.
     * @return True if doctor already exist, false otherwise.
     */
    public boolean isExistingDoctor(String name) {
        return getExistingDoctor(name) > -1;
    }

    /**
     * Gets index of existing doctor.
     *
     * @param name Name of doctor.
     * @return Index of doctor, -1 if doctor doesn't exist.
     */
    public int getExistingDoctor(String name) {
        assert name != null && !name.equals("") : "Cannot get doctor of null name";

        for (int i = 0; i < getSize(); i++) {
            if (getDoctorUsingIndex(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets doctor using name.
     *
     * @param name Name of doctor.
     * @return Doctor with that naem.
     */
    public Doctor getDoctorFromName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    /**
     * Deletes doctor with given name.
     *
     * @param doctorName Name of doctor.
     * @return Removed doctor.
     */
    public Doctor deleteDoctor(String doctorName) throws RexException {
        if (doctors.size() == 0) {
            throw new RexException("No doctors!");
        }
        int i;
        for (i = 0; i < getSize(); i++) {
            if (getDoctorUsingIndex(i).getName().equals(doctorName)) {
                return doctors.remove(i);
            }
        }
        throw new RexException("Doctor not found!");
    }
}
