package seedu.revised.ui;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.card.Topic;
import seedu.revised.list.TopicList;

import seedu.revised.card.Flashcard;

import seedu.revised.card.quizcard.Result;

import seedu.revised.card.taskcard.Task;
import seedu.revised.list.TaskList;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String LONG_DIVIDER = "_______________________________________________________________________"
            + "___________________________________________________";
    public static final String LOGO =
            "                                    ___________\n"
                    + "                                    |  __ |  _ \\\n"
                    + " ____  ______      _____   ________ |  |__| | | |\n"
                    + "|  __|/ __ \\ \\    / /| |  /  _____/ |   __| | | |\n"
                    + "| |  |  __/ \\ \\__/ / | | /_____  /  |  |__| |_| |\n"
                    + "| |   \\___|  \\____/  |_|/_______/   |_____|_____/\n";
    public static final String HELP_MESSAGE = "Type help for all available commands";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String OOPS_PREFIX = " OOPS!!!";
    public static final String OOPS_SUFFIX = ":-(";

    public static final String FAILED_PARSE_EXCEPTION = OOPS_PREFIX + " I'm sorry, but I don't know what that means "
            + OOPS_SUFFIX;
    public static final String NO_SUBJECT_EXCEPTION = "Please enter an existing subject.";
    public static final String SUBJECT_NOT_FOUND_EXCEPTION = OOPS_PREFIX + " I'm sorry, but I can't find that subject "
            + OOPS_SUFFIX;
    public static final String REPEATED_SUBJECT_EXCEPTION = OOPS_PREFIX + " I'm sorry, but subject is already "
            + "in the list " + OOPS_SUFFIX;
    public static final String REPEATED_TOPIC_EXCEPTION = OOPS_PREFIX + " I'm sorry, but topic is already in the list "
            + OOPS_SUFFIX;
    public static final String REPEATED_FLASHCARD_EXCEPTION = OOPS_PREFIX + " I'm sorry, but flashcard is already "
            + "in the list " + OOPS_SUFFIX;
    public static final String TODO_EXCEPTION = OOPS_PREFIX + " The format should be: todo <taskname>";
    public static final String DEADLINE_EXCEPTION = OOPS_PREFIX + " The format should be: deadline <taskname> /by "
            + "<datetime>";
    public static final String EVENT_EXCEPTION = OOPS_PREFIX + " The format should be: event <taskname> /at <datetime>";
    public static final String SUBJECT_INDEX_FORMAT_EXCEPTION = OOPS_PREFIX
            + " The index number must be a positive integer \n that belongs to an existing subject.";
    public static final String TOPIC_INDEX_FORMAT_EXCEPTION = OOPS_PREFIX
            + " The index number must be a positive integer \n that belongs to an existing task or topic.";
    public static final String FLASHCARD_INDEX_FORMAT_EXCEPTION = OOPS_PREFIX
            + " The index number must be a positive integer \n that belongs to an existing flashcard.";
    public static final String NO_FLASHCARD_EXCEPTION = "There are no flashcards present yet!";
    public static final String NO_TOPIC_YET_EXCEPTION = "There are no topics present yet!";
    public static final String NO_TOPIC_EXCEPTION = "Please enter an existing topic.";
    public static final String TOPIC_NOT_FOUND_EXCEPTION = OOPS_PREFIX + " I'm sorry, but I can't find that topic "
            + OOPS_SUFFIX;
    public static final String INVALID_SUBJECT_EXCEPTION = "Please enter a subject!";
    public static final String INVALID_TOPIC_EXCEPTION = "Please enter a topic!";
    public static final String INVALID_FLASHCARD_EXCEPTION = "Please enter a valid flashcard!";
    public static final String INVALID_DATETIME_EXCEPTION = "Enter date and time in the following format: "
            + "HH:MM DD-MM-YYYY";
    public static final String DATA_LOADING_EXCEPTION = "Error loading saved data from the disk, "
            + "proceeding with clean state.";
    public static final String WRITING_EXCEPTION = "Writing to file failed.";

    private static final Scanner scan = new Scanner(System.in);

    public static String readCommand() {
        return scan.nextLine();
    }

    public static void printStart(SubjectList subjects) {

        System.out.println("Hello from");
        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm revisED");
        System.out.println(DIVIDER);

        if (subjects.getList().size() != 0) {
            printUpcomingTasks(subjects);
        }

        System.out.println(DIVIDER);
        System.out.println("Alright, What can I do for you?");
        System.out.println(DIVIDER);
        printHelpMessage();
    }

    private static void printHelpMessage() {
        System.out.println(DIVIDER);
        System.out.println(HELP_MESSAGE);
        System.out.println(DIVIDER);
    }

    public static void printSubjectList(List<Subject> list) {
        System.out.println(DIVIDER);

        if (list.size() == 0) {
            System.out.println("There are no subjects in your list!");
        } else {
            System.out.println("Here are the subject(s) in your list:");
            int num = 1;
            for (Subject item : list) {
                System.out.println(num + ". " + item);
                num++;
            }
        }

        System.out.println(DIVIDER);
    }

    public static void printTaskList(Subject subject) {
        TaskList taskList = subject.getTasks();
        List<Task> list = taskList.getList();

        if (list.size() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks(s) under " + subject.getTitle() + ": ");
            int index = 1;
            for (Task t : list) {
                assert index > 0;
                System.out.println(index + ". " + t);
                index++;
            }
        }
        System.out.println(DIVIDER);
    }

    public static void printDone(Task task) {
        System.out.println(DIVIDER);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(DIVIDER);
    }

    public static void printDelete(Task task, int total) {
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + total + (total == 1 ? " task " : " tasks ") + "in the list.");
        System.out.println(DIVIDER);
    }

    public static void printSubjectDelete(Subject subject, int total) {
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this subject:");
        System.out.println("   " + subject);
        System.out.println(" Now you have " + total + (total == 1 ? " subject " : " subjects ") + "in the list.");
        System.out.println(DIVIDER);
    }

    public static void printBye() {
        System.out.println(DIVIDER);
        System.out.println(BYE_MESSAGE);
        System.out.println(DIVIDER);
    }

    public static void printError(Exception err) {
        System.out.println(DIVIDER);
        System.out.println(err.getMessage());
        System.out.println(DIVIDER);
    }

    public static void printErrorMsg(String msg) {
        System.out.println(DIVIDER);
        System.out.println(msg);
        System.out.println(DIVIDER);
    }

    public static void printFindTask(TaskList taskList, String find) {
        boolean isTaskFound = false;
        for (Task task : taskList.getList()) {
            if (task.toString().contains(find)) {
                Ui.printTaskMatch(isTaskFound);
                System.out.println(task);
                isTaskFound = true;
            }
        }
        if (!isTaskFound) {
            System.out.println(" Sorry! I could not find any tasks with " + find + " in the list.");
        }
        System.out.println(DIVIDER);
    }

    public static void printFindSubject(SubjectList subjectList, String find) {
        int subjectPresent = 0;
        System.out.println(DIVIDER);
        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(find)) {
                Ui.printSubjectMatch(subjectPresent);
                System.out.println("  " + subject);
                subjectPresent = 1;
            }
        }
        assert (subjectPresent == 1 || subjectPresent == 0);
        if (subjectPresent == 0) {
            System.out.println(" Sorry! I could not find any subject with " + find + " in the list.");
        }
        System.out.println(DIVIDER);
    }

    public static void printSubjectMatch(int subjectPresent) {
        if (subjectPresent == 0) {
            System.out.println(" Here are the matching subject(s) in your list:");
        }
    }

    public static void printTaskMatch(boolean isTaskFound) {
        if (!isTaskFound) {
            System.out.println(" Here are the matching task(s) in your list:");
        }
    }

    public static void printGoToSubject(Subject subject) {
        System.out.println(DIVIDER);
        System.out.println("Entering the Subject Level for the " + subject.toString() + " subject.");
        System.out.println(DIVIDER);

        printHelpMessage();
    }

    public static void printBackToSubjects() {
        System.out.println(DIVIDER);
        System.out.println("Going back to the main level.");
        System.out.println(DIVIDER);

    }

    public static void printTopicList(Subject subject) {
        System.out.println(DIVIDER);

        TopicList topicList = subject.getTopics();
        List<Topic> list = topicList.getList();

        if (list.size() == 0) {
            System.out.println("There are no topics in your list!");
        } else {
            System.out.println("Here are the topic(s) under " + subject.getTitle() + ": ");
            int index = 1;
            for (Topic t : list) {
                assert index > 0;
                System.out.println(index + ". " + t);
                index++;
            }
        }
        System.out.println(DIVIDER);
    }

    public static void printTopicMatch(boolean isTopicFound) {
        if (!isTopicFound) {
            System.out.println("Here are the matching topic(s) in your list:");
        }
    }


    public static void printFindTopic(TopicList topicList, String query) {
        boolean isTopicPresent = false;
        System.out.println(DIVIDER);
        for (Topic topic : topicList.getList()) {
            if (topic.toString().contains(query)) {
                Ui.printTopicMatch(isTopicPresent);
                System.out.println(topic);
                isTopicPresent = true;
            }
        }
        if (!isTopicPresent) {
            System.out.println("Sorry! I could not find any topics with " + query + " in the list.");
        }
        System.out.println(DIVIDER);
    }

    public static void printGoToTopic(Topic topic) {
        System.out.println(DIVIDER);
        System.out.println("Entering the topic level for the " + topic.getTitle() + " topic.");
        System.out.println(DIVIDER);

        printHelpMessage();
    }


    public static void printTopicDelete(Topic topic, int total) {
        System.out.println(DIVIDER);
        System.out.println("Noted. I've removed this topic:");
        System.out.println("   " + topic);
        System.out.println(" Now you have " + total + (total == 1 ? " topic " : " topics " + "in the list."));
        System.out.println(DIVIDER);
    }


    public static void printFlashcardDelete(Flashcard flashcard, int total) {
        System.out.println(DIVIDER);
        System.out.println("Noted. I've removed this flashcard:");
        System.out.println("  Q: " + flashcard.getQuestion() + "\n  A: " + flashcard.getAnswer());
        System.out.println("Now you have " + total + (total == 1 ? " flashcard " : " flashcards " + "in the list."));
        System.out.println(DIVIDER);
    }

    public static void printFlashcardList(Topic topic) {
        System.out.println(DIVIDER);
        List<Flashcard> list = topic.getFlashcards();

        if (list.size() == 0) {
            System.out.println("There are no flashcards in your list!");
        } else {
            System.out.println("Here are the flashcard(s) under " + topic.getTitle() + ": ");
            int index = 1;
            for (Flashcard t : list) {
                assert index > 0;
                System.out.println(index + ". " + t);
                index++;
            }
        }
        System.out.println(DIVIDER);
    }

    public static void printScore(Result result) {
        System.out.println(DIVIDER);
        System.out.println("Result:" + result);
        System.out.println(DIVIDER);
    }

    public static void printIncorrectAnswers(List<String> incorrectAnswers) {
        System.out.println("Here are the questions which you got wrong.");
        System.out.println(DIVIDER);

        for (int i = 0; i < incorrectAnswers.size(); i += 3) {
            System.out.println("Question:" + incorrectAnswers.get(i));
            System.out.println("Correct Answer: " + incorrectAnswers.get(i + 1));
            System.out.println("Your Answer: " + incorrectAnswers.get(i + 2));
            System.out.println(DIVIDER);
        }

    }

    public static void printStartSubjectQuiz(Subject subject) {
        System.out.println(DIVIDER);
        System.out.println("You are about to begin the quiz for " + subject + ".");
        System.out.println(DIVIDER);
    }

    public static void printStartTopicQuiz(Topic topic) {
        System.out.println(DIVIDER);
        System.out.println("You are about to begin the quiz for " + topic + ".");
        System.out.println(DIVIDER);
    }

    public static void printStopQuiz() {
        System.out.println(DIVIDER);
        System.out.println("The quiz has been stopped!");
    }

    public static void printEndQuiz() {
        System.out.println(DIVIDER);
        System.out.println("The quiz has ended");
    }

    public static void printSubjectResults(Subject subject) {
        System.out.println(DIVIDER);

        int index = 1;
        if (subject.getResults().getList().size() == 0) {
            System.out.println("You do not have any results!");
        } else {
            for (Result result : subject.getResults().getList()) {
                assert index > 0;
                System.out.println("Quiz " + index + ": " + result);
                index++;
            }
        }

        System.out.println(DIVIDER);
    }

    public static void printSubject(Subject subject, SubjectList subjectList) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this subject:");
        System.out.println("  " + subject);
        System.out.println("Now you have " + subjectList.getList().size() + (subjectList.getList().size() == 1
                ? " subject " : " subjects ") + "in the list.");
        System.out.println(DIVIDER);
    }

    public static void printTopic(Topic topic, TopicList topicList) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this topic:");
        System.out.println("  " + topic);
        System.out.println("Now you have " + topicList.getList().size() + (topicList.getList().size() == 1
                ? " topic " : " topics ") + "in the list.");
        System.out.println(DIVIDER);
    }

    public static void printFlashcard(Flashcard flashcard, List<Flashcard> flashcards) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this flashcard:");
        System.out.println("  Q: " + flashcard.getQuestion() + "\n  A: " + flashcard.getAnswer());
        System.out.println("Now you have " + flashcards.size() + (flashcards.size() == 1
                ? " flashcard " : " flashcards ") + "in the list.");
        System.out.println(DIVIDER);
    }

    public static void printTopicResults(Topic topic) {
        System.out.println(DIVIDER);

        int index = 1;
        if (topic.getResults().getList().size() == 0) {
            System.out.println("You do not have any results!");
        } else {
            for (Result result : topic.getResults().getList()) {
                assert index > 0;
                System.out.println("Quiz " + index + ": " + result);
                index++;

            }
        }

        System.out.println(DIVIDER);
    }

    public static void printQuestion(String question) {
        System.out.println("Question: " + question);
    }

    public static void printBackToTopicsAndTasks() {
        System.out.println(DIVIDER);
        System.out.println("Going back to the subject level.");
        System.out.println(DIVIDER);
    }

    public static void printTask(Task task, TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.getList().size() + (taskList.getList().size() == 1
                ? " task " : " tasks ") + "in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints all the upcoming tasks that are due within a week.
     *
     * @param subjects The list of subjects present in the application
     */
    public static void printUpcomingTasks(SubjectList subjects) {
        LocalDateTime nextWeek = LocalDateTime.now().plusDays(7);
        List<Task> taskList = new ArrayList<>();
        int firstTask = 0;
        for (Subject subject : subjects.getList()) {

            int index = 1;

            for (Task task : subject.getTasks().getList()) {

                if (task.getDateTime() != null) {
                    if (task.getDateTime().isBefore(nextWeek) & task.getDateTime().isAfter(LocalDateTime.now())
                            & ! task.getIsDone()) {
                        taskList.add(task);
                        if (firstTask == 0) {
                            System.out.println("Here are the upcoming tasks for next week!");
                            firstTask = 1;
                        }

                        if (index == 1) {
                            System.out.println(subject);
                        }
                        System.out.println(index + ". " + task);
                        index = index + 1;
                    }

                }
            }

            taskList.clear();
        }
    }

    public static String fileSyntaxErrorMsg(String dataType, String fileLocation) {
        return String.format("Error reading the %s data under %s. Make sure the syntax is correct "
                + "if you changed it manually. Proceeding with empty %ss.", dataType, fileLocation, dataType);
    }

    public static void printExportSuccessful(File exportFile) {
        System.out.println(DIVIDER);
        System.out.println("Your data has been successfully exported to " + exportFile.getAbsolutePath() + ".");
        System.out.println(DIVIDER);
    }

    public static void printSubjectHelp() {
        System.out.println(LONG_DIVIDER);
        System.out.println(
                "help:          shows the list of commands available at the main level\n"
                        + "add abc:       adds a subject called 'abc'\n"
                        + "find abc:      finds all subjects containing the letters abc\n"
                        + "list:          shows the list of all subjects\n"
                        + "list all:      shows the tree of all subjects, topics, tasks and flashcards\n"
                        + "delete 1:      deletes the 1st subject in the list.\n"
                        + "subject abc:   enters the subject called abc, now you can create, find, list, delete "
                        + "and enter the topics of subject abc\n"
                        + "quiz abc:      starts a quiz for all the flashcards present in all topics of subject abc\n"
                        + "               answer the questions of the current flashcards to test your knowledge\n"
                        + "results abc:   gives you the results of all attempted quizzes for abc subject\n"
                        + "export:        exports all the data to a JSON file\n"
                        + "bye:           exits the application"
        );
        System.out.println(LONG_DIVIDER);

    }

    public static void printTopicHelp() {
        System.out.println(LONG_DIVIDER);
        System.out.println(
                "help:                              shows the list of commands available at the subject level\n"
                        + "add abc:                           adds a topic called 'abc' in the current subject\n"
                        + "todo abc:                          adds a todo type task with the description 'abc'\n"
                        + "deadline abc /by 12:00 13-11-2020: adds a deadline type task with description 'abc' with "
                        + "date/time of deadline\n"
                        + "                                   as 12:00 AM 13 Nov 2020\n"
                        + "event abc /at 01:00 21-11-2020:    adds an event type task with description 'abc' "
                        + "with date/time of event\n"
                        + "                                   as 1:00 AM 21 Nov 2020\n"
                        + "find abc:                          finds all topics and tasks containing 'abc' in the "
                        + "current subject\n"
                        + "list:                              shows the list of all topics and tasks in the current "
                        + "subject\n"
                        + "list all:                          shows the tree of all subjects, topics, tasks and "
                        + "flashcards\n"
                        + "delete topic 1:                    deletes the 1st topic in the list of topics.\n"
                        + "delete task 1:                     deletes the 1st task in the list of tasks.\n"
                        + "done 1:                            marks the 1st task in the list of tasks as done\n"
                        + "topic abc:                         enters the topic abc, now you can add, find, list "
                        + "and delete flashcards of topic abc\n"
                        + "quiz abc:                          starts a quiz for all the flashcards of the topic abc,\n"
                        + "                                   answer the questions of the prompted flashcards "
                        + "to test your knowledge\n"
                        + "results abc:                       gives you the results of all attempted quizzes for "
                        + "abc topic\n"
                        + "exit:                              exits the subject to return to the main screen, "
                        + "where you can work with subjects"
        );
        System.out.println(LONG_DIVIDER);
    }

    public static void printFlashcardHelp() {
        System.out.println(LONG_DIVIDER);
        System.out.println(
                "help:              shows the list of commands available at the topic level\n"
                        + "add abc; def:      adds a flashcard with question 'abc' and answer 'def' "
                        + "in the current topic\n"
                        + "list:              shows the list of all flashcards in the current topic\n"
                        + "list all:          shows the tree of all subjects, topics, tasks and flashcards\n"
                        + "delete 1:          deletes the 1st flashcard in the list\n"
                        + "exit:              exits the topic to return to the subject level, "
                        + "where you can work with tasks and topics"
        );
        System.out.println(LONG_DIVIDER);
    }

    /**
     * Prints a tree of all subjects, topics, tasks, and flashcards.
     * Tells user which subject you are currently accessing.
     *
     * @param subjects      the list of all subjects to be printed
     * @param activeSubject Subject that the user is currently accessing. null if user is not accessing a subject
     * @param activeTopic   Topic that the user is currently accessing. null if user is not accessing a topic
     */
    public static void printAll(List<Subject> subjects, Subject activeSubject, Topic activeTopic) {
        assert !(activeSubject != null && activeTopic != null);
        System.out.println(DIVIDER);
        System.out.println("Here's a list of all items:");
        if (activeSubject == null && activeTopic == null) {
            printActiveTreeMessage();
        }

        int i = 1;
        for (Subject subject : subjects) {
            boolean isLastSubject = i == subjects.size();
            System.out.print((isLastSubject ? "└─ " : "├─ ")
                    + (i++) + ". " + subject.toString() + " ");
            if (activeSubject != null && subject == activeSubject) {
                printActiveTreeMessage();
            } else {
                System.out.println();
            }
            printTreeUnderSubject(isLastSubject, subject, activeTopic);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints a subtree of all items under a subject.
     * Prints where in the tree the user is currently accessing.
     *
     * @param isLastSubject Whether the subject containing the topics is the last subject in the list
     * @param subject       The subject containing all the topics to be printed
     * @param activeTopic   Topic that the user is currently accessingaccessing. null if user is not accessing a topic
     */
    public static void printTreeUnderSubject(boolean isLastSubject, Subject subject, Topic activeTopic) {
        // Print topics
        int i = 1;
        TopicList topicList = subject.getTopics();
        List<Topic> topics = topicList.getList();
        String subjectTreeSymbol = (isLastSubject ? " " : "│");

        if (topics.size() == 0) {
            System.out.println(subjectTreeSymbol + "  ├─ No topics");
        } else {
            System.out.println(subjectTreeSymbol + "  │  Topics");
        }
        for (Topic topic : topics) {
            printTreeTopic(topic, activeTopic, subjectTreeSymbol, i++);
        }

        // Print tasks
        i = 1;
        TaskList taskList = subject.getTasks();
        List<Task> tasks = taskList.getList();

        if (tasks.size() == 0) {
            System.out.println(subjectTreeSymbol + "  └─ No tasks");
        } else {
            System.out.println(subjectTreeSymbol + "  │  Tasks");
        }
        for (Task task : tasks) {
            printTreeTask(task, subjectTreeSymbol, tasks.size(), i++);
        }
    }

    /**
     * Prints the subtree of a topic.
     *
     * @param topic The topic to be printed
     * @param activeTopic The topic the user is accessing. Null if user is accessing a topic
     * @param subjectTreeSymbol Appropriate symbol to print under subject column
     * @param index Index number of topic
     */
    public static void printTreeTopic(Topic topic, Topic activeTopic, String subjectTreeSymbol, int index) {
        System.out.print(subjectTreeSymbol + "  ├─ "
                + index + ". " + topic.toString() + " ");
        if (activeTopic != null && topic == activeTopic) {
            printActiveTreeMessage();
        } else {
            System.out.println();
        }
        int numberOfFlashcards = topic.getFlashcards().size();
        if (numberOfFlashcards != 0) {
            System.out.println(subjectTreeSymbol
                    + "  │  └─ "
                    + numberOfFlashcards
                    + (numberOfFlashcards == 1 ? " Flashcard" : " Flashcards"));
        }
    }

    /**
     * Prints the subtree of a task.
     *
     * @param task The task to be printed
     * @param subjectTreeSymbol Appropriate symbol to print under subject column
     * @param tasksSize Size of the list of tasks
     * @param index Index number of task
     */
    public static void printTreeTask(Task task, String subjectTreeSymbol, int tasksSize, int index) {
        System.out.println(subjectTreeSymbol
                + "  "
                + ((index == tasksSize) ? "└─ " : "├─ ")
                + index + ". " + task.toString());
    }

    public static void printActiveTreeMessage() {
        System.out.println("(You are currently here)");
    }
}


