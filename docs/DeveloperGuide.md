# Developer Guide

This developer guide aims to provide an overview of Nav@NUS to aid developers in creating extensions or making 
enhancements.

##Table of Contents

- [1. Setting up, getting started](#1-setting-up-getting-started)
- [2. Design & Implementation](#2-design--implementation)
  * [2.1. Architecture](#21-architecture)
    + [2.1.1 Ui Component](#211-ui-component)
    + [2.1.2. Logic Component](#212-logic-component)
    + [2.1.3. Model Component](#213-model-component)
    + [2.1.4. Storage Component](#214-storage-component)
  * [2.2. Implementation](#22-implementation)
    + [2.2.1 Direct Route Finder (`/route` Feature)](#221-direct-route-finder-route-feature)
- [3. Appendix I: Requirements](#3-appendix-i-requirements)
  * [3.1 Product scope](#31-product-scope)
    + [3.1.1 Target user profile](#311-target-user-profile)
    + [3.1.2 Value](#312-value)
  * [3.2. User Stories](#32-user-stories)
  * [3.3. Non-Functional Requirements](#33-non-functional-requirements)
  * [3.4. Glossary](#34-glossary)
- [6. Appendix II: Instructions for manual testing](#6-appendix-ii-instructions-for-manual-testing)

## 1. Setting up, getting started

Refer to the guide Setting up and getting started.

## 2. Design & Implementation

This section describes the design and implementation of the product. It has been divided into two sections: Architecture 
and Implementation. 

### 2.1. Architecture

The architecture diagram given in the figure below explains the high-level design of the App. 

![Architecture Diagram](DG_Diagrams/Architecture.png)

Given below is a quick overview of each component.

`Main` is the class Duke.java. It is responsible for:

- Initializing the components in the correct sequence, and calling or creating objects in the order of execution.

The rest of the App consists of 4 main components:

- `Ui` : The Ui component handles all interactions with the user.
- `Logic` : The Logic component makes sense of the command and executes it.
- `Model` : The Model component is responsible for all data held in the memory.
- `Storage` : The Storage component handles data by reading from and writing to files in the hard disk. 

The sections below give more details of each component.

#### 2.1.1 Ui Component
The Ui component is responsible for getting the user input and for displaying appropriate messages as response 
to commands executed by the logic component. The following class diagram gives a brief overview of the Ui component 
and it's interaction with other components.

![uicomponent](DG_Diagrams/UiComponent.png)

#### 2.1.2. Logic Component
The Logic component is responsible for the following tasks:
- Make sense of the command entered by the user by splitting it into the command type and the parameters and/or 
delimiters if any.
-  Executes a command based on its type and provides an appropriate result to the user via the Ui component.

The following class diagram briefly explains how different classes in the logic component interact with each other
and with other components.

![logiccomponent](DG_Diagrams/LogicComponent.png)

#### 2.1.3. Model Component
[Work in Progress]

#### 2.1.4. Storage Component
[Work in Progress]

### 2.2. Implementation
This section provides details of how the main features of Nav@NUS have been implemented.

#### 2.2.1 Direct Route Finder (`/route` Feature)

The `/route <location1> /to <location2>` is the command that has to entered by the user to see all direct bus routes 
available from *location1* to *location2*.

The class diagram in the figure below shows how different classes used for implementation of the `/route` command 
are linked to each other. 

![RouteCommandClass](DG_Diagrams/RouteCommandClass.png)

The `RouteCommand#executeCommand()` method of RouteCommand Class executes the command in the following steps:
1. Call `RouteParser#getLocations()` to get the locations entered by the user in the order of starting location and 
destination.
    - The `RouteParser#getLocations()` method throws an exception if the locations or the delimiter `/to` is missing.
2. Calls `RouteCommand#checkLocations()` to make sure location strings are not empty or the same.
    - The `RouteCommand#checkLocation()` method throws an exception if locations are empty or the same.
3. Calls `RouteCommand#similarityCheck()` to check if the locations are not in the list of bus stops but 
are similar.
    - The `RouteCommand#similarityCheck()` method calls the static `SimilarityCheck#similarLoc()` method and returns 
    a list of similar locations, if any.
    - If the list of similar locations is empty, `RouteCommand#executeCommand()` performs step 4 given below, 
    else it calls the static method, `Ui#printPossibleLocsMessage()`, to print the list of similar locations. 
4. Calls static `BusData#possibleBuses()` to get a list of buses with their routes from the starting location to 
the destination.
   - `BusData#possibleBuses()` calls `Bus#getPossibleRoute()` to check for a possible route for the given bus number.
   - `BusData#possibleBuses()` repeats this call for all bus numbers.

The following sequence diagram explains the above steps when the user enters `/route loc1 /to loc2`.

![Overview](DG_Diagrams/RouteCommand.png)

The following sequence diagrams explain the interactions omitted in the main diagram.

![executing command](DG_Diagrams/RouteCommandInternal.png)

![bus data](DG_Diagrams/BusData.png)

## 3. Appendix I: Requirements

### 3.1 Product scope

#### 3.1.1 Target user profile

Nav@NUS targets people who are unfamiliar with the shuttle bus service in NUS Kent Ridge Campus 
including students, professors and visitors.
These are people who:
 - needs to be punctual for events such tutorials
 - prefer a desktop CLI app over other types
 - are new to NUS Kent Ridge Campus
  
#### 3.1.2 Value 

Nav@NUS seeks to help the intended audience to achieve the following:
 - Efficient checking of bus routes in NUS
 - Fast viewing of dining options available at other locations

### 3.2. User Stories

|Version| As a ... | I want to ... | So that ...|
|--------|----------|---------------|------------------|
|v1.0|freshman/ anyone new to nus|know if there is any direct bus to my target destination|I do not need to change buses|
|v1.0|someone that is unfamiliar with the NUS campus|know the full routes of the buses|I could plan my route to my desired destination|
|v1.0|someone new to NUS|know all the available bus stops in the school|I can check the possible bus stops I can board/alight at to reach my destination|
|v1.0|curious person|know the full routes of all the buses in NUS|I can see where each bus is heading to|
|v1.0|freshman/ anyone new to NUS|know the buses available at specific bus stops|I can better plan my trip around the campus in advance|
|v2.0|frequent user|have a list of favourite commands|I can access my favourite commands quickly|
|v2.0|frequent user|be able to customise my list of favourite commands|I can change the list according to my needs|
|v2.0|frequent user|view my most searched bus stop|it can promptly remind me of the bus stop to key in|

### 3.3. Non-Functional Requirements

1. Nav@NUS should be able to work on any OS which has Java 11 or a higher version of Java installed.
2. The user is expected to have a basic idea about the places around NUS.
3. A user comfortable with typing english text should be able to find this application faster and more useful than those
that require mouse clicks.

### 3.4. Glossary

* *glossary item* - Definition

## 6. Appendix II: Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
