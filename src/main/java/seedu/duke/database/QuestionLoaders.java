package seedu.duke.database;

import seedu.duke.exceptions.FilePathInvalidException;
import seedu.duke.question.FillBlank;
import seedu.duke.question.Qna;
import seedu.duke.question.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionLoaders {

    /**
     * Gets details of FillBlank and Qna questions in the text file.
     *
     * @param directoryPath Directory containing the question text file.
     * @param fileName The name of the file in the directory.
     * @return Question object.
     * @throws FilePathInvalidException File path is incorrectly formatted.
     */
    public static Question loadFillBlankAndQnaQuestion(String directoryPath, String fileName)
            throws FilePathInvalidException{

        // TODO: Check validity of directoryPath and fileName

        File directory = new File(directoryPath);
        File f = new File(directoryPath + "\\" + fileName);

        if (!directory.exists()) {
            throw new FilePathInvalidException();
        }
        
        Question question = null;

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            question = getFillBlankAndQnaFromFile(br, fileName);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return question;
    }

    private static Question getFillBlankAndQnaFromFile(BufferedReader br, String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }

        if (fileName.contains("_fb.txt")) {
            filterFillBlankQuestionLines(lines);
            return new FillBlank(lines.get(0), lines.get(1), lines.get(2));
        } else { // if (fileName.contains("_qna.txt")
            filterQnaQuestionLines(lines);
            return new Qna(lines.get(0), lines.get(1));
        }
    }

    private static void filterQnaQuestionLines(ArrayList<String> lines) {
        for (int i = 0; i < 2; i++) {
            String cutString;
            switch(i) {
            case 0:
                cutString = lines.get(i).replace("question: ", "");
                break;
            case 1:
                cutString = lines.get(i).replace("answer: ", "");
                break;
            default:
                cutString = "ERROR"; // throw error
                System.out.println("Error");
            }
            lines.set(i, cutString);
        }
    }

    private static void filterFillBlankQuestionLines(ArrayList<String> lines) {
        for (int i = 0; i < 3; i++) {
            String cutString;
            switch(i) {
            case 0:
                cutString = lines.get(i).replace("string before: ", "");
                break;
            case 1:
                cutString = lines.get(i).replace("blank: ", "");
                break;
            case 2:
                cutString = lines.get(i).replace("string after: ", "");
                break;
            default:
                cutString = "ERROR"; // throw error
                System.out.println("Error");
            }
            lines.set(i, cutString);
        }
    }
}
