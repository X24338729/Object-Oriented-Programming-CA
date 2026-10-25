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
    
    ArrayList<SolarPanel> solar = new ArrayList<>();
    
    
    public void CleanEnergyApp(){

        startApp();
        
    }
    
    
    public void startApp(){
    
        int myLoop = 1;
        int myOpe;
        int yearlyKwUsage;
        
        while(myLoop != 0){
            
            myOpe = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter a num"));
            if(myOpe == 0){
            
                myLoop = 0;
            }else{
                        
                if(myOpe == 1){
                
                    System.out.println("you have selected solar panels");
                    addSolarBrands();
                    for (int i = 0; i < solar.size(); i++){
                        
                        solar.get(i).calculateAnnualSavings();
                        System.out.println(solar.get(i).printDetails());
                    
                    }
                
                }else if(myOpe == 2){
                
                    System.out.println("you have selected wind turbines");
                    
                }else if(myOpe == 3){
                
                    System.out.println("you have selected heat pump");
                    
                }
            
            
            
            
            }
            
        }
    
    
    }
    
    
    public void addSolarBrands(){
    
        SolarPanel solarBrand1 = new SolarPanel("Maxeon 6 AC","SunPower","High-efficiency monocrystalline roof panel with an integrated microinverter.",45000,425,8,12);
        solar.add(solarBrand1);
        SolarPanel solarBrand2 = new SolarPanel("NeON R", "LG", "Premium monocrystalline solar panel with excellent performance under high temperatures.", 42000, 400, 8, 15);
        solar.add(solarBrand2);
        SolarPanel solarBrand3 = new SolarPanel("EverVolt HK", "Panasonic", "High-power residential solar panel featuring heterojunction technology.", 38000, 410, 8, 14);
        solar.add(solarBrand3);
        SolarPanel solarBrand4 = new SolarPanel("Eagle Continental", "Jinko Solar", "Cost-effective and reliable half-cell monocrystalline panel.", 28000, 390, 8, 16);
        solar.add(solarBrand4);
        SolarPanel solarBrand5 = new SolarPanel("Q.PEAK DUO BLK", "Qcells", "All-black solar module with zero gap Q.ANTUM DUO Z technology.", 32000, 400, 8, 18);
        solar.add(solarBrand5);
        SolarPanel solarBrand6 = new SolarPanel("Vertex S", "Trina Solar", "Compact design engineered for maximum residential rooftop efficiency.", 35000, 430, 8, 10);
        solar.add(solarBrand6);
        return;
        
    }
    
    
}
