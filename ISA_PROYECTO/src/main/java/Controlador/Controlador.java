/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Logica.*;
import Vistas.*;
/**
 *
 * @author jorge
 */
public class Controlador {

    public Controlador() {
    }
    
    private Acceso_Base_Datos acceso_base = new Acceso_Base_Datos();
    private static Inicio inicio = new Inicio();
    private static Vista_empleado_inicio_sesion vista_empleado_inicio_sesion = new Vista_empleado_inicio_sesion();
    private static Vista_socio_inicio_sesion vista_socio_inicio_sesion = new Vista_socio_inicio_sesion();
    private static Vista_Director vista_Director = new Vista_Director();
    private static Vista_Entrenador vista_Entrenador = new Vista_Entrenador();
    private static Vista_Limpiador vista_Limpiador= new Vista_Limpiador();
    private static Vista_Monitor vista_Monitor = new Vista_Monitor();
    private static Vista_Recepcionista vista_Recepcionista = new Vista_Recepcionista();
    private static Vista_Socio vista_Socio = new Vista_Socio();
    
    private String id_actual = "";
    

    public String getId_actual() {
        return id_actual;
    }

    public void setId_actual(String id_actual) {
        this.id_actual = id_actual;
    }
    
    

    public static void main(String[] args) {
        inicio.setVisible(true);
        vista_empleado_inicio_sesion.setVisible(false);
        vista_socio_inicio_sesion.setVisible(false);
        vista_Director.setVisible(false);
        vista_Entrenador.setVisible(false);
        vista_Limpiador.setVisible(false);
        vista_Monitor.setVisible(false);
        vista_Recepcionista.setVisible(false);
        vista_Socio.setVisible(false);
        
        
        inicio.setLocationRelativeTo(null);
        vista_empleado_inicio_sesion.setLocationRelativeTo(null);
        vista_socio_inicio_sesion.setLocationRelativeTo(null);
        vista_Director.setLocationRelativeTo(null);
        vista_Entrenador.setLocationRelativeTo(null);
        vista_Limpiador.setLocationRelativeTo(null);
        vista_Monitor.setLocationRelativeTo(null);
        vista_Recepcionista.setLocationRelativeTo(null);
        vista_Socio.setLocationRelativeTo(null);
    }
    
    
    public void mostrar_empleado_inicio_sesion(){
        inicio.setVisible(false);
        vista_empleado_inicio_sesion.setVisible(true);
    }
    
    public void mostrar_socio_inicio_sesion(){
        inicio.setVisible(false);
        vista_socio_inicio_sesion.setVisible(true);
    }
    
    public void mostrar_pantalla_inicio(){
        inicio.setVisible(true);
        vista_empleado_inicio_sesion.setVisible(false);
        vista_socio_inicio_sesion.setVisible(false);
    }
    
    public boolean empleado_correcto(String usuario, String contraseña){
        boolean correcto=false;
        acceso_base.connect();
        if (acceso_base.consultar_empleado(usuario, contraseña)){
            correcto=true;
        }
        acceso_base.disconnect();
        return correcto;
    }
    
    public boolean socio_correcto(String usuario, String contraseña){
        boolean correcto=false;
        acceso_base.connect();
        if (acceso_base.consultar_socio(usuario, contraseña)){
            correcto=true;
        }
        acceso_base.disconnect();
        return correcto;
    }
    
    public void mostrar_pantalla_empleado(){
        if (id_actual.equals("1") || id_actual.equals("2") || id_actual.equals("3")) mostrar_pantalla_entrenador();
        else if (id_actual.equals("4") || id_actual.equals("5"))  mostrar_pantalla_recepcionista();
        else if (id_actual.equals("6") || id_actual.equals("7")) mostrar_pantalla_limpiador();
        else if (id_actual.equals("8") || id_actual.equals("10") || id_actual.equals("11") || id_actual.equals("12")) mostrar_pantalla_monitor();
        else mostrar_pantalla_director();
    }
    
    public void mostrar_pantalla_entrenador(){
        vista_empleado_inicio_sesion.setVisible(false);
        vista_Entrenador.setVisible(true);
    }
    
    public void mostrar_pantalla_recepcionista(){
        vista_empleado_inicio_sesion.setVisible(false);
        vista_Recepcionista.setVisible(true);
    }
    
    public void mostrar_pantalla_limpiador(){
        vista_empleado_inicio_sesion.setVisible(false);
        vista_Limpiador.setVisible(true);
    }
    
    public void mostrar_pantalla_monitor(){
        vista_empleado_inicio_sesion.setVisible(false);
        vista_Monitor.setVisible(true);
    }
    
    public void mostrar_pantalla_director(){
        vista_empleado_inicio_sesion.setVisible(false);
        vista_Director.setVisible(true);
    }
    
    public void mostrar_pantalla_socio(){
        vista_socio_inicio_sesion.setVisible(false);
        vista_Socio.setVisible(true);
    }
}