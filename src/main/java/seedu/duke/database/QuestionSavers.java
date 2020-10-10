//package seedu.duke.database;
//
//import seedu.duke.exceptions.QuestionTypeException;
//import seedu.duke.question.FillBlank;
//import seedu.duke.question.Qna;
//import seedu.duke.question.Question;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class QuestionSavers {
//
//    /**
//     * Creates a new file storing FillBlank and Qna questions into the database.
//     * File questionName.txt is created under topic/subTopic.
//     *
//     * @param topic Topic directory.
//     * @param subTopic Sub-directory under topic.
//     * @param questionName Question file name.
//     * @param question An object of Question type.
//     * @throws QuestionTypeException Couldn't recognize question type.
//     */
//    public static void saveFillBlankAndQnaQuestionToNewFile(String topic, String subTopic, String questionName,
//                                                            Question question) throws QuestionTypeException {
//        // TODO: Check validity of topic, subTopic and questionName
//
//        // Creates a new directory for the file
//        String filePathWithoutQuestionName = "\\data\\Quizzes\\" + topic + "\\" + subTopic;
//        File directory = new File(filePathWithoutQuestionName);
//
//        if (directory.mkdirs()) {
//            System.out.println("A directory has just been created");
//        } else {
//            System.out.printf("Opening %s ...\n", filePathWithoutQuestionName);
//        }
//
//        // Creates a new _fb.txt / _qna.txt file
//        String textToWrite;
//        String suffices;
//        if (question instanceof FillBlank) {
//            textToWrite = "string before: " + ((FillBlank) question).getStringBefore() + System.lineSeparator()
//                    + "blank: " + question.getCorrectAnswer() + System.lineSeparator()
//                    + "string after: " + ((FillBlank) question).getStringAfter();
//            suffices = "_fb.txt";
//        } else if (question instanceof Qna) {
//            textToWrite = "question: " + question.getQuestion() + System.lineSeparator()
//                    + "answer: " + question.getCorrectAnswer();
//            suffices = "_qna.txt";
//        } else {
//            throw new QuestionTypeException();
//        }
//        String filePathWithQuestionName = filePathWithoutQuestionName + "\\" + questionName + suffices;
//        File f = new File(filePathWithQuestionName);
//        try {
//            System.out.println("File name: " + f.getName());
//            FileWriter fw = new FileWriter(f.getAbsoluteFile());
//            fw.write(textToWrite);
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("Cannot write to file");
//            e.printStackTrace();
//        }
//
//        System.out.println(f.getAbsolutePath());
//    }
//
//}
