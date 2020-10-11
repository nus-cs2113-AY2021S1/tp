package seedu.rex.data.hospital;

import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;

public class Hospital {
    private String name;
    private PatientList patients;
    private DoctorList doctors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PatientList getPatients() {
        return patients;
    }

    public void setPatients(PatientList patients) {
        this.patients = patients;
    }

    public DoctorList getDoctors() {
        return doctors;
    }

    public void setDoctors(DoctorList doctors) {
        this.doctors = doctors;
    }

}
