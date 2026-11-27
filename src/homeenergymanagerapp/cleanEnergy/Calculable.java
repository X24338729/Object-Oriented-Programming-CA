/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package homeenergymanagerapp.cleanEnergy;

/**
 * Interface implemented by SolarPanel, HeatPump and WindTurbine.
 * Each class calculates savings in its own way (polymorphism).
 *
 * @author Alpsan Karademir
 */

public interface Calculable {
    
    // Calculates the results using the product count and yearly energy use in kWh
    void calculateAnnualSavings(int count, double electricityConsumption);
    
    // Returns the calculation results as text
    String printSavings();
    
}
