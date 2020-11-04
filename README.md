# Quotesify

Quotesify is a free greenfield Java project by a team of software engineering students.

Are you constantly reading books and browsing articles on the web only to forget most of it in a short period of time?

If so Quotesify is for you! A minimal distraction free CLI that helps you manage the information you consume by tracking
the things you read and logging those important nuggets of wisdom you find along the way!

With Quotesify, users can add books and quotes that they wish to remember. They can categorize their books and quotes
by author, customized categories, and even rate their books. Quotesify also comes with a progress tracker just to
improve the reading experience of users.

Interested to contribute to the development of Quotesify? Given below are instructions on how to use it.

## Setting up in IntelliJ

1. Fork the Quotesify repo from [here](https://github.com/AY2021S1-CS2113T-T09-3/tp),
and clone the fork to your computer.
2. Open up your IDE (IntelliJ is highly recommended). If you are not at the welcome screen,
click `File` > `Close Project` to close any existing project.
3. Set up the correct JDK version for Gradle:
    1. Click `Configure` > `Project Defaults` > `Project Structure`.
    2. Click `New…` and find the directory where you saved your JDK.
4. Click `Import Project`.
5. Locate the *build.gradle* file and select it.
6. Click `OK`.
7. Click `Open as Project`.
8. Click `OK` to accept all default settings.
9. To verify the set up, locate the `Quotesify.java` file, right-click it and select `Run Quotesify.main()`.
If the setup is correct, you should see something like this as shown below:

```
> Task :compileJava UP-TO-DATE
> Task :processResources NO-SOURCE
> Task :classes UP-TO-DATE

> Task :Quotesify.main()
________                __                .__  _____       
\_____  \  __ __  _____/  |_  ____   _____|__|/ ____\__.__.
 /  / \  \|  |  \/  _ \   __\/ __ \ /  ___/  \   __<   |  |
/   \_/.  \  |  (  <_> )  | \  ___/ \___ \|  ||  |  \___  |
\_____\ \_/____/ \____/|__|  \___  >____  >__||__|  / ____|
       \__>                      \/     \/          \/    
Welcome to Quotesify v2.1!
Before you continue, here's something:
Better days are coming, they are called Saturday and Sunday.
---------------------------------------------------------------------------------------------------------------------------------------

What would you like to do with Quotesify?
```

## Documentation

Documentation for Quotesify including User and Developer guide is stored in */docs* directory.

Click [here](https://ay2021s1-cs2113t-t09-3.github.io/tp/) to access the documentation such as User Guide and Developer Guide.


## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* A skeleton JUnit test (`src/test/java/seedu/duke/DukeTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.
