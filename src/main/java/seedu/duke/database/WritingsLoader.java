package seedu.duke.database;

import java.io.File;
import java.io.FileNotFoundException;
import seedu.duke.exceptions.NotEnoughWritingComponentException;
import seedu.duke.writing.Essay;
import seedu.duke.writing.Poem;
import seedu.duke.writing.WritingList;
import seedu.duke.logs.Logging;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.constants.DataFileConvention.ESSAY;
import static seedu.duke.constants.DataFileConvention.NUMBER_OF_WRITING_COMPONENT;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_AUTHOR;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_DATE;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_ID;
import static seedu.duke.constants.DataFileConvention.POEM;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_REMINDER_DATE;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_TITLE;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_TOPIC;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_TYPE;
import static seedu.duke.constants.DataFileConvention.WRITING_COMPONENT_DIVIDER;
import static seedu.duke.constants.DataFileConvention.WRITING_COMPONENT_MARK;
import static seedu.duke.constants.FilePaths.WRITING_FILE_PATH;
import static seedu.duke.database.FileFunctions.autoCreateNewFile;

public class WritingsLoader {

    /**This function reads data stored in data file and coverts it into ArrayList structure.
     *
     * @param f data file storing the information of the tasks
     * @param savedWritings List of writings available in the txt database file
     * @return total writings detected in the data file
     * @throws FileNotFoundException when the file is not found
     * @throws NotEnoughWritingComponentException when the format of minimum
     */

    private static int convertWritingFile(File f, WritingList savedWritings)
            throws FileNotFoundException, NotEnoughWritingComponentException {
        //Count the number of asterisks in the datafile -> number of components in the Writing class
        int countContent = 0;
        int countWritings = 0;
        Scanner s = new Scanner(f);
        int id = 0;
        String author = "";
        String type = "";
        String title = "";
        String topic = "";
        String date = "";
        LocalDate reminderDate = null;
        //Initializing the content
        String content;

        try {
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                //Reset the content to blank
                content = "";
                while (!currentLine.equals(WRITING_COMPONENT_DIVIDER)) {
                    while (currentLine.startsWith(WRITING_COMPONENT_MARK)) {
                        countContent++;
                        if (countContent == 1) {
                            id = Integer.parseInt(currentLine.substring(POSITION_OF_ID).trim());
                        } else if (countContent == 2) {
                            author = currentLine.substring(POSITION_OF_AUTHOR).trim();
                        } else if (countContent == 3) {
                            type = currentLine.substring(POSITION_OF_TYPE).trim();
                        } else if (countContent == 4) {
                            title = currentLine.substring(POSITION_OF_TITLE).trim();
                        } else if (countContent == 5) {
                            topic = currentLine.substring(POSITION_OF_TOPIC).trim();
                        } else if (countContent == 6) {
                            date = currentLine.substring(POSITION_OF_DATE).trim();
                        } else if (countContent == 7) {
                            reminderDate = LocalDate.parse(currentLine.substring(POSITION_OF_REMINDER_DATE).trim(),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } else if (countContent == 8) {
                            break;
                        }
                        currentLine = s.nextLine();
                    }
                    //Writing text to the writing object
                    currentLine = s.nextLine();

                    //prevent adding the WRITING_COMPONENT_DIVIDER to the content of the writing
                    if (!currentLine.equals(WRITING_COMPONENT_DIVIDER)) {
                        content = content.concat(currentLine + "\n");
                    }
                }
                countWritings++;
                if (type.equals(POEM)) {
                    savedWritings.add(new Poem(title, date, topic, content, author, id, reminderDate));
                } else if (type.equals(ESSAY)) {
                    savedWritings.add(new Essay(title, date, topic, content, author, id, reminderDate));
                }
                if (countContent != NUMBER_OF_WRITING_COMPONENT) {
                    throw new NotEnoughWritingComponentException();
                } else {
                    countContent = 0; //Reload for reading the data of the next object in the txt file
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("This is not valid!");
        }
        return countWritings;
    }

    /**Check if the data file exists or not, creates "writings.txt" in "data" directory if not
     *  @param f file to be processed
     * @param savedWritings writings being stored
     */
    public static void convertFromFile(File f, WritingList savedWritings) {
        File folder = new File("data");
        boolean bool = folder.mkdirs(); // If folder doesn't exist, then create it

        try {
            convertWritingFile(f, savedWritings);
        } catch (FileNotFoundException e) {
            //System.out.println("File not found");
            try {
                f.createNewFile();
                System.out.println("duke.txt aka the data file created in the data folder!");
            } catch (IOException err) {
                err.printStackTrace();
            }
        } catch (NotEnoughWritingComponentException e) {
            System.out.println("There is something wrong with the data file");
        }
    }

    /**Write data from ArrayList structure to text form in directory "data/duke.txt"
     *
     * @param f file to be processed
     * @throws IOException IO error
     */
    private static void recordFile(File f, WritingList savedWritings) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < WritingList.getCountWritings(); i++) {
            fw.write("*id: " + savedWritings.get(i).getId() + "\n"
                    + "*Author: " + savedWritings.get(i).getAuthor().getName() + "\n"
                    + "*Type: " + savedWritings.get(i).getType() + "\n"
                    + "*Title: " + savedWritings.get(i).getTitle() + "\n"
                    + "*Topic: " + savedWritings.get(i).getTopic() + "\n"
                    + "*Date: " + savedWritings.get(i).getDate() + "\n"
                    + "*Reminder: " + savedWritings.get(i).getReminderDateString() + "\n"
                    + "*Content: \n" + savedWritings.get(i).getContent()
                    + WRITING_COMPONENT_DIVIDER + "\n");
        }
        fw.close();
    }

    /**Record the file or print error message.
     *
     * @param f data file to be processed
     * @param savedWritings saved Writings of the current state of Writing List
     */
    public static void recordListToFile(File f, WritingList savedWritings) {
        try {
            recordFile(f, savedWritings);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /** Allow the main program to access the file processing.
     *
     * @param savedWritings list of writing called in the main function
     */
    public static void loadWritings(WritingList savedWritings) {
        Logging log = new Logging();
        try {
            Logging.test();
            File userWritingsFile = FileFunctions.getFileFromFilePath(WRITING_FILE_PATH);
            FileFunctions.checkFileExists(userWritingsFile);
            convertFromFile(userWritingsFile, savedWritings);
        } catch (FileNotFoundException e) {
            autoCreateNewFile(WRITING_FILE_PATH);
        }
    }


}
