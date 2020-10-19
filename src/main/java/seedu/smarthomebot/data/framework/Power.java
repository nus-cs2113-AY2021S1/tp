package seedu.smarthomebot.data.framework;

import seedu.smarthomebot.ui.TextUi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_TIME_FORMAT_ERROR;

/**
 * Class representing the power consumption of appliances.
 */

public class Power {
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy-HH:mm:ss";
    private final int power;
    private final TextUi ui = new TextUi();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.ENGLISH);
    LocalDateTime currentTime;
    private String offTime;
    private String onTime;
    private double powerUsed;
    private double totalHours;
    private Boolean status;
    private double totalPowerConsumption;

    public Power(String power) {
        this.power = Integer.parseInt(power);
        this.status = false;
        this.totalPowerConsumption = 0;
        this.totalHours = 0;
    }

    /**
     * Gets the status of the appliance.
     *
     * @return the status of the appliance in Boolean.
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * Appliance only can be switched on if it was 'off' previously.
     *
     * @return true if appliance turn on successfully
     */
    public boolean onAppliance() {
        if (!this.status) {
            this.status = true;
            onTime = getCurrentTime();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Appliance only can be switched off if it was 'on' previously.
     * Compute the total power consumption once appliance is off.
     *
     * @return true if appliance turn off successfully
     */
    public boolean offAppliance() {
        if (status) {
            this.status = false;
            offTime = getCurrentTime();
            computeTotalPower();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gets the power consumption of appliance.
     *
     * @return the power consumption in double.
     */
    public double getPower() {
        computeTotalPower();
        return this.totalPowerConsumption;
    }

    /**
     * Gets the current system time from local machine.
     *
     * @return formatted current time with given format "dd/MM/yyyy-HH:mm:ss".
     */
    private String getCurrentTime() {
        currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    private String getCurrentDate() {
        return getCurrentTime().substring(0, 10);
    }

    /**
     * Computes power consumption from loaded file.
     *
     * @param powerConsumption of each appliance used within a day
     * @param dateTime         is the recorded time in data file when user exit the program
     */
    public void loadDataFromFile(double powerConsumption, String dateTime) {
        if (isToday(dateTime)) {
            this.powerUsed = powerConsumption;
            if (status) {
                onTime = dateTime;
                offTime = getCurrentTime();
                computePower();
            }
        } else {
            if (!status) {
                this.powerUsed = 0;
            } else {
                onTime = getCurrentDate() + "-00:00:00";
                offTime = getCurrentTime();
                computePower();
            }
        }
        this.totalPowerConsumption += this.powerUsed;
    }

    private void calculateTimeUsed() throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date onTimeValue;
        Date offTimeValue;
        double timeUsed;

        if (onTime != null) {
            onTimeValue = timeFormat.parse(onTime);
            if (!this.status) {
                offTimeValue = timeFormat.parse(offTime);
                timeUsed = offTimeValue.getTime() - onTimeValue.getTime();
                // System time cannot be negative time
                assert timeUsed >= 0 : "System Time is not correct! " + timeUsed;
                onTime = offTime;
            } else {
                Date currentUsedTime = timeFormat.parse(getCurrentTime());
                timeUsed = currentUsedTime.getTime() - onTimeValue.getTime();
                onTime = getCurrentTime();
            }
        } else {
            timeUsed = 0;
        }

        // For simulation purpose, 1 second in System equals to 10 minutes in SmartHomeBot
        this.totalHours = timeUsed / (1000 * 6);
    }

    private boolean isToday(String dateFromFile) {
        dateFromFile = dateFromFile.substring(0, 10);
        return (getCurrentDate()).equals(dateFromFile);
    }

    private void computePower() {
        try {
            calculateTimeUsed();
        } catch (ParseException e) {
            ui.printToUser(LINE + MESSAGE_TIME_FORMAT_ERROR);
        }
        // Convert power unit to kWh
        this.powerUsed = this.totalHours * this.power / 1000.00;
        assert this.powerUsed >= 0 : "Power usage cannot be negative! " + this.powerUsed;
    }

    private void computeTotalPower() {
        computePower();
        this.totalPowerConsumption += this.powerUsed;
        assert this.totalPowerConsumption >= 0 : "totalPowerConsumption cannot be negative";
    }

    /**
     * Gets the power consumption in String.
     *
     * @return the power consumption with only 2 decimal places in String.
     */
    public String toString() {
        return String.format("%.2f", this.totalPowerConsumption);
    }

}
