package academic;

/**
 * Represents a person in study-it.
 */
public class Person {
    protected String nameOfPerson;
    protected String contactNumberOfPerson;
    protected String emailOfPerson;
    protected Boolean isStar;

    public Person(String name, String number, String email) {
        this.nameOfPerson = name;
        this.contactNumberOfPerson = number;
        this.emailOfPerson = email;
        this.isStar = false;
    }

    /**
     * Receive a person and convert it into a string to be stored into local storage.
     * @param person person to be stored.
     * @return string to be added to local storage.
     */
    public static String printIndividualPerson(Person person) {
        return "[P] | " + person.nameOfPerson + " | " + person.contactNumberOfPerson
                + " | " + person.emailOfPerson + " | " + person.isStar;
    }

    public static void changePersonStar(Person person) {
        person.isStar = true;
    }

    public static Boolean isStar(Person person) {
        return person.isStar;
    }
}