package academic;

/**
 * Represents a person in study-it.
 */
public class Person {
    protected String nameOfPerson;
    protected String contactNumberOfPerson;
    protected String emailOfPerson;

    public Person(String name, String number, String email) {
        this.nameOfPerson = name;
        this.contactNumberOfPerson = number;
        this.emailOfPerson = email;
    }

    public String getDetailsOfPerson() {
        return this.nameOfPerson + "[" + this.contactNumberOfPerson + "][" + this.emailOfPerson + "]";
    }



}
