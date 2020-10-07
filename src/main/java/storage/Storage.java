package storage;

import commands.AddCommand;
import commands.Command;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import manager.card.Card;
import manager.chapter.CardList;
import parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATHWAY = "questions.txt";   // file pathway

    public static final String QUESTION_ANSWER_PREFIX = " \\| ";
    public static final String QUESTION_PREFIX = "[Q]";
    public static final String ANSWER_PREFIX = "[A]";

    public static void getFileContents(CardList cards) {
        try {
            File f = new File(FILE_PATHWAY);    // create a File for the given file path
            Scanner s = new Scanner(f);     // create a Scanner using the File as the source
            while (s.hasNext()) {
                String fileCommand = s.nextLine();
                String[] args = fileCommand.split(QUESTION_ANSWER_PREFIX, 2);
                String question = Parser.parseQuestioninFile(args[0]);
                String answer = Parser.parseAnswerinFile(args[1]);
                Card card = new Card(question, answer);
                cards.addCard(card);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (InvalidFileFormatException e) {
            System.out.println("The format of some commands in the file is invalid");
        }
    }

    public static void writeToFile(CardList cards) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATHWAY);
        for (int i = 0; i < cards.getCardCount(); i++) {
            fw.write(cards.getCard(i).toString() + "\n");
        }
        fw.close();
    }
}
