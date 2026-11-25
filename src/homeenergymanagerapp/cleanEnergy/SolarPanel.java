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
    
    private double annualKw, yearlySaving, dailySunTime, paybackYears, totalCost,usedKwh, surplusKwh, savedMoney, exportIncome;
    private int panelCount;
    private final double electricFee = 0.38;
    private final double exportFee = 0.15;

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

    public double getYearlySaving() {
        return yearlySaving;
    }

    public void setYearlySaving(double yearlySaving) {
        this.yearlySaving = yearlySaving;
    }

    public double getDailySunTime() {
        return dailySunTime;
    }

    public void setDailySunTime(double dailySunTime) {
        this.dailySunTime = dailySunTime;
    }

    public double getPaybackYears() {
        return paybackYears;
    }

    public void setPaybackYears(double paybackYears) {
        this.paybackYears = paybackYears;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
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

    public int getPanelCount() {
        return panelCount;
    }

    public void setPanelCount(int panelCount) {
        this.panelCount = panelCount;
    }

    


    @Override
    public void calculateAnnualSavings(int panelCount,double electricityConsumption) { 
        
        annualKw = (outputWatt / 1000.0) * dailySunTime * 365 * panelCount;
        usedKwh = Math.min(annualKw, electricityConsumption);
        surplusKwh = annualKw - usedKwh;

        savedMoney = usedKwh * electricFee;
        exportIncome = surplusKwh * exportFee;
        yearlySaving = savedMoney + exportIncome;

        totalCost = installCost * panelCount;
        paybackYears = yearlySaving > 0 ? totalCost / yearlySaving : 0;

    }
    
    public void saveTXT(String txt){
    
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/homeenergymanagerapp/cleanEnergy/Data/SP_Data.txt", true))) {
                
            bw.write(txt);
            bw.newLine();
                
        }catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Could not save: " + ex.getMessage());
        }
        
    }

    @Override
    public String printSavings(){
    
        return "Brand: " + brand + "\nPanel Count: " + panelCount + "\nProduced: " + String.format("%.0f", annualKw) + " kWh" + "\nSurplus sold: " + String.format("%.0f", surplusKwh) + " kWh" + "\nSaved on bill: " + String.format("%.2f", savedMoney) + " €" + "\nExport income: " + String.format("%.2f", exportIncome) + " €" + "\nTotal install Cost: " + String.format("%.2f", totalCost) + " €" + "\nYearly Benefit: " + String.format("%.2f", yearlySaving) + " €" + "\nPayback Years: " + String.format("%.1f", paybackYears) + " years";    
    }
            
}
