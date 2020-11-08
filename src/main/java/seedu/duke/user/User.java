package seedu.duke.user;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static seedu.duke.constants.DataFileConvention.LENGTH_OF_DATE;
import static seedu.duke.constants.FluffleMessages.HELP_MESSAGE;

public class User {
    private String name;
    private String registeredDate;

    public User(String name) {
        setName(name);
        setRegisteredDate();
    }

    public String getName() {
        return name;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegisteredDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.registeredDate = now.format(dtf);
    }

    public void greetUser() {
        System.out.println("Hello " + this.name + ". Welcome to Fluffle!");
        System.out.println("Your registration has been recorded on "
                + registeredDate);
    }

    public void printInstruction() {
        System.out.println(HELP_MESSAGE);
    }

    @Override
    public String toString() {
        return "Your username is: " + name
                + "Your registeredDate is: " + registeredDate;
    }
}

