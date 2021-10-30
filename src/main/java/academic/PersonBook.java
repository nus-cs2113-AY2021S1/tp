package academic;

import exceptions.EmptyInputException;
import exceptions.InvalidEmailException;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class PersonBook {
    /**
     * Adds a person to an array list of person.
     * @param args parameters of the person.
     * @param listOfPerson array list of person.
     * @throws InvalidEmailException when email added does not have an @ in it.
     */
    public static void addPerson(String[] args, ArrayList<Person> listOfPerson)
            throws InvalidEmailException, EmptyInputException {

        if (args[0].equals("")) {
            throw new EmptyInputException();
        }

        if (args[2].contains("@")) {
            listOfPerson.add(new Person(args[0], args[1], args[2]));
            if (args.length == 4) {
                if (args[3].equals("true")) {
                    Person.changePersonStar(listOfPerson.get(listOfPerson.size() - 1));
                }
            }
        } else {
            throw new InvalidEmailException();
        }
    }

    /**
     * Delete a person from an array list of person.
     * @param indexToBeDeleted index of the person to be deleted.
     * @param listOfPerson array list of person.
     */
    public static void deletePerson(Integer indexToBeDeleted, ArrayList<Person> listOfPerson) {
        listOfPerson.remove(indexToBeDeleted - 1);
    }

    public static void starContact(Integer indexToBeStar, ArrayList<Person> listOfPerson) {
        if (indexToBeStar > 0 && indexToBeStar <= listOfPerson.size()) {
            Person.changePersonStar(listOfPerson.get(indexToBeStar - 1));
        } else {
            throw new NumberFormatException();
        }
    }

    public static String printPersonBook(ArrayList<Person> listOfPerson) {
        int listIndex = 0;
        StringBuilder listToPrint = new StringBuilder();
        if (listOfPerson.size() == 0) {
            listToPrint.append("You have not added any contacts!");
        }
        for (Person person : listOfPerson) {
            if (person != null) {
                listToPrint.append((listIndex + 1) + ".");
                listToPrint.append(combinePersonDetails(person));
                listIndex++;
                if (listOfPerson.size() != listIndex) {
                    listToPrint.append("\n");
                }
            }
        }

        return listToPrint.toString();
    }

    public static String combinePersonDetails(Person person) {
        StringBuilder personDetails = new StringBuilder();

        personDetails.append("[" + person.nameOfPerson + "]");
        personDetails.append(" [" + person.contactNumberOfPerson + "]");
        personDetails.append(" [" + person.emailOfPerson + "]");
        if (Person.isStar(person)) {
            personDetails.append(" (*)");
        }
        return personDetails.toString();
    }
}