package seedu.duke.database;

public class FileSavers {
    public static void saveQnaToNewFile(String topic, String subTopic, String questionName, Qna question) {
        // Creates a new directory for the file
        String filePathWithoutQuestionName = "data\\Quizzes\\" + topic + "\\" + subTopic;
        File directory = new File(filePathWithoutQuestionName);

        if (directory.mkdirs()) {
            System.out.println("A directory has just been created");
        } else {
            System.out.printf("Opening %s ...", filePathWithoutQuestionName);
        }

        // Creates a new _qna.txt file
        String filePathWithQuestionName = filePathWithoutQuestionName + "\\" + questionName + "_qna.txt";
        String textToWrite = "question: " + question.getQuestion() + System.lineSeparator() +
                "answer: " + question.getCorrectAnswer();
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
