package seedu.duke.user;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static seedu.duke.constants.DataFileConvention.LENGTH_OF_DATE;

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.registeredDate = now.toString().substring(0, LENGTH_OF_DATE);
    }

    public void greetUser() {
        System.out.println("Hello " + this.name + ". Welcome to Fluffle!");
        System.out.println("Your registration has been recorded at " + registeredDate);
    }

    public void printInstruction() {
        System.out.println("Please type in \"help\" command for information!");
    }

    @Override
    public String toString() {
        return "Your username is: " + name
                + "Your registeredDate is: " + registeredDate;
    }
}

