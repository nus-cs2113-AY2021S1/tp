package seedu.duke.command;

import seedu.duke.exception.DukeException;

import java.util.Hashtable;

public class ProjectCommand {
    public void createProjectCommand(String title, String desc, String end, String sd,
                                     Hashtable<String, String> parameters) throws DukeException {

        /*
        Example of how to use the hashtable and how to throw the exception.
         */
        if (parameters.get(title) != null) {
            System.out.println(parameters.get(title));
        } else {
            throw new DukeException("no title");
        }
        if (parameters.get(desc) != null) {
            System.out.println(parameters.get(desc));
        } else {
            throw new DukeException("no description");
        }
        if (parameters.get(end) != null) {
            System.out.println(parameters.get(end));
        } else {
            throw new DukeException("no deadline");
        }
        if (parameters.get(sd) != null) {
            System.out.println(parameters.get(sd));
        } else {
            throw new DukeException("no sprint interval");
        }


        /*Insert code for putting the title into the project class here.
        .
        .
        .
         */

        /*Insert code for putting the description into the project class here.
        .
        .
        .
         */

        /*Insert code for putting the deadline into the project class here.
        .
        .
        .
         */

        /*Insert code for putting the sprint interval into the project class here.
        .
        .
        .
         */
    }
}
