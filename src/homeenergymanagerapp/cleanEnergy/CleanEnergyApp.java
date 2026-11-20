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
    
    protected String name,brand,description;
    protected double installCost,outputWatt,electricityPricePerKw;
    OptionManager optionMan = new OptionManager();
    
    
    
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
