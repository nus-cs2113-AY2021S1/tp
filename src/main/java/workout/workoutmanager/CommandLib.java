package workout.workoutmanager;

import workout.workoutmanager.command.Command;
import workout.workoutmanager.command.ByeWS;
import workout.workoutmanager.command.DeleteWS;
import workout.workoutmanager.command.ListWS;
import workout.workoutmanager.command.NewWS;

import java.util.Hashtable;

public class CommandLib {

    public Hashtable<String, Command> library;

    public CommandLib() {
        library = new Hashtable<>();
    }

    public void initWorkoutManagerCL() {
        this.library.put("list", new ListWS());
        this.library.put("new", new NewWS());
        this.library.put("delete", new DeleteWS());
        this.library.put("bye", new ByeWS());
    }

    public Command get(String keyword) {
        return library.get(keyword);
    }
}
