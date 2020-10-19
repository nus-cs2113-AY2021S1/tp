package fitr.list;

import java.util.Random;
import java.util.ArrayList;

public class TipList {
    private final ArrayList<String> tipList;

    public TipList(ArrayList<String> tipList) {
        this.tipList = tipList;
    }

    public String getTip(int totalTips) {
        Random random = new Random();
        return tipList.get(random.nextInt(totalTips));
    }
}
