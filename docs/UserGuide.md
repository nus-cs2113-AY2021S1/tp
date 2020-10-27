# User Guide

## Introduction

Hospitalsaurus ReX is a program to help administrative staff to keep track of patients.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `Hospitalsaurus ReX` from [here](https://github.com/AY2021S1-CS2113-T16-4/tp).

## Features

### Adding a patient: `add`
Adds a patient to the database.

Format: `add NRIC`

* The `NRIC` must be valid.
* Program will prompt name and date of birth to be entered.

Example of usage: 

`add S9922312D`

`add S7423213Z`

### Retrieve a patient: `retrieve`
Retrieves a patient to the database.

Format: `retrieve NRIC`

* The `NRIC` must be valid and in the database.
* Program will show name and date of birth of the patient.

Example of usage:

`retrieve S9922312D`

`retrieve S7423213Z`

### Edit a patient: `edit`
Edits a patient to the database.

Format: `edit NRIC`

* The `NRIC` must be valid and in the database.
* Program will prompt name and date of birth to be edited.

Example of usage:

`edit S9922312D`

`edit S7423213Z`

### Delete a patient: `delete`
Deletes a patient to the database.

Format: `delete NRIC`

* The `NRIC` must be valid and in the database.
* Program will delete patient with nric as `NRIC`.

Example of usage:

`delete S9922312D`

`delete S7423213Z`

### Create an appointment: `create`
Creates an appointment date.

Format: `create`

* Program will prompt for date to be inputted.

Example of usage:

`create`

### Book an appointment: `book`
Books an appointment date.

Format: `book NRIC`

* The `NRIC` should be valid and in the database, else `add` command will run
* Program will show a lists of available dates and prompt for which date to use.

Example of usage:

`book S9922312D`

`book S7423213Z`

### Add a doctor: `doctor`
Adds a doctor.

Format: `doctor NAME`

* The `NAME` should not already be inside the list.

Example of usage:

`doctor tan`

`doctor trump`

### Remove a doctor: `doctor`
Remove a doctor.

Format: `nodoctor NAME`

* The `NAME` should be in the list.

Example of usage:

`nodoctor tan`

`nodoctor trump`

### View patient's appointments: `appointments`
View patient's appointments

Format: `appointments NRIC`

* The `NRIC` should be valid and in the list.

Example of usage:

`appointments S2233445D`

`appointments S1234556F`

### List patients: `list`
View patient's appointments

Format: `list`

* The patient list should not be empty.

Example of usage:

`list`

### Edit patient's appointment: `editappt`
Edit patient's appointment

Format: `editappt NRIC`

* The patient with `NRIC` should already exist.
* The program will prompt user to select the booked appointment to be replaced, the new appointment to be booked, and the doctor to be in charge.

Example of usage:

`editappt S9900999D`
`editappt S9900299D`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Clone the program to the other computer, and copy the data folder over.

## Command Summary

* Add patient `add NRIC`
* Retrieve patient `retrieve NRIC`
* Edit patient `edit NRIC`
* Delete patient `delete NRIC`
* Create appointment `create`
* Book appointment `book NRIC`
* Add doctor `doctor NAME`
* Remove doctor `nodoctor NAME`
* View appointments of a patient `appointments NRIC`
* List patients `list`
* Edit patient's appointment `editappt`
