# Setting Up

## Setting up the project in your computer

1. **Fork** this repo, and **clone** the fork into your computer.
1. **Configure the JDK**: Follow the guide [_[se-edu/guides] IDEA: Configuring the JDK_](https://se-education.org/guides/tutorials/intellijJdk.html) to configure IntelliJ to use **JDK11**.
1. **Import the project as a Gradle project**: Follow the guide [_[se-edu/guides] IDEA: Importing a Gradle project_](https://se-education.org/guides/tutorials/intellijImportGradleProject.html) to import the project into IDEA.
1. **Verify the setup**:
   1. Run `WhereGotTime`, you should see something like the following:
       ```
      Executing task 'WhereGotTime.main()'...
       
       > Task :compileJava UP-TO-DATE
       > Task :processResources NO-SOURCE
       > Task :classes UP-TO-DATE
       
       > Task :WhereGotTime.main()
       ____________________________________________________________
        _    _ _                   _____       _ _____ _                
       | |  | | |                 |  __ \     | |_   _(_)               
       | |  | | |__   ___ _ __ ___| |  \/ ___ | |_| |  _ _ __ ___   ___ 
       | |/\| | '_ \ / _ \ '__/ _ \ | __ / _ \| __| | | | '_ ` _ \ / _ \
       \  /\  / | | |  __/ | |  __/ |_\ \ (_) | |_| | | | | | | | |  __/
        \/  \/|_| |_|\___|_|  \___|\____/\___/ \__\_/ |_|_| |_| |_|\___|
                                                                        
       Hello! Welcome to WhereGotTime!
       Please enter your time table details.
       You may refer to the User Guide for instructions.
       ____________________________________________________________
      ```
   1. Run the test to ensure they all pass.
      * Method 1: Use IntelliJ JUnit test runner
         * To run all test, right-click on the `src/test/java/seedu.duke` folder and select `Run 'Tests in 'seedu.duke''`
         * To run a test , right-click on the class and select `Run '(class_name)''`
      * Method 2: Using Gradle
         * (Windows) Open the terminal in intelliJ and run the command `gradlew clean test`
         * (Mac/Linux) Open the terminal in intelliJ and run the command `./gradlew clean test`
         
## Before Writing Code

1. **Configure the coding style**

   Follow the guide [_[se-edu/guides] IDEA: Configuring the code style_](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to set up IDEA's coding style to match ours.

   Additionally, you can follow the guide [_[se-edu/guides] Using Checkstyle_](https://se-education.org/guides/tutorials/checkstyle.html) to find how to use the CheckStyle within IDEA.

1. **Set up CI**

   This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the `master` branch or to any PR. No set up required.

1. **Learn the design**

   When you are ready to start coding, we recommend that you get some sense of the overall design by reading about Duke's architecture from our [DeveloperGuide](DeveloperGuide.md).