package fitr.command;

import fitr.calorie.Calorie;
import fitr.exception.DuplicateIndexException;
import fitr.exception.FitrException;
import fitr.exception.InvalidRecommendationException;
import fitr.exercise.Recommender;
import fitr.exercise.StandardExercise;
import fitr.list.StandardExerciseList;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;
import fitr.exercise.Exercise;

import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

import static fitr.common.DateManager.getCurrentDate;
import static fitr.common.Messages.BURNT_CAL_HEADER;
import static fitr.common.Messages.CLOSE_SQUARE_BRACKET;
import static fitr.common.Messages.ECHO_ADDED_EXERCISE;
import static fitr.common.Messages.ERROR_IN_FILE;
import static fitr.common.Messages.EXERCISE_HEADER;
import static fitr.common.Messages.INTENSITY_CAL_HEADER;
import static fitr.common.Messages.OPEN_SQUARE_BRACKET;
import static fitr.common.Messages.SPACE_FORMATTING;

public class RecommendCommand extends Command {
    public RecommendCommand(String command) {
        this.command = command.trim();
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        int recommendationType = recommender.recommendParser(command);
        try {
            if (recommendationType == 5) {
                throw new InvalidRecommendationException();
            }
            StandardExerciseList recommendList = recommender.recommend(recommendationType);
            int fitnessLevel = user.getFitnessLevel();
            assert fitnessLevel >= 0 && fitnessLevel <= 2;
            for (int i = 0; i < 4; i++) {
                StandardExercise standardExercise = recommendList.getExercise(i);
                int calorieBurnt = (int) (standardExercise.getDuration().get(fitnessLevel)
                        * standardExercise.getMet()
                        * standardExercise.getSets().get(fitnessLevel)
                        * user.getWeight())
                        / 60;
                Ui.printCustomMessage(OPEN_SQUARE_BRACKET + (i + 1) + CLOSE_SQUARE_BRACKET
                        + EXERCISE_HEADER + recommendList.getExercise(i).getName()
                        + SPACE_FORMATTING + INTENSITY_CAL_HEADER
                        + recommendList.getExercise(i).getSets().get(fitnessLevel) + " sets of "
                        + recommendList.getExercise(i).getDuration().get(fitnessLevel) + " minutes"
                        + SPACE_FORMATTING + BURNT_CAL_HEADER + calorieBurnt);
            }

            Ui.printCustomMessage("Will you be doing this workout?" + System.lineSeparator()
                    + "Type 'y' to add all 4 to your exercise list," + System.lineSeparator()
                    + "or you can type in the index of the exercises you want added to you exercise list "
                    + "(separated by a space)." + System.lineSeparator() + "Any other input will be taken as a no.");

            String checker = Ui.read();

            if (checker.toLowerCase().equals("y")) {
                Ui.printCustomMessage("-".repeat(136));
                Ui.printCustomMessage(ECHO_ADDED_EXERCISE);
                for (int i = 0; i < 4; i++) {
                    StandardExercise standardExercise = recommendList.getExercise(i);
                    Calorie caloriesBurnt = new Calorie((int) (standardExercise.getDuration().get(fitnessLevel)
                            * standardExercise.getMet()
                            * standardExercise.getSets().get(fitnessLevel)
                            * user.getWeight())
                            / 60);
                    Ui.printCustomMessage(OPEN_SQUARE_BRACKET + (i + 1) + CLOSE_SQUARE_BRACKET
                            + EXERCISE_HEADER + standardExercise.getName()
                            + SPACE_FORMATTING + BURNT_CAL_HEADER
                            + caloriesBurnt.get());
                    listManager.addExercise(new Exercise(standardExercise.getName(), caloriesBurnt, getCurrentDate()));
                    storageManager.writeExerciseList(listManager.getExerciseList());
                }
            } else if (checker.chars().anyMatch(Character::isLetter) || (checker.trim().length() == 0)) {
                Ui.printCustomMessage("Next time then!");
            } else {
                try {
                    if (checker.split(" ").length > 4) {
                        throw new FitrException();
                    }
                    ArrayList<Integer> indexArr = new ArrayList<>();
                    for (int i = 0; i < checker.split(" ").length; i++) {
                        if (Integer.parseInt(checker.split(" ")[i]) < 0
                                || Integer.parseInt(checker.split(" ")[i]) > 4) {
                            throw new IndexOutOfBoundsException();
                        }
                        if (indexArr.contains(Integer.parseInt(checker.split(" ")[i]))) {
                            throw new DuplicateIndexException();
                        }
                        indexArr.add(Integer.parseInt(checker.split(" ")[i]));
                    }
                    Ui.printCustomMessage("-".repeat(136));
                    Ui.printCustomMessage(ECHO_ADDED_EXERCISE);
                    for (int i = 0; i < indexArr.size(); i++) {
                        StandardExercise standardExercise = recommendList.getExercise(indexArr.get(i) - 1);
                        Calorie caloriesBurnt = new Calorie((int) (standardExercise.getDuration()
                                .get(fitnessLevel)
                                * standardExercise.getMet()
                                * standardExercise.getSets().get(fitnessLevel)
                                * user.getWeight())
                                / 60);
                        Ui.printCustomMessage(OPEN_SQUARE_BRACKET + (i + 1) + CLOSE_SQUARE_BRACKET
                                + EXERCISE_HEADER + standardExercise.getName()
                                + SPACE_FORMATTING + BURNT_CAL_HEADER
                                + caloriesBurnt.get());
                        listManager.addExercise(new Exercise(standardExercise.getName(), caloriesBurnt,
                                getCurrentDate()));
                        storageManager.writeExerciseList(listManager.getExerciseList());
                    }
                } catch (FitrException e) {
                    Ui.printCustomError("You have typed in too many indexes.");
                } catch (NumberFormatException e) {
                    Ui.printCustomError("The indexes have to be a number!");
                } catch (IndexOutOfBoundsException e) {
                    Ui.printCustomError("Sorry, you have to key in a positive number below 4 inclusive.");
                } catch (DuplicateIndexException e) {
                    Ui.printCustomError("You cannot add the same index multiple times!");
                }
            }
        } catch (IOException e) {
            Ui.printCustomError(ERROR_IN_FILE);
        } catch (InvalidRecommendationException e) {
            Ui.printCustomError("Sorry, that is an invalid recommendation type.");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
