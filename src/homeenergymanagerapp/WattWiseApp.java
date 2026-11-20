/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homeenergymanagerapp;

import consumption.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author 
 */
public class WattWiseApp {
    
    
    public void startGUI(){

        SwingUtilities.invokeLater(() -> {
            new ConsumptionTrackerFrame().setVisible(true);
        });

    }
    
}


