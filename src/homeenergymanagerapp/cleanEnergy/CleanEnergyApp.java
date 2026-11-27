/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homeenergymanagerapp.cleanEnergy;

/**
 * Parent class of SolarPanel, WindTurbine and HeatPump.
 * Stores the data that all three products share and
 * starts the Clean Energy section.
 *
 * @author Alpsan Karademir
 */

public class CleanEnergyApp {
    
    // Shared fields. They are protected so the subclasses can use them directly.
    protected String name, brand, description;
    protected double installCost, outputWatt;
    
    // Used to load the default brands into the ArrayLists
    OptionManager optionMan = new OptionManager();

    // Empty constructor, used by the main menu to open this section
    public CleanEnergyApp() {
    
    
    }
    
    // Sets the shared fields. Subclasses call this with super().
    public CleanEnergyApp(String name, String brand, String description, double installCost, double outputWatt) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.installCost = installCost;
        this.outputWatt = outputWatt;
    }

    // Getters and setters for the shared fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getInstallCost() {
        return installCost;
    }

    public void setInstallCost(double installCost) {
        this.installCost = installCost;
    }

    public double getOutputWatt() {
        return outputWatt;
    }

    public void setOutputWatt(double outputWatt) {
        this.outputWatt = outputWatt;
    }
    
    // Loads the brands for all three products, then opens the GUI
    public void CleanEnergyApp(){
        
        optionMan.addSolarBrands();
        optionMan.addHeatPumpBrands();
        optionMan.addWindTurbineBrands();
        startGUI();
        
    }

    // Creates the Clean Energy window and makes it visible
    public void startGUI(){
    
        CleanEnergyGUI CEGUI = new CleanEnergyGUI();
        CEGUI.setVisible(true);
    
    }
   
    
}
