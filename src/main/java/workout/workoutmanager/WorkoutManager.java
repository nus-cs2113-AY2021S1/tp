package workout.workoutmanager;

import storage.workout.WorkOutManagerStorage;
import workout.workoutmanager.command.Command;

import java.util.Arrays;
import java.util.Scanner;

public class WorkoutManager {

    private final CommandLib cl;

    public WorkoutManager() {
        WorkOutManagerStorage.init();
        cl = new CommandLib();
        cl.initWorkoutManagerCL();
    }

    public void start() {
        WorkoutManagerUI.printOpening();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            WorkoutManagerUI.printSeperationLine();
            String[] commParts = WorkoutManagerParser.parse(command);

            try {
                processCommand(commParts);
            } catch (ExitException e) {
                break;
            }
            WorkoutManagerUI.printSeperationLine();
        }
    }

    public void processCommand(String[] commands) throws ExitException {
        try {
            Command command = cl.get(commands[0]);
            command.execute(Arrays.copyOfRange(commands, 1, commands.length));
        } catch (NullPointerException e) {
            WorkoutManagerUI.commandNotFoundResponse();
        }
    }
}
