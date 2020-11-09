//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Calculate user's cap.
 */
public class CapAction extends GradeAction {

    private double totalMc;
    private double totalGrade;
    private boolean isAssigned = false;
    private String assignedGrade = "";

    @Override
    public String act(Data data) throws Exception {
        successes = 0;
        if (isShowing) {
            blindSearch = Constants.TAKEN;
        } else {
            blindSearch = Constants.SELECTED;
        }
        String output = super.act(data).replace("for", "for graded modules in");
        if (isShowing) {
            output = output.replace("selected", "taken");
        }
        double cap = totalGrade / totalMc;
        String capString = new DecimalFormat("#.##").format(cap);
        return output + (capString.equals("NaN") ? "CAP calculation is skipped because there is no Module specified "
                + "with valid grade." + Constants.WIN_NEWLINE + "Try \"grade [module code] [letter grade]\" "
                + "to set grades to your modules, or " + Constants.WIN_NEWLINE
                + "try \"help cap\" to see other usages using hypothetical grades!" : "CAP = " + capString);
    }

    @Override
    protected String getObjectInfo(Item item) {
        String outputGrade = isAssigned ? "(hypothetical)" + assignedGrade : ((SingleModule)item).grade;
        return item.getName() + Constants.DETAILS_SIGNATURE + outputGrade;
    }

    @Override
    protected boolean modifyObject(Item item) {
        isAssigned = false;
        assignedGrade = "";
        assert item instanceof SingleModule;
        if (((SingleModule) item).isCompleted) {
            return false;
        }
        for (String key : map.keySet()) {
            boolean notBlind = !isBlind
                    && (key.equals(((SingleModule) item).moduleCode) || key.equals(item.immediateData));
            boolean blind = isBlind && key.equals("DEFAULT");
            if (notBlind || blind) {
                ArrayList<String> values = map.get(key);
                String grade = "";
                if (values == null || values.size() == 0) {
                    grade = null;
                } else {
                    grade = filterGrade(values.get(0));
                }
                if (grade == null) {
                    continue;
                }
                double mc = 0;
                try {
                    mc = Double.parseDouble(((SingleModule) item).getModuleMC());
                } catch (Exception e) {
                    return false;
                }
                isAssigned = true;
                assignedGrade = grade;
                if (!grade.equals("U")) {
                    totalGrade += parseGrade(grade) * mc;
                    if (!grade.equals("S")) {
                        totalMc += mc;
                    }
                }
                break;
            }
        }
        item.immediateData = null;
        if (!isAssigned) {
            String currentGrade = filterGrade(((SingleModule)item).grade);
            String score = currentGrade == null ? "U" : currentGrade;
            double mc = 0;
            try {
                mc = Double.parseDouble(((SingleModule) item).getModuleMC());
            } catch (Exception e) {
                return false;
            }
            if (!score.equals("U")) {
                totalGrade += parseGrade(score) * mc;
                if (!score.equals("S")) {
                    totalMc += mc;
                }
            }
        }
        successes++;
        return true;
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        totalMc = 0;
        totalGrade = 0;
        super.prepare(args);
    }

    protected double parseGrade(String grade) {
        double gradeValue;
        if (grade == null) {
            return 0;
        }
        switch (grade) {
        case "A+":
        case "A":
            gradeValue = 5;
            break;
        case "A-":
            gradeValue = 4.5;
            break;
        case "B+":
            gradeValue = 4;
            break;
        case "B":
            gradeValue = 3.5;
            break;
        case "B-":
            gradeValue = 3;
            break;
        case "C+":
            gradeValue = 2.5;
            break;
        case "C":
            gradeValue = 2;
            break;
        case "D+":
            gradeValue = 1.5;
            break;
        case "D":
            gradeValue = 1;
            break;
        default: //F, S, U all 0
            gradeValue = 0;
        }
        return gradeValue;
    }
}