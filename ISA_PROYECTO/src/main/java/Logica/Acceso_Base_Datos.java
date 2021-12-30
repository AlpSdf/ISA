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
    private final String url = "jdbc:postgresql://127.0.0.1:5432/ISA";
    private final String user = "postgres";
    private final String password = "password";
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
    
    
}
