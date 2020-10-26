package fitr.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDateTime = calendar.getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M/y");
        return dateFormatter.format(currentDateTime);
    }
}
