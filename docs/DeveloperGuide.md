# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


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

## Development

### Logging
Whenever you need to use logging in a class, add this line 
`private static final Logger logger = Logger.getLogger(<CurrentClass>.class.getName());` to the start
of the class, where \<CurrentClass\> is replaced by the class name you are adding the logger to.

The logging configuration can be found under `src/main/resources/logging.properties`. The current configuration directs
the logs to a file named `revisED%u.log` in the project root directory. 