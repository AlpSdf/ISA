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
    public String cuadrante_limpieza(String id){
        String limpiador = null;
        connect();
        try {
            String query = "SELECT * from limpiador where id_empleado_empleado = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                limpiador = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Acceso_Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return limpiador;
    }
    public String datos_de_un_socio(String id){
        String socio = null;
        connect();
        try {
            String query = "SELECT * from socio where numero_socio = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                socio = rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+
                        rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Acceso_Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return socio;
    }
    public void bajaSocio(String id){
        connect();
        try {
            this.borraActividadesSocio(id);
            String query = "delete from socio where numero_socio = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Acceso_Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    public void bajaLimpiador(String id){
        connect();
        try {
            String query = "delete from limpiador where id_empleado_empleado = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Acceso_Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    public void bajaMonitor(String id){
        connect();
        try {
            String query = "delete from monitor where id_empleado_empleado = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Acceso_Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    public void borraActividadesSocio(String id){
        try {
            String query = "delete from realizan where numero_socio_socio = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Acceso_Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList obtener_actividades_socio(){
        ArrayList<Actividad> actividades = new ArrayList<>();
        Actividad actividad;
        connect();
        try {
            String query = "select * from actividad";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                actividad = new Actividad(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                actividades.add(actividad);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return actividades;
        
    }
    public int comprobar_aforo_actividad(String id){
        connect();
        int total = 0;
        try {
            String query = "SELECT count(*) FROM realizan where id_actividad_actividad = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                total = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return total;
    }
    
    public void socio_reserva_actividad(String id_actividad, String id_socio){
        connect();
        try {
            String query = "insert into realizan values(" + id_actividad + "," + id_socio +")";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        
    }
    public String socio_reserva_entrenador(String id_socio){
        connect();
        String id_entrenador=null;
        int horas_reservadas = 0;
        int horas_libres = 0;
        try {
            String query = "SELECT * FROM entrenador\n" +
                            "WHERE (horas_libres = (SELECT MAX(horas_libres) FROM entrenador)) AND (horas_libres >= 1) ";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()){
                horas_reservadas = Integer.parseInt(rs.getString(1));
                horas_libres = Integer.parseInt(rs.getString(2));
                id_entrenador = rs.getString(3);
            }
            this.actualizar(id_socio,id_entrenador,horas_reservadas,horas_libres);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return id_entrenador;
    }
    public void actualizar(String id_socio,String id_entrenador, int horas_r,int horas_l){
        try {
            String query = "UPDATE socio SET id_empleado_empleado_entrenador = " + id_entrenador + " WHERE numero_socio = " + id_socio;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            String query1 = "UPDATE entrenador SET horas_libres = " + (horas_l-1) + " , horas_reservadas = " + (horas_r + 1) + " WHERE id_empleado_empleado = " + id_entrenador;
            Statement stmnt1 = conn.createStatement();
            ResultSet rs1 = stmnt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        
    }
    
    public String nombre_entrenador(String id){
        connect();
        String nombre = null;
        try {
            String query = "select nombre from empleado where id_empleado = " + id;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            nombre = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
        disconnect();
        return nombre;
    }
}
