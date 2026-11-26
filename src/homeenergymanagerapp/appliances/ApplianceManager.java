package homeenergymanagerapp.appliances;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Manages the list of appliances.
 * Add, delete, search, report calculations and saving/loading the list to a file.
 *
 * @author Emre Cholakoglu
 */
public class ApplianceManager {

    private ArrayList<Appliance> appliances;
    private final String FILE_NAME = "appliances.dat";

    public ApplianceManager() {
    appliances = new ArrayList<>();
    // try to load the saved list when the app starts
    try {
        loadFromFile();
    } catch (IOException | ClassNotFoundException e) {
        // no saved file yet (first time running), so we start with an empty list
    }
    }

    // ---------- ADD ----------
    public void addAppliance(Appliance a) {
        appliances.add(a);
    }

    // ---------- DELETE ----------
    // deletes the appliance with this name, returns true if it was found and deleted
    public boolean deleteAppliance(String name) {
        for (int i = 0; i < appliances.size(); i++) {
            if (appliances.get(i).getName().equalsIgnoreCase(name)) {
                appliances.remove(i);
                return true;
            }
        }
        return false;   // not found
    }

    // ---------- SEARCH ----------
    // returns the first appliance with this name, or null if not found
    public Appliance searchByName(String name) {
        for (Appliance a : appliances) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    // returns all appliances in this room (can be more than one)
    public ArrayList<Appliance> searchByRoom(String room) {
        ArrayList<Appliance> results = new ArrayList<>();
        for (Appliance a : appliances) {
            if (a.getRoom().equalsIgnoreCase(room)) {
                results.add(a);
            }
        }
        return results;
    }

    // ---------- DISPLAY ----------
    public ArrayList<Appliance> getAllAppliances() {
        return appliances;
    }

    // ---------- REPORT ----------
    // finds the appliance that uses the most kWh per day
    public Appliance getHighestConsumer() {
        if (appliances.isEmpty()) {
            return null;
        }
        Appliance highest = appliances.get(0);
        for (Appliance a : appliances) {
            if (a.getDailyKWh() > highest.getDailyKWh()) {   // polymorphism: each type calculates its own kWh
                highest = a;
            }
        }
        return highest;
    }

    // adds up the monthly cost of all appliances
    public double getTotalMonthlyCost(double rate) {
        double total = 0;
        for (Appliance a : appliances) {
            total = total + a.getMonthlyCost(rate);
        }
        return total;
    }
    // adds up the daily kWh of all appliances (used for the percentages in the report)
    public double getTotalDailyKWh() {
     double total = 0;
     for (Appliance a : appliances) {
        total = total + a.getDailyKWh();
    }
    return total;
    }
    // adds up the monthly CO2 of all appliances
    public double getTotalMonthlyCO2() {
        double total = 0;
    for (Appliance a : appliances) {
        total = total + a.getMonthlyCO2();
    }
    return total;
    }
    
    // total money saved per month if every appliance is used 1 hour less each day
    public double getTotalSavingOneHourLess(double rate) {
    double total = 0;
    for (Appliance a : appliances) {
        total = total + a.getSavingOneHourLess(rate);
    }
    return total;
    }   

    // ---------- FILE ----------
    // saves the whole list to a file
    public void saveToFile() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        out.writeObject(appliances);
        out.close();
    }

    // loads the list back from the file
    public void loadFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
        appliances = (ArrayList<Appliance>) in.readObject();
        in.close();
    }
    
    public void startGUI(){
    
        ApplianceMenuGUI AMGUI = new ApplianceMenuGUI();
        AMGUI.setVisible(true);
    
    }
}