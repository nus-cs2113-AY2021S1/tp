# Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### 4.6. Scheduler feature
#### 4.6.1. Implementation
In KAJI, each `Chapter` stores a `CardList` of `Card`s, each with their own `int` attribute `previousInterval`. Each `Chapter` also has a `LocalDate` attribute named `dueBy` that determines when the `Chapter` is due for revision. 
The Scheduler mechanism implements Spaced Repetition by computing the `deckInterval`, the mean (rounded off to the nearest integer) of the `previousInterval`s of every `Card` within the `Chapter`, and updates the `dueBy` attribute to `deckInterval` days after the day of revision.

`Scheduler` implements the following operations:
* `Scheduler#computeEasyInterval()`
* `Scheduler#computeMediumInterval()`
* `Scheduler#computeHardInterval()`
* `Scheduler#computeDeckInterval()`
* `Scheduler#computeDeckDeadline()`

`Scheduler#computeEasyInterval()`, `Scheduler#computeMediumInterval()` and `Scheduler#computeHardInterval()` are exposed in the `ReviseCommand` class as `ReviseCommand#rateCard()` while `Scheduler#computeDeckDeadline()` is exposed as `ReviseCommand#execute()`.


#### 4.6.1.1 Prerequisites
* There must at least be one `Chapter` with at least one `Card` in the `CardList` attribute of said `Chapter`

#### 4.6.1.2 Example
Given below is an example usage scenario and how the Scheduler mechanism behaves at each step when: 
`revise 1` is called in a `Module` that contains only one `Chapter` with three `Card`s in its `CardList` attribute and confirmation is given to proceed with revision. (For more details on revision, refer to [Revise feature](#43-Revise-feature))

Step 1:
* The user enters `revise 1` within the `Module` and `ReviseCommand` is instantiated. 
* Upon confirmation and check that `CardList` of the designated `Chapter` is not empty, `ReviseCommand` proceeds to create an `ArrayList<Card>` named `allCards` comprising of all `Card`s within in the `CardList`.

Step 2:
* For each `Card` in `allCards`, `ReviseCommand#reviseCard()` is called upon completion of either `ReviseCommand#execute()` or `ReviseCommand#repeateRevision()`.
* This operation then calls `Scheduler#computeEasyInterval()`, `Scheduler#computeMediumInterval()` or `Scheduler#computeHardInterval()` depending on the user input to compute and update the new value of `previousInteral` for each card.

Step 3:
* Upon completion of all revision, `ReviseCommand#execute()` will call `Scheduler#computeDeckDeadline()`, which in turn calls `Scheduler#computeDeckInterval()`. `Scheduler#computeDeckInterval()` computes `deckInterval`, the mean (rounded off to the nearest integer) of the `previousInterval`s of each `Card` in `allCards,` and returns it to `Scheduler#computeDeckDeadline()`.


Step 4:
* Using `deckInterval`, `Scheduler#computeDeckDeadline()` computes the new value of `dueBy` for the Chapter, which is then returned to `ReviseCommand#execute()`, where it will then update the value of `dueBy` for the `Chapter` that was just revised.

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
