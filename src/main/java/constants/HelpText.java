//@@author TomLBZ

package constants;

/**
 * The enum Help text.
 */
public enum HelpText {

    /**
     * The Add.
     */
    ADD(
        "add",
        "Add task(s) to module(s): Add specified task(s) to specified module(s).",
        new String[]{
            "add -task [index(es)] -mod [module code(s)]",
        },
        new String[]{
            "1. Index should be a positive integer. Otherwise you should expect an \"invalid command\" error message",
            "2. You must reference EXISTING tasks or modules when using this command. For example:",
            "2.1. If \"list\" shows only 2 tasks but you try to use \"-task 3\" as a parameter for \"add\", you should "
                + "expect an \"index out of range\" error message because \"3\" is out of range for your task list.",
            "2.2. Similarly, if there is no mod called CS9999 in the module list and you try to use "
                + "\"-mod CS9999\" as a parameter for \"add\", you should expect a \"not found\" error message.",
            "3. Both parameters here (i.e. task and mod) are compulsory.",
            "4. Once a task is added to a module, it is unlinked from the task list."
        },
        new String[]{
            "1. \"add -task 1 2 -mod CS2113 CS2113T \" >> add task 1 and task 2 in the current list to "
                + "the modules CS2113 and CS2113T",
        }),
    /**
     * The Bye.
     */
    BYE(
        "bye",
        "Quit the program",
        new String[]{
            "bye"
        },
        new String[]{
            "1. Extra inputs after \"bye\" will be ignored. For example:",
            "1.1. If \"bye domsun\" is input in, \"domsun\" will be ignored and \"bye\" will be executed.",
        },
        new String[]{
            "1. \"bye\" >> quits the program"
        }),
    /**
     * Help commands for CAP. (not done)
     */
    CAP(
        "cap",
        "Calculate CAP for courses based on selected option.",
        new String[]{
            "cap [index / code (for modules only)] [letter grade] ...",
            "cap [index(es) / codes (for modules only)]",
            "cap"
        },
        new String[]{
            "1. Index should be a positive integer. Otherwise you should expect an \"invalid command\" error message",
            "2. You must reference existing tasks or modules when using this command. For example:",
            "2.1. If \"list\" shows only 2 tasks but you try to use \"-task 3\" as a parameter for \"add\", you should "
                + "expect an \"index out of range\" error message because \"3\" is out of range for your task list.",
            "2.2. Similarly, if there is no mod called CS9999 in the module list and you try to use "
                + "\"-mod CS9999\" as a parameter for \"add\", you should expect a \"not found\" error message.",
        },
        new String[]{
            "1. \"cap CS2113 A CG1112 A-\" >> calculate the cap using A for CS2113 and A- for CG1112",
            "2. \"cap CS2113 CG1112\" >> calculate the cap using existing grades for CS2113 and CG1112 "
                + "if they have already been assigned a grade using the \"grade\" command",
            "3. \"cap\" >> calculate the cap using all taken and graded modules",
            "4. \"cap CG1111 CG1112 A\" >> calculated cap using existing grade for CG1111, and using A for CG1112",
        }),
    /**
     * The Clear.
     */
    CLEAR(
        "clear",
        "Clear the task list, or clear the bottom text region for the fancy UI.",
        new String[]{
            "clear",
            "clear fancy",
        },
        new String[]{
            "1. \"clear fancy\" can only be used in fancy UI mode",
            "2. Extra inputs after \"clear\" will get an \"invalid command\" error unless"
                + " it contains the word \"fancy\" (case insensitive). For example:",
            "2.1. \"clear domsun\" results in an \"invalid command\" error.",
            "2.2. If \"clear fancy domsun\" is input in, \"domsun\" will be ignored "
                + "and \"clear fancy\" will be executed.",
            "2.3. If \"clear MyFancyBoy\" is input in, \"clear fancy\" will be executed.",
        },
        new String[]{
            "1. \"clear\" >> clears the task list",
            "2. \"clear fancy\" >> clears the bottom region of the fancy UI",
        }),
    /**
     * The Complete.
     */
    COMPLETE(
        "complete",
        "Mark a module as completed",
        new String[]{
            "complete [index / code]"
        },
        new String[]{
            "1. Module should first be \"taken\" and \"grade\" before this function can run. "
                + "Otherwise, you should expect an error message",
            "2. Index should be a positive integer. Otherwise you should expect an \"invalid command\" error message",
            "3. Both parameters here (i.e. task and mod) are compulsory.",
            "4. You must reference existing tasks or modules when using this command. For example:",
            "4.1. If \"list\" shows only 2 tasks but you try to use \"-task 3\" as a parameter for \"add\", you should "
                + "expect an \"index out of range\" error message because \"3\" is out of range for your task list.",
            "4.2. Similarly, if there is no mod called CS9999 in the module list and you try to use "
                + "\"-mod CS9999\" as a parameter for \"add\", you should expect a \"not found\" error message.",

        },
        new String[]{
            "1. \"complete CS2113\" >> marks module CS2113 as completed",
            "2. \"complete 1\" >> marks the 1st item in the list as completed if it is a module"
        }),
    /**
     * The Deadline.
     */
    DEADLINE(
        "deadline",
        "Add a deadline to the task list",
        new String[]{
            "deadline [description] -by [time]"
        },
        new String[]{"1. Deadline description and time parameters here are compulsory.",},
        new String[]{
            "1. \"deadline ddl -by 21/9/15 1:12\" >> "
                + "adds a deadline with description \"ddl\" and time \"Sep 15 2021 1:12\"",
            "2. \"deadline ddl -by 21/9/15 1:12 Weekly\" >> "
                + "adds a weekly recurring deadline with description \"ddl\" and time \"Sep 15 2021 1:12\""
        }),
    /**
     * The Delete.
     */
    DELETE(
        "delete",
        "Delete a task from the task list",
        new String[]{
            "delete [index]"
        },
        new String[]{
            "1. Index should be a positive integer. Otherwise you should expect an error message",
            "2. You must reference EXISTING tasks when using this command. For example:",
            "2.1. If \"list\" shows only 2 tasks but you try to use \"delete 3\", you should "
                + "expect an \"index out of range\" error message because \"3\" is out of range for your task list.",
        },
        new String[]{
            "1. \"delete 1\" >> deletes the task with index 1 from the current list"
        }),
    /**
     * The Detail.
     */
    DETAIL(
        "detail",
        "Print the details of a specified item.",
        new String[]{
            "detail [module code (for modules only) / index]",
        },
        new String[]{
            "1. Index should be a positive integer. Otherwise you should expect an error message",
            "2. You must reference EXISTING tasks or module when using this command. For example:",
            "2.1. If \"list\" shows only 2 tasks but you try to use \"detail 3\", you should "
                + "expect an \"index out of range\" error message because \"3\" is out of range for your task list.",
            "2.2. Similarly, if there is no mod called CS9999 in the module list and you try to use "
                + "\"detail CS9999\", you should expect a \"not found\" error message.",
        },
        new String[]{
            "1. \"detail 1\" >> print the detail of item 1 in the list",
            "2. \"detail CS2113T\" >> print the detail of module CS2113T",
        }),
    /**
     * Goal Planner.
     */
    GOAL(
        "goal",
        "Calculate how far the user is from his/her target CAP",
        new String[]{
            "goal -total [total MC] [target CAP] -taken [taken MC] [current CAP]",
        },
        new String[]{
            "1. All values on the parameters should be a positive integer. "
                + "Otherwise you should expect an error message",
            "2. Both total MC and target CAP are compulsory parameters.",
            "3. CAP values need to be within 0 to 5.",
        },
        new String[]{
            "1. \"goal -total 160 4.9\" >> comment on required average grade to achieve CAP of 4.9 "
                + "with 160MC in total based on current grades",
            "2. \"goal -total 160 4.9 -taken 100 4.5\" >> "
                + "comment on required average grade needed for:\r\n"
                + "- Total MC for graduation: 160\r\n" + "- Target CAP: 4.9\r\n"
                + "- Total MC taken: 100\r\n" + "- Current CAP: 4.5\r\n"
        }),
    /**
     * The Done.
     */
    DONE(
        "done",
        "Mark a task as done",
        new String[]{
            "done [index]"
        },
        new String[]{
            "1. Index must be a positive integer referencing an existing item.",
            "2. If the index starts with a letter, it will be treated as a numerical value mapped A to 1 and Z to 26. "
                + "For example, \"done apple\" is equivalent to \"done 1\" and \"done C4\" is equivalent to \"done 3\"."
        },
        new String[]{
            "1. \"done 1\" >> marks the task with index 1 as done"
        }),
    /**
     * The Edit. (not sure)
     */
    EDIT(
        "edit",
        "Modify the attributes of an item (task / module), or operate on one linked task of a module",
        new String[]{
            "edit -task [index] field=new_value {[field=new_value] ...}",
            "edit -mod [module code] field=new_value {[field=new_value] ...}",
            "edit -mod [module code] task=[del / done / undone]<linked task index>",
        },
        new String[]{
            "1. Fields for \"-task\" include \"description\", \"type\", \"selected\", \"weekly\" and \"done\"",
            "2. Fields for \"-mod\" include \"grade\", \"su\", \"selected\" and \"taken\"",
            "3. No space allowed around the \"=\" sign. Use \"_\" in for spaces in \"[field=new_value]\" parameters",
            "4. Modules and task referenced need to exist.",
            "5. Removing a specified linked task from the module does not delete the task from the task list",
        },
        new String[]{
            "1. \"edit -mod CS2113T grade=A\" >> changes the \"grade\" field of module \"CS2113T\" to be \"A\"",
            "2. \"edit -task 1 description=do_homework\" >> "
                + "changes the \"description\" field of the 1st task in the current list to \"do homework\"",
            "3. \"edit -task 1 type=event\" >> changes the \"type\" of the 1st task in the current list to \"event\"",
            "4. \"edit -mod CS2113 grade=A -task 1 description=do_homework type=event\" >> do 1 to 3 sequentially",
            "5. \"edit -mod CS2113 task=del<1>\" >> removes the first linked task from the module if it exists",
            "6. \"edit -mod CS2113 task=done<1>\" >> marks the first linked task from the module as done if it exists",
            "7. \"edit -mod CS2113 task=undone<1>\" >> "
                + "marks the first linked task from the module as undone if it exists",
        }),
    /**
     * The Event.
     */
    EVENT(
        "event",
        "Add an event to the task list",
        new String[]{
            "event [description] -at [time]"
        },
        new String[]{"1. Event description and time parameters here are compulsory.",},
        new String[]{
            "1. \"event e -at May 13 2020 8:00\" >> "
                + "adds an event with description \"e\" and time \"May 13 2020 8:00\"",
            "2. \"event e -at May 13 2020 8:00 Weekly\" >> "
                + "adds an weekly recurring event with description \"e\" and time \"May 13 2020 8:00\""
        }),
    /**
     * The Fancy.
     */
    FANCY(
        "fancy",
        "Switch to a fancy Cli (requires the shell to support ansi codes).",
        new String[]{
            "fancy"
        },
        new String[]{
            "1. This feature can be used on Linux or Mac only. Error numbers will be displayed on Windows.",
        },
        new String[]{
            "1. \"fancy\" >> switch to a fancy Cli with graphics. Use \"plain\" to switch to plain Cli"
        }),
    /**
     * Help text to add grade to a course.
     */
    GRADE(
        "grade",
        "Modify grade to the user's taken course/module.",
        new String[]{
            "grade [index / code (for modules only)] [letter grade] ...",
            "grade [index(es) / codes (for modules only)]",
            "grade"
        },
        new String[]{
            "1. Modules need to be \"taken\" first before grade is applied!",
            "2. Grade and module code/index are compulsory parameters.",
            "3. Grade and module code need to be acceptable grade and module in NUS. For example:",
            "3.1. If \"grade CS2113 Z\" or \"grade CS9999 A\" is input, error message will be displayed.",
        },
        new String[]{
            "1. \"grade CS2113 A CG1112 A-\" >> make A and A- the grades of modules CS2113 and CG1112 respectively",
            "2. \"grade CS2113 CG1112\" >> delete grade[s] of modules CS2113 and CG1112",
            "3. \"grade\" >> show grades of all taken modules",
            "4. \"grade CG1111 CG1112 A\" >> delete the grade of CG1111, and then set the grade of CG1112 to A, "
                + "equivalent of executing \"grade CG1111\" followed by \"grade CG1112 A\" sequentially",
        }),
    /**
     * The Find.
     */
    FIND(
        "find",
        "Find an event in the task list with the specified keyword",
        new String[]{
            "find [keyword]"
        },
        new String[]{
            "1. Keyword here means any word (time/description) on task list.",
            "2. Ensure that you are referring to the most updated task list.",
            "3. If keyword does not exist on the task list, a \"no found\" message will be shown.",
        },
        new String[]{
            "1. \"find exam\" >> finds all tasks with the \"exam\" keyword from the task list"
        }),
    /**
     * The Focus.
     */
    FOCUS(
        "focus",
        "Change context. Changes the target of other commands to the specified target",
        new String[]{
            "focus",
            "focus [deadline / todo / event / task / mod / selected / taken]"
        },
        new String[]{
            "1. This function is used together with \"list\" to see the result of the \"focus\".",
            "2. For more explanation, refer to the diagram on \"Domsun Tutorial\" on the UserGuide.",
        },
        new String[]{
            "1. \"focus\" >> focus on \"task\". e.g. \"list\" will list all tasks from now on",
            "2. \"focus mod\" >> focus on \"mod\". e.g. \"list\" will list all modules from now on",
            "3. \"focus selected\" >> focus on \"selected\". e.g. \"list\" will list selected items from now on"
        }),
    /**
     * The Postpone.
     */
    POSTPONE(
        "postpone",
        "postpone task a day by default",
        new String[]{
            "postpone [index]",
            "postpone (h / w / y) [index]"
        },
        new String[]{
            "1. The tasks should consists of date type i.e. events or deadline tasks, does not work on todo tasks",
            "2. Each postpone delays the tasks by a day, an hour, a week or a year",
            "3. Does not work with custom date unless you have updated the task with the preferred date format.",
            "4. When letter appears without a number as its parameter, the letter will be treated as a numeric value "
                    + "mapped A to 1 and Z to 26." + Constants.WIN_NEWLINE
                    + "For example, \"postpone boy\" is equivalent to \"postpone 2\" and "
                    + "\"postpone h\" is equivalent to \"postpone 8\".",
        },
        new String[]{
            "1. \"postpone 1\" >> postpone the task with index 1 by a day",
            "2. \"postpone h 1\" >> postpone the task with index 1 by an hour",
            "3. \"postpone w 1\" >> postpone the task with index 1 by a week",
            "4. \"postpone y 1\" >> postpone the task with index 1 by a year"
        }),
    /**
     * The Help.
     */
    HELP(
        "help",
        "Print the list of available commands, or print the details of a specified command",
        new String[]{
            "help [target]",
        },
        new String[]{
            "1. If unknown command is put in as \"target\", general help of all commands will be displayed.",
            "2. The parameter \"target\" is the name of the explained command/feature."
        },
        new String[]{
            "1. \"help\" >> prints the list of available commands",
            "2. \"help event\" >> prints the details of the \"event\" command"
        }),
    /**
     * Statistics help text.
     */
    STATS(
        "stats",
        "Print statistics for a given modules/tasks",
        new String[]{
            "stats [-option] [module code]",
            "option: -mod"
        },
        new String[]{
            "1. Module entered should exist. Otherwise you should expect an \"Module Not Found\" error message",
            "2. If the command entered is stats alone, ensure that you are focusing on task by typing \"focus\""
        },
        new String[]{
            "1. \"stats\" >> prints statistics of task completed",
            "2. \"stats -mod CS2113\" >> prints statistics of tasks completed under tasks of CS2113"
        }),
    /**
     * The List.
     */
    LIST(
        "list",
        "Print a list of items depending on the current Focus",
        new String[]{
            "list",
            "list date [asc / desc / spec \"date\"(any common date format)]"
        },
        new String[]{
            "1. When updates are done on the list (e.g : new \"todo\" task is added), "
                + "\"list\" need to be run again to see the result of the update.",
            "2. There are 2 main list in this application (i.e. task and module list). "
                + "For more explanation, refer to the diagram on \"Domsun Tutorial\" on the UserGuide.",
        },
        new String[]{
            "1. \"list\" >> list all added items",
            "2. \"list date asc\" >> list items with a \"date\" field in ascending order",
            "3. \"list date spec Oct 5 2020\" >> list items with specific \"date\" field of Oct 5 2020"
        }),
    /**
     * The Load.
     */
    LOAD(
        "load",
        "Loads linked tasks to ONE specified module without adding them to the main task list",
        new String[]{
            "load [module code] [task_string] ...",
        },
        new String[]{
            "1. This command should only be used if you are highly familiar with the save file "
                + "and you want to manually edit linked tasks to a specific module",
            "2. We do NOT recommend using this command on a daily basis"
        },
        new String[]{
            "1. \"load EE2028 [D][V]_Exam_(by:_Jan_11_2011_11:11)\" >> loads a task with attributes: "
                + "\"description=Exam\", \"type=deadline\", \"date=Jan_11_2011_11:11\" and \"isdone=true\" "
                + "into the linked task list of module EE2028, WITHOUT adding this task object to the main task list.",
            "2. \"load EE2028 [T][X]_test1,[T][V]_test2\" >> loads a task with attributes: "
                + "\"description=test1\", \"type=todo\" and \"isdone=false\", then another task with attributes: "
                + "\"description=test2\", \"type=todo\" and \"isdone=true\", WITHOUT adding any of these tasks "
                + "to the main task list",
        }),
    /**
     * The Mc.
     */
    MC(
        "mc",
        "Print the number of MCs based on selected option.",
        new String[]{
            "mc [-option]",
            "option: -d(detailed)"
        },
        new String[]{
            "1. Default mc command prints the total mc that exist in the taken list of module",
            "2. To print out a detailed list of mc belonging to the taken modules, ensure you have entered "
                + "\"focus taken\"."
        },
        new String[]{
            "1. \"mc\" >> print the total number of MCs currently taking",
            "2. \"mc -d\" >> print the detailed MC composition of the selection region"
        }),
    /**
     * The Next.
     */
    NEXT(
        "next",
        "Switch the target region to the next page, keeping other regions unchanged.",
        new String[]{
            "next [region]",
            "region: i(item list), s(selection), a(all, default)"
        },
        new String[]{"1. This function should be used in FANCY UI only!",},
        new String[]{
            "1. \"next\" >> switch both item list (top) and selection (bottom) region to the next page (default)",
            "2. \"next s\" >> switch only the selection (bottom) region to the next page"
        }),
    /**
     * The Plain.
     */
    PLAIN(
        "plain",
        "Switch to a plain Cli.",
        new String[]{
            "plain"
        },
        new String[]{
            "1. Extra inputs after \"plain\" will be ignored. For example:",
            "1.1. If \"plain bye\" is input in, \"bye\" will be ignored and \"plain\" will be executed.",
        },
        new String[]{
            "1. \"plain\" >> switch to a plain pure-text Cli. Use \"fancy\" to switch to fancy Cli"
        }),
    /**
     * The Prev.
     */
    PREV(
        "prev",
        "Switch the target region to the previous page, keeping other regions unchanged.",
        new String[]{
            "prev [region]",
            "region: i(items), s(selection), a(all, default)"
        },
        new String[]{
            "1. This function should be used in FANCY UI only!",
        },
        new String[]{
            "1. \"prev\" >> switch both item list (top) and selection (bottom) to the previous page (default)",
            "2. \"prev s\" >> switch the selection (bottom) to the previous page (as explained on UserGuide)"
        }),
    /**
     * The Reminder.
     */
    REMINDER(
        "reminder",
        "List out events and deadlines tasks that are due within " + Constants.REMINDER_RANGE + " days",
        new String[]{
            "reminder [on/off]"
        },
        new String[]{ "1. You should expect the reminder will appear every 5 minutes.", },
        new String[]{
            "1. \"reminder\" >> list tasks that are due within the set period of time",
            "2. \"reminder on\" >> Switch on reminder",
            "3. \"reminder off\" >> Switch off reminder"
        }),
    /**
     * The Sel.
     */
    SEL(
        "sel",
        "Make selection: Add specified item(s) to the selection.",
        new String[]{
            "sel [index(es) (for the currently listed items) / module code(s) (for modules only)]",
        },
        new String[]{
            "1. Index must be a positive integer referencing an existing item.",
            "2. Module code needs to represent legitimate NUS module. Otherwise, an error message can be expected.",
        },
        new String[]{
            "1. \"sel 1 2 3\" >> add the item with indices 1, 2 and 3 from the item list to the selection",
            "2. \"sel CS1010 CS2113\" >> add the modules CS1010 and CS2113 on the item list to the selection",
            "3. \"sel 5 CS2113\" >> add the item with index 5 and module CS2113 on the item list to the selection"
        }),
    /**
     * The Snooze.
     */
    SNOOZE(
        "snooze",
        "Delay the reminder pop up by 1 minute.",
        new String[]{
            "snooze",
        },
        new String[]{
            "1. No additional parameter needed!",
            "2. If there is parameter, you should expect \"Invalid Command\" message."
        },
        new String[]{
            "1. \"snooze\" >> Delay reminder popup by 1 minute"
        }),
    /**
     * The Take.
     */
    TAKE(
        "take",
        "Take module(s): Mark specified module(s) as taken.",
        new String[]{
            "take [index(es) / module code(s) (for modules only)]",
        },
        new String[]{
            "1. Index must be a positive integer referencing an existing item on the current list.",
            "2. Module code must be a legitimate NUS module.",
        },
        new String[]{
            "1. \"take\" >> if there is any module selected but not taken, mark it as taken",
            "2. \"take 1 2\" >> mark module 1 and module 2 as taken",
            "3. \"take CS2113T\" >> mark module CS2113T as taken",
            "4. \"take 1 2 CS2113T\" >> mark module 1, module 2 and module CS2113T as taken",
        }),
    /**
     * The Todo.
     */
    TODO(
        "todo",
        "Add a todo to the task list",
        new String[]{
            "todo [description]"
        },
        new String[]{"1. Todo description parameter here are compulsory.",},
        new String[]{
            "1. \"todo class\" >> adds a todo with description \"class\""
        }),
    /**
     * The Undone.
     */
    UNDONE(
        "undone",
        "Mark a task as undone",
        new String[]{
            "undone [index]"
        },
        new String[]{
            "1. Index must be a positive integer referencing an existing item on the current list.",
            "2. If the index starts with a letter, it will be treated as "
                + "a number mapped A to 1 and Z to 26. For example:",
            "2.1. \"undone apple\" is equivalent to \"undone 1\" and \"undone C4\" is equivalent to \"undone 3\".",
        },
        new String[]{
            "1. \"undone 1\" >> marks the task with index 1 as undone"
        }),
    /**
     * The Unknown.
     */
    UNKNOWN(
        "unknown",
        "Prints the error message for an unrecognized command for debugging purposes",
        new String[]{
            "unknown"
        },
        new String[]{"1. This fucntion is only used to check if the program is working correctly.",},
        new String[]{
            "1. \"unknown\" >> prints \"OOPS, I don't know what that means :-( Try \"help\"!\""
        }),
    /**
     * The Unsel.
     */
    UNSEL(
        "unsel",
        "Cancel selection: Make specified item(s) no longer selected.",
        new String[]{
            "unsel [index(es) (for the currently listed items) / module code(s) (for modules only)]",
        },
        new String[]{
            "1. Index must be a positive integer referencing an existing item.",
            "2. Item need to first be selected using \"sel\" for \"unsel\" to function properly.",
        },
        new String[]{
            "1. \"unsel 1 2 3\" >> make items with indices 1, 2and 3 no longer selected",
            "2. \"unsel CS1010 CS2113\" >> make modules CS1010 and CS2113 no longer selected",
            "2. \"unsel 5 CS2113\" >> make the item with index 5 and module CS2113 no longer selected"
        }),
    /**
     * The Untake.
     */
    UNTAKE(
        "untake",
        "Untake module(s): Mark specified module(s) as not taken.",
        new String[]{
            "untake [index(es) / module code(s) (for modules only)]",
        },
        new String[]{
            "1. Index must be a positive integer referencing an existing item.",
            "2. If modules that is not taken is input, module will still be marked as \"no longer taken\".",
        },
        new String[]{
            "1. \"untake\" >> if there is any module selected and taken, mark it as not taken",
            "2. \"untake 1 2\" >> mark module 1 and module 2 as not taken",
            "3. \"untake CS2113T\" >> mark module CS2113T as not taken",
            "4. \"untake 1 2 CS2113T\" >> mark module 1, module 2 and module CS2113T as not taken"
        });

    /**
     * The Name.
     */
    public String name;
    /**
     * The Description.
     */
    public String description;
    /**
     * The Syntax.
     */
    public String[] syntax;
    /**
     * The Notes. Includes pre-requisites, and errors expected in different cases. See add as an example.
     */
    public String[] notes;
    /**
     * The Usages.
     */
    public String[] usages;

    HelpText(String name, String description, String[] syntax, String[] notes, String[] usages) {
        this.name = name;
        this.syntax = syntax;
        this.notes = notes;
        this.description = description;
        this.usages = usages;
    }

    /**
     * Array to string string.
     *
     * @param input the input
     * @return the string
     */
    public String arrayToString(String[] input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string: input) {
            stringBuilder.append(string).append(Constants.WIN_NEWLINE);
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Name:");
        stringBuilder.append(Constants.SPACE).append(name).append(Constants.WIN_NEWLINE);
        stringBuilder.append("Description:").append(Constants.SPACE).append(
                description).append(Constants.WIN_NEWLINE);
        stringBuilder.append("Syntax:").append(Constants.WIN_NEWLINE).append(arrayToString(syntax));
        stringBuilder.append("Notes:").append(Constants.WIN_NEWLINE).append(arrayToString(notes));
        stringBuilder.append("Usages:").append(Constants.WIN_NEWLINE).append(arrayToString(usages));
        return stringBuilder.toString();
    }
}
