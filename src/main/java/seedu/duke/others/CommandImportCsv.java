package seedu.duke.others;

import seedu.duke.Command;
import seedu.duke.DukeFileFormatException;
import seedu.duke.DukeFileHeaderException;
import seedu.duke.backend.FileManager;
import seedu.duke.backend.UserInput;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.finance.FinanceList;
import seedu.duke.finance.FinanceLog;
import seedu.duke.hr.Member;
import seedu.duke.hr.MemberList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandImportCsv extends Command {
    private UserInput userInput;

    @Override
    public String execute() {
        String filename = userInput.getArg("");
        String category = userInput.getArg("c");
        int rowsBefore = 0;
        int rowsAfter = 0;
        String failed = "";
        try {
            HashMap<String, ArrayList<String>> map = FileManager.readFile(filename);

            if (category.equalsIgnoreCase("finance")) {
                int rows = map.get(userInput.getArg("name")).size();
                rowsBefore = FinanceList.financeLogs.size();
                for (int i = 0; i < rows; i++) {
                    try {
                        FinanceLog tmp = new FinanceLog(map.get(userInput.getArg("name")).get(i),
                                Double.parseDouble(map.get(userInput.getArg("value")).get(i)));
                        FinanceList.financeLogs.add(tmp);
                    } catch (Exception e) {
                        failed += (failed.equals("")) ? "" + i : "," + i;
                    }
                }
                rowsAfter = FinanceList.financeLogs.size();
            } else if (category.equalsIgnoreCase("hr")) {
                int rows = map.get(userInput.getArg("name")).size();
                rowsBefore = MemberList.members.size();
                for (int i = 0; i < rows; i++) {
                    try {
                        Member tmp = new Member(map.get(userInput.getArg("name")).get(i),
                                Long.parseLong(map.get(userInput.getArg("phone")).get(i)),
                                map.get(userInput.getArg("email")).get(i),
                                map.get(userInput.getArg("role")).get(i));
                        MemberList.members.add(tmp);
                    } catch (Exception e) {
                        failed += (failed.equals("")) ? "" + i : "," + i;
                    }
                }
                rowsAfter = MemberList.members.size();
            } else if (category.equalsIgnoreCase("event")) {
                int rows = map.get(userInput.getArg("name")).size();
                rowsBefore = EventList.events.size();
                for (int i = 0; i < rows; i++) {
                    try {
                        Event tmp = new Event(map.get(userInput.getArg("name")).get(i),
                                map.get(userInput.getArg("date")).get(i),
                                map.get(userInput.getArg("time")).get(i));
                        EventList.events.add(tmp);
                    } catch (Exception e) {
                        failed += (failed.equals("")) ? "" + i : "," + i;
                    }
                }
                rowsAfter = EventList.events.size();
            } else {
                return "Please specify a valid category.";
            }
            assert (rowsAfter < rowsBefore) : "Less rows than before after importing!";
        } catch (IOException e) {
            return "Unable to read file! Please check your path/filename!";
        } catch (DukeFileHeaderException e) {
            // This shouldn't happen because we do not enforce header check when invoking the function
            assert false : "Header checking shouldn't be enabled!";
            return "Header mismatch!";
        } catch (DukeFileFormatException e) {
            return "Unable to read CSV file! Please check that the specified file is properly formatted!";
        } catch (NullPointerException e) {
            return "Unable to find column header name in your file! Please check the spelling and capitalization.";
        }
        final String s = "Successfully imported " + (rowsAfter - rowsBefore) + " entries."
                + ((failed.equals("")) ? "" : "\nFailed to import index: " + failed);
        return s;
    }

    @Override
    public String help() {
        return "Syntax: import FILENAME /c finance /name HEADER_NAME /value HEADER_NAME \n"
               + "OR: import FILENAME /c hr /name HEADER_NAME /phone HEADER_NAME /email HEADER_NAME /role HEADER_NAME\n"
               + "OR: import FILENAME /c event /name HEADER_NAME /date HEADER_NAME /time HEADER_NAME\n\n"
               + "Opens the given CSV file and imports the data in the columns specified by the HEADER_NAME. "
               + "The mapping specifies that the HEADER_NAME will be assigned to a particular argument. "
               + "HEADER_NAME is case sensitive";
    }

    public int validate(UserInput ui) {
        userInput = ui;
        if (ui.getCategory().equals("") && (ui.getCommand().equalsIgnoreCase("import")
                || ui.getCommand().equalsIgnoreCase("i"))) {
            if (ui.getArg("") == null || ui.getArg("").equals("")) {
                return ARGUMENT_ERR;
            }
            if (ui.getArg("c") == null || ui.getArg("c").equals("")) {
                return ARGUMENT_ERR;
            }
            // Shorthand category support
            if (ui.getArg("c").equalsIgnoreCase("h")) {
                ui.getArgs().put("c", "hr");
            } else if (ui.getArg("c").equalsIgnoreCase("f")) {
                ui.getArgs().put("c", "finance");
            } else if (ui.getArg("c").equalsIgnoreCase("e")) {
                ui.getArgs().put("c", "event");
            } else {
                if (!ui.getArg("c").equalsIgnoreCase("hr")
                        && ui.getArg("c").equalsIgnoreCase("finance")
                        && ui.getArg("c").equalsIgnoreCase("event")) {
                    return ARGUMENT_ERR;
                }
            }
            // Shorthand argument support
            if (ui.getArg("n") != null && !ui.getArg("n").equals("")) {
                ui.getArgs().put("name", ui.getArg("n"));
            }
            if (ui.getArg("v") != null && !ui.getArg("v").equals("")) {
                ui.getArgs().put("value", ui.getArg("v"));
            }
            if (ui.getArg("p") != null && !ui.getArg("p").equals("")) {
                ui.getArgs().put("phone", ui.getArg("p"));
            }
            if (ui.getArg("e") != null && !ui.getArg("e").equals("")) {
                ui.getArgs().put("email", ui.getArg("e"));
            }
            if (ui.getArg("r") != null && !ui.getArg("r").equals("")) {
                ui.getArgs().put("role", ui.getArg("r"));
            }
            if (ui.getArg("d") != null && !ui.getArg("d").equals("")) {
                ui.getArgs().put("date", ui.getArg("d"));
            }
            if (ui.getArg("t") != null && !ui.getArg("t").equals("")) {
                ui.getArgs().put("time", ui.getArg("t"));
            }
            // check for the correct arguments depending on the category
            if (ui.getArg("c").equalsIgnoreCase("hr")) {
                if (ui.getArg("name") == null || ui.getArg("name").equals("")) {
                    return ARGUMENT_ERR;
                }
                if (ui.getArg("phone") == null || ui.getArg("phone").equals("")) {
                    return ARGUMENT_ERR;
                }
                if (ui.getArg("email") == null || ui.getArg("email").equals("")) {
                    return ARGUMENT_ERR;
                }
                if (ui.getArg("role") == null || ui.getArg("role").equals("")) {
                    return ARGUMENT_ERR;
                }
            } else if (ui.getArg("c").equalsIgnoreCase("finance")) {
                if (ui.getArg("name") == null || ui.getArg("name").equals("")) {
                    return ARGUMENT_ERR;
                }
                if (ui.getArg("value") == null || ui.getArg("value").equals("")) {
                    return ARGUMENT_ERR;
                }
            } else if (ui.getArg("c").equalsIgnoreCase("event")) {
                if (ui.getArg("name") == null || ui.getArg("name").equals("")) {
                    return ARGUMENT_ERR;
                }
                if (ui.getArg("date") == null || ui.getArg("date").equals("")) {
                    return ARGUMENT_ERR;
                }
                if (ui.getArg("time") == null || ui.getArg("time").equals("")) {
                    return ARGUMENT_ERR;
                }
            }
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
