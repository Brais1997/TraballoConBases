/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traballobasesjava;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author balva
 */
public class Metodos {
      private static Connection conexion;
    private static final String BD = "TraballoBasesJava";
    private static final String USER = "root";
    private static final String PASSWORD = "<za123s";
    private static final String HOST = "localhost";
    private static final String SERVER = "jdbc:mysql://" + HOST + "/" + BD;

 public static String conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(SERVER, USER, PASSWORD);
            return "Conecta á base de datos " + SERVER + " ... OK";
        } catch (ClassNotFoundException ex) {
            return "Hai un erro no MySQL JDBC";
        } catch (SQLException ex) {
            return "Imposible realizar conexion con " + SERVER + " ... Erro";
        }

    }
 
    public static void insertar(String xogador, String posicion, String numero, String equipo) {
        Statement x = null;
        try {
            x = conexion.createStatement();
          x.executeUpdate("insert into equipo values('"+ xogador + "','" + posicion+ "'," + numero + ",'" + equipo + "');");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                x.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
       public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public static ResultSet consultar() {
        ResultSet rs = null;
        Statement s = null;
        try {
            s = conexion.createStatement();
            rs = s.executeQuery("select * from equipo;");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs;
    }
          public static void eliminar(String primaryKey) throws Exception {
        if(primaryKey.equals("null")){
            throw new Exception("Fila vacía");
        }
        Statement s = null;
        try {
            s = conexion.createStatement();
            s.executeUpdate("delete from equipo where xogador='" + primaryKey + "';");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                s.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
public static void actualizar(String columna, String valor, String primaryKey) throws Exception{
        if(primaryKey.equals("null")){
            throw new Exception("Fila inválida");
        }
        try {
            Statement s = conexion.createStatement();
            s.executeUpdate("update equipo set " + columna + "='" + valor + "' where xogador='" + primaryKey +"';");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}