package com.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection conection;
    private final String URL = "jdbc:postgresql://localhost/gary94746?currentSchema=p_refaccionaria";
    private final String USER = "gary94746";
    private final String PASSWORD = "16559536";

    public void establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException ignored){}
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            conection.close();
        }catch (Exception ignored){}
    }


    public Connection getConection() {
        return conection;
    }

    public void setConection(Connection conection) {
        this.conection = conection;
    }

}
