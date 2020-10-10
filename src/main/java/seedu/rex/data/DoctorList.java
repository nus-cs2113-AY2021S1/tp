package seedu.rex.data;

import seedu.rex.data.hospital.Doctor;

import java.util.ArrayList;

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
}
