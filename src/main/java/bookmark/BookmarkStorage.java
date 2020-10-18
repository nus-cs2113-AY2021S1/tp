package bookmark;

import flashcard.Flashcard;
import flashcard.FlashcardDeck;
import timetable.DateList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class BookmarkStorage {
    private static File bookmarkFile;
    private final String filePath;

    public BookmarkStorage(String filePath, DateList dateList) {
        String dirPath = "data";
        File fileDir = new File(dirPath);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        this.filePath = filePath;
        bookmarkFile = new File(filePath);
    }

    public void readFromFile(FlashcardDeck flashcardDeck) {
        try {
            Scanner s = new Scanner(bookmarkFile);
            Flashcard flashcard;
            while (s.hasNext()) {
                String[] parseCard = s.nextLine().split("\\|");
                String question = parseCard[0];
                String answer = parseCard[1];
                flashcard = new Flashcard(question, answer);
                flashcardDeck.flashcardDeck.add(flashcard);
            }
        } catch (FileNotFoundException e) {
            System.out.println("This file is not found, creating a new file now!");
        }
    }

    private void loadFile(DateList dateList) {
        try {
            Scanner s = new Scanner(bookmarkFile);
            while (s.hasNext()) {
                String command = s.nextLine();
                TimeTableParser.fileParser(command, dateList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void writeFile(Event event) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (event.eventType.equals(EventType.L)) {
                fw.write("L|" + event.name + "|" + event.linkOrVenue + "|" + event.isOnline
                        + event.getStorageString() + System.lineSeparator());
            } else if (event.eventType.equals(EventType.A)) {
                fw.write("A|" + event.name + "|" + event.linkOrVenue + "|" + event.isOnline
                        + event.getStorageString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

}
