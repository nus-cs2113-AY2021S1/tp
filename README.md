# Duke project template

The Schwarzenegger is a desktop command line interface-based app for managing all your needs regarding fitness. With the built-in personal assistant, you are able to track your daily workout and diet sessions based on your profile. If you can type fast, The Schwarzenegger can help you maximise your efficiency for maintaining fitness.

## Setting up in Intellij

Prerequisites: JDK 11 \(use the exact version\), update Intellij to the most recent version.

1. **Configure Intellij for JDK 11**, as described [here](https://se-education.org/guides/tutorials/intellijJdk.html).
2. **Import the project** _**as a Gradle project**_, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
3. **Verify the set up**: After the importing is complete, locate the `src/main/java/seedu/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:

   ```text
   > Task :compileJava UP-TO-DATE
   > Task :processResources NO-SOURCE
   > Task :classes UP-TO-DATE

   > Task :Duke.main()
        _________________________________________________________________________________________________
           _____        _
          / ____|      | |
         | (___    ___ | |__ __      __ __ _  _ __  ____ ___  _ __    ___   __ _   __ _   ___  _ __ 
          \___ \  / __|| '_ \\ \ /\ / // _` || '__||_  // _ \| '_ \  / _ \ / _` | / _` | / _ \| '__|
          ____) || (__ | | | |\ V  V /| (_| || |    / /|  __/| | | ||  __/| (_| || (_| ||  __/| |
         |_____/  \___||_| |_| \_/\_/  \__,_||_|   /___|\___||_| |_| \___| \__, | \__, | \___||_|
                                                                            __/ |  __/ |
                                                                           |___/  |___/
        _________________________________________________________________________________________________

        _________________________________________________________________________________________________
        Welcome back to The Schwarzenegger, Khoa!
        How can I help you today?
        _________________________________________________________________________________________________

   Main Menu >>>>>
   ```

   Type `help` and press Enter to see the available commands under Main Menu.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well \(i.e. the `build.gradle` file\).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### JUnit tests

* JUnit test files are placed under `src/test/java` with this project. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public: 1. If you are using this project template for an individual project, go your fork on GitHub.  
 If you are using this project template for a team project, go to the team fork on GitHub. 1. Click on the `settings` tab. 1. Scroll down to the `GitHub Pages` section. 1. Set the `source` as `master branch /docs folder`. 1. Optionally, use the `choose a theme` button to choose a theme for your documentation.

