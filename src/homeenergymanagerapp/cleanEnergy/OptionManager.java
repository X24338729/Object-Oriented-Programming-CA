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
    public static ArrayList<HeatPump> heatPump = new ArrayList<>();
    public static ArrayList<WindTurbine> windturbine = new ArrayList<>();
    
    public void addSolarBrands(){
        if (!solar.isEmpty()){ 
            return;
        }
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
            
            outfile = new File("src/homeenergymanagerapp/cleanEnergy/Data/SP_Brands.txt");
            fw = new FileWriter(outfile);
            bw = new BufferedWriter(fw);
            
            for(SolarPanel p : solar){
                
                bw.write(p.getBrand() + "," + p.getInstallCost() + "," + p.getOutputWatt() + "," + p.getDailySunTime());
                bw.newLine();
            
            }
            
            bw.close();
                    
        }catch (IOException e) {
        
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            
        }
        
        
    }
   
    public void addHeatPumpBrands() {
        if (!heatPump.isEmpty()) {
            return;
        }
        
        File outfile;
        FileWriter fw;
        BufferedWriter bw;

        HeatPump heatBrand0 = new HeatPump("", "", "", 0, 0, 1);
        heatPump.add(heatBrand0);
        HeatPump heatBrand1 = new HeatPump("Altherma 3 R", "Daikin", "Reliable air-to-water heat pump with high efficiency in cold climates.", 10500, 8000, 4.0);
        heatPump.add(heatBrand1);
        HeatPump heatBrand2 = new HeatPump("Ecodan PUZ", "Mitsubishi Electric", "Quiet inverter-driven air-to-water heat pump for homes.", 9800, 8500, 3.8);
        heatPump.add(heatBrand2);
        HeatPump heatBrand3 = new HeatPump("aroTHERM plus", "Vaillant", "R290 natural refrigerant heat pump with high flow temperature.", 11500, 7500, 4.2);
        heatPump.add(heatBrand3);
        HeatPump heatBrand4 = new HeatPump("EHS Mono", "Samsung", "Compact monobloc air-to-water heat pump for smaller homes.", 8200, 6000, 3.6);
        heatPump.add(heatBrand4);
        HeatPump heatBrand5 = new HeatPump("Logatherm WLW", "Bosch", "Efficient split heat pump with smart control and easy installation.", 9200, 7000, 3.7);
        heatPump.add(heatBrand5);
        HeatPump heatBrand6 = new HeatPump("Arotherm Split", "Viessmann", "Premium heat pump system with excellent performance and low noise.", 12500, 9000, 4.1);
        heatPump.add(heatBrand6);

        try{
            
            outfile = new File("src/homeenergymanagerapp/cleanEnergy/Data/HP_Brands.txt");
            fw = new FileWriter(outfile);
            bw = new BufferedWriter(fw);
            
            for(HeatPump p : heatPump){
                
                bw.write(p.getBrand() + "," + p.getInstallCost() + "," + p.getOutputWatt() + "," + p.getCop());
                bw.newLine();
            
            }
            
            bw.close();
                    
        }catch (IOException e) {
        
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            
        }
    }
    
    public void addWindTurbineBrands() {
        if (!windturbine.isEmpty()) {
            return;
        }
        
        File outfile;
        FileWriter fw;
        BufferedWriter bw;

        WindTurbine windBrand0 = new WindTurbine("", "", "", 0, 0, 0);
        windturbine.add(windBrand0);
        WindTurbine windBrand1 = new WindTurbine("Aeolos-H 1kW", "Aeolos", "Horizontal-axis small wind turbine for residential use.", 3200, 1000, 4);
        windturbine.add(windBrand1);
        WindTurbine windBrand2 = new WindTurbine("Excel 1", "Bergey", "Durable and reliable residential wind turbine.", 4500, 1000, 5);
        windturbine.add(windBrand2);
        WindTurbine windBrand3 = new WindTurbine("Ampair 600", "Ampair", "Compact turbine designed for gardens and small homes.", 2200, 600, 4);
        windturbine.add(windBrand3);
        WindTurbine windBrand4 = new WindTurbine("QR6", "Quiet Revolution", "Vertical-axis turbine with quiet operation.", 9000, 6000, 3);
        windturbine.add(windBrand4);
        WindTurbine windBrand5 = new WindTurbine("Scirocco E5", "Eoltec", "Efficient small turbine for moderate wind sites.", 12000, 5000, 4);
        windturbine.add(windBrand5);
        WindTurbine windBrand6 = new WindTurbine("Skystream", "Southwest Windpower", "Compact residential turbine with built-in inverter.", 7500, 2400, 4);
        windturbine.add(windBrand6);

        try{
            
            outfile = new File("src/homeenergymanagerapp/cleanEnergy/Data/WT_Brands.txt");
            fw = new FileWriter(outfile);
            bw = new BufferedWriter(fw);
            
            for(WindTurbine w : windturbine){
                
                bw.write(w.getBrand() + "," + w.getInstallCost() + "," + w.getOutputWatt() + "," + w.getDailyWindTime());
                bw.newLine();
            
            }
            
            bw.close();
                    
        }catch (IOException e) {
        
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            
        }
    }
    
}
