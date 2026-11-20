/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homeenergymanagerapp.cleanEnergy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author author Alpsan Karademir
 */
public class SolarPanel extends CleanEnergyApp implements Calculable{
    
    private double annualKw, yearlySaving, dailySunTime, paybackYears, totalCost;
    private int panelCount;
    private final double electricFee = 0.38;

    public SolarPanel(String name, String brand, String description,double installCost, double outputWatt, int dailySunTime) {
        
        super(name, brand, description, installCost, outputWatt);
        this.dailySunTime = dailySunTime;
        
    }

    public double getAnnualKw() {
        return annualKw;
    }

    public void setAnnualKw(double annualKw) {
        this.annualKw = annualKw;
    }

    public double getSaving() {
        return yearlySaving;
    }

    public void setSaving(double saving) {
        this.yearlySaving = saving;
    }

    public double getDailySunTime() {
        return dailySunTime;
    }

    public void setDailySunTime(double dailySunTime) {
        this.dailySunTime = dailySunTime;
    }

    public int getPanelCount() {
        return panelCount;
    }

    public void setPanelCount(int panelCount) {
        this.panelCount = panelCount;
    }


    @Override
    public void calculateAnnualSavings(int panelCount,double electricityConsumption) { 
        
        annualKw = (outputWatt / 1000.0) * dailySunTime * 365 * panelCount;
        yearlySaving = annualKw * electricFee;
        totalCost = installCost * panelCount;
        paybackYears = totalCost / yearlySaving;

    }
    
    public void saveTXT(String txt){
    
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("SolarPanelsData.txt", true))) {
                
            bw.write(txt);
            bw.newLine();
                
        }catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Could not save: " + ex.getMessage());
        }
        
    }

    public String printDetails(){
    
        return brand +" " + name +" " + description +" " + installCost +" " + outputWatt +" " + panelCount +" " + annualKw +" " + yearlySaving +" ";
    
    }
    @Override
    public String printSavings(){
    
        return "Brand: " + brand + "\nPanel Count: " + panelCount + "\nTotal install Cost: " + String.format("%.2f", totalCost) + " €\n"+ "Yearly Saving: " + String.format("%.2f", yearlySaving) + " €\n" + "Payback Years: " + String.format("%.1f", paybackYears) + " years";
    
    }
            
}
