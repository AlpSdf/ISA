/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class Director {

    private ArrayList<String> FechasBajaEntrenadores = new ArrayList<String>();
    private ArrayList<String> Actividades = new ArrayList<String>();
    
    public Director() {
    }
    
    public void recibirDatosBajasEntrenadores(String fecha, String motivo) {
        String txt = "-" + fecha + ":" + motivo;
        FechasBajaEntrenadores.add(txt);
    }
    
    public void recibirActividades(ArrayList<String> list) {
        Actividades = list;
    }
    
    
    
    
}
