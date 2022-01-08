/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class Acceso_Base_Datos {
    private final String url = "jdbc:postgresql://127.0.0.1:5432/ISA_iteracion3";
    private final String user = "postgres";
    private final String password = "dpc292001";
    private final Logger logger = Logger.getLogger(Acceso_Base_Datos.class.getName());
    private Connection conn = null;

    public Acceso_Base_Datos() {
    }
    
    
    public Connection connect() { 
        try {
            Class.forName("org.postgresql.Driver").newInstance(); 
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Driver no detectado");
        } 
        return conn;
    }
    
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido cerrar la conexión",ex);
        } catch (Exception ex){
            logger.log(Level.WARNING, "Excepción capturada",ex);
        }
    }
    
    
    public boolean consultar_empleado(String usuario,String contraseña){ //consultamos si el usuario y contraseña son correctos
        boolean correcto=false;
        String password = "'"+contraseña+"'";
        try {
            String query = "select id_empleado from empleado where contraseña="+password+";";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            String id="";
            while(rs.next()){
                id = rs.getString(1);
            }
            if (id.equals(usuario) && !id.equals("")){
                correcto=true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        return correcto;
    }
    
    public boolean consultar_socio(String usuario,String contraseña){ //consultamos si el usuario y contraseña son correctos
        boolean correcto=false;
        String password = "'"+contraseña+"'";
        try {
            String query = "select numero_socio from socio where contraseña="+password+";";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            String id="";
            while(rs.next()){
                id = rs.getString(1);
            }
            if (id.equals(usuario) && !id.equals("")){
                correcto=true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        return correcto;
    }
    
    public ArrayList obtener_valoraciones(){
        ArrayList<String> valoraciones = new ArrayList<>();
        String valoracion;
        connect();
        try {
            String query = "select * from evalua;";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                valoracion = "Valoracion: "+rs.getString(1)+ " - Número Socio: "+rs.getString(2)+ " - Número monitor: "+rs.getString(3);
                valoraciones.add(valoracion);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return valoraciones;
    }
    
    public ArrayList obtener_actividades(){
        ArrayList<String> actividades = new ArrayList<>();
        String actividad;
        connect();
        try {
            String query = "select * from actividad;";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                actividad = rs.getString(2)+";"+rs.getString(3)+";"+rs.getString(4)+";"+rs.getString(5);
                actividades.add(actividad);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return actividades;
    }
    
    public void añadir_actividad(int id, String tipo, String horario,int aforo, int sala){
        connect();
        try {
            String query = "insert into actividad values("+id+","+tipo+","+horario+","+aforo+","+sala+")";
            Statement stmnt = conn.createStatement();
            stmnt.executeQuery(query);
        } catch (SQLException ex) {}
        disconnect();
    }
    
    public ArrayList obtener_plantilla(){
        ArrayList<String> plantilla = new ArrayList<>();
        String empleado;
        connect();
        try {
            String query = "select nombre,id_empleado from empleado;";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                empleado = rs.getString(1)+";"+rs.getString(2);
                plantilla.add(empleado);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return plantilla;
    }
    
    public ArrayList obtener_datos_Socio(){
        ArrayList<String> datos = new ArrayList<>();
        String socio;
        connect();
        try {
            String query = "SELECT * from socio";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            
            System.out.println("Listado de socios");
            System.out.println("============================");
            while(rs.next()){
               System.out.println(rs.getString("numero_socio").trim()+" "+rs.getString("nombre").trim()+" "+rs.getString("telefono").trim()+" "+rs.getString("email").trim()
               +" "+rs.getString("tipo_cuota").trim()+" "+rs.getString("numero_cuenta_bancaria").trim()+" "+rs.getString("id_empleado_empleado_entrenador").trim());
            }
            System.out.println("============================");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return datos;
    }
}
