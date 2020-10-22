package fitr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StandardExerciseStorage {
    private static final String DEFAULT_UPPER_BODY_FILEPATH = "src/main/resources/upperBodyList.txt";
    private static final String DEFAULT_LOWER_BODY_FILEPATH = "src/main/resources/lowerBodyList.txt";
    private static final String DEFAULT_AEROBIC_FILEPATH = "src/main/resources/aerobicList.txt";
    private static final String DEFAULT_STRETCH_FILEPATH = "src/main/resources/stretchList.txt";
    private static final String COMMA_SEPARATOR = ", ";

    public StandardExerciseStorage() {
    }

    public StandardExerciseList loadUpperBodyList() throws FileNotFoundException {
        StandardExerciseList upperBodyList = new StandardExerciseList();
        upperBodyList = readToList(DEFAULT_UPPER_BODY_FILEPATH, upperBodyList);
        return upperBodyList;
    }

    public StandardExerciseList loadLowerBodyList() throws FileNotFoundException {
        StandardExerciseList lowerBodyList = new StandardExerciseList();
        lowerBodyList = readToList(DEFAULT_LOWER_BODY_FILEPATH, lowerBodyList);
        return lowerBodyList;
    }

    public StandardExerciseList loadAerobicList() throws FileNotFoundException {
        StandardExerciseList aerobicList = new StandardExerciseList();
        aerobicList = readToList(DEFAULT_AEROBIC_FILEPATH, aerobicList);
        return aerobicList;
    }

    public StandardExerciseList loadStretchList() throws FileNotFoundException {
        StandardExerciseList stretchList = new StandardExerciseList();
        stretchList = readToList(DEFAULT_STRETCH_FILEPATH, stretchList);
        return stretchList;
    }

    private StandardExerciseList readToList(String filePath, StandardExerciseList exerciseList)
            throws FileNotFoundException {
        File exerciseFile = new File(filePath);
        Scanner readExerciseFile = new Scanner(exerciseFile);
        String line;
        String[] arguments;
        String name;
        double metValue;
        ArrayList<Integer> duration;
        ArrayList<Integer> sets;
        while (readExerciseFile.hasNext()) {
            line = readExerciseFile.nextLine().trim();
            arguments = line.split(COMMA_SEPARATOR);
            name = arguments[0];
            metValue = Double.parseDouble(arguments[1]);
            duration = new ArrayList<Integer>();
            duration.add(Integer.parseInt(arguments[2]));
            duration.add(Integer.parseInt(arguments[3]));
            duration.add(Integer.parseInt(arguments[4]));
            sets = new ArrayList<Integer>();
            sets.add(Integer.parseInt(arguments[5]));
            sets.add(Integer.parseInt(arguments[6]));
            sets.add(Integer.parseInt(arguments[7]));

            StandardExercise exercise = new StandardExercise(name, metValue, duration, sets);
            exerciseList.addExercise(exercise);
        }
        return exerciseList;
    }
}
