package seedu.rex.data;

import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;

import java.util.ArrayList;
import java.util.stream.Collectors;

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


    /**
     * Returns if ArrayList is empty.
     *
     * @return True if empty, false if not.
     */
    public boolean isEmpty() {
        return appointments.isEmpty();
    }

    /**
     * Returns size of ArrayList.
     *
     * @return Size of ArrayList.
     */
    public int getSize() {
        return appointments.size();
    }

    /**
     * Returns appointment of given index.
     *
     * @param index Index of appointment.
     * @return Appointment at that index.
     */
    public Appointment getAppointmentByIndex(int index) throws RexException {
        if (index >= getSize()) {
            throw new RexException("Invalid index!");
        }
        return appointments.get(index);
    }

    /**
     * Adds appointment to the ArrayList.
     *
     * @param appointment Appointment to add.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Returns ArrayList of available appointments.
     *
     * @return Appointments that are not booked.
     */
    public ArrayList<Appointment> getAvailableAppointments() {
        return (ArrayList<Appointment>) appointments.stream().filter((a) -> !a.isBooked()).collect(Collectors.toList());
    }
}
