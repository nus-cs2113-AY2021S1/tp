package seedu.duke.commands;

import java.util.ArrayList;
import java.util.Collections;

public class AddCommands {
    // Sample command to add questions
    // qna tp\topic st\subTopic qn\questionName qns\question ans\answer
    // fb tp\topic st\subTopic qn\questionName sb\stringBefore bl\blank sa\stringAfter
    // mcq tp\topic st\subTopic qn\questionName qns\question na\numberOfWrongAns ans\answer wa1\ wa2\ wa3\ wa4\

    private static final ArrayList<Integer> indexes = new ArrayList<>();

    public static void getIndexesOfTagsFromQna(String command) {
        indexes.clear();

        indexes.add(command.indexOf("tp\\"));
        indexes.add(command.indexOf("st\\"));
        indexes.add(command.indexOf("qn\\"));
        indexes.add(command.indexOf("qns\\"));
        indexes.add(command.indexOf("ans\\"));

        Collections.sort(indexes);
    }

    public static void getIndexesOfTagsFromFillBlank(String command) {
        indexes.clear();

        indexes.add(command.indexOf("tp\\"));
        indexes.add(command.indexOf("st\\"));
        indexes.add(command.indexOf("qn\\"));
        indexes.add(command.indexOf("sb\\"));
        indexes.add(command.indexOf("bl\\"));
        indexes.add(command.indexOf("sa\\"));

        Collections.sort(indexes);
    }

    public static void getIndexesOfTagsFromMcq(String command) {
        indexes.clear();

        indexes.add(command.indexOf("tp\\"));
        indexes.add(command.indexOf("st\\"));
        indexes.add(command.indexOf("qn\\"));
        indexes.add(command.indexOf("qns\\"));
        indexes.add(command.indexOf("ans\\"));
        indexes.add(command.indexOf("wa1\\"));
        indexes.add(command.indexOf("wa2\\"));
        indexes.add(command.indexOf("wa3\\"));

        if (command.contains("w4\\")) {
            indexes.add(command.indexOf("w4\\"));
        }

        Collections.sort(indexes);
    }

    public static String extractTagInformationFromCommand(String command, String extract) {
        // TODO: Exceptions: extract is not one of the three tp, st, qn
        String questionType = command.substring(0, indexes.get(0)).trim();

        for (int i = 0; i < indexes.size() - 1; i++) {

            String tag;
            if (i < indexes.size() - 2) {
                tag = command.substring(indexes.get(i), indexes.get(i + 1));
            } else {
                tag = command.substring(indexes.get(i));
            }

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

            switch (questionType) {
            case "qna":
                if (hasQuestionTag) {
                    return tag.replace("qns\\", "").trim();
                }
                if (hasAnswerTag) {
                    return tag.replace("ans\\", "").trim();
                }
                break;
            case "mcq":
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
            case "fillblank":
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
