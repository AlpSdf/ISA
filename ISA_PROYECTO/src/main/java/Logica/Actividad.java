/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author jorge
 */
public class Actividad {

    private String id,tipo,horario,aforo,sala;
    
    public Actividad(String id,String t,String horario,String a,String s) {
        this.id = id ;
        this.tipo = t;
        this.horario = horario;
        this.aforo = a;
        this.sala = s;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getHorario() {
        return horario;
    }

    public String getAforo() {
        return aforo;
    }

    public String getSala() {
        return sala;
    }
    
    
    
}
