package seedu.duke.database;

import java.io.File;
import java.io.FileNotFoundException;
import seedu.duke.exceptions.NotEnoughWritingComponentException;
import seedu.duke.writing.Essay;
import seedu.duke.writing.Poem;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static seedu.duke.Duke.writings;
import static seedu.duke.constants.DataFileConvention.WRITING_COMPONENT_MARK;
import static seedu.duke.constants.DataFileConvention.WRITING_COMPONENT_DIVIDER;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_AUTHOR;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_DATE;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_ID;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_TITLE;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_TOPIC;
import static seedu.duke.constants.DataFileConvention.POSITION_OF_TYPE;
import static seedu.duke.constants.DataFileConvention.POEM;
import static seedu.duke.constants.DataFileConvention.ESSAY;
import static seedu.duke.constants.DataFileConvention.NUMBER_OF_WRITING_COMPONENT;

public class WritingsLoader {

    /**This function reads data stored in data file and coverts it into ArrayList structure.
     *
     * @param f data file storing the information of the tasks
     * @return total writings detected in the data file
     * @throws FileNotFoundException when the file is not found
     * @throws NotEnoughWritingComponentException when the format of minimum
     */
    private static int convertWritingFile(File f) throws FileNotFoundException, NotEnoughWritingComponentException {
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

        //Initializing the content
        String content = "";

        try {
            while (s.hasNext()) {
                String currentLine = s.nextLine();
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
                            break;
                        }
                        currentLine = s.nextLine();
                    }
                    //Writing text to the writing object
                    currentLine = s.nextLine();
                    content = content.concat(currentLine + "\n");
                }
                if (type.equals(POEM)) {
                    writings.add(new Poem(title, id, topic, content, author));
                } else if (type.equals(ESSAY)) {
                    writings.add(new Essay(title, id, topic, content, author));
                }
                if (countContent != NUMBER_OF_WRITING_COMPONENT) {
                    throw new NotEnoughWritingComponentException();
                } else {
                    countContent = 0; //Reload for reading the data of the next object in the txt file
                }
                countWritings++;
            }
        } catch (DateTimeParseException e) {
            System.out.println("This is not valid!");
        }
        return countWritings;
    }

    /**Check if the data file exists or not, creates "writings.txt" in "data" directory if not
     *
     * @param f file to be processed
     * @return the number of writings in the data extracted from convertWritingFile function
     */
    public static int convertFromFile(File f) {
        File folder = new File("data");
        boolean bool = folder.mkdirs(); // If folder doesn't exist, then create it

        try {
            return convertWritingFile(f);
        } catch (FileNotFoundException e) {
            //System.out.println("File not found");
            try {
                f.createNewFile();
                System.out.println("duke.txt aka the data file created in the data folder!");
                return 0;
            } catch (IOException err) {
                err.printStackTrace();
                return 0;
            }
        } catch (NotEnoughWritingComponentException e) {
            System.out.println("There is something wrong with the data file");
            return 0;
        }
    }

    /**Write data from ArrayList structure to text form in directory "data/duke.txt"
     *
     * @param f file to be processed
     * @param countWritings total number of tasks/lines in the data
     * @throws IOException IO error
     */
    private static void recordFile(File f, int countWritings) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < countWritings; i++) {
            fw.write("*id: " + writings.get(i).getId() + "\n"
                    + "*Author: " + writings.get(i).getAuthor().getName() + "\n"
                    + "*Type: " + writings.get(i).getType() + "\n"
                    + "*Title: " + writings.get(i).getTitle() + "\n"
                    + "*Topic: " + writings.get(i).getTopic() + "\n"
                    + "*Date: " + writings.get(i).getDate() + "\n"
                    + "*Content: \n" + writings.get(i).getContent() + "\n");
        }
        fw.close();
    }

    /**Record the file or print error message.
     *
     * @param f data file to be processed
     * @param countTasks total number of tasks
     */
    public static void recordListToFile(File f, int countTasks) {
        try {
            recordFile(f, countTasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
