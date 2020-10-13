package seedu.duke.data.framework;

import seedu.duke.ui.TextUi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static seedu.duke.common.Messages.LINE;
import static seedu.duke.common.Messages.MESSAGE_TIME_FORMAT_ERROR;

public class Power {

    private static final String TIME_DATE_FORMAT = "dd/MM/yyyy | HH:mm:ss";
    private static TextUi ui = new TextUi();
    private final int power;
    private String offTime;
    private String onTime;
    private double powerUsed;
    private double totalHours;
    private Boolean isOn;
    private double totalPowerConsumption;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_DATE_FORMAT);
    LocalDateTime currentTime;

    public Power(String power) {
        this.power = Integer.parseInt(power);
        this.isOn = false;
        totalPowerConsumption = 0;
        totalHours = 0;
    }

    /**
     * Appliance only can be switched on if it was 'off' previously.
     *
     * @return true if appliance turn on successfully
     */
    public boolean onAppliance() {
        if (!isOn) {
            this.isOn = true;
            onTime = getCurrentTime();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Appliance only can be switched off if it was 'on' previously.
     * Compute the total power consumption once appliance is off.
     *
     * @return true if appliance turn off successfully
     */
    public boolean offAppliance() {
        if (isOn) {
            this.isOn = false;
            offTime = getCurrentTime();
            try {
                calculatePowerConsumed();
            } catch (ParseException e) {
                ui.showToUser(LINE + MESSAGE_TIME_FORMAT_ERROR);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the system current time.
     *
     * @return currentTime in string with the format defined
     */
    private String getCurrentTime() {
        currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    private void calculateTimeUsed() throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_DATE_FORMAT);
        Date onTimeValue;
        Date offTimeValue;
        double timeUsed;
        boolean isOnOnce;

        if (onTime != null) {
            isOnOnce = true;
        } else {
            isOnOnce = false;
        }

        if (isOnOnce) {
            onTimeValue = timeFormat.parse(onTime);
            if (!this.isOn) {
                offTimeValue = timeFormat.parse(offTime);
                timeUsed = offTimeValue.getTime() - onTimeValue.getTime();
                onTime = offTime;
            } else {
                Date currentUsedTime = timeFormat.parse(getCurrentTime());
                timeUsed = currentUsedTime.getTime() - onTimeValue.getTime();
                onTime = getCurrentTime();
            }
        } else {
            timeUsed = 0;
        }

        // For simulation purpose, 1 second in System equals to 1 hour in SmartHomeBot
        // Convert back to hours timeDifference/(1000 * 60 * 60)
        totalHours = timeUsed / (1000);
    }

    private void calculatePowerConsumed() throws ParseException {
        calculateTimeUsed();
        // Convert power unit to kWh
        powerUsed = totalHours * power / 1000.00;
        totalPowerConsumption += powerUsed;
    }

    public Boolean getStatus() {
        return this.isOn;
    }

    public double getPower() {
        try {
            calculatePowerConsumed();
        } catch (ParseException e) {
            ui.showToUser(LINE + MESSAGE_TIME_FORMAT_ERROR);
        }
        return this.totalPowerConsumption;
    }

    public void computeFromFile(double powerComsumption) {
        powerUsed = powerComsumption;
        totalPowerConsumption += powerUsed;
    }

    public String toString() {
        return String.valueOf(this.totalPowerConsumption);
    }

}
