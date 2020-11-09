//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;
import exceptions.TooManyArgumentsException;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Goal planner function.
 */
public class GoalAction extends CapAction {

    double takenMC;
    double takenCap;
    double targetMC;
    double targetCap;
    boolean isDefault = true;

    @Override
    public String act(Data data) throws Exception {
        double goalScore = targetMC * targetCap;
        double currentScore = takenMC * takenCap;
        if (isDefault) {
            double takenGrade = 0;
            double totalTakenMC = 0;
            double totalCapMC = 0;
            ArrayList<Item> targetBackup = data.target;
            String flagBackup = data.flag;
            for (Item item : data.getTarget(Constants.CAP_DATA)) {
                String currentGrade = filterGrade(((SingleModule)item).grade);
                String score = currentGrade == null ? "U" : currentGrade;
                double mc = 0;
                try {
                    mc = Double.parseDouble(((SingleModule) item).getModuleMC());
                } catch (Exception e) {
                    throw new Exception("One of your taken modules has invalid grade." + Constants.WIN_NEWLINE);
                }
                if (!score.equals("U")) {
                    takenGrade += parseGrade(score) * mc;
                    totalTakenMC += mc;
                    if (!score.equals("S")) {
                        totalCapMC += mc;
                    }
                }
            }
            data.refreshTarget(flagBackup);
            data.target = targetBackup;
            takenCap = takenGrade / totalCapMC;
            takenMC = totalTakenMC;
            currentScore = takenGrade;
        }
        if ((targetMC - takenMC) <= 0) {
            return Constants.CAN_GRADUATE;
        }
        double capRequired = (goalScore - currentScore) / (targetMC - takenMC);
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.REQUIRED_CAP).append(new DecimalFormat("#.##").format(capRequired))
                .append(Constants.WIN_NEWLINE).append("Try \"cap\" to see your current cap!")
                .append(Constants.WIN_NEWLINE);
        if (capRequired < 0) {
            sb.append(Constants.WORK_LESS);
        } else if (capRequired <= 1.0) {
            sb.append(Constants.LOW_CAP);
        } else if (capRequired <= 5.0) {
            sb.append(Constants.JIAYOU);
        } else {
            sb.append(Constants.HIGH_CAP);
        }
        return sb.toString();
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        targetMC = 0;
        targetCap = 0;
        takenMC = 0;
        takenCap = 0;
        isDefault = true;
        flattenedArgs = args.thisData.flatten().toArray(new ParamNode[0]);
        if (flattenedArgs.length > 2) {
            throw new TooManyArgumentsException();
        }
        try {
            targetMC = Double.parseDouble(flattenedArgs[0].thisData.name);
            targetCap = Double.parseDouble(flattenedArgs[0].thisData.thisData.name);
            if (flattenedArgs.length == 2) {
                isDefault = false;
                takenMC = Double.parseDouble(flattenedArgs[1].thisData.name);
                takenCap = Double.parseDouble(flattenedArgs[1].thisData.thisData.name);
            }
        } catch (Exception e) {
            throw new Exception("I do not see two parsable non-negative numbers. "
                    + "Did you make a typo?" + Constants.WIN_NEWLINE);
        }
        if (takenMC < 0) {
            throw new Exception("Your taken MC cannot be negative!" + Constants.WIN_NEWLINE);
        }
        if (takenCap < 0) {
            throw new Exception("Your taken CAP cannot be negative!" + Constants.WIN_NEWLINE);
        } else if (takenCap > 5) {
            throw new Exception("Your taken CAP cannot be higher than 5.0!" + Constants.WIN_NEWLINE);
        }
        if (targetMC < 0) {
            throw new Exception("Your total MC required cannot be negative!" + Constants.WIN_NEWLINE);
        }
        if (targetCap < 0) {
            throw new Exception("Your target CAP cannot be negative!" + Constants.WIN_NEWLINE);
        } else if (targetCap > 5) {
            throw new Exception("Your target CAP cannot be higher than 5.0!" + Constants.WIN_NEWLINE);
        }
    }
}