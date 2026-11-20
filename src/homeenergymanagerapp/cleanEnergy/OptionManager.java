/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homeenergymanagerapp.cleanEnergy;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author Alpsan Karademir
 */
public class OptionManager {
    
    public static ArrayList<SolarPanel> solar = new ArrayList<>(); 
    
    public void addSolarBrands(){
      
        File outfile;
        FileWriter fw;
        BufferedWriter bw;
        
        SolarPanel solarBrand = new SolarPanel("","","",0,0,0);
        solar.add(solarBrand);
        SolarPanel solarBrand1 = new SolarPanel("Maxeon 6 AC","SunPower","High-efficiency monocrystalline roof panel with an integrated microinverter.",550,425,3);
        solar.add(solarBrand1);
        SolarPanel solarBrand2 = new SolarPanel("NeON R", "LG", "Premium monocrystalline solar panel with excellent performance under high temperatures.", 500, 400, 3);
        solar.add(solarBrand2);
        SolarPanel solarBrand3 = new SolarPanel("EverVolt HK", "Panasonic", "High-power residential solar panel featuring heterojunction technology.", 480, 410, 3);
        solar.add(solarBrand3);
        SolarPanel solarBrand4 = new SolarPanel("Eagle Continental", "Jinko Solar", "Cost-effective and reliable half-cell monocrystalline panel.", 320, 390, 3);
        solar.add(solarBrand4);
        SolarPanel solarBrand5 = new SolarPanel("Q.PEAK DUO BLK", "Qcells", "All-black solar module with zero gap Q.ANTUM DUO Z technology.", 380, 400, 3);
        solar.add(solarBrand5);
        SolarPanel solarBrand6 = new SolarPanel("Vertex S", "Trina Solar", "Compact design engineered for maximum residential rooftop efficiency.", 400, 430, 3);
        solar.add(solarBrand6);
        
        try{
            
            outfile = new File("solar_Brands.txt");
            fw = new FileWriter(outfile);
            bw = new BufferedWriter(fw);
            
            for(SolarPanel p : solar){
                
                bw.write(p.getBrand() + "," + p.getInstallCost() + "," + p.getOutputWatt() + "," + p.getDailySunTime());
                bw.newLine();
            
            }
        
        }catch (IOException e) {
        
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            
        }
        
        
    }
}
