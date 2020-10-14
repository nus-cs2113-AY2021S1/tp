package seedu.rex.data;

import seedu.rex.data.hospital.Appointment;

import java.util.ArrayList;

public class AppointmentList {

    private final ArrayList<Appointment> appointments;

    public AppointmentList() {
        appointments = new ArrayList<>();
    }

    /**
     * Initializes patients ArrayList using parameter.
     *
     * @param appointments Appointments list to use.
     */
    public AppointmentList(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }


}
