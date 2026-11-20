package homeenergymanagerapp.appliances;
import java.io.Serializable;

/**
 * Abstract class for all appliances.
 * Holds the details every appliance has (name, room, watts, hours).
 * There is no "general appliance" so this class is abstract.
 *
 * @author Emre Cholakoglu
 */
public abstract class Appliance implements EnergyConsumer, Serializable {

    private String name;
    private String room;
    private double watts;
    private double hoursPerDay;

    public Appliance(String name, String room, double watts, double hoursPerDay) {
        this.name = name;
        this.room = room;
        this.watts = watts;
        this.hoursPerDay = hoursPerDay;
    }

    // daily kWh = watts x hours / 1000
    @Override
    public double getDailyKWh() {
        return watts * hoursPerDay / 1000;
    }

    // monthly cost = daily kWh x 30 days x electricity rate
    public double getMonthlyCost(double rate) {
        return getDailyKWh() * 30 * rate;
    }

    // every type of appliance gives a different tip, so the subclasses will write this
    @Override
    public abstract String getSavingTip();

    // getters
    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public double getWatts() {
        return watts;
    }

    public double getHoursPerDay() {
        return hoursPerDay;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setWatts(double watts) {
        this.watts = watts;
    }

    public void setHoursPerDay(double hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Room: " + room
             + "\nWatts: " + watts + " | Hours per day: " + hoursPerDay
             + " | Daily kWh: " + getDailyKWh();
    }
}