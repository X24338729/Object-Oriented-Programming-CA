package homeenergymanagerapp.appliances;

/**
 * Lighting in a room. Can have more than one bulb,
 * so the daily kWh is calculated differently.
 *
 * @author Emre Cholakoglu
 */
public class Lighting extends Appliance {

    private String bulbType;
    private int numberOfBulbs;

    public Lighting(String name, String room, double watts, double hoursPerDay, String bulbType, int numberOfBulbs) {
        super(name, room, watts, hoursPerDay);
        this.bulbType = bulbType;
        this.numberOfBulbs = numberOfBulbs;
    }

    // watts is for one bulb, so we multiply by the number of bulbs
    @Override
    public double getDailyKWh() {
        return super.getDailyKWh() * numberOfBulbs;
    }

    @Override
    public String getSavingTip() {
        if (bulbType.equalsIgnoreCase("LED")) {
            return "You already use LED bulbs. Remember to turn off lights in empty rooms.";
        } else {
            return "Switch to LED bulbs. They use up to 80% less energy than old bulbs.";
        }
    }

    public String getBulbType() {
        return bulbType;
    }

    public int getNumberOfBulbs() {
        return numberOfBulbs;
    }

    public void setBulbType(String bulbType) {
        this.bulbType = bulbType;
    }

    public void setNumberOfBulbs(int numberOfBulbs) {
        this.numberOfBulbs = numberOfBulbs;
    }

    @Override
    public String toString() {
        return "[Lighting] " + super.toString()
             + "\nBulb type: " + bulbType + " | Number of bulbs: " + numberOfBulbs;
    }
}