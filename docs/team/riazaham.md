# Riaz Ahamed's Project Portfolio Page

## Project: EZ Manager
EZ Manager is a simple and efficient project management tool for software project managers. 
EZ Manager is a desktop application and the user interacts with the CLI 
(Command Line Interface) to manage projects, tasks and team members.

Given below are my contributions to the project.

#### Code contributed: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=riazaham)

### Features added
**Select Task**: Allows the user to select specific tasks.
**Edit Task**: Allows the user to edit specific tasks.
**Home**: Allows the user to switch from project view to home view. 
**Delete Task**: Allows the user to delete specific tasks.
**Assign team member to task**: Allows the user to assign an existing member to a specific task.
**Assign priority to task**: Allows the user to assign priority to a specific task

### Enhancements to the codebase
The initial design of our codebase implemented the logic for the different commands in one Parser class.
As a team, we decided to redesign the codebase such that the logic is implemented in the respective command classes.
Hence, I contributed to the design by abstracting logic from most functions in the Parser class to the respective command classes.
This enhancement was a tedious process as I had to redesign the entire codebase for every feature.

#### Code testing
I contributed to the testing of the program by writing JUnit tests for the features
mentioned above.

#### Contributions to the UG (User Guide)
I contributed to the UG in the following ways:
- Designed the application logo
- Wrote user guide for the features mentioned above
- Drafted the initial official full UG with hyperlinked table of contents
- Formatted the full UG with text colors, decorations and indentations

#### Contributions to the DG (Developer Guide)
I contributed to the DG in the following ways:
- Included the code explanations and the sequence diagrams for the features mentioned above
- Fixed most of the sequence diagram issues brought up by the tutor across the DG

#### Contributions to reviewing/mentoring
Our project has about 70 PRs (Pull requests) as of 6/11/2020. I reviewed, commented 
and approved almost half, 34 of the PRs.
