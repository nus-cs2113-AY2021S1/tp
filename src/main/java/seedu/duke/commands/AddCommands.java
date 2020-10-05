package seedu.duke.commands;

import seedu.duke.database.QuestionSavers;
import seedu.duke.exceptions.CommandTagMissingException;
import seedu.duke.exceptions.QuestionTypeException;
import seedu.duke.question.FillBlank;
import seedu.duke.question.Qna;
import seedu.duke.question.Question;

import java.util.ArrayList;
import java.util.Collections;

public class AddCommands {
    // Sample command to add questions
    // qna tp\topic st\subTopic qn\questionName qns\question ans\answer
    // fillblank tp\topic st\subTopic qn\questionName sb\stringBefore bl\blank sa\stringAfter
    // mcq tp\topic st\subTopic qn\questionName qns\question ans\answer wa1\ wa2\ wa3\ wa4\

    private static final ArrayList<Integer> indexesList = new ArrayList<>();

    public static void addQuestion() throws QuestionTypeException {
        System.out.println("Input add function, exit by command \"exit\" ");
        String command = CommandChecker.getUserInput();
        while (!command.equalsIgnoreCase("exit")) {
            switch(CommandChecker.commandType){
            case QNA:
                addQnaQuestion(command);
                break;
            case FILLBLANK:
                addFillBlankQuestion(command);
                break;
//            case MCQ:
//                addMcqQuestion(command);
//                break;
            default:
                throw new QuestionTypeException();
            }
            command = CommandChecker.getUserInput();
        }
    }

    private static void addQnaQuestion(String command) {
        try {
            getIndexesOfTagsFromQna(command);
        } catch (CommandTagMissingException e) {
            System.out.println("Your Qna command tags are missing somewhere ...");
        }

        String topic = extractTagInformationFromCommand(command, "topic");
        String subTopic = extractTagInformationFromCommand(command, "subTopic");
        String questionName = extractTagInformationFromCommand(command, "questionName");
        String question = extractTagInformationFromCommand(command, "question");
        String answer = extractTagInformationFromCommand(command, "answer");

        try {
            QuestionSavers.saveFillBlankAndQnaQuestionToNewFile(topic, subTopic, questionName, new Qna(answer, question));
        } catch (QuestionTypeException e) {
            System.out.println("Input code failed to create correct question type");
        }
    }

    private static void addFillBlankQuestion(String command) {
        try {
            getIndexesOfTagsFromFillBlank(command);
        } catch (CommandTagMissingException e) {
            System.out.println("Your FB command tags are missing somewhere ...");
        }

        String topic = extractTagInformationFromCommand(command, "topic");
        String subTopic = extractTagInformationFromCommand(command, "subTopic");
        String questionName = extractTagInformationFromCommand(command, "questionName");
        String stringBefore = extractTagInformationFromCommand(command, "stringBefore");
        String blank = extractTagInformationFromCommand(command, "blank");
        String stringAfter = extractTagInformationFromCommand(command, "stringAfter");

        try {
            QuestionSavers.saveFillBlankAndQnaQuestionToNewFile(topic, subTopic, questionName, new FillBlank(blank, stringBefore, stringAfter));
        } catch (QuestionTypeException e) {
            System.out.println("Input code failed to create correct question type");
        }
    }

//    private static void addMcqQuestion(String command) {
//
//    }

    private static void getIndexesOfTagsFromQna(String command) throws CommandTagMissingException {
        indexesList.clear();

        indexesList.add(0);

        indexesList.add(command.indexOf("tp\\"));
        indexesList.add(command.indexOf("st\\"));
        indexesList.add(command.indexOf("qn\\"));
        indexesList.add(command.indexOf("qns\\"));
        indexesList.add(command.indexOf("ans\\"));

        indexesList.add(command.length());

        Collections.sort(indexesList);

        if (indexesList.contains(-1)) {
            throw new CommandTagMissingException();
        }
    }

    private static void getIndexesOfTagsFromFillBlank(String command) throws CommandTagMissingException {
        indexesList.clear();

        indexesList.add(0);

        indexesList.add(command.indexOf("tp\\"));
        indexesList.add(command.indexOf("st\\"));
        indexesList.add(command.indexOf("qn\\"));
        indexesList.add(command.indexOf("sb\\"));
        indexesList.add(command.indexOf("bl\\"));
        indexesList.add(command.indexOf("sa\\"));

        indexesList.add(command.length());

        Collections.sort(indexesList);

        if (indexesList.contains(-1)) {
            throw new CommandTagMissingException();
        }
    }

    private static void getIndexesOfTagsFromMcq(String command) throws CommandTagMissingException {
        indexesList.clear();

        indexesList.add(0);

        indexesList.add(command.indexOf("tp\\"));
        indexesList.add(command.indexOf("st\\"));
        indexesList.add(command.indexOf("qn\\"));
        indexesList.add(command.indexOf("qns\\"));
        indexesList.add(command.indexOf("ans\\"));
        indexesList.add(command.indexOf("wa1\\"));
        indexesList.add(command.indexOf("wa2\\"));
        indexesList.add(command.indexOf("wa3\\"));

        if (command.contains("w4\\")) {
            indexesList.add(command.indexOf("w4\\"));
        }

        indexesList.add(command.length());

        Collections.sort(indexesList);

        if (indexesList.contains(-1)) {
            throw new CommandTagMissingException();
        }

        // Throws Error if any of the indexes is -1
    }

    private static String extractTagInformationFromCommand(String command, String extract) {
        // TODO: Exceptions: extract is not one of the three tp, st, qn

        for (int i = 1; i < indexesList.size() - 1; i++) {

            String tag = command.substring(indexesList.get(i), indexesList.get(i + 1));;
//            if (i < indexes.size() - 2) {
//
//            } else {
//                tag = command.substring(indexes.get(i));
//            }

            if (tag.trim().startsWith("tp\\") && extract.equalsIgnoreCase("topic")) {
                return tag.replace("tp\\", "").trim();
            }

            if (tag.trim().startsWith("st\\") && extract.equalsIgnoreCase("subTopic")) {
                return tag.replace("st\\", "").trim();
            }

            if (tag.trim().startsWith("qn\\") && extract.equalsIgnoreCase("questionName")) {
                return tag.replace("qn\\", "").trim();
            }

            boolean hasQuestionTag = tag.trim().startsWith("qns\\") && extract.equalsIgnoreCase("question");
            boolean hasAnswerTag = tag.trim().startsWith("ans\\") && extract.equalsIgnoreCase("answer");

            switch (CommandChecker.commandType) {
            case QNA:
                if (hasQuestionTag) {
                    return tag.replace("qns\\", "").trim();
                }
                if (hasAnswerTag) {
                    return tag.replace("ans\\", "").trim();
                }
                break;
            case MCQ:
                if (hasQuestionTag) {
                    return tag.replace("qns\\", "").trim();
                }
                if (hasAnswerTag) {
                    return tag.replace("ans\\", "").trim();
                }
                if (tag.trim().startsWith("wa1\\") && extract.equalsIgnoreCase("wrongAnswer1")) {
                    return tag.replace("wa1\\", "").trim();
                }
                if (tag.trim().startsWith("wa2\\") && extract.equalsIgnoreCase("wrongAnswer2")) {
                    return tag.replace("wa2\\", "").trim();
                }
                if (tag.trim().startsWith("wa3\\") && extract.equalsIgnoreCase("wrongAnswer3")) {
                    return tag.replace("wa3\\", "").trim();
                }
                if (!command.contains("wa4\\")) {
                    break;
                }
                if (tag.trim().startsWith("wa4\\") && extract.equalsIgnoreCase("wrongAnswer4")) {
                    return tag.replace("wa4\\", "").trim();
                }
                break;
            case FILLBLANK:
                if (tag.trim().startsWith("sb\\") && extract.equalsIgnoreCase("stringBefore")) {
                    return tag.replace("sb\\","").trim();
                }
                if (tag.trim().startsWith("bl\\") && extract.equalsIgnoreCase("blank")) {
                    return tag.replace("bl\\", "").trim();
                }
                if (tag.trim().startsWith("sa\\") && extract.equalsIgnoreCase("stringAfter")) {
                    return tag.replace("sa\\", "").trim();
                }
                break;
            }
        }
        return null;
    }
}
