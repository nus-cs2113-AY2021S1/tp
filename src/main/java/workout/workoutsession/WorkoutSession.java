package workout.workoutsession;

import storage.workout.Storage;
import workout.workoutsession.exercise.Exercise;
import workout.workoutsession.workoutsessionui.WorkoutSessionUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutSession {
    private String filePath = null;
    private boolean endWorkoutSession = false;
    private final ArrayList<Exercise> exercise;

    public WorkoutSession() {
        this.exercise = new ArrayList<>();
    }

    public WorkoutSession(String filePath) {
        this.filePath = filePath;
        this.exercise = new ArrayList<>();
    }

    private void setEndWorkoutSessionF() {

        this.endWorkoutSession = false;
    }

    private void setEndWorkoutSessionT() {

        this.endWorkoutSession = true;
    }

    /**
     * Starts workout session.
     */
    public void workoutSessionStart() {

        setEndWorkoutSessionF();
        exercise.clear();
        while (!endWorkoutSession) {
            try {
                workoutSessionProcessCommand();
            } catch (IOException e) {
                WorkoutSessionUI.printError();
            }
        }
    }

    private void workoutSessionProcessCommand() throws IOException {

        Scanner in = new Scanner(System.in);
        String[] input = WorkoutSessionParser.workoutSessionParser(in.nextLine().trim());

        switch (input[0].toLowerCase()) {

        case "add":
            exercise.add(WorkoutSessionParser.addParser(input));
            Storage.writeToStorage(filePath, exercise);
            break;
        case "list":
            Storage.readFileContents(filePath, exercise);
            printList();
            Storage.writeToStorage(filePath, exercise);
            break;
        case "delete":
            exercise.remove(WorkoutSessionParser.deleteParser(input));
            Storage.writeToStorage(filePath, exercise);
            break;
        case "bye":
            setEndWorkoutSessionT();
            Storage.writeToStorage(filePath, exercise);
            break;
        default:

        }
    }

    private void printList() {

        for (int i = 0; i < exercise.size(); i++) {
            System.out.println((i + 1) + ": " + exercise.get(i).toString());
        }
    }

}
