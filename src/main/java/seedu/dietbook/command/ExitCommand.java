package seedu.dietbook.command;

import seedu.dietbook.DietBook;
import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.person.FitnessLevel;
import seedu.dietbook.person.Gender;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.saveload.PersonSaveLoadManager;
import seedu.dietbook.saveload.FoodSaveLoadManager;

public class ExitCommand extends Command {
    PersonSaveLoadManager personSave;
    FoodSaveLoadManager foodSave;
    String input;

    public ExitCommand(String input) {
        this.personSave = new PersonSaveLoadManager();
        this.foodSave = new FoodSaveLoadManager();
        this.input = input;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        //ActivityLevel actLvl = manager.getPerson().getActivityLevel();
        //int actLvlInt = 1;
        //if (actLvl.equals(ActivityLevel.NONE)) {
        //    actLvlInt = 1;
        //} else if (actLvl.equals(ActivityLevel.LOW)) {
        //    actLvlInt = 2;
        //} else if (actLvl.equals(ActivityLevel.MEDIUM)) {
        //    actLvlInt = 3;
        //} else if (actLvl.equals(ActivityLevel.HIGH)) {
        //    actLvlInt = 4;
        //} else if (actLvl.equals(ActivityLevel.EXTREME)) {
        //    actLvlInt = 5;
        //}

        //Gender gender = manager.getPerson().getGender();
        //String genderString;
        //if (gender.equals(Gender.MALE)) {
        //    genderString = "Male";
        //} else if (gender.equals(Gender.FEMALE)) {
        //    genderString = "Female";
        //} else {
        //    genderString = "Others";
        //}

        //personSave.setActivityLevel(actLvlInt);
        //personSave.setAge(manager.getPerson().getAge());
        //personSave.setCurrentWeight(manager.getPerson().getCurrentWeight());
        //personSave.setGender(genderString);
        //personSave.setHeight(manager.getPerson().getHeight());
        //personSave.setName(manager.getPerson().getName());
        //personSave.setOriginalWeight(manager.getPerson().getOriginalWeight());
        //personSave.setTargetWeight(manager.getPerson().getTargetWeight());
        //personSave.save("UserInfo.txt");
        //foodSave.save("FoodList.txt", manager.getFoodList().getFoods());
        //ui.dataSuccessfullySavedMessage();
        InputChecker.checkSingleCommand(this.input);
        ui.printExitMessage();
        DietBook.isExit = true;
    }
}
