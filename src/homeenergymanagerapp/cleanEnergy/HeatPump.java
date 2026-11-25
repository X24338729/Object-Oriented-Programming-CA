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
 * @author Alpsan Karademir
 */
public class HeatPump extends CleanEnergyApp implements Calculable{
    
    private double annualKw, yearlySaving, annualHeatingKwh, paybackYears, totalCost,cop,oldHeatingCost, newHeatingCost,producedHeat;
    private int unitCount;
    private final double fullLoadHours = 1500;
    private final double electricFee = 0.38;
    private final double gasFee = 0.13; 

    public HeatPump(String name, String brand, String description,double installCost, double outputWatt, double cop) {
        
        super(name, brand, description, installCost, outputWatt);
        this.cop = cop;
        
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

    public double getAnnualHeatingKwh() {
        return annualHeatingKwh;
    }

    public void setAnnualHeatingKwh(double annualHeatingKwh) {
        this.annualHeatingKwh = annualHeatingKwh;
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

    public double getCop() {
        return cop;
    }

    public void setCop(double cop) {
        this.cop = cop;
    }

    public double getOldHeatingCost() {
        return oldHeatingCost;
    }

    public void setOldHeatingCost(double oldHeatingCost) {
        this.oldHeatingCost = oldHeatingCost;
    }

    public double getNewHeatingCost() {
        return newHeatingCost;
    }

    public void setNewHeatingCost(double newHeatingCost) {
        this.newHeatingCost = newHeatingCost;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    public double getProducedHeat() {
        return producedHeat;
    }

    public void setProducedHeat(double producedHeat) {
        this.producedHeat = producedHeat;
    }


    @Override
    public void calculateAnnualSavings(int count, double heatingKwh) { 
        
        this.unitCount = count;
        this.annualHeatingKwh = heatingKwh;
        
        producedHeat = (outputWatt / 1000.0) * fullLoadHours * count;
        oldHeatingCost = heatingKwh * gasFee;
        newHeatingCost = (heatingKwh / cop) * electricFee;
        yearlySaving = oldHeatingCost - newHeatingCost;
        totalCost = installCost * count;
        paybackYears = yearlySaving > 0 ? totalCost / yearlySaving : 0;

    }
    
    public void saveTXT(String txt){
    
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/homeenergymanagerapp/cleanEnergy/Data/HP_Data.txt", true))) {
                
            bw.write(txt);
            bw.newLine();
                
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not save: " + ex.getMessage());
        }
        
    }

    @Override
    public String printSavings(){
    
        return "Brand: " + brand + "\nUnits: " + unitCount + "\nProduced heat: " + String.format("%.0f", producedHeat) + " kWh" + "\nOld heating cost: " + String.format("%.2f", oldHeatingCost) + " €" + "\nNew heating cost: " + String.format("%.2f", newHeatingCost) + " €" + "\nYearly Saving: " + String.format("%.2f", yearlySaving) + " €" + "\nPayback Years: " + String.format("%.1f", paybackYears) + " years";
    }
}
