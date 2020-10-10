package seedu.duke.data;

import java.time.LocalDate;
import java.util.ArrayList;

public class SprintList {
    private ArrayList<Sprint> sprintList;
    private int currentSprint;

    public SprintList() {
        sprintList = new ArrayList<>();
        currentSprint = -1;
    }

    public int size() {
        return sprintList.size();
    }

    public Sprint getSprint(int index) {
        return sprintList.get(index);
    }

    public void addSprint(String goal, LocalDate start, LocalDate end) {
        sprintList.add(new Sprint(goal, start, end));
    }

    public int getCurrentSprint() {
        return currentSprint;
    }

    public void setCurrentSprint(int currentSprint) {
        this.currentSprint = currentSprint;
    }
}
