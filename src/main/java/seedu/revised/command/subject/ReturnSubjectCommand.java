package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.card.quiz.TopicQuiz;
import seedu.revised.command.Command;
import seedu.revised.command.task.TaskCommand;
import seedu.revised.command.topic.QuizTopicCommand;
import seedu.revised.command.topic.ReturnTopicCommand;
import seedu.revised.command.topic.TopicCommand;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.card.InvalidTopicCommand;
import seedu.revised.exception.card.NoSubjectException;
import seedu.revised.exception.card.NoTopicException;
import seedu.revised.exception.card.RepeatedSubjectException;
import seedu.revised.exception.task.TaskDeadlineException;
import seedu.revised.exception.task.TaskEventException;
import seedu.revised.exception.task.TaskException;
import seedu.revised.exception.task.TaskTodoException;
import seedu.revised.exception.card.NoFlashCardException;
import seedu.revised.parser.TopicParser;
import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

public class ReturnSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public ReturnSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        //Subject currentSubject = new Subject(message[1]);
        if (message[1].isEmpty()) {
            throw new NoSubjectException();
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(message[1])) {
                return subject;
            }
        }
        throw new NoSubjectException();
    }

    public void goToSubject(Subject subject) {
        Ui.printGoToSubject(subject);
        boolean isSubjectExit = false;
        while (!isSubjectExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = TopicParser.parse(fullCommand);
                if (c instanceof ReturnTopicCommand) {
                    Topic topic = ((ReturnTopicCommand) c).execute(subject);
                    ((ReturnTopicCommand) c).goToTopic(topic, subject);
                } else if (c instanceof QuizTopicCommand) {
                    Topic topic = ((QuizTopicCommand) c).execute(subject);
                    TopicQuiz topicQuiz = new TopicQuiz(topic);
                    topicQuiz.startQuiz(topic.getResults());
                } else if (c instanceof TopicCommand) {
                    ((TopicCommand) c).execute(subject);
                } else if (c instanceof TaskCommand) {
                    TaskList taskList = subject.getTasks();
                    ((TaskCommand) c).execute(taskList);
                }

                isSubjectExit = c.isExit();
                //TODO: implement the storage methods for Topic
                //topics.saveSubject(topicStorage.getFileName());
                //} catch (IOException e) {
                //Ui.printWritingError();

            } catch (NumberFormatException e) {
                Ui.printIndexError();
            } catch (NoSubjectException | NoTopicException e) {
                Ui.printNoTopicError();
            } catch (RepeatedSubjectException e) {
                Ui.printRepeatedTopicError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printOutOfBoundsError();
            } catch (TaskTodoException e) {
                Ui.printTodoError();
            } catch (TaskDeadlineException e) {
                Ui.printDeadlineError();
            } catch (TaskEventException e) {
                Ui.printEventError();
            } catch (TaskException e) {
                Ui.printError();
            } catch (NoFlashCardException e) {
                Ui.printNoFlashcards();
            } catch (InvalidTopicCommand invalidTopicCommand) {
                Ui.printEnterTopic();
            }
        }
        Ui.printBackToSubjects();

    }

    public boolean isExit() {
        return false;
    }
}
