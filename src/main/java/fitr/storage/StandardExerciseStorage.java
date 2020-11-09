package fitr.storage;

import fitr.exercise.StandardExercise;
import fitr.common.ResourceManager;
import fitr.list.StandardExerciseList;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class StandardExerciseStorage {
    private static final String DEFAULT_UPPER_BODY_FILEPATH = "upperBodyList.txt";
    private static final String DEFAULT_LOWER_BODY_FILEPATH = "lowerBodyList.txt";
    private static final String DEFAULT_AEROBIC_FILEPATH = "aerobicList.txt";
    private static final String DEFAULT_STRETCH_FILEPATH = "stretchList.txt";
    private static final String COMMA_SEPARATOR = ", ";
    private static final Logger LOGGER = Logger.getLogger("StandardExerciseStorage");

    public StandardExerciseStorage() {
    }

    public StandardExerciseList loadUpperBodyList() throws IOException {
        StandardExerciseList upperBodyList = new StandardExerciseList();
        upperBodyList = readToList(DEFAULT_UPPER_BODY_FILEPATH, upperBodyList);
        LOGGER.fine("UpperBodyList loaded.");
        return upperBodyList;
    }

    public StandardExerciseList loadLowerBodyList() throws IOException {
        StandardExerciseList lowerBodyList = new StandardExerciseList();
        lowerBodyList = readToList(DEFAULT_LOWER_BODY_FILEPATH, lowerBodyList);
        LOGGER.fine("LowerBodyList loaded.");
        return lowerBodyList;
    }

    public StandardExerciseList loadAerobicList() throws IOException {
        StandardExerciseList aerobicList = new StandardExerciseList();
        aerobicList = readToList(DEFAULT_AEROBIC_FILEPATH, aerobicList);
        LOGGER.fine("AerobicList loaded.");
        return aerobicList;
    }

    public StandardExerciseList loadStretchList() throws IOException {
        StandardExerciseList stretchList = new StandardExerciseList();
        stretchList = readToList(DEFAULT_STRETCH_FILEPATH, stretchList);
        LOGGER.fine("StretchList loaded.");
        return stretchList;
    }

    private StandardExerciseList readToList(String filePath, StandardExerciseList exerciseList) throws IOException {
        BufferedReader br = new BufferedReader(ResourceManager.loadResource(filePath));
        String line;
        String[] arguments;
        String name;
        double metValue;
        ArrayList<Double> duration;
        ArrayList<Integer> sets;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            arguments = line.split(COMMA_SEPARATOR);
            name = arguments[0];
            metValue = Double.parseDouble(arguments[1]);
            duration = new ArrayList<Double>();
            duration.add(Double.parseDouble(arguments[2]));
            duration.add(Double.parseDouble(arguments[3]));
            duration.add(Double.parseDouble(arguments[4]));
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
