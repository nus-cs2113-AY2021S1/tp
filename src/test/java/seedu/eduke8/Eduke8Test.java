package seedu.eduke8;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.explanation.Explanation;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.note.Note;
import seedu.eduke8.note.NoteList;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Eduke8Test {
    protected static final String PLACEHOLDER_TOPIC_ONE_DESCRIPTION = "First_Topic";
    protected static final String PLACEHOLDER_TOPIC_TWO_DESCRIPTION = "Second_Topic";
    protected static final String PLACEHOLDER_QUESTION_ONE_DESCRIPTION = "First question description.";
    protected static final String PLACEHOLDER_QUESTION_TWO_DESCRIPTION = "Second question description.";
    protected static final String PLACEHOLDER_QUESTION_THREE_DESCRIPTION = "Third question description.";
    protected static final String PLACEHOLDER_QUESTION_FOUR_DESCRIPTION = "Fourth question description.";
    protected static final String PLACEHOLDER_QUESTION_FIVE_DESCRIPTION = "Fifth question description.";
    protected static final String PLACEHOLDER_OPTION_ONE_DESCRIPTION = "Option one";
    protected static final String PLACEHOLDER_OPTION_TWO_DESCRIPTION = "Option two";
    protected static final String PLACEHOLDER_HINT_DESCRIPTION = "Please check the textbook page 88";
    protected static final String PLACEHOLDER_EXPLANATION_DESCRIPTION =
            "Option A gives the best explanation out of all.";
    protected static final String NONSENSE_DESCRIPTION = "Nonsense";
    protected static final int TIMER = 5;

    protected static final int POINTS_PER_QUESTION = 2;
    protected static final int POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT_SHOWN = 2;
    protected static final int POINTS_FOR_CORRECT_ANSWER_WITH_HINT_SHOWN = 1;
    protected static final int POINTS_FOR_INCORRECT_ANSWER = 0;

    protected static final String DATA_TEST_INVALID_PATH = "data/test/invalid.json";
    protected static final String DATA_TEST_EMPTY_JSON = "data/test/empty.json";
    protected static final String DATA_TEST_WRONG_FORMAT_JSON = "data/test/wrong_format.json";
    protected static final String DATA_TEST_MISSING_KEY_JSON = "data/test/missing_key.json";

    protected static final String NOTE_ONE = "First Note";
    protected static final String NOTE_TWO = "Second Note";
    protected static final String NOTE_THREE = "Third Note";
    protected static final String NOTE_DESCRIPTION = "Test";

    protected Ui ui;

    public Eduke8Test() {
        ui = new Ui();
    }

    // Creates a TopicList object with two topics that have 3 questions each
    protected TopicList createTestTopicList() {
        Topic topic1 = createTestTopic(PLACEHOLDER_TOPIC_ONE_DESCRIPTION);
        Topic topic2 = createTestTopic(PLACEHOLDER_TOPIC_TWO_DESCRIPTION);

        ArrayList<Displayable> topics = new ArrayList<>(
                Arrays.asList(topic1, topic2)
        );

        return new TopicList(topics);
    }

    // Creates a Topic object with a question list of 3 questions
    protected Topic createTestTopic(String description) {
        QuestionList questionList = createTestQuestionList();
        NoteList noteList = createTestNoteList();

        return new Topic(description, questionList, noteList);
    }

    // Creates a QuestionList object with 3 questions
    protected QuestionList createTestQuestionList() {
        Question question1 = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestion(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestion(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);

        ArrayList<Displayable> questions = new ArrayList<>(
                Arrays.asList(question1, question2, question3)
        );

        return new QuestionList(questions);
    }

    // Creates a Question object with 2 options, 1 hint and an explanation.
    protected Question createTestQuestion(String description) {
        OptionList optionList = createTestOptionList();

        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);
        Explanation explanation = new Explanation(PLACEHOLDER_EXPLANATION_DESCRIPTION);

        return new Question(description, optionList, hint, explanation);
    }

    protected OptionList createTestOptionList() {
        Option option1 = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);
        Option option2 = new Option(PLACEHOLDER_OPTION_TWO_DESCRIPTION);

        ArrayList<Displayable> options = new ArrayList<>(
                Arrays.asList(option1, option2)
        );

        return new OptionList(options);
    }

    protected BookmarkList createTestBookmarkList() {
        QuestionList questionList = createTestQuestionList();
        BookmarkList bookmarkList = new BookmarkList();
        for (Displayable question: questionList.getInnerList()) {
            question.markAsShown();
            bookmarkList.add(question);
        }
        return bookmarkList;
    }

    protected NoteList createTestNoteList() {
        Note note1 = new Note(NOTE_ONE, NOTE_DESCRIPTION);
        Note note2 = new Note(NOTE_TWO, NOTE_DESCRIPTION);

        ArrayList<Displayable> noteArrayList = new ArrayList<>(
                Arrays.asList(note1, note2)
        );

        return new NoteList(noteArrayList);
    }
}
