/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package homeenergymanagerapp;
//Clean Energy package
import consumption.ConsumptionTrackerFrame;
import homeenergymanagerapp.cleanEnergy.*;

//Saving App package


//imports
import homeenergymanagerapp.appliances.*;
import javax.swing.SwingUtilities;



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
        startGUI();
 
    }
    
    public static void startGUI(){
    
        HomeEnergyManagerAppGUI mainGUI = new HomeEnergyManagerAppGUI();
        mainGUI.setVisible(true);
    
    }
    
}
    