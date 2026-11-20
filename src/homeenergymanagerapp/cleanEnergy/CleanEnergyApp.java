/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homeenergymanagerapp.cleanEnergy;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author Alpsan Karademir
 */
public class CleanEnergyApp {
    
    protected String name, brand, description;
    protected double installCost, outputWatt;
    
    OptionManager optionMan = new OptionManager();

    public CleanEnergyApp(String name, String brand, String description, double installCost, double outputWatt) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.installCost = installCost;
        this.outputWatt = outputWatt;
    }

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
    
    
    
    public void CleanEnergyApp(){
        
        optionMan.addSolarBrands();
        startApp();
        startGUI();
        
    }
    
    
    public void startApp(){
    
        
    
    
    }
    
    public void startGUI(){
    
        CleanEnergyGUI CEGUI = new CleanEnergyGUI();
        CEGUI.setVisible(true);
    
    }
   
    
}
