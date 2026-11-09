/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package homeenergymanagerapp;
import homeenergymanagerapp.cleanEnergy.CleanEnergyApp;
import homeenergymanagerapp.cleanEnergy.CleanEnergyGUI;
/**
 *
 * @author 
 */
public class HomeEnergyManagerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CleanEnergyGUI myGUI = new CleanEnergyGUI();
        myGUI.setVisible(true);
        CleanEnergyApp CEA = new CleanEnergyApp();
        CEA.CleanEnergyApp();
        
        
    }
    
}
    