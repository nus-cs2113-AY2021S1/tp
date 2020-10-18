package bookmark;

import flashcard.Flashcard;
import flashcard.FlashcardDeck;
import timetable.DateList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class BookmarkStorage {
    private static File bookmarkFile;
    private final String filePath;

    public BookmarkStorage(String filePath) {
        String dirPath = "data";
        File fileDir = new File(dirPath);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        this.filePath = filePath;
        bookmarkFile = new File(filePath);
    }

    public ArrayList<BookmarkCategory> loadFile() {
        try {
            Scanner s = new Scanner(bookmarkFile);
            ArrayList<BookmarkCategory> bookmarkCategories = new ArrayList<>();
            while (s.hasNext()) {
                String[] parseCard = s.nextLine().split("\\|");
                String category = parseCard[0];
                String links = parseCard[1];
                if ( parseCard.equals("NUS")) {
                    bookmarkCategories.add(new NusCategory());
                    bookmarkCategories.get(0).addLink(links);
                } else if (category.equals("ZOOM")){
                    bookmarkCategories.add(new ZoomCategory());
                    bookmarkCategories.get(1).addLink(links);
                }
            }
            return bookmarkCategories;
        } catch (FileNotFoundException e) {
            System.out.println("This file is not found, creating a new file now!");
            return null;
        }
    }

    public void writeFile(ArrayList<BookmarkCategory> bookmarkCategories) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (bookmarkCategories.get(1).getName().equals("Zoom")) {
                fw.write("ZOOM"  + "|" + bookmarkCategories.get(1).getLinks());
            } else if (bookmarkCategories.get(1).getName().equals("Nus")) {
                fw.write("NUS"  + "|" + bookmarkCategories.get(0).getLinks());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

}
