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
 * Wind turbine product. Extends CleanEnergyApp and implements
 * Calculable to work out yearly savings and payback time.
 *
 * @author Alpsan Karademir
 */

public class WindTurbine extends CleanEnergyApp implements Calculable {
    
    // Fields used for the turbine data and calculation results
    private double dailyWindTime, annualKw, usedKwh, surplusKwh,savedMoney, exportIncome, yearlySaving, totalCost, paybackYears;
    private int turbineCount;
    
    // Fixed prices per kWh: buying electricity and selling surplus to the grid
    private final double electricFee = 0.38;
    private final double exportFee = 0.15;
    
    // super() sets the shared fields, daily wind time belongs to this class
     public WindTurbine(String name, String brand, String description, double installCost, double outputWatt, int dailyWindTime) {
         
        super(name, brand, description, installCost, outputWatt);
        this.dailyWindTime = dailyWindTime;
    }

    // Getters and setters
    public double getDailyWindTime() {
        return dailyWindTime;
    }

    public void setDailyWindTime(double dailyWindTime) {
        this.dailyWindTime = dailyWindTime;
    }

    public double getAnnualKw() {
        return annualKw;
    }

    public void setAnnualKw(double annualKw) {
        this.annualKw = annualKw;
    }

    public double getUsedKwh() {
        return usedKwh;
    }

    public void setUsedKwh(double usedKwh) {
        this.usedKwh = usedKwh;
    }

    public double getSurplusKwh() {
        return surplusKwh;
    }

    public void setSurplusKwh(double surplusKwh) {
        this.surplusKwh = surplusKwh;
    }

    public double getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(double savedMoney) {
        this.savedMoney = savedMoney;
    }

    public double getExportIncome() {
        return exportIncome;
    }

    public void setExportIncome(double exportIncome) {
        this.exportIncome = exportIncome;
    }

    public double getYearlySaving() {
        return yearlySaving;
    }

    public void setYearlySaving(double yearlySaving) {
        this.yearlySaving = yearlySaving;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getPaybackYears() {
        return paybackYears;
    }

    public void setPaybackYears(double paybackYears) {
        this.paybackYears = paybackYears;
    }

    public int getTurbineCount() {
        return turbineCount;
    }

    public void setTurbineCount(int turbineCount) {
        this.turbineCount = turbineCount;
    }
    
    // From the Calculable interface.
    // Yearly production = kW x daily wind hours x 365 x turbine count.
    // Used energy saves money on the bill and the surplus is sold.
    // Payback = total install cost / yearly benefit.
    @Override
    public void calculateAnnualSavings(int count, double electricityConsumption) {
        this.turbineCount = count;

        annualKw = (outputWatt / 1000.0) * dailyWindTime * 365 * count;
        usedKwh = Math.min(annualKw, electricityConsumption);
        surplusKwh = annualKw - usedKwh;

        savedMoney = usedKwh * electricFee;
        exportIncome = surplusKwh * exportFee;
        yearlySaving = savedMoney + exportIncome;

        totalCost = installCost * count;
        paybackYears = yearlySaving > 0 ? totalCost / yearlySaving : 0;
    }
    
    // File writing: adds the result to the history file without deleting old ones
    public void saveTXT(String txt){
    
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/homeenergymanagerapp/cleanEnergy/Data/WT_Data.txt", true))) {
                
            bw.write(txt);
            bw.newLine();
                
        }catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Could not save: " + ex.getMessage());
        }
        
    }
    
    // From the Calculable interface. Returns the results as text for the GUI and file.
    @Override
    public String printSavings() {
        
        return "Brand: " + brand + "\nTurbines: " + turbineCount + "\nProduced: " + String.format("%.0f", annualKw) + " kWh" + "\nSurplus sold: " + String.format("%.0f", surplusKwh) + " kWh" + "\nSaved on bill: " + String.format("%.2f", savedMoney) + " €" + "\nExport income: " + String.format("%.2f", exportIncome) + " €" + "\nTotal Cost: " + String.format("%.2f", totalCost) + " €" + "\nYearly Benefit: " + String.format("%.2f", yearlySaving) + " €" + "\nPayback Years: " + String.format("%.1f", paybackYears) + " years";
   
    }
}
