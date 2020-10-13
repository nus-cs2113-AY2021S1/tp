package seedu.duke;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodlist,ExerciseList exerciseList, Storage storage) {
        try {
            command = command.split(" ", 2)[1];
            String type = command.split(" ", 2)[0];
            if (type.equals("food")) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                System.out.println("The following has been deleted from the list of food consumed: "
                        + foodlist.getFood(deletionIndex - 1).getFoodName());
                foodlist.deleteFood(deletionIndex - 1);
            } else if (type.equals("exercise")) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                System.out.println("The following has been deleted from the list of food consumed: "
                        + exerciseList.getExercise(deletionIndex - 1).getNameOfExercise());
                exerciseList.deleteExercise(deletionIndex - 1);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry that index does not exist in the list");
        } catch (NumberFormatException e) {
            System.out.println("Sorry index deletion must be an number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
