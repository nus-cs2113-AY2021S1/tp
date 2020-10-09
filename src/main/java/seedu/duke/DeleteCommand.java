package seedu.duke;

public class DeleteCommand extends Command {
    DeleteCommand(String command){
        this.command = command;
    }
    @Override
    public void execute(FoodList foodlist,ExerciseList exerciseList, Storage storage) {
        try {
            command = command.split(" ", 2)[1];
            String type = command.split(" ", 2)[0];
            if (type.equals("food")) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                foodlist.deleteFood(deletionIndex - 1);
            } else if (type.equals("exercise")) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                exerciseList.deleteExercise(deletionIndex - 1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry that index does not exist in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please key in the delete function in the correct format");
        } catch (NumberFormatException e) {
            System.out.println("Sorry index deletion must be an number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
