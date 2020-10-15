package workout.workoutsession;

import workout.workoutsession.exercise.Exercise;

public class WorkoutSessionParser {

    public static String[] workoutSessionParser(String input) {

        return input.split(" ");
    }

    public static Exercise addParser(String[] input) {

        String[] returnString = new String[3];
        int tracker = 0;
        for (String s : input) {
            if (s.compareTo("/n") == 0) {
                tracker = 1;
            } else if (s.compareTo("/w") == 0) {
                tracker = 2;
            } else {
                returnString[tracker] += s;
            }
        }
        return new Exercise(returnString[0],Integer.parseInt(returnString[1]),Integer.parseInt(returnString[2]));
    }

    public static int deleteParser(String[] input) {

        return Integer.parseInt(input[1]);
    }
}
