package fitr.exercise;

import fitr.exception.FitrException;
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

    public StandardExerciseList recommend(int recommendationType) {
        assert recommendationType >= 0 && recommendationType < 5;
        StandardExerciseList finalList = new StandardExerciseList();
        int firstIndex;
        int secondIndex;
        int thirdIndex;
        int fourthIndex;
        switch (recommendationType) {
        case 0:
            firstIndex = (int) (Math.random() * (upperBodyList.getSize() - 1));
            secondIndex = (int) (Math.random() * (lowerBodyList.getSize() - 1));
            thirdIndex = (int) (Math.random() * (aerobicList.getSize() - 1));
            fourthIndex = (int) (Math.random() * (stretchingList.getSize() - 1));
            finalList.addExercise(upperBodyList.getExercise(firstIndex));
            finalList.addExercise(lowerBodyList.getExercise(secondIndex));
            finalList.addExercise(aerobicList.getExercise(thirdIndex));
            finalList.addExercise(stretchingList.getExercise(fourthIndex));
            break;
        case 1:

            firstIndex = (int) (Math.random() * (aerobicList.getSize() - 1));
            secondIndex = (firstIndex + 1) % aerobicList.getSize();
            thirdIndex = (secondIndex + 2) % aerobicList.getSize();
            fourthIndex = (secondIndex + 3) % aerobicList.getSize();
            assert firstIndex < aerobicList.getSize() && secondIndex < aerobicList.getSize()
                    && thirdIndex < aerobicList.getSize()
                    && fourthIndex < aerobicList.getSize();
            finalList.addExercise(aerobicList.getExercise(firstIndex));
            finalList.addExercise(aerobicList.getExercise(secondIndex));
            finalList.addExercise(aerobicList.getExercise(thirdIndex));
            finalList.addExercise(aerobicList.getExercise(fourthIndex));
            break;
        case 2:
            firstIndex = (int) (Math.random() * (upperBodyList.getSize() - 1));
            secondIndex = (firstIndex + 1) % upperBodyList.getSize();
            thirdIndex = (secondIndex + 2) % upperBodyList.getSize();
            fourthIndex = (secondIndex + 3) % upperBodyList.getSize();
            assert firstIndex < upperBodyList.getSize() && secondIndex < upperBodyList.getSize()
                    && thirdIndex < upperBodyList.getSize()
                    && fourthIndex < upperBodyList.getSize();
            finalList.addExercise(upperBodyList.getExercise(firstIndex));
            finalList.addExercise(upperBodyList.getExercise(secondIndex));
            finalList.addExercise(upperBodyList.getExercise(thirdIndex));
            finalList.addExercise(upperBodyList.getExercise(fourthIndex));
            break;
        case 3:
            firstIndex = (int) (Math.random() * (lowerBodyList.getSize() - 1));
            secondIndex = (firstIndex + 1) % lowerBodyList.getSize();
            thirdIndex = (secondIndex + 2) % lowerBodyList.getSize();
            fourthIndex = (secondIndex + 3) % lowerBodyList.getSize();
            assert firstIndex < lowerBodyList.getSize() && secondIndex < lowerBodyList.getSize()
                    && thirdIndex < lowerBodyList.getSize()
                    && fourthIndex < lowerBodyList.getSize();
            finalList.addExercise(lowerBodyList.getExercise(firstIndex));
            finalList.addExercise(lowerBodyList.getExercise(secondIndex));
            finalList.addExercise(lowerBodyList.getExercise(thirdIndex));
            finalList.addExercise(lowerBodyList.getExercise(fourthIndex));
            break;
        default:
            firstIndex = (int) (Math.random() * (stretchingList.getSize() - 1));
            secondIndex = (firstIndex + 1) % stretchingList.getSize();
            thirdIndex = (secondIndex + 2) % stretchingList.getSize();
            fourthIndex = (secondIndex + 3) % stretchingList.getSize();
            assert firstIndex < stretchingList.getSize() && secondIndex < stretchingList.getSize()
                    && thirdIndex < stretchingList.getSize()
                    && fourthIndex < stretchingList.getSize();
            finalList.addExercise(stretchingList.getExercise(firstIndex));
            finalList.addExercise(stretchingList.getExercise(secondIndex));
            finalList.addExercise(stretchingList.getExercise(thirdIndex));
            finalList.addExercise(stretchingList.getExercise(fourthIndex));
            break;
        }
        return finalList;
    }

    public int recommendParser(String command) {
        int recommendationType = 0;
        if (command.length() == 0) {
            return 0;
        } else {
            switch (command) {
            case "aerobic":
                recommendationType = 1;
                break;
            case "upperbody":
                recommendationType = 2;
                break;
            case "lowerbody":
                recommendationType = 3;
                break;
            case "stretch":
                recommendationType = 4;
                break;
            default:
                recommendationType = 5;
            }
        }
        return recommendationType;

    }

}
