package fitr;

import fitr.ui.Ui;

import java.io.FileNotFoundException;

public class Recommender {
    protected StandardExerciseList upperBodyList;
    protected StandardExerciseList lowerBodyList;
    protected StandardExerciseList aerobicList;
    protected StandardExerciseList stretchingList;

    Recommender() {
        Boolean isSuccessful = false;
        StandardExerciseStorage storage = new StandardExerciseStorage();
        try {
            this.upperBodyList = storage.loadUpperBodyList();
            this.lowerBodyList = storage.loadLowerBodyList();
            this.aerobicList = storage.loadAerobicList();
            this.stretchingList = storage.loadStretchList();
            isSuccessful = true;
        } catch (FileNotFoundException e) {
            Ui.printCustomMessage("File(s) missing!");
            isSuccessful = false;
        }
        assert (isSuccessful);
    }

    public StandardExerciseList recommend() {
        StandardExerciseList finalList = new StandardExerciseList();
        int firstIndex = (int) Math.round(Math.random() * (upperBodyList.getSize() - 0 + 1) + 0);
        int secondIndex = (int) Math.round(Math.random() * (lowerBodyList.getSize() - 0 + 1) + 0);
        int thirdIndex = (int) Math.round(Math.random() * (aerobicList.getSize() - 0 + 1) + 0);
        int fourthIndex = (int) Math.round(Math.random() * (stretchingList.getSize() - 0 + 1) + 0);
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
