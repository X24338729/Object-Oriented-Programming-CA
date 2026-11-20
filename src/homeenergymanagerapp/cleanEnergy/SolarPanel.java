/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homeenergymanagerapp.cleanEnergy;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author author Alpsan Karademir
 */
public class SolarPanel extends CleanEnergyApp{
    
    private String name,brand,description;
    private double installCost,outputWatt,annualKw,yearlySaving,dailySunTime,paybackYears,totalCost;
    private int panelCount;
    private double electricFee = 0.38;
            
    public SolarPanel(String name, String brand, String description, double installCost, double outputWatt, int dailySunTime) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.installCost = installCost;
        this.outputWatt = outputWatt;
        this.dailySunTime = dailySunTime;
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


    //@Override
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
    
    public String printSavings(){
    
        return "Total install Cost: " + String.format("%.2f", totalCost) + " €\n"+ "Yearly Saving: " + String.format("%.2f", yearlySaving) + " €\n" + "Payback Years: " + String.format("%.1f", paybackYears) + " years";
    
    }
            
}
