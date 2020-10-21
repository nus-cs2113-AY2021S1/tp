package fitr;

import java.util.ArrayList;

public class Recommender {
    protected StandardExerciseList upperBodyList;
    protected StandardExerciseList lowerBodyList;
    protected StandardExerciseList aerobicList;
    protected StandardExerciseList stretchingList;

    public Recommender(String nameOfFile){


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

}
