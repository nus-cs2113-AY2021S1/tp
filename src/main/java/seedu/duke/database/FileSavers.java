package seedu.duke.database;

import seedu.duke.question.FillBlank;
import seedu.duke.question.Qna;
import seedu.duke.question.Question;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSavers {

    public static void saveFillBlankAndQnaQuestionToNewFile(String topic, String subTopic, String questionName,
                                                            Question question) {
        // Creates a new directory for the file
        String filePathWithoutQuestionName = "data\\Quizzes\\" + topic + "\\" + subTopic;
        File directory = new File(filePathWithoutQuestionName);

        if (directory.mkdirs()) {
                System.out.println("A directory has just been created");
        } else {
            System.out.printf("Opening %s ...", filePathWithoutQuestionName);
        }

        // Creates a new _fb.txt / _qna.txt file
        String filePathWithQuestionName = filePathWithoutQuestionName + "\\" + questionName + "_fb.txt";
        String textToWrite;
        if (question instanceof FillBlank) {
            textToWrite = "string before: " + ((FillBlank) question).getStringBefore() + System.lineSeparator() +
                    "blank: " + question.getCorrectAnswer() + System.lineSeparator() +
                    "string after: " + ((FillBlank) question).getStringAfter();
        } else { //  if (question instanceof Qna)
            textToWrite = "question: " + question.getQuestion() + System.lineSeparator() +
                    "answer: " + question.getCorrectAnswer();
        }

        File f = new File(filePathWithQuestionName);
        try {
            System.out.println("File name: " + f.getName());
            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

}
