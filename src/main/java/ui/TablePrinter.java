package ui;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TablePrinter {
    String[][] rawTable;
    ArrayList<String[]> finalTable;
    int maxWidth = 30;
    Printer printer;

    public TablePrinter(Printer printer, CheatSheetList cheatSheetList) {
        this.printer = printer;
        makeRawTable(cheatSheetList.getList());
    }

    public TablePrinter(Printer printer, ArrayList<CheatSheet> cheatSheetToBePrinted) {
        this.printer = printer;
        makeRawTable(cheatSheetToBePrinted);
    }

    private void makeRawTable(ArrayList<CheatSheet> cheatSheetsToBePrinted) {
        rawTable = new String[cheatSheetsToBePrinted.size() + 1][4];
        rawTable[0][0] = "INDEX";
        rawTable[0][1] = "NAME";
        rawTable[0][2] = "SUBJECT";
        rawTable[0][3] = "PREVIEW";
        for (int i = 1; i <= cheatSheetsToBePrinted.size(); i++) {
            CheatSheet cs = cheatSheetsToBePrinted.get(i - 1);
            assert cs != null;
            rawTable[i][0] = String.valueOf(i);
            rawTable[i][1] = cs.getName().trim() + (cs.getIsFavourite() ? " [*]" : "");
            try {
                rawTable[i][2] = cs.getSubject().trim();
            } catch (NullPointerException n) {
                rawTable[i][2] = "";
            }
            try {
                rawTable[i][3] = cs.getDetails().split("\n")[0].trim();
                if (rawTable[i][3].isEmpty()) {
                    rawTable[i][3] = cs.getDetails().split("\n")[1].trim();
                }
            } catch (ArrayIndexOutOfBoundsException a) {
                rawTable[i][3] = cs.getDetails().trim();
            }
        }
    }

    private int calculateNoOfNewRowsNeededByCell(String cell) {
        int rowsNeeded = 0;
        if (cell.contains("\n")) {
            String[] splittedCell = cell.split("\n");
            for (String s : splittedCell) {
                rowsNeeded += (s.length() / maxWidth) + 1;
            }
            return rowsNeeded;
        } else {
            return (cell.length() / maxWidth) + 1;
        }
    }

    private int calculateNoOfNewRowsNeededByRow(String[] row) {
        int rowsNeeded = 0;
        for (String cell : row) {
            if (rowsNeeded < calculateNoOfNewRowsNeededByCell(cell)) {
                rowsNeeded = calculateNoOfNewRowsNeededByCell(cell);
            }
        }
        return rowsNeeded;
    }

    private String[] contentsByColAfterWrapping(String cell) {
        if (cell.contains("\n")) {
            return cell.split("\n");
        } else {
            return cell.split("(?<=\\G.{30})");
        }
    }

    private void makeFinalTable() {
        finalTable = new ArrayList<>();
        int rowsNeeded = 0;

        for (String[] rows : rawTable) {
            rowsNeeded = calculateNoOfNewRowsNeededByRow(rows);
            for (int k = 0; k < rowsNeeded; k++) {
                String[] newRow = new String[rows.length];
                for (int i = 0; i < rows.length; i++) {
                    String[] cellArray = contentsByColAfterWrapping(rows[i]);
                    if (k < cellArray.length) {
                        newRow[i] = cellArray[k];
                    } else {
                        newRow[i] = "";
                    }
                }
                finalTable.add(newRow);
            }
        }
    }

    public HashMap<Integer, Integer> calculateLengthOfIndividualCol() {
        HashMap<Integer, Integer> columnLengths = new HashMap<>();
        for (String[] row : finalTable) {
            for (int i = 0; i < row.length; i++) {
                columnLengths.putIfAbsent(i, 0);
                if (columnLengths.get(i) < row[i].length()) {
                    columnLengths.put(i, row[i].length());
                }
            }
        }
        return columnLengths;
    }

    public StringBuilder prepareStringFormatForEachRow(HashMap<Integer, Integer> columnLengths) {
        final StringBuilder stringFormat = new StringBuilder("");
        String flag = "-";
        int col = 0;
        String color = "";
        for (HashMap.Entry<Integer, Integer> e : columnLengths.entrySet()) {
            if (col % 4 == 0) {
                color = printer.favColor.toString();
            } else if (col % 4 == 1) {
                color = printer.nameColor.toString();
            } else if (col % 4 == 2) {
                color = printer.subjectColor.toString();
            } else {
                color = printer.detailsColor.toString();
            }
            stringFormat
                    .append("| ")
                    .append(color)
                    .append("%")
                    .append(flag)
                    .append(e.getValue())
                    .append("s ")
                    .append(printer.reset.toString());
            col++;
        }
        stringFormat.append("|\n");
        return stringFormat;
    }

    public StringBuilder prepareLine(HashMap<Integer, Integer> columnLengths) {
        StringBuilder line = new StringBuilder();
        for (Integer value : columnLengths.values()) {
            line.append("+");
            line.append("-".repeat(value + 2));
        }

        line.append("+\n");
        return line;
    }

    public void printFinalTable(StringBuilder stringFormat, StringBuilder line) {
        System.out.print(line);
        System.out.printf(stringFormat.toString(), rawTable[0][0], rawTable[0][1], rawTable[0][2], rawTable[0][3]);
        for (int i = 1; i < finalTable.size(); i++) {
            Pattern regex = Pattern.compile("[0-9]{1,2}");
            Matcher regexMatcher = regex.matcher(finalTable.get(i)[0]);
            if (regexMatcher.find()) {
                System.out.print(line);
            }
            System.out.printf(stringFormat.toString(), finalTable.get(i)[0], finalTable.get(i)[1],
                    finalTable.get(i)[2], finalTable.get(i)[3]);
        }
        System.out.print(line);
    }

    public void execute() {
        makeFinalTable();
        HashMap<Integer, Integer> columnLengths = calculateLengthOfIndividualCol();
        StringBuilder stringFormat = prepareStringFormatForEachRow(columnLengths);
        StringBuilder line = prepareLine(columnLengths);
        printFinalTable(stringFormat, line);
    }
}
