# John - Project Portfolio Page

## Overview
This document covers my contribution to CCA Manager. CCA Manager was a revolutionary all in one tool conceptualized to deliver an efficient management system that integrates many different categories and aspects of management into one single easy to use program.
Instead of having dozens of text fields and hard to navigate menus, we made a focus on simplicity and efficiency to allow people to quickly get things done with CCA Manager rather than to have to figure out how to find a specific feature or spend hours managing the inputs in spreadsheet apps.

### Summary of Contributions

As one of the more experienced coders on the team, I had the responsibility of overseeing the development and structure of the high level aspects of the project, to ensure that the backend design and fundamentals of project base was built on a firm foundation that would allow for easy and quick development with minimal conflicting files.
Additionally, as the only member on the team doing Information Security, I was responsible for ensuring the architecture of the program was secure and not vulnerable to user exploits by minimizing the attack surface of the application.


User-Facing features developed

* Bye command to terminate program
* Help command to display help information
* import command to import data from other csv files

Backend features developed

* File Manager to load and save to CSV files without any third party library dependencies
* Parser to be able to read user input of any accepted format
* Sanitization whitelist to prevent potentially malicious input from causing undefined behavior in the program
* Ui framework for reading and printing user input
* Backend logic design from program boot to the command reading cycle
* Command framework to enable commands to be added by only creating one file and adding one line to register and activate it

Additional contributions

* Fuzzing inputs with common attack vectors to ensure consistent program behavior
* Setting up of the team organization and repo
* Creation of deliverable builds (.jar files)
* Design and art for product logo


**Code Contributed**

[RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=JohnNub&tabRepo=AY2021S1-CS2113T-F14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

**Contributions to Development Guide**
* Introduction
* Setting Up
* Input Parsing
* Commands
* Storage
* Target User Profile
* Value Proposition
* Non-Functional Requirements
* Glossary
* Instructions for Manual Testing
