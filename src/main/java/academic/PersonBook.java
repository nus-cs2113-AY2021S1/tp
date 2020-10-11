package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class PersonBook {
    public static ArrayList<Person> listOfPerson = new ArrayList<>(); //TODO change to local storage

    public static void addPerson(String[] args) {
        PersonBook.listOfPerson.add(new Person(args[0],args[1],args[2]));
    }

    public static String printPersonBook() {
        int listIndex = 0;
        StringBuilder listToPrint = new StringBuilder();
        for (Person person : PersonBook.listOfPerson) {
            if (person != null) {
                listToPrint.append(listIndex + 1);
                listToPrint.append(". [" + person.nameOfPerson + "]");
                listToPrint.append(" [" + person.contactNumberOfPerson + "]");
                listToPrint.append(" [" + person.emailOfPerson + "]");
                listIndex++;
                if (PersonBook.listOfPerson.size() != listIndex) {
                    listToPrint.append("\n");
                }

            }
        }
        return listToPrint.toString();
    }

}