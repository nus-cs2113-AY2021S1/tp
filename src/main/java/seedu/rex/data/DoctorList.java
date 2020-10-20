package seedu.rex.data;

import seedu.rex.data.hospital.Doctor;
import seedu.rex.data.hospital.Patient;

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

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public boolean isExistingDoctor(String name) {
        return getExistingDoctor(name) > -1;
    }

    public int getExistingDoctor(String name) {
        assert name != null && !name.equals("") : "Cannot get doctor of null name";

        for (int i = 0; i < getSize(); i++) {
            if (getDoctorUsingIndex(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Doctor getDoctorFromName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor deleteDoctor(String doctorName) {
        int i;
        for (i = 0; i < getSize(); i++) {
            if (getDoctorUsingIndex(i).getName().equals(doctorName)) {
                break;
            }
        }
        return doctors.remove(i);
    }
}
