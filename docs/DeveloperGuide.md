# Developer Guide
It is recommended that you read through the user guide to familiarize yourself with the program before using the 
developer guide.

# Setting up PaperTrade
Fork the repository and clone the fork into your computer.

To set up the project:
1. Ensure that you have JDK 11 installed.
2. Import the project as a Gradle project.
3. To build the project, run "./gradlew build" on a Unix machine or run "gradlew build" on Windows.

## Design & implementation

### Architecture

![](./diagrams/Packages.png)

The Package Diagram above gives a high level view of the project structure and the classes in each package. 

![](./diagrams/Architecture.png)

The Architecture Diagram above gives a high level design of PaperTrade. Below is a quick overview of each component.

### Overview

* `PaperTrade`: The main entry point into the program. It initializes the Controller object which then takes over the 
execution of the program.
* `Controller`: Acts as an interface between all the other classes, to process all the business logic incoming commands
from the user. Manipulates the data through `PortfolioManager` and interact with `Ui` to display information to the 
user.
* `PortfolioManager`: Responsible for managing the persistency of the user's data through the `Storage` class and holds 
all the data for the program at a point in time.
* `Ui`: Responsible for displaying information to the user and getting input from the user.
* `Parser`: Responsible for parsing the user input and returning a `Command` object corresponding to the user command 
requested.
* `StockPriceFetcher`: Responsible for calling the AlphaVantage API to retrieve stock information.
* `Storage`: Responsible for managing the storing of data for persistency.

### Lifecycle of PaperTrade
The sequence diagram below shows how different packages and classes interact with each other throughout the lifecycle 
of PaperTrade.

![](./diagrams/Lifecycle.png)

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
