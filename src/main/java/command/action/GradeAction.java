//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Grade input action.
 */
public class GradeAction extends TakeAction {

    protected HashMap<String, ArrayList<String>> map;
    protected boolean isShowing;

    @Override
    public String act(Data data) throws Exception {
        successes = 0;
        if (isShowing) {
            blindSearch = Constants.TAKEN;
        } else {
            blindSearch = Constants.SELECTED;
        }
        String output = super.act(data).replace("take or untake", "grade")
                .replace("taken", "graded").replace(" or ungraded", Constants.ZERO_LENGTH_STRING);
        if (isShowing) {
            output = output.replace("selected", "taken");
        }
        return output;
    }

    @Override
    protected boolean modifyObject(Item item) {
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
                ((SingleModule) item).grade = grade;
                ((SingleModule) item).isTaken = true;
                successes++;
                return true;
            }
        }
        item.immediateData = null;
        if (isShowing) {
            successes++;
        }
        return isShowing;
    }

    protected String filterGrade(String rawGrade) {
        if (rawGrade == null) {
            return null;
        }
        String myGrade = rawGrade.toUpperCase();
        for (String grade : Constants.VALID_GRADES) {
            if (grade.equals(myGrade)) {
                return myGrade;
            }
        }
        return null;
    }

    @Override
    protected String getObjectInfo(Item item) {
        return item.getName() + Constants.DETAILS_SIGNATURE + ((SingleModule)item).grade;
    }

    @Override
    protected void safetyCheck() {
        isBlind = true;
        isShowing = true;
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        isShowing = false;
        map = new HashMap<>();
        indices = new ArrayList<>();
        codes = new ArrayList<>();
        super.superPrepare(args);
        isBlind = false;
        if (args.thisData == null || flattenedArgs.length < 1) {
            safetyCheck();
            return;
        }
        String[] identifiers = args.thisData.toFlatString().split(Constants.SPACE);
        String lastKey = "DEFAULT";
        for (String id : identifiers) {
            String uid = id.toUpperCase();
            try {
                indices.add(Integer.parseInt(uid) - 1);
                lastKey = uid;
                map.put(uid, new ArrayList<>());
            } catch (Exception e) {
                if (uid.matches("([A-Z])+([0-9])+[A-Z]*")) {
                    codes.add(uid);
                    lastKey = uid;
                    map.put(uid, new ArrayList<>());
                } else {
                    if (map.get(lastKey) == null) {
                        isBlind = true;
                        map.put(lastKey, new ArrayList<>());
                    }
                    map.get(lastKey).add(uid);
                }
            }
        }
    }
}
