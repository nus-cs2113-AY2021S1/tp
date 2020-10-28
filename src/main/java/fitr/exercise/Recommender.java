package fitr.exercise;

import fitr.list.StandardExerciseList;
import fitr.storage.StandardExerciseStorage;
import fitr.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Recommender {
    protected StandardExerciseList upperBodyList;
    protected StandardExerciseList lowerBodyList;
    protected StandardExerciseList aerobicList;
    protected StandardExerciseList stretchingList;

    public Recommender() {
        Boolean isSuccessful = false;
        StandardExerciseStorage storage = new StandardExerciseStorage();
        try {
            this.upperBodyList = storage.loadUpperBodyList();
            this.lowerBodyList = storage.loadLowerBodyList();
            this.aerobicList = storage.loadAerobicList();
            this.stretchingList = storage.loadStretchList();
            isSuccessful = true;
        } catch (IOException e) {
            Ui.printCustomMessage("File(s) missing!");
            isSuccessful = false;
        }
        assert (isSuccessful);
    }

    public StandardExerciseList recommend() {
        StandardExerciseList finalList = new StandardExerciseList();
        int firstIndex = (int) (Math.random() * (upperBodyList.getSize() - 1));
        int secondIndex = (int) (Math.random() * (lowerBodyList.getSize() - 1));
        int thirdIndex = (int) (Math.random() * (aerobicList.getSize() - 1));
        int fourthIndex = (int) (Math.random() * (stretchingList.getSize() - 1));
        finalList.addExercise(upperBodyList.getExercise(firstIndex));
        finalList.addExercise(lowerBodyList.getExercise(secondIndex));
        finalList.addExercise(aerobicList.getExercise(thirdIndex));
        finalList.addExercise(stretchingList.getExercise(fourthIndex));
        return finalList;
    }

    public void printExercise() {
        StandardExerciseList list = recommend();
        for (int i = 0; i < list.getSize(); i++) {
            Ui.printCustomMessage("Name of Exercise:");
            Ui.printCustomMessage(list.getExercise(i).getName());
        }
    }
}
