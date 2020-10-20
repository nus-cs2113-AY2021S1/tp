package seedu.rex.data;

import seedu.rex.data.hospital.Appointment;

import java.util.ArrayList;

/**
 * Contains ArrayList of appointments.
 */
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

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }


    public boolean isEmpty() {
        return appointments.isEmpty();
    }

    public int getSize() {
        return appointments.size();
    }

    public Appointment getAppointmentByIndex(int index) {
        return appointments.get(index);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointmentByIndex(int i) {
        appointments.remove(i);
    }
}
