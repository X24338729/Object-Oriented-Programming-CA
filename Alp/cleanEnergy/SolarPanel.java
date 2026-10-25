/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homeenergymanagerapp.cleanEnergy;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author author Alpsan Karademir
 */
public class SolarPanel extends CleanEnergyApp{
    
    private String name,brand,description;
    private double installCost,outputWatt,annualKw,saving;
    private int dailySunTime,panelCount;

    public SolarPanel(String name, String brand, String description, double installCost, double outputWatt, int dailySunTime, int panelCount) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.installCost = installCost;
        this.outputWatt = outputWatt;
        this.dailySunTime = dailySunTime;
        this.panelCount = panelCount;
    }
    

    //@Override
    public void calculateAnnualSavings() { 
        
        annualKw = (outputWatt / 1000) * panelCount * dailySunTime * 365;
        saving = annualKw * 0.4;

    }
    
    public String printDetails(){
    
        return brand +" " + name +" " + description +" " + installCost +" " + outputWatt +" " + panelCount +" " + annualKw +" " + saving +" ";
    
    }
            
}
