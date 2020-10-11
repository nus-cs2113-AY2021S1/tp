package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class PersonBook {
    protected ArrayList<Person> listOfPerson;

    public void addPerson(Person person){
        this.listOfPerson.add(person);
    }

}