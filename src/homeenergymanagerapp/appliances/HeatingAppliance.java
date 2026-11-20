package homeenergymanagerapp.appliances;

/**
 * A heating appliance (heater, radiator, boiler etc.)
 * Has a target temperature and can have a timer.
 *
 * @author Emre Cholakoglu
 */
public class HeatingAppliance extends Appliance {

    private int targetTemp;
    private boolean hasTimer;

    public HeatingAppliance(String name, String room, double watts, double hoursPerDay, int targetTemp, boolean hasTimer) {
        super(name, room, watts, hoursPerDay);   // the parent class sets the common details
        this.targetTemp = targetTemp;
        this.hasTimer = hasTimer;
    }

    // the tip depends on the temperature and the timer
    @Override
    public String getSavingTip() {
        if (targetTemp > 20) {
            return "Lower the temperature to 20°C or less. Each degree can save around 10% on heating.";
        } else if (!hasTimer) {
            return "Use a timer so the heating is only on when you need it.";
        } else {
            return "Good settings! Keep doors and windows closed while heating.";
        }
    }

    public int getTargetTemp() {
        return targetTemp;
    }

    public boolean getHasTimer() {
        return hasTimer;
    }

    public void setTargetTemp(int targetTemp) {
        this.targetTemp = targetTemp;
    }

    public void setHasTimer(boolean hasTimer) {
        this.hasTimer = hasTimer;
    }

    @Override
    public String toString() {
        return "[Heating] " + super.toString()
             + "\nTarget temp: " + targetTemp + "°C | Timer: " + (hasTimer ? "Yes" : "No");
    }
}