# User Guide

## Introduction

Hospitalsaurus ReX is a program to help administrative staff to keep track of patients.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Hospitalsaurus ReX` from [here](https://github.com/AY2021S1-CS2113-T16-4/tp).

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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Clone the program to the other computer, and copy the data folder over.

## Command Summary

* Add patient `add NRIC`
* Retrieve patient `retrieve NRIC`