package flashcard;

import studyit.StudyItLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlashcardStorage {

    public ArrayList<Flashcard> flashcardDeck;
    private final File file;
    private final String dirPath = "data";
    private final String filePath;

    public FlashcardStorage(String filePath) throws IOException {
        // Creates the data directory
        File fileDir = new File(dirPath);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        file = new File(filePath);
        this.filePath = filePath;
    }

    public void writeToFile(FlashcardDeck flashcardDeck) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Flashcard flashcard : flashcardDeck.flashcardDeck) {
                fw.write(flashcard.writeToFile());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong!" + e.getMessage());
            StudyItLog.logger.warning("Problem writing to flashcard storage file\n" + e);
        }
    }

    public void readFromFile(FlashcardDeck flashcardDeck) throws IOException {
        try {
            Scanner s = new Scanner(file);
            Flashcard flashcard;
            while (s.hasNext()) {
                String[] parseCard = s.nextLine().split("\\|");
                String question = parseCard[0];
                String answer = parseCard[1];
                flashcard = new Flashcard(question, answer);
                flashcardDeck.flashcardDeck.add(flashcard);
            }
        } catch (FileNotFoundException e) {
            System.out.println("data/flashcard.txt is not found, creating a new file now!");

            file.createNewFile();

            StudyItLog.logger.info(e + "\nflashcard storage file created");
        }
    }

}
